package com.example.lesson_architecturepatterns.mvp.presenter

import android.media.MediaPlayer
import android.widget.TextView
import com.example.lesson_architecturepatterns.mvp.view.ViewActivity

interface PresenterInterface {
    fun timerStart(millisInFuture: Long, timerTextView: TextView, soundOfStop: MediaPlayer)
    fun timerPause()
    fun timerResume(timerTextView: TextView, soundOfStop: MediaPlayer)
    fun plus30Sec(millisPlus: Int, timerTextView: TextView, soundOfStop: MediaPlayer)
    fun soundPlay(sound: MediaPlayer)
    fun soundPause(sound: MediaPlayer)
    fun attachView(viewActivity: ViewActivity)
    fun getRecommendations(): String
}