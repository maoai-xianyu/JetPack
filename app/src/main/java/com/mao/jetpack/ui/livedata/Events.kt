package com.mao.jetpack.ui.livedata

import androidx.lifecycle.BusLiveData

interface Events {
    fun event(): BusLiveData<String>
    fun personEvent(): BusLiveData<PersonModel>
}