package com.mao.jetpack.ui.viewmodelfile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mao.jetpack.R
import com.mao.jetpack.databinding.ActivityViewModelBinding
import com.mao.jetpack.ui.viewmodelfile.viewmodel.ViewModelActivityViewModel
import com.mao.jetpack.utils.Logger

/**
 *
 * @author zhangkun
 * @time 2020/9/24 9:19 PM
 * @Description
 */
class ViewModelActivity : AppCompatActivity() {

    private var viewModelActivityViewModel: ViewModelActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelBinding: ActivityViewModelBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_view_model)
        // 绑定生命周期
        viewModelBinding.lifecycleOwner = this
        // 初始化化数据源
        viewModelActivityViewModel = ViewModelActivityViewModel()
        // 绑定数据源
        viewModelBinding.viewModel = viewModelActivityViewModel
        // 获取数据
        viewModelActivityViewModel!!.getUser()
        viewModelBinding.presenter = Presenter()
    }

    fun send(view: View) {
        Logger.debug(" 发送1 ${viewModelActivityViewModel!!.userKotlin.name}")
    }

    class Presenter {
        fun onClick(viewModel: ViewModelActivityViewModel) {
            Logger.debug(" 发送2  ${viewModel.userKotlin.name}")
        }
    }
}