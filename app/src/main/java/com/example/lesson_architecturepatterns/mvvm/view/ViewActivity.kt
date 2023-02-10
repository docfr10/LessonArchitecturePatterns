package com.example.lesson_architecturepatterns.mvvm.view

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson_architecturepatterns.R
import com.example.lesson_architecturepatterns.databinding.ActivityMvpBinding
import com.example.lesson_architecturepatterns.mvvm.viewmodel.ViewModel

class ViewActivity : AppCompatActivity() {
    private lateinit var soundOfStop: MediaPlayer // Звук, оповещающий об окончании отдыха

    private var plus30Sec: Int = 30000

    private lateinit var binding: ActivityMvpBinding
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        soundOfStop = MediaPlayer.create(this, R.raw.sound_of_stop)
        updateRecommendations()
    }

    private fun updateRecommendations() {
        binding.recommendationsTextView.text = viewModel.getRecommendations().recommendations
    }

    override fun onResume() {
        super.onResume()
        viewModel.timerResume(binding.timerTextViewRest, soundOfStop)
        binding.plus30secButton.setOnClickListener { plus30Sec(plus30Sec) }
        binding.nextButton2.setOnClickListener { nextButtonClicked() }
    }

    override fun onPause() {
        super.onPause()
        viewModel.timerPause()
        viewModel.soundPause(soundOfStop)
    }

    private fun nextButtonClicked() {
        super.finish()
    }

    private fun plus30Sec(millisPlus: Int) {
        viewModel.plus30Sec(millisPlus, binding.timerTextViewRest, soundOfStop)
    }
}