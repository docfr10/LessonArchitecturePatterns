package com.example.lesson_architecturepatterns.mvvm.model

import androidx.lifecycle.MutableLiveData
import java.util.*

class Model {
    private val arrayList = arrayListOf<RecommendationsModel>()
    private var millisStart = MutableLiveData<Long>().apply { postValue(12000) } //Время отдыха
    private var millisLeft: MutableLiveData<Long> = millisStart //Время, оставщееся до конца отдыха

    init {
        arrayList.add(RecommendationsModel("Не выполняйте упражнения через боль!"))
        arrayList.add(RecommendationsModel("Если Вы устали сделайте перерыв."))
        arrayList.add(RecommendationsModel("Одежда во время выполнения упражнений должна быть легкой, не стесняющей движений."))
    }

    fun getRecommendations(): RecommendationsModel {
        val random = Random()
        val index = random.nextInt(arrayList.size)
        return arrayList[index]
    }

    fun setMillisLeft(newMillisLeft: Long) {
        millisLeft.value = newMillisLeft
    }

    fun getMillisLeft(): MutableLiveData<Long> {
        return millisLeft
    }
}