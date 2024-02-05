package com.example.hetcuu.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hetcuu.R
import com.example.hetcuu.adapter.SingleAdapter
import com.example.hetcuu.data.DataTruyen
import com.example.hetcuu.data.Truyen
import com.example.hetcuu.my_interface.TruyenClickInterface

class TagsSearchActivity : AppCompatActivity() {

    private var listItem: ArrayList<Truyen> = ArrayList()
    private lateinit var listTruyen: ArrayList<Truyen>
    private lateinit var rcvSearch: RecyclerView
    private lateinit var searchAdapter: SingleAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tagsearch)

        val intent = intent
        val bundle = intent.extras
        val info = bundle?.get("tags_info") as String

        listTruyen = DataTruyen.getTruyenCapNhat()
        for (data in listTruyen) {
            if (info in data.tags) {
                listItem.add(data)
            }
        }

        val textView : TextView = findViewById(R.id.tagsearchTv)
        textView.text = info

        val button : ImageButton = findViewById(R.id.tags_back)
        button.setOnClickListener{
            finish()
        }

        searchAdapter = SingleAdapter(listItem, this, object : TruyenClickInterface {
            override fun OnClickItemListener(info: Truyen) {
                onClickGoToDetail(info)
            }
        })

        rcvSearch = findViewById(R.id.tagsearchRcv)
        rcvSearch.layoutManager = LinearLayoutManager(this)
        rcvSearch.setHasFixedSize(false)
        rcvSearch.adapter = searchAdapter

    }

    private fun onClickGoToDetail(info: Truyen) {
        val intent: Intent = Intent(this, ChiTietTruyen::class.java)
        var bundle = Bundle()
        bundle.putSerializable("object_info", info)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}