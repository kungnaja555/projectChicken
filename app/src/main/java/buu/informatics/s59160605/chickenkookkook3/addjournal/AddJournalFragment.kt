package buu.informatics.s59160605.chickenkookkook3.addjournal


import android.os.Bundle
import android.util.Log
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
import buu.informatics.s59160605.chickenkookkook3.databinding.FragmentAddJournalBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class AddJournalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAddJournalBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_journal,
            container, false)


        val username = AddJournalFragmentArgs.fromBundle(arguments!!).username
        val application = requireNotNull(this.activity).application
        val dataSource = MyDatabase.getInstance(application).henDao
        val viewModelFactory = AddJournalViewModelFactory(dataSource,application)

        val addJournalViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AddJournalViewModel::class.java)

        addJournalViewModel.insertComplete.observe(this, Observer {
            if(it){
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    "Insert Success",
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                addJournalViewModel.doneShowingSnackbar()
            }
        })

       addJournalViewModel.gotoAbout.observe(this, Observer {
           if(it){
                findNavController().navigate(
                    AddJournalFragmentDirections
                        .actionAddJournalFragmentToAboutFragment(username)
                )
           }
       })

        addJournalViewModel.gotoData.observe(this, Observer {
            if(it){
                findNavController().navigate(
                    AddJournalFragmentDirections
                        .actionAddJournalFragmentToDataHenFragment(username)
                )
            }
        })

        binding.addJournalViewModel = addJournalViewModel
        binding.lifecycleOwner = this

        return binding.root
    }


}
