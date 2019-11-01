package buu.informatics.s59160605.chickenkookkook3.about


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import buu.informatics.s59160605.chickenkookkook3.R
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


        return binding.root
    }


}
