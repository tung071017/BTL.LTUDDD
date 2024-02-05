package com.example.hetcuu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hetcuu.data.Truyen
import com.example.hetcuu.data.TypeTruyen
import com.example.hetcuu.databinding.TruyendcLayoutBinding
import com.example.hetcuu.databinding.TruyencnLayoutBinding
import com.example.hetcuu.my_interface.TruyenClickInterface

class ChildAdapter(private val viewType: Int, private val listTruyen: List<Truyen>, var mContext : Context?,
                   val onItemClick : TruyenClickInterface) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class TruyenDeCuViewHolder(private val binding: TruyendcLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val cardViewLayout : CardView = binding.layoutDecu

        fun bindTruyenDeCuView(truyenDeCu: Truyen) {
            Glide.with(mContext!!).load(truyenDeCu.image).into(binding.TruyenDCAvt)
            binding.TruyenDCTen.text = truyenDeCu.name
            binding.TruyenDCMoiNhat.text = truyenDeCu.newchap.split(":")[0]
        }
    }

    inner class TruyenCapNhatViewHolder(private val binding: TruyencnLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val cardViewLayout : CardView = binding.layoutCapnhat

        fun bindTruyenCapNhatView(truyenCapNhat: Truyen) {
            Glide.with(mContext!!).load(truyenCapNhat.image).into(binding.TruyenCNAvt)
            binding.TruyenCNTen.text = truyenCapNhat.name
            binding.TruyenCNTacGia.text = truyenCapNhat.author
            binding.TruyenCNMoiNhat.text = truyenCapNhat.newchap
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TypeTruyen.TRUYEN_DE_CU -> {
                val binding = TruyendcLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return TruyenDeCuViewHolder(binding)
            }
            else -> {
                val binding = TruyencnLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return TruyenCapNhatViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return listTruyen.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        // tạo hàm xử lý click

        if (holder is TruyenDeCuViewHolder) {
            holder.bindTruyenDeCuView(listTruyen[position])
            holder.cardViewLayout!!.setOnClickListener {
                onItemClick.OnClickItemListener(listTruyen[position])
            }
        }
        if (holder is TruyenCapNhatViewHolder) {
            holder.bindTruyenCapNhatView(listTruyen[position])
            holder.cardViewLayout!!.setOnClickListener {
                onItemClick.OnClickItemListener(listTruyen[position])
            }
        }
    }

}