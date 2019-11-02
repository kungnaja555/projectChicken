package buu.informatics.s59160605.chickenkookkook3.about


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import buu.informatics.s59160605.chickenkookkook3.R
import buu.informatics.s59160605.chickenkookkook3.database.MyDatabase
import buu.informatics.s59160605.chickenkookkook3.databinding.FragmentAboutBinding

/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAboutBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)

        val username = AboutFragmentArgs.fromBundle(arguments!!).username
        val applicaion = requireNotNull(this.activity).application
        val dataSource = MyDatabase.getInstance(applicaion).userDao
        val viewModelFactory = AboutViewModelFactory(dataSource,applicaion,username)

        val aboutViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AboutViewModel::class.java)

        aboutViewModel.gotoAddJournal.observe(this, Observer {
            if(it){
                findNavController().navigate(
                    AboutFragmentDirections
                        .actionAboutFragmentToAddJournalFragment(username)
                )
            }
        })

        binding.aboutViewModel = aboutViewModel
        binding.lifecycleOwner = this

        return binding.root
    }


}
