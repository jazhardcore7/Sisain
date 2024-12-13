package com.sisain.capstone.ui.ui.views.auth

import ApiService
import RegisterRequest
import RegisterResponse
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.sisain.capstone.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//import com.sisain.capstone.data.remote.ApiService
import com.sisain.capstone.network.RetrofitClient
import com.sisain.capstone.network.NetworkConfig
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usernameEditText = findViewById<EditText>(R.id.register_username)
        val emailEditText = findViewById<EditText>(R.id.register_email)
        val passwordEditText = findViewById<EditText>(R.id.register_password)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirm_password)
        val registerButton = findViewById<Button>(R.id.btn_register)
        val loginButton = findViewById<TextView>(R.id.btn_login)

        // Register button click listener
        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            // Logic validasi enter
            if (TextUtils.isEmpty(username)) {
                usernameEditText.error = getString(R.string.error_username_required)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(email)) {
                emailEditText.error = getString(R.string.error_email_required)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                passwordEditText.error = getString(R.string.error_password_required)
                return@setOnClickListener
            }
            if (password != confirmPassword) {
                confirmPasswordEditText.error = getString(R.string.error_password_mismatch)
                return@setOnClickListener
            }

            // Ngirim register user
            registerUser(username, email, password)
        }

        // Login button click listener
        loginButton.setOnClickListener {
            // Redirect ke LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun registerUser(username: String, email: String, password: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://your-api-url.com/") // Nanti ganti API base URLnya
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val request = RegisterRequest(username, email, password)

        apiService.registerUser(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful && response.body()?.success == true) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Registrasi berhasil!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        response.body()?.message ?: "Maaf registrasi gagal",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
