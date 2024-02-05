package com.example.hetcuu.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.hetcuu.activity.ChiTietTruyen
import com.example.hetcuu.R
import com.example.hetcuu.data.RecyclerTruyen
import com.example.hetcuu.data.Truyen
import com.example.hetcuu.data.TypeTruyen
import com.example.hetcuu.databinding.ItemRcvBinding
import com.example.hetcuu.my_interface.TruyenClickInterface


class MainAdapter(private val listRecyclerTruyen:List<RecyclerTruyen>, var mContext : Context?) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // xử lý onclick
    private fun onClickGoToDetail(info: Truyen) {
        val intent : Intent = Intent(mContext, ChiTietTruyen::class.java)
        val bundle = Bundle()
        bundle.putSerializable("object_info", info)
        intent.putExtras(bundle)
        mContext!!.startActivity(intent)


    }

    // giải phóng context
    fun release(){
        mContext = null
    }

    // khai báo recyclerView con
    inner class RecyclerItemViewHolder(private val binding: ItemRcvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.itemRcv.setHasFixedSize(true)
        }

        fun bindDeCuRecyclerView(recyclerItemList: List<Truyen>) {

            binding.textviewRcv.text = "Truyện Đề Cử >"

            val adapter = ChildAdapter(TypeTruyen.TRUYEN_DE_CU, recyclerItemList,mContext,object :TruyenClickInterface {
                override fun OnClickItemListener(info: Truyen) {
                    onClickGoToDetail(info)
                }
            })
            binding.itemRcv.layoutManager =
                LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
            binding.itemRcv.adapter = adapter
        }

        fun bindCapNhatRecyclerView(recyclerItemList: List<Truyen>) {

            binding.textviewRcv.text = "Truyện Mới Cập Nhật >"

            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(binding.itemRcv)
            val adapter = ChildAdapter(TypeTruyen.TRUYEN_CAP_NHAT, recyclerItemList,mContext,object :TruyenClickInterface {
                override fun OnClickItemListener(info: Truyen) {
                    onClickGoToDetail(info)
                }
            })
            binding.itemRcv.layoutManager =
                LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
            binding.itemRcv.adapter = adapter
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (listRecyclerTruyen[position].viewType) {
            TypeTruyen.TRUYEN_DE_CU ->
                R.layout.truyendc_layout
            else ->
                R.layout.truyencn_layout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemRcvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        RecyclerItemViewHolder(binding)
        return RecyclerItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listRecyclerTruyen.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (listRecyclerTruyen[position].viewType) {
            TypeTruyen.TRUYEN_DE_CU -> {
                listRecyclerTruyen[position].rcvListTruyen?.let {
                    (holder as RecyclerItemViewHolder).bindDeCuRecyclerView(
                        it
                    )
                }
            }
            else -> {
                listRecyclerTruyen[position].rcvListTruyen?.let {
                    (holder as RecyclerItemViewHolder).bindCapNhatRecyclerView(
                        it
                    )
                }
            }
        }
    }



}