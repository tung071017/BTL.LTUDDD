package com.example.hetcuu.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hetcuu.R
import com.example.hetcuu.adapter.SingleAdapter
import com.example.hetcuu.data.DataTruyen
import com.example.hetcuu.data.Truyen
import com.example.hetcuu.my_interface.TruyenClickInterface
import java.util.Locale


class SearchActivity : AppCompatActivity() {

    private lateinit var searchAdapter: SingleAdapter
    private lateinit var rcvSearch : RecyclerView
    private lateinit var listsearch : ArrayList<Truyen>
    private lateinit var templistsearch : ArrayList<Truyen>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        listsearch = DataTruyen.getTruyenCapNhat()
        templistsearch = DataTruyen.getTruyenCapNhat()

        searchAdapter = SingleAdapter(templistsearch,this,object : TruyenClickInterface {
            override fun OnClickItemListener(info: Truyen) {
                onClickGoToDetail(info)
            }
        })

        rcvSearch = findViewById(R.id.rcvSearch)
        rcvSearch.layoutManager = LinearLayoutManager(this)
        rcvSearch.setHasFixedSize(false)
        rcvSearch.adapter = searchAdapter


        val searchView : SearchView = findViewById(R.id.frg_search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {

                templistsearch.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    listsearch.forEach{
                        if (it.name.toLowerCase(Locale.getDefault()).contains(searchText)){
                            templistsearch.add(it)
                        }
                    }
                    rcvSearch.adapter!!.notifyDataSetChanged()
                }
                else {
                    templistsearch.clear()
                    templistsearch.addAll(listsearch)
                    rcvSearch.adapter!!.notifyDataSetChanged()
                }

                return false
            }
        })

        val backButton : ImageButton = findViewById(R.id.imageButton)
        backButton.setOnClickListener{
            finish()
        }

    }

    private fun onClickGoToDetail(info: Truyen) {
        val intent : Intent = Intent(this, ChiTietTruyen::class.java)
        var bundle = Bundle()
        bundle.putSerializable("object_info", info)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}


