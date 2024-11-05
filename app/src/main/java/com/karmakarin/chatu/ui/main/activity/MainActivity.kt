package com.karmakarin.chatu.ui.main.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.karmakarin.chatu.R
import com.karmakarin.chatu.data.model.CM
import com.karmakarin.chatu.databinding.ActivityMainBinding
import com.karmakarin.chatu.ui.main.adapter.ChatAdapter
import com.karmakarin.chatu.ui.main.viewModel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var chatAdapter: ChatAdapter? = null
    private var list: ArrayList<CM>? = null
    private val chatViewModel by viewModels<ChatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
    }

    private fun setUpUI() {
        try {
            binding.ma = this
            binding.lifecycleOwner = this
            binding.vm = chatViewModel
            chatAdapter = ChatAdapter(ArrayList(), this)
            binding.adapter = chatAdapter
            onClickHandler()
            textWater()
            chatViewModel.chatList.observe(this) { list ->
                this.list = list
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun onClickHandler() {
        binding.ivBack.setOnClickListener(this)
        binding.ivAttachment.setOnClickListener(this)
        binding.ivCall.setOnClickListener(this)
        binding.ivOption.setOnClickListener(this)
        binding.ivEmoji.setOnClickListener(this)
        binding.ivMic.setOnClickListener(this)
        binding.ivCamera.setOnClickListener(this)
        binding.edText.setOnClickListener(this)
        binding.ivSend.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivBack -> {
                onBackPressed()
            }

            R.id.ivCall, R.id.ivAttachment, R.id.ivOption, R.id.ivEmoji, R.id.ivMic, R.id.ivCamera, R.id.edText -> {
                Toast.makeText(this, "Work in Progress", Toast.LENGTH_SHORT).show()
            }

            R.id.ivSend -> {
                try {
                    if (binding.edText.text!!.isNotEmpty()) {
                        if (list.isNullOrEmpty()) list = ArrayList()
                        val chat = getData()
                        list?.add(chat)
                        binding.edText.setText("")
                        chatViewModel._chatList.value = list
                        chatViewModel.insertChat(chat)
                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getData(): CM {
        return CM(
            System.currentTimeMillis(),
            true,
            binding.edText.text.toString(),
            System.currentTimeMillis(),
            ""
        )
    }

    private fun textWater() {
        binding.edText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {

                    if (s.toString().startsWith("0")) {
                        binding.edText.setText(s.toString().replaceFirst("0", ""))
                    } else if (s.toString().startsWith(".")) {
                        binding.edText.setText(s.toString().replaceFirst(".", ""))
                    } else {
                        binding.ivMic.visibility = View.GONE
                        binding.ivSend.visibility = View.VISIBLE
                    }
                } else {
                    binding.ivSend.visibility = View.GONE
                    binding.ivMic.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
}