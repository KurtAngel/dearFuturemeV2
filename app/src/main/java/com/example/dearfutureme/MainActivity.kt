package com.example.dearfutureme

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.dearfutureme.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var dao : UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        initVars()
        createAcc()
        logInAcc()
    }

    private fun initVars() {
        dao = UserDatabase.getDatabase(this).userDao()
    }

    private fun createAcc() {
        binding.createAccount.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignupUi::class.java))
        }
    }

    private fun logInAcc() {
        binding.loginBtn.setOnClickListener {
            val name = binding.eTEmailAddress.text.toString()
            val password = binding.eTPassword.text.toString()

            if (name.isNotEmpty() && password.isNotEmpty()) {
                lifecycleScope.launch {
                    val user = dao?.getUserByNameAndPassword(name, password)
                    if (user != null) {
                        // User exists, navigate to the next activity
                        val intent = Intent(this@MainActivity, MyCapsuleList::class.java)
                        startActivity(intent)
                    } else {
                        // User does not exist, show an error message
                        Toast.makeText(this@MainActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Handle empty fields
                Toast.makeText(this@MainActivity, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}