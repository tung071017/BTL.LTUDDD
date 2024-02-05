package com.example.hetcuu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hetcuu.databinding.TagLayoutBinding
import com.example.hetcuu.my_interface.TagsClickInterface

class TagsAdapter(private val listTags : List<String>,
    val onItemClick : TagsClickInterface) : RecyclerView.Adapter<TagsAdapter.TagsViewHolder>() {
    inner class TagsViewHolder(private val binding:TagLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val textView : TextView = binding.nametag

        fun bindTagsView(tags: String) {
            binding.nametag.text = tags
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsAdapter.TagsViewHolder {
        val binding = TagLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TagsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagsAdapter.TagsViewHolder, position: Int) {
        val currentItem = listTags[position]
        holder.bindTagsView(currentItem)

        holder.textView.setOnClickListener{
            onItemClick.OnClickItemListener(currentItem)
        }

    }

    override fun getItemCount(): Int {
        return listTags.size
    }
}