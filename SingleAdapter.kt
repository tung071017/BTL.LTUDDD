package com.example.hetcuu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hetcuu.data.Truyen
import com.example.hetcuu.databinding.TruyencnLayoutBinding
import com.example.hetcuu.my_interface.TruyenClickInterface

class SingleAdapter(private val listTruyen: List<Truyen>, var mContext: Context?,
                    val onItemClick: TruyenClickInterface
) :
    RecyclerView.Adapter<SingleAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(private val binding: TruyencnLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val cardViewLayout : CardView = binding.layoutCapnhat

        fun bindSearchView(truyenCapNhat: Truyen) {
            Glide.with(mContext!!).load(truyenCapNhat.image).into(binding.TruyenCNAvt)
            binding.TruyenCNTen.text = truyenCapNhat.name
            binding.TruyenCNTacGia.text = truyenCapNhat.author
            binding.TruyenCNMoiNhat.text = truyenCapNhat.newchap
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = TruyencnLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentItem = listTruyen[position]
        holder.bindSearchView(currentItem)

        holder.cardViewLayout!!.setOnClickListener {
            onItemClick.OnClickItemListener(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return listTruyen.size
    }
}

