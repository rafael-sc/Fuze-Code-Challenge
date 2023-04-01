package com.orafaelsc.fuzecodechallenge.ui.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.orafaelsc.fuzecodechallenge.databinding.FragmentMatchesBinding

class MatchesFragment : Fragment() {

    private var binding: FragmentMatchesBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentMatchesBinding
        .inflate(inflater, container, false)
        .apply {
            binding = this
        }.also {
            setupView()
            initDataObserver()
        }.root

    private fun initDataObserver() {
    }

    private fun setupView() {
    }
}
