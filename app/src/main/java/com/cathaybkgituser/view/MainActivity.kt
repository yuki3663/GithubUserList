package com.tom.cathaybkgituser.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cathaybkgituser.network.GitHubUsersApi
import com.cathaybkgituser.network.NetworkManager
import com.cathaybkgituser.network.UserDetailData
import com.cathaybkgituser.network.UserInfoData
import com.tom.cathaybkgituser.R
import com.tom.cathaybkgituser.contract.ContractInterface
import com.tom.cathaybkgituser.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_row.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), ContractInterface.View {

    private var presenter: MainActivityPresenter? = null
    private lateinit var detailFragment: UserDetailFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        detailFragment = UserDetailFragment.instance
        supportFragmentManager.beginTransaction().run{
            add(R.id.container, detailFragment)
            commit()
        }
        presenter = MainActivityPresenter(this)
    }

    override fun initView() {
        /*counterTextView.text = com.tom.presenter?.getCounter()
        clickButton.setOnClickListener { com.tom.presenter?.incrementValue() }*/
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        val apiService = NetworkManager.provideRetrofit(NetworkManager.provideOkHttpClient())
            .create(GitHubUsersApi::class.java)

        apiService.getAllUsers(0).enqueue(object : Callback<List<UserInfoData>> {
            override fun onResponse(
                call: Call<List<UserInfoData>>,
                response: Response<List<UserInfoData>>
            ) {
                Log.d("MainActivity", "response: ${response.body().toString()}")
                response.body()?.let { updateViewData(it) }
            }

            override fun onFailure(call: Call<List<UserInfoData>>, t: Throwable) {
                Log.d("MainActivity", "error: ${t.message}")
            }
        })

    }

    fun updateViewData(dataList: List<UserInfoData>)
    {
        val adapter = object : RecyclerView.Adapter<UserViewHolder>(){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder
            {
                val view = layoutInflater.inflate(R.layout.user_row, parent, false)
                return  UserViewHolder(view)
            }

            override fun getItemCount(): Int
            {
                return dataList.size
            }

            override fun onBindViewHolder(holder: UserViewHolder, position: Int)
            {
                holder.name.text = dataList.get(position).login
                DownloadImageFromInternet(holder.avatarUrl).execute(dataList.get(position).avatarUrl)
                holder.staff.isVisible = dataList.get(position).siteAdmin == "true"
                holder.itemView.setOnClickListener {
                    val apiService = NetworkManager.provideRetrofit(NetworkManager.provideOkHttpClient())
                        .create(GitHubUsersApi::class.java)

                    apiService.getUser(dataList.get(position).login).enqueue(object :
                        Callback<UserDetailData> {
                        override fun onResponse(
                            call: Call<UserDetailData>,
                            response: Response<UserDetailData>
                        ) {
                            Log.d("MainActivity", "response: ${response.body().toString()}")
                            response.body()?.let { detailFragment.updateFragment(it) }
                        }

                        override fun onFailure(call: Call<UserDetailData>, t: Throwable) {
                            Log.d("MainActivity", "error: ${t.message}")
                        }
                    })
                }
            }
        }
        recycler.adapter = adapter
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val name = view.name
        val avatarUrl = view.user_head
        val staff = view.staff
    }
    private inner class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {

        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageURL = urls[0]
            var image: Bitmap? = null
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
            }
            catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }
}
