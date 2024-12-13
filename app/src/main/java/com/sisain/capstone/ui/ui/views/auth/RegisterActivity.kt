package com.sisain.capstone.ui.ui.views.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sisain.capstone.R
import com.sisain.capstone.data.userlocaldb.MyApp
import com.sisain.capstone.data.userlocaldb.User
import com.sisain.capstone.databinding.ActivityRegisterBinding
import com.sisain.capstone.ui.viewmodel.UserViewModelFactory
import com.sisain.capstone.viewmodel.UserViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(MyApp.appDatabase.userDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol untuk register
        binding.btnRegister.setOnClickListener {
            val username = binding.registerUsername.text.toString()
            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()

            val newUser = User(username = username, email = email, password = password)
            userViewModel.register(newUser) { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(this, "Register berhasil", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    Toast.makeText(this, "Register gagal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}