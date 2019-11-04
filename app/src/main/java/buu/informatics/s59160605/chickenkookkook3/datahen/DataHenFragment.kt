package buu.informatics.s59160605.chickenkookkook3.datahen


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import buu.informatics.s59160605.chickenkookkook3.R
import buu.informatics.s59160605.chickenkookkook3.database.MyDatabase
import buu.informatics.s59160605.chickenkookkook3.databinding.FragmentDataHenBinding

/**
 * A simple [Fragment] subclass.
 */
class DataHenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentDataHenBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_data_hen,
            container, false)
        val username = DataHenFragmentArgs.fromBundle(arguments!!).username
        val application = requireNotNull(this.activity).application
        val dataSource = MyDatabase.getInstance(application).henDao
        val viewModelFactory = DataHenViewModelFactory(dataSource,application)

        val dataHenViewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(DataHenViewModel::class.java)

        binding.backBt.setOnClickListener {
            findNavController().navigate(
                DataHenFragmentDirections.actionDataHenFragmentToAddJournalFragment(username)
            )
        }

        binding.shareBt.setOnClickListener {
            startActivity(getShareIntent(dataHenViewModel.showDieQuantity))
        }

        binding.dataHenViewModel = dataHenViewModel
        binding.setLifecycleOwner(this)

        setHasOptionsMenu(true)

        val adapter = DataHenAdapter()
        binding.henList.adapter = adapter

        dataHenViewModel.dieHenAll.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }

    private fun getShareIntent(shareContent: LiveData<String?>): Intent? {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, shareContent.value)
        return shareIntent
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }


}
