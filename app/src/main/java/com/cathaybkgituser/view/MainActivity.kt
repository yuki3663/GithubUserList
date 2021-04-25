package com.tom.cathaybkgituser.view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tom.cathaybkgituser.R
import com.tom.cathaybkgituser.contract.ContractInterface
import com.tom.cathaybkgituser.model.UserData
import com.tom.cathaybkgituser.presenter.MainActivityPresenter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_row.view.*

class MainActivity : AppCompatActivity(), ContractInterface.View {

    private var presenter: MainActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter(this)
    }

    override fun initView() {
        /*counterTextView.text = com.tom.presenter?.getCounter()
        clickButton.setOnClickListener { com.tom.presenter?.incrementValue() }*/
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        val users = listOf<UserData>(
                UserData("A","A","A","A","A","A","A"),
                UserData("A","A","A","A","A","A","A"),
                UserData("A","A","A","A","A","A","A")
        )
        val adapter = object : RecyclerView.Adapter<UserViewHolder>(){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder
            {
                val view = layoutInflater.inflate(R.layout.user_row, parent, false)
                return  UserViewHolder(view)
            }

            override fun getItemCount(): Int
            {
                return users.size
            }

            override fun onBindViewHolder(holder: UserViewHolder, position: Int)
            {
                holder.name.setText(users.get(position).name)
            }
        }
        recycler.adapter = adapter
    }

    override fun updateViewData() {
        /*counterTextView.text = com.tom.presenter?.getCounter()*/
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val name = view.name
    }
}
