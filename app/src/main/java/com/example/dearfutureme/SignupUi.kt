package com.example.dearfutureme

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dearfutureme.databinding.ActivitySignupuiBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupUi : AppCompatActivity() {

    private lateinit var binding: ActivitySignupuiBinding
    private var dao : UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupuiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        loginAcc()
        signup()
    }

    private fun initVars() {
        dao = UserDatabase.getDatabase(this).userDao()
    }

    private fun signup() {
        binding.signupBtn.setOnClickListener {
            val name = binding.etEmailAddress.text.toString()
            val password = binding.etPassword.text.toString()

            if (name.isNotEmpty() && password.isNotEmpty()) {
                val user = User(0, name, password)

                lifecycleScope.launch(Dispatchers.IO) {
                    val userExists = dao?.isUsernameExists(name) ?: false

                    if(userExists) {
                        withContext(Dispatchers.Main) {
                            binding.tvUserExist.text = "User Already Exists! Try Again"
                            hideMessageAfterDelay()
                        }
                    } else {

                        withContext(Dispatchers.Main) {
                        dao?.getUsername(user)
                            binding.tvUserExist.text = "Registration Successful"
                            hideMessageAfterDelay()
                        }
                    }
                }
            } else {
                binding.tvUserExist.text = "Please fill in all fields"
                hideMessageAfterDelay()
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