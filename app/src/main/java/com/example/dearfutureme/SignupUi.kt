package com.example.dearfutureme

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.dearfutureme.databinding.ActivitySignupuiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupUi : AppCompatActivity() {

    private lateinit var binding: ActivitySignupuiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupuiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginAcc()
        signup()
    }

    private fun signup() {
        binding.signupBtn.setOnClickListener {
            val username = binding.etuserName.text.toString()
            val email = binding.etEmailAddress.text.toString()
            val password = binding.etPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                val request = UserRegistration(username, email, password)

                RetrofitInstance.instance.registerUser(request).enqueue(object : Callback<UserRegistration> {
                    override fun onResponse(call: Call<UserRegistration>, response: Response<UserRegistration>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            val userRegistration = response.body()!!

                                binding.tvUserExist.text = "Registration Successful"
                                hideMessageAfterDelay()

                        } else {
                            binding.tvUserExist.text = "Registration Failed"
                            hideMessageAfterDelay()
                        }
                    }

                    override fun onFailure(call: Call<UserRegistration>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            }
        }
    }


    private fun hideMessageAfterDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.tvUserExist.text = ""
        }, 4000)
    }

    private fun loginAcc() {
        binding.loginAccount.setOnClickListener {
            startActivity(Intent(this@SignupUi, MainActivity::class.java))
        }
    }
}
