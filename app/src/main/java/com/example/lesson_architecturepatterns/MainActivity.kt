package com.example.lesson_architecturepatterns

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_architecturepatterns.databinding.ActivityMainBinding
import com.example.lesson_architecturepatterns.mvp.view.ViewActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonMVC.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    com.example.lesson_architecturepatterns.mvc.view.ViewActivity::class.java
                )
            )
        }
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
                    com.example.lesson_architecturepatterns.mvvm.view.ViewActivity::class.java
                )
            )
        }
    }
}