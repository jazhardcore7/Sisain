package com.sisain.capstone.ui.ui.views.auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sisain.capstone.R
import com.sisain.capstone.data.userlocaldb.MyApp
import com.sisain.capstone.databinding.ActivityLoginBinding
import com.sisain.capstone.ui.viewmodel.UserViewModelFactory
import com.sisain.capstone.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(MyApp.appDatabase.userDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol untuk login
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            userViewModel.login(email, password) { user ->
                if (user != null) {
                    // Login berhasil, pindah ke aktivitas utama
                    // Example: startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    // Tampilkan pesan error
                }
            }
        }
    }
}