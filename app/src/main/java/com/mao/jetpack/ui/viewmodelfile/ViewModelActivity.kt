package com.mao.jetpack.ui.viewmodelfile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.mao.jetpack.R
import com.mao.jetpack.databinding.ActivityViewModelBinding
import com.mao.jetpack.ui.viewmodelfile.model.UserKotlin
import com.mao.jetpack.ui.viewmodelfile.viewmodel.ViewModelActivityViewModel
import com.mao.jetpack.utils.Logger

/**
 *
 * @author zhangkun
 * @time 2020/9/24 9:19 PM
 * @Description
 */
class ViewModelActivity : AppCompatActivity() {

    private lateinit var viewModelActivityViewModel: ViewModelActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelBinding: ActivityViewModelBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_view_model)
        // 绑定生命周期
        viewModelBinding.lifecycleOwner = this
        // 初始化化数据源
        //viewModelActivityViewModel = ViewModelProvider(this).get(ViewModelActivityViewModel::class.java)
        viewModelActivityViewModel = ViewModelActivityViewModel()
        // 绑定数据源
        viewModelBinding.viewModel = viewModelActivityViewModel
        viewModelBinding.presenter = Presenter()
        val userKotlin = UserKotlin()
        userKotlin.name = "测试"
        //viewModelBinding.user = viewModelActivityViewModel.getUser()

//        viewModelActivityViewModel.name.observe(this) {
//
//        }
    }

    fun send(view: View) {
        Logger.debug(" 发送1 ${viewModelActivityViewModel.userKotlin.name}")
    }

    class Presenter {
        fun onClick(viewModel: ViewModelActivityViewModel) {
            Logger.debug(" 发送2  ${viewModel.userKotlin.name}")
        }
    }
}