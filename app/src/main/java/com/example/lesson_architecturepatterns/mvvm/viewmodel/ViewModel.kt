package com.example.lesson_architecturepatterns.mvvm.viewmodel

import android.media.MediaPlayer
import android.os.CountDownTimer
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson_architecturepatterns.mvvm.model.Model
import com.example.lesson_architecturepatterns.mvvm.model.RecommendationsModel


class ViewModel : ViewModel() {
    // Model должен быть заменён на репозиторий, если требуется взаимодействие с API или базой данных
    private val model = Model()

    // LiveData для наблюдения изменений из View
    val millisLeft: LiveData<Long> = model.getMillisLeft()
    val recommendations: LiveData<RecommendationsModel> = MutableLiveData()

    // Инициализация LiveData рекомендаций
    init {
        updateRecommendations()
    }

    private fun updateRecommendations() {
        (recommendations as MutableLiveData).value = model.getRecommendations()
    }

    private var timer: CountDownTimer? = null // Таймер

    // Запуск и проверка таймера на окончание
    private fun timerStart(
        millisInFuture: Long,
        timerTextView: TextView,
        soundOfStop: MediaPlayer
    ) {
        timer = object : CountDownTimer(millisInFuture, 1) {
            override fun onTick(p0: Long) {
                model.setMillisLeft(p0)
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
    fun timerPause() {
        timer?.cancel()
    }

    // Воспроизведение таймера с того момента когда он остановился
    fun timerResume(timerTextView: TextView, soundOfStop: MediaPlayer) {
        model.getMillisLeft().value?.let { timerStart(it, timerTextView, soundOfStop) }
    }

    fun plus30Sec(millisPlus: Int, timerTextView: TextView, soundOfStop: MediaPlayer) {
        timer?.cancel()
        timerStart(model.getMillisLeft().value!! + millisPlus, timerTextView, soundOfStop)
    }

    // Проигрывание звука
    fun soundPlay(sound: MediaPlayer) {
        sound.start()
    }

    // Остановка звука
    fun soundPause(sound: MediaPlayer) {
        sound.pause()
    }

    fun getRecommendations(): RecommendationsModel {
        return model.getRecommendations()
    }
}