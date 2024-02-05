package com.example.hetcuu.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hetcuu.data.User
import com.example.hetcuu.databinding.LoginLayoutBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : LoginLayoutBinding
    private lateinit var reference: DatabaseReference
    var user : User = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logBack.setOnClickListener{
            finish()
        }

        binding.textView.setOnClickListener {
            val intent : Intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener{

            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()){
                checkData(username,password)
            } else {
                Toast.makeText(this,"Hãy điền đủ tài khoản và mật khẩu",Toast.LENGTH_SHORT).show()
            }

        }

        binding.textView.setOnClickListener {
            val intent : Intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun checkData(username : String, password : String) {
        val database = Firebase.database
        reference = database.getReference("User")
        reference.child(username).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {

                if (task.result.exists()) {

                    val dataSnapshot: DataSnapshot = task.result
                    val pass = dataSnapshot.child("password").value.toString()

                    if (password == pass) {
                        Toast.makeText(applicationContext, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                        val intent: Intent = Intent(applicationContext, MainScreen::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(applicationContext, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show()
                    }

                    val followId = dataSnapshot.child("followid").value.toString()
                    val email = dataSnapshot.child("email").value.toString()
                    user = User(username,email,password)
                    user.followId = followId
                } else {
                    Toast.makeText(applicationContext, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(applicationContext, "Failed to Read", Toast.LENGTH_SHORT).show()
            }
        }
    }

}