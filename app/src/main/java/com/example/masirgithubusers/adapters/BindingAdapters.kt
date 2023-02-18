package com.example.masirgithubusers.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.masirgithubusers.R
import com.example.masirgithubusers.model.GitUser
import com.example.masirgithubusers.viewmodels.GitApiStatus

// showing the received image given image view
@BindingAdapter("imgUrl")
fun bindImage(imageView: ImageView, url: String?){
    url?.let {
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        imageView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

// showing the list in MainScreen recycler view
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<GitUser>?) {
    val adapter = recyclerView.adapter as MainScreenAdapter
    adapter.submitList(data)
}

// getting ready the MainScreen for showing list
@BindingAdapter("gitApiStatus")
fun bindStatus(view: View, status: GitApiStatus?){
    view.let {
        when (view){
            is ImageView -> readyImage(view, status)
            is TextView -> readyTextView(view, status)
            is RecyclerView -> readyRecyclerView(view, status)
        }
    }
}

fun readyRecyclerView(view: RecyclerView, status: GitApiStatus?) {
    when (status){
        GitApiStatus.DONE ->
            view.visibility = View.VISIBLE
        else ->
            view.visibility = View.GONE
    }
}

fun readyTextView(view: TextView, status: GitApiStatus?) {
    when (status){
        GitApiStatus.ERROR ->
            view.visibility = View.VISIBLE
        else ->
            view.visibility = View.GONE
    }
}

fun readyImage(view: ImageView, status: GitApiStatus?) {
    when (status){
        GitApiStatus.LOADING -> {
            view.visibility = View.VISIBLE
            view.setImageResource(R.drawable.loading_animation)
        }
        GitApiStatus.ERROR -> {
            view.visibility = View.VISIBLE
            view.setImageResource(R.drawable.ic_baseline_app_blocking_24)
        }
        else -> {
            view.visibility = View.GONE
        }
    }
}
