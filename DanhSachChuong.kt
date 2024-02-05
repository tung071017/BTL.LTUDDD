package com.example.hetcuu.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hetcuu.R
import com.example.hetcuu.adapter.ChuongAdapter
import com.example.hetcuu.data.Truyen
import com.example.hetcuu.data.ChuongTruyen
import com.example.hetcuu.linkedlist.LinkedList
import com.example.hetcuu.my_interface.ChuongClickInterface

class DanhSachChuong : AppCompatActivity() {

    private lateinit var listChuongAdapter: ChuongAdapter
    private lateinit var listChuongTruyen : LinkedList<ChuongTruyen>
    private lateinit var name : String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_chuong_rcv)

        //Lấy dữ liệu truyện từ Chi tiết truyện
        val intent = intent
        val bundle = intent.extras
        val infobundle = bundle?.get("object_info") as Truyen
        listChuongTruyen = infobundle.listChuongTruyen

        name = infobundle.name

        val listChuongRcv : RecyclerView = findViewById(R.id.rcvlistchuong)

        listChuongAdapter = ChuongAdapter(listChuongTruyen,object : ChuongClickInterface{
            override fun onClickItemListener(info: ChuongTruyen) {
                onClickGoToChapter(info)
            }
        })

        listChuongRcv.layoutManager = LinearLayoutManager(this)
        listChuongRcv.setHasFixedSize(true)
        listChuongRcv.adapter = listChuongAdapter

        val button : ImageButton = findViewById(R.id.listchuong_back)
        button.setOnClickListener{
            finish()
        }

    }

    private fun onClickGoToChapter(info: ChuongTruyen) {
        val intent: Intent = Intent(applicationContext, ReadActivity::class.java)

        //đưa thông tin chương vào bundle
        val bundle = Bundle()
        bundle.putSerializable("chapter_info", info)
        bundle.putString("name_info",name)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}