package buu.informatics.s59160605.chickenkookkook3.register


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import buu.informatics.s59160605.chickenkookkook3.R
import buu.informatics.s59160605.chickenkookkook3.database.MyDatabase
import buu.informatics.s59160605.chickenkookkook3.databinding.FragmentRegisterBinding

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_register,
            container, false )

        val application = requireNotNull(this.activity).application
        val dataSource = MyDatabase.getInstance(application).userDao
        val viewModelFactory = RegisterViewModelFactory(dataSource,application)

        registerViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(RegisterViewModel::class.java)

        registerViewModel.registerComplete.observe(this, Observer {
            if(it){
                Toast.makeText(activity,"Register Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(
                    RegisterFragmentDirections
                        .actionRegisterFragmentToLoginFragment())
            }
        })



        binding.registerViewModel = registerViewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}
