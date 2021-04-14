package com.mao.jetpack.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mao.jetpack.R
import com.mao.jetpack.databinding.FragmentNvOneBinding

/**
 *
 * @author zhangkun
 * @time 2021/4/14 8:48 PM
 * @Description
 */
class FragmentNVOne : Fragment() {

    private var binding: FragmentNvOneBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 用onCreateView 使用  FragmentNvOneBinding.inflate 初始化 binding
        binding = FragmentNvOneBinding.inflate(inflater, container, false)
        return binding?.root

        // 创建布局
        // val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        //return root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // onCreateView 使用原始方式 创建布局 onViewCreated 可以绑定 view
        //val binding = FragmentNvOneBinding.bind(view)
        //this.binding = binding;
        binding?.let {
            it.btnTwo.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.ac_2)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}