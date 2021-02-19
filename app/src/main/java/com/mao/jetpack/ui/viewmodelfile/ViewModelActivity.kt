package com.mao.jetpack.ui.viewmodelfile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.mao.jetpack.BR
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

        // 双向绑定 UserKotlin 需要继承 BaseObservable
        val userKotlin = UserKotlin()
        userKotlin.name = "测试"
        //viewModelBinding.user = userKotlin
        viewModelBinding.setVariable(BR.user, userKotlin)


        // MutableLiveData<String>() 的双向绑定实现原理
        /*viewModelBinding.tvTextEdit.addTextChangedListener {
            viewModelActivityViewModel.name.value = it.toString()
        }
        viewModelActivityViewModel.name.observe(this,onChanged = {
            viewModelBinding.tvText.text = it
        })*/


        // 动态改变，那么 xml 中的 @={viewmodel.user.name} 就没有用了，可以删除
        viewModelBinding.tvTextEdit1.addTextChangedListener {
            viewModelActivityViewModel.changeUserName(it.toString())
        }

        viewModelActivityViewModel.user.observe(this, onChanged = {
            viewModelBinding.tvText1.text = it.name
        })
    }

    fun send(view: View) {
         Logger.debug(" 发送1 ${viewModelActivityViewModel.user}")
    }

    class Presenter {
        fun onClick(viewModel: ViewModelActivityViewModel) {
            Logger.debug(" 发送2  ${viewModel.user}")
        }
    }
}