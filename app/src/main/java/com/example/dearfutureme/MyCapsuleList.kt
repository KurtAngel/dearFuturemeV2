package com.example.dearfutureme

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dearfutureme.databinding.ActivityMyCapsuleListBinding

class MyCapsuleList : AppCompatActivity() {

    private lateinit var binding: ActivityMyCapsuleListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyCapsuleListBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

//        RetrofitInstance.instance.displayName("Angel")

        val paint = binding.tvMyCapsule.paint
        val width = paint.measureText(binding.tvMyCapsule.text.toString())
        binding.tvMyCapsule.paint.shader = LinearGradient(
            0f,0f,width,binding.tvMyCapsule.textSize, intArrayOf(
                Color.parseColor("#6B26D4"),
                Color.parseColor("#C868FF")
            ), null, Shader.TileMode.CLAMP
        )

        binding.addCapsuleBtn.setOnClickListener {
            startActivity(Intent(this@MyCapsuleList, createCapsule::class.java))
        }
    }
}