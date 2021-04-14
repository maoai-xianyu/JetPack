package com.mao.jetpack.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import com.mao.jetpack.R
import com.mao.jetpack.databinding.FragmentNvOneBinding
import com.mao.jetpack.databinding.FragmentNvTwoBinding

/**
 *
 * @author zhangkun
 * @time 2021/4/14 8:48 PM
 * @Description
 */
class FragmentNVTwo : Fragment() {

    private var binding: FragmentNvTwoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 用onCreateView 使用  FragmentNvOneBinding.inflate 初始化 binding
        /*binding = FragmentNvOneBinding.inflate(inflater, container, false)
        return binding?.root*/
        // 创建布局
        return inflater.inflate(R.layout.fragment_dashboard, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // onCreateView 使用原始方式 创建布局 onViewCreated 可以绑定 view
        val binding = FragmentNvTwoBinding.bind(view)
        this.binding = binding

        binding.let {

            binding.btnOne.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.ac_back_1)

                // 跳转上一步
                //Navigation.findNavController(view).navigateUp()
            }
            binding.btnThree.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.ac_3)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}