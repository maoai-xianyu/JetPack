package com.mao.jetpack.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mao.jetpack.R
import com.mao.jetpack.databinding.FragmentNotificationsBinding
import com.mao.jetpack.ui.deeplink.WebActivity
import com.mao.jetpack.ui.constraintLayout.ConstraintLayoutActivity
import kotlinx.android.synthetic.main.fragment_notifications.view.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bind = FragmentNotificationsBinding.bind(view)

        bind.textNotifications.setOnClickListener {
            startActivity(Intent(context, WebActivity::class.java));
        }


        bind.btnJumpC.setOnClickListener {
            startActivity(Intent(context, ConstraintLayoutActivity::class.java))
        }
    }
}