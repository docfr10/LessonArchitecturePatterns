package com.example.lesson_architecturepatterns.mvp.model

import java.util.*

class Model {
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

    fun getMillisLeft(): Long {
        return millisLeft
    }
}