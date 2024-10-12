package com.example.dearfutureme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.dearfutureme.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        loginAcc()
        createAcc()
    }

    private fun loginAcc() {
        binding.loginBtn.setOnClickListener {
            val email = binding.etEmailAddress.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val request = UserLogin(email, password)

                RetrofitInstance.instance.loginUser(request).enqueue(object :
                    Callback<UserLogin>{
                    override fun onResponse(call: Call<UserLogin>, response: Response<UserLogin>) {
                        if (response.isSuccessful) {
//                            val UserLogin = response.body()!!
                            startActivity(Intent(this@MainActivity, MyCapsuleList::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@MainActivity, "Login failed ${response.message()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<UserLogin>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Login error: ${t.message}", Toast.LENGTH_SHORT).show()
                        Log.e("Login Error", "Error: ${t.message}")
                    }

                })
            }
        }
    }

    private fun createAcc() {
        binding.createAccount.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignupUi::class.java))
        }
    }

}
