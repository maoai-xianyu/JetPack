package com.mao.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.mao.jetpack.utils.Logger

class MyPresenter : DefaultLifecycleObserver {

    companion object {
        private const val TAG = "test"
    }

    override fun onResume(owner: LifecycleOwner) {
        Logger.debug("Lifecycle call onResume")
        Logger.debug("Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )
    }

    override fun onPause(owner: LifecycleOwner) {
        Logger.debug("Lifecycle call onPause")
        Logger.debug("Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )

    }

    override fun onCreate(owner: LifecycleOwner) {
        Logger.debug( "Lifecycle call onCreate")
        Logger.debug("Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )

    }

    override fun onStart(owner: LifecycleOwner) {
        Logger.debug("Lifecycle call onStart")
        Logger.debug( "Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )

    }

    override fun onStop(owner: LifecycleOwner) {
        Logger.debug("Lifecycle call onStop")
        Logger.debug("Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )

    }

    override fun onDestroy(owner: LifecycleOwner) {
        Logger.debug("Lifecycle call onDestroy")
        Logger.debug("Lifecycle 当前状态 ${owner.lifecycle.currentState.name}" )
    }


}