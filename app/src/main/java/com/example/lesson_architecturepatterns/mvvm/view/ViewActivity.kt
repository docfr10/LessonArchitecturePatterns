package com.example.lesson_architecturepatterns.mvvm.view

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lesson_architecturepatterns.R
import com.example.lesson_architecturepatterns.databinding.ActivityMvpBinding
import com.example.lesson_architecturepatterns.mvvm.viewmodel.ViewModel
import java.util.Locale

class ViewActivity : AppCompatActivity() {
    private lateinit var soundOfStop: MediaPlayer // Звук, оповещающий об окончании отдыха

    private var plus30Sec: Int = 30000

    private lateinit var binding: ActivityMvpBinding
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val provider = ViewModelProvider(this)
        viewModel = provider[ViewModel::class.java]

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        soundOfStop = MediaPlayer.create(this, R.raw.sound_of_stop)

        // Наблюдение за LiveData
        viewModel.millisLeft.observe(this, Observer { millis ->
            binding.timerTextViewRest.text = formatMillis(millis)
        })

        // Наблюдение за LiveData
        viewModel.recommendations.observe(this, Observer { recommendation ->
            binding.recommendationsTextView.text = recommendation.recommendations
        })
    }

    private fun formatMillis(millis: Long): String {
        val minutes = (millis / 1000) / 60
        val seconds = (millis / 1000) % 60
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
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