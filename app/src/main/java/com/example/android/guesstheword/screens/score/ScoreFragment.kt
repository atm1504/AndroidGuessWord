package com.example.android.guesstheword.screens.score

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.ScoreFragmentBinding


class ScoreFragment : Fragment() {

    private lateinit var viewModelFactory: ScoreViewModelFactory
    private lateinit var viewModel: ScoreViewModel
    private lateinit var binding: ScoreFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.score_fragment,
            container,
            false
        )

        // Get args using by navArgs property delegate
        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()

        viewModelFactory = ScoreViewModelFactory(scoreFragmentArgs.score)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)

        binding.scoreViewModel = viewModel

        viewModelObservers()

//        binding.playAgainButton.setOnClickListener {
//            viewModel.onPlayAgain()
//        }

        return binding.root
    }

    fun viewModelObservers() {
        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
            Log.d("ATM", "Score is $newScore")
        })

        viewModel.eventPLayAgain.observe(viewLifecycleOwner, Observer { playAgain ->
            if (playAgain) {
                onPlayAgain()
            }
        })

    }

    private fun onPlayAgain() {
        findNavController().navigate(ScoreFragmentDirections.actionRestart())
        viewModel.onPlayAgainComplete()
    }
}
