package buu.informatics.s59160605.chickenkookkook3.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import buu.informatics.s59160605.chickenkookkook3.R
import buu.informatics.s59160605.chickenkookkook3.database.MyDatabase
import buu.informatics.s59160605.chickenkookkook3.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login,
            container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = MyDatabase.getInstance(application).userDao
        val viewModelFactory = LoginViewModelFactory(dataSource,application)
        loginViewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(LoginViewModel::class.java)

        loginViewModel.checkLoginComplete.observe(this, Observer {
            if (it) {
                Toast.makeText(activity,"Go To Next Page", Toast.LENGTH_SHORT).show()
                findNavController().navigate(
                    LoginFragmentDirections
                        .actionLoginFragmentToAddJournalFragment(loginViewModel.username.value.toString())
                )
            }
        })

        binding.registerBt.setOnClickListener{
            it.findNavController().navigate(
                LoginFragmentDirections
                    .actionLoginFragmentToRegisterFragment()
            )
        }

        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this

        return binding.root
    }



}
