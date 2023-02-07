package com.example.lesson_architecturepatterns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson_architecturepatterns.databinding.ActivityMainBinding
import com.example.lesson_architecturepatterns.mvp.view.ViewActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.buttonMVP.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    ViewActivity::class.java
                )
            )
        }
        binding.buttonMVVM.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    ViewActivity::class.java
                )
            )
        }
        setContentView(binding.root)
    }
}