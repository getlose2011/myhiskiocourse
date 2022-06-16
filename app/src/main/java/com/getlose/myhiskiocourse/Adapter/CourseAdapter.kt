package com.getlose.myhiskiocourse.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.getlose.myhiskiocourse.databinding.ItemLayoutBinding

class CourseAdapter(private val course:List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        //return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val name = course[position]
        (holder as ItemViewHolder).setText(name)
    }

    override fun getItemCount(): Int {
        return course.count()
    }
}

private class ItemViewHolder(viewBinding : ItemLayoutBinding) : RecyclerView.ViewHolder(viewBinding.root) {
    private var textView: TextView = viewBinding.textView

    fun setText(text:String){
        textView.text = text
    }

}

/*
private class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private var textView: TextView = itemView.findViewById(R.id.textView)

    fun setText(text:String){
        textView.text = text
    }

}
*/