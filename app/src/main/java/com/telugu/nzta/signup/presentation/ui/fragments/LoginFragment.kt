package com.telugu.nzta.signup.presentation.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.telugu.nzta.R
import com.telugu.nzta.common.ViewModelFactory
import com.telugu.nzta.signup.presentation.viewmodel.SignupViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class LoginFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private lateinit var mSignupViewModel: SignupViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            mSignupViewModel =
                ViewModelProviders.of(requireActivity(), viewModelFactory)
                    .get(SignupViewModel::class.java)
        }
    }


}
