package com.mao.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyPresenter : DefaultLifecycleObserver {

    companion object {
        private const val TAG = "test"
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call onResume")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call onPause")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )

    }

    override fun onCreate(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call onCreate")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )

    }

    override fun onStart(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call onStart")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )

    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call onStop")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )

    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d(TAG, "Lifecycle call onDestroy")
        Log.d(TAG, "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )
    }


}