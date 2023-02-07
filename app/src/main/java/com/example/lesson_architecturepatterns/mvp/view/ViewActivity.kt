package com.example.lesson_architecturepatterns.mvp.view

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson_architecturepatterns.R
import com.example.lesson_architecturepatterns.databinding.ActivityMvpBinding
import com.example.lesson_architecturepatterns.mvp.presenter.Presenter

class ViewActivity : AppCompatActivity() {
    private lateinit var soundOfStop: MediaPlayer //Звук, оповещающий об окончании отдыха

    private var plus30Sec: Int = 30000

    private var presenter: Presenter = Presenter()
    private lateinit var binding: ActivityMvpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        soundOfStop = MediaPlayer.create(this, R.raw.sound_of_stop)
        presenter.attachView(this)
        binding.recommendationsTextView.text = presenter.getRecommendations()
    }

    override fun onResume() {
        super.onResume()
        presenter.timerResume(binding.timerTextViewRest, soundOfStop)
        binding.plus30secButton.setOnClickListener { plus30Sec(plus30Sec) }
        binding.nextButton2.setOnClickListener { nextButtonClicked() }
    }

    override fun onPause() {
        super.onPause()
        presenter.timerPause()
        presenter.soundPause(soundOfStop)
    }

    private fun nextButtonClicked() {
        super.finish()
    }

    private fun plus30Sec(millisPlus: Int) {
        presenter.plus30Sec(millisPlus, binding.timerTextViewRest, soundOfStop)
    }
}