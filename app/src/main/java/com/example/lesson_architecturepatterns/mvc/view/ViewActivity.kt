package com.example.lesson_architecturepatterns.mvc.view

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_architecturepatterns.R
import com.example.lesson_architecturepatterns.databinding.ActivityMvpBinding
import com.example.lesson_architecturepatterns.mvc.controller.Controller

class ViewActivity : AppCompatActivity() {
    private lateinit var soundOfStop: MediaPlayer // Звук, оповещающий об окончании отдыха

    private var plus30Sec: Int = 30000

    private lateinit var binding: ActivityMvpBinding
    private val controller: Controller = Controller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        soundOfStop = MediaPlayer.create(this, R.raw.sound_of_stop)
        controller.attachView(this)
        binding.recommendationsTextView.text = controller.getRecommendations()
    }

    override fun onResume() {
        super.onResume()
        controller.timerResume(binding.timerTextViewRest, soundOfStop)
        binding.plus30secButton.setOnClickListener { plus30Sec(plus30Sec) }
        binding.nextButton2.setOnClickListener { nextButtonClicked() }
    }

    override fun onPause() {
        super.onPause()
        controller.timerPause()
        controller.soundPause(soundOfStop)
    }

    private fun nextButtonClicked() {
        super.finish()
    }

    private fun plus30Sec(millisPlus: Int) {
        controller.plus30Sec(millisPlus, binding.timerTextViewRest, soundOfStop)
    }
}
