package com.mao.jetpack.ui.viewmodelfile.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.mao.jetpack.BR

/**
 *
 * @author zhangkun
 * @time 2020/9/25 12:01 PM
 * @Description
 */
class UserKotlin : BaseObservable() {

    var name: String = ""
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }


    var age: Int = 0
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
        }
}