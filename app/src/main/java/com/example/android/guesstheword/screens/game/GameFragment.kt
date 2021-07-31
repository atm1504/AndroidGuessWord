package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding


class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )

        //viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }
        viewModelObservers()
        return binding.root

    }

    private fun viewModelObservers() {
        viewModel.score.observe(viewLifecycleOwner, Observer { newValue ->
            binding.scoreText.text = newValue.toString()
        })

        viewModel.word.observe(viewLifecycleOwner, Observer { newValue ->
            binding.wordText.text = newValue
        })

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { newValue ->
            if (newValue) {
                gameFinished()
            }
        })
    }

    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value as Int)
        findNavController(this).navigate(action)
        viewModel.onGameFinishedComplete()
    }

}
