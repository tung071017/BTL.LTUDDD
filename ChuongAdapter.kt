package com.example.hetcuu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hetcuu.data.ChuongTruyen
import com.example.hetcuu.R
import com.example.hetcuu.linkedlist.LinkedList
import com.example.hetcuu.my_interface.ChuongClickInterface

class ChuongAdapter (private val listChuong : LinkedList<ChuongTruyen>, val onItemClick : ChuongClickInterface)
    : RecyclerView.Adapter<ChuongAdapter.ChuongViewHolder>(){
    inner class ChuongViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tenChuong : TextView? = itemView.findViewById(R.id.idChuong)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChuongViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_chuong_layout,parent,false)
        return ChuongViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return listChuong.size
    }

    override fun onBindViewHolder(holder: ChuongViewHolder, position: Int) {
        val currentItem = listChuong.getNode(position)
        holder.tenChuong!!.text = currentItem!!.tenChuong
        holder.tenChuong!!.setOnClickListener{
            onItemClick.onClickItemListener(currentItem)
        }
    }

}