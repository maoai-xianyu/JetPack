package com.mao.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class MyPresenter2 : LifecycleObserver {

    companion object {
        private const val TAG = "test"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateX(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call onResume")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartX(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call ON_START")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call ON_STOP")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call ON_RESUME")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call ON_PAUSE")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call ON_DESTROY")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call ON_ANY")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )
    }


}