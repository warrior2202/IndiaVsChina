package com.example.kotlintest1.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlintest1.Activity.MainActivity
import com.example.kotlintest1.Modals.CategoryModal
import com.example.kotlintest1.R
import kotlinx.android.synthetic.main.singlelayoutmainrecycler.view.*

class MainAdapter(private val context: MainActivity, private val chaptersList: List<CategoryModal>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(LayoutInflater.from(context).inflate(R.layout.singlelayoutmainrecycler, parent, false))
}override fun getItemCount(): Int {
    return chaptersList.size
} override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder?.chapterName?.text = chaptersList.get(position).cat_name
    val url :String = chaptersList.get(position).image
    Glide.with(context).load(url).into(holder.ChapterImage)
//    holder?.ChapterImage?



    holder.itemView.setOnClickListener {
//        Toast.makeText(context, chaptersList.get(position).cat_name, Toast.LENGTH_LONG).show()
    }
}class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val chapterName = view.Text1
    val ChapterImage = view.FirstImg

}
}


