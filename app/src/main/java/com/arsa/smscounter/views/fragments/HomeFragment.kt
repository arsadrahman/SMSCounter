package com.arsa.smscounter.views.fragments

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.arsa.smscounter.Model.MessageCount
import com.arsa.smscounter.R
import com.arsa.smscounter.viewmodel.MessageViewModel
import com.arsa.smscounter.viewmodel.MessageViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var messageViewModelFactory:MessageViewModelFactory
    private lateinit var viewModel:MessageViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initListeners()
         messageViewModelFactory =
            MessageViewModelFactory(requireActivity().application)
         viewModel = ViewModelProvider(this, messageViewModelFactory).get(MessageViewModel::class.java)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun initListeners(){
        submitBtn.setOnClickListener(View.OnClickListener {
            var phoneNumber:String = phoneNumber_TIL.editText?.text.toString()
            var days : String = days_TIL.editText?.text.toString()
            if(TextUtils.isEmpty(phoneNumber)){
                phoneNumber_TIL.error = "Enter the phone number"
            }else if(TextUtils.isEmpty(days) && TextUtils.isDigitsOnly(days)){
                days_TIL.error = "Enter the correct day in number"
            }else{
                countMessagesAndUpdate(phoneNumber,days.toInt())
            }
        })

    }

    fun countMessagesAndUpdate(phoneNumber:String,days:Int){
        showLoadingScreen()
        viewModel.messageCount(phoneNumber,days).observe(requireActivity(), Observer { messageCount ->
            val bundle = bundleOf("messageCount" to MessageCount(messageCount))
            navController!!.navigate(R.id.resultFragment,bundle)

            dismissLoadingScreen()
        })
    }
    fun showLoadingScreen(){
        loadingscreen.visibility = VISIBLE
    }

    fun dismissLoadingScreen(){
            loadingscreen.visibility = GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }




}