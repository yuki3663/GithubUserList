package com.tom.cathaybkgituser.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.cathaybkgituser.network.UserDetailData
import com.tom.cathaybkgituser.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_user_detail.*


class UserDetailFragment : Fragment() {

    private var fragmentView: View? = null
    private var userDetailData: UserDetailData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_user_detail, container, false)
        return fragmentView
    }

    fun updateFragment(userDetailData: UserDetailData)
    {
        val cancelButton: ImageButton = fragmentView?.findViewById(R.id.cancelButton) ?: return
        if(!cancelButton.hasOnClickListeners())
        {
            cancelButton.setOnClickListener{
                fragmentView?.isVisible = false
            }
        }
        val detailName: TextView= fragmentView?.findViewById(R.id.detailName) ?: return
        detailName.text= userDetailData.name
        val avatarView: CircleImageView= fragmentView?.findViewById(R.id.avatar_image_view) ?: return
        DownloadImageFromInternet(avatarView).execute(userDetailData.avatarUrl)
        val detailBio: TextView= fragmentView?.findViewById(R.id.detailBio) ?: return
        detailBio.text= userDetailData.bio
        val accountName: TextView= fragmentView?.findViewById(R.id.accountName) ?: return
        accountName.text= userDetailData.login
        val detailStaff: TextView= fragmentView?.findViewById(R.id.detailStaff) ?: return
        detailStaff.isVisible = userDetailData.siteAdmin == "true"
        val locationText: TextView= fragmentView?.findViewById(R.id.locationText) ?: return
        locationText.text= userDetailData.location
        val blogText: TextView= fragmentView?.findViewById(R.id.blogText) ?: return
        blogText.text= userDetailData.blog
        fragmentView?.isVisible = true
    }
    companion object {
        val instance: UserDetailFragment by lazy{
            UserDetailFragment()
        }
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