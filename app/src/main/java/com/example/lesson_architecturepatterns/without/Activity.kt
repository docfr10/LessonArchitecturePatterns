package com.example.lesson_architecturepatterns.without

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_architecturepatterns.R
import com.example.lesson_architecturepatterns.databinding.ActivityMvpBinding
import java.util.Random

class Activity : AppCompatActivity() {
    private lateinit var soundOfStop: MediaPlayer // Звук, оповещающий об окончании отдыха

    private var plus30Sec: Int = 30000

    private var timer: CountDownTimer? = null // Таймер

    private lateinit var binding: ActivityMvpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        soundOfStop = MediaPlayer.create(this, R.raw.sound_of_stop)
        updateRecommendations()
    }

    private fun updateRecommendations() {
        binding.recommendationsTextView.text = getRecommendation()
    }

    override fun onResume() {
        super.onResume()
        timerResume(binding.timerTextViewRest, soundOfStop)
        binding.plus30secButton.setOnClickListener { plus30Sec(plus30Sec) }
        binding.nextButton2.setOnClickListener { nextButtonClicked() }
    }

    override fun onPause() {
        super.onPause()
        timerPause()
        soundPause(soundOfStop)
    }

    private fun nextButtonClicked() {
        super.finish()
    }

    private fun plus30Sec(millisPlus: Int) {
        plus30Sec(millisPlus, binding.timerTextViewRest, soundOfStop)
    }

    // Запуск и проверка таймера на окончание
    private fun timerStart(
        millisInFuture: Long,
        timerTextView: TextView,
        soundOfStop: MediaPlayer
    ) {
        timer = object : CountDownTimer(millisInFuture, 1) {
            override fun onTick(p0: Long) {
                setMillisLeft(p0)
                val minutes = (p0 / (1000 * 60))
                val seconds = ((p0 / 1000) - minutes * 60)
                timerTextView.text = "$minutes:$seconds"
            }

            override fun onFinish() {
                timerTextView.text = "Закончили"
                soundPlay(soundOfStop)
            }
        }
        (timer as CountDownTimer).start()
    }

    // Постановка таймера на паузу
    private fun timerPause() {
        timer?.cancel()
    }

    // Воспроизведение таймера с того момента когда он остановился
    private fun timerResume(timerTextView: TextView, soundOfStop: MediaPlayer) {
        timerStart(getMillisLeft(), timerTextView, soundOfStop)
    }

    private fun plus30Sec(millisPlus: Int, timerTextView: TextView, soundOfStop: MediaPlayer) {
        timer?.cancel()
        timerStart(getMillisLeft() + millisPlus, timerTextView, soundOfStop)
    }

    // Проигрывание звука
    fun soundPlay(sound: MediaPlayer) {
        sound.start()
    }

    // Остановка звука
    private fun soundPause(sound: MediaPlayer) {
        sound.pause()
    }

    private fun getRecommendation(): String {
        return getRecommendations()
    }

    private val arrayList = arrayOf(
        "Не выполняйте упражнения через боль!", "Если Вы устали сделайте перерыв.",
        "Одежда во время выполнения упражнений должна быть легкой, не стесняющей движений."
    )
    private var millisStart: Long = 120000 //Время отдыха
    private var millisLeft: Long = millisStart //Время, оставщееся до конца отдыха

    fun getRecommendations(): String {
        val random = Random()
        val index = random.nextInt(arrayList.size)
        return arrayList[index]
    }

    fun setMillisLeft(newMillisLeft: Long) {
        millisLeft = newMillisLeft
    }

    private fun getMillisLeft(): Long {
        return millisLeft
    }
}