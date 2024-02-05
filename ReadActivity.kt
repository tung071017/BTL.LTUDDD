package com.example.hetcuu.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.hetcuu.R
import com.example.hetcuu.data.ChuongTruyen
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReadActivity : AppCompatActivity() {

    private lateinit var chap : ChuongTruyen
    private lateinit var listChuongTruyen : ArrayList<ChuongTruyen>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        val intent = intent
        val bundle = intent.extras

        var info = bundle?.get("chapter_info") as ChuongTruyen
        var infoname = bundle.get("name_info") as String

        val textView : TextView = findViewById(R.id.readTv)
        textView.text = infoname

        val button : ImageButton = findViewById(R.id.read_back)
        button.setOnClickListener{
            finish()
        }

        setTextViewData(info)

        val nextButton: FloatingActionButton = findViewById(R.id.next)
        nextButton.setOnClickListener {
            if (info.next == null) {
                Toast.makeText(applicationContext, "Đã đến chương đầu tiên", Toast.LENGTH_SHORT)
                    .show()
            }
            else {
                info = info.next!!
                setTextViewData(info)
            }
        }

        val previousButton : FloatingActionButton = findViewById(R.id.previous)
        previousButton.setOnClickListener{
            if (info.previous == null) {
                Toast.makeText(applicationContext, "Đã đến chương cuối cùng", Toast.LENGTH_SHORT)
                    .show()
            }
            else {
                info = info.previous!!
                setTextViewData(info)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setTextViewData(info: ChuongTruyen) {
        val data: TextView = findViewById(R.id.data)
        data.text = info.tenChuong + "\n\n\n" + info.noiDung
        data.scrollTo(0,0)
        data.movementMethod = ScrollingMovementMethod()
    }
}