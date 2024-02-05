package com.example.hetcuu.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.hetcuu.R
import com.example.hetcuu.data.DataTruyen
import com.example.hetcuu.data.Truyen

class ChiTietTruyen : AppCompatActivity() {

    private var idChuong : Int = 0

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_layout)

        val tenct : TextView = findViewById(R.id.ten_ct)
        val tacgiact : TextView = findViewById(R.id.tacgia_ct)
        val imgct : ImageView = findViewById(R.id.img_ct)

        //lấy thông tin truyện từ bundle
        val intent = intent
        val bundle = intent.extras
        if (bundle == null) {
            return
        }
        val info = bundle.get("object_info") as Truyen
        tenct.text = info.name
        tacgiact.text = "Tác giả : " + info.author
        Glide.with(this).load(info.image).into(imgct)


        val back : ImageButton = findViewById(R.id.ctc_back)
        back.setOnClickListener{
            finish()
        }

        val follow : ImageButton = findViewById(R.id.followButton)
        if (info.isFollow) {
            follow.setImageResource(R.drawable.baseline_favorite_24_red)
            follow.setOnClickListener{
                follow.setImageResource(R.drawable.baseline_favorite_border_24)
                Toast.makeText(this,"Đã hủy theo dõi",Toast.LENGTH_SHORT).show()
            }
        }
        else {
            follow.setImageResource(R.drawable.baseline_favorite_border_24)
            follow.setOnClickListener{
                follow.setImageResource(R.drawable.baseline_favorite_24_red)
                Toast.makeText(this,"Đã theo dõi",Toast.LENGTH_SHORT).show()
                DataTruyen.setTruyenTheoDoi(info)
            }
        }


        val tags : TextView = findViewById(R.id.tags_tv)
        var listTag : String = "Thể Loại: "
        for (data in info.tags){
            listTag += "$data ,"
        }
        tags.text= listTag


        val firstchap : Button = findViewById(R.id.firstchap)
        firstchap.setOnClickListener{
            if (info.listChuongTruyen.isEmpty()){
                Toast.makeText(applicationContext,"Truyện hiện chưa có chương nào",Toast.LENGTH_SHORT).show()
            }
            else {
                val intent: Intent = Intent(applicationContext, ReadActivity::class.java)

                //đưa thông tin chương vào bundle
                val bundle = Bundle()
                bundle.putSerializable("chapter_info", info.listChuongTruyen.head)
                bundle.putString("name_info",info.name)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }

        val dsChuong : Button = findViewById(R.id.dschuong)
        dsChuong.setOnClickListener{
            if (info.listChuongTruyen.isEmpty()){
                Toast.makeText(applicationContext,"Truyện hiện chưa có chương nào",Toast.LENGTH_SHORT).show()
            }
            else {

                val intent: Intent = Intent(applicationContext, DanhSachChuong::class.java)
                val bundle = Bundle()
                bundle.putSerializable("object_info", info)
                intent.putExtras(bundle)
                startActivity(intent)

            }
        }
    }
}