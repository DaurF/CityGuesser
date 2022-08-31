package mob.dau.cityguesser.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import mob.dau.cityguesser.R
import mob.dau.cityguesser.data.MAX_OF_STEPS
import mob.dau.cityguesser.data.SCORE_INCREASE
import mob.dau.cityguesser.databinding.FragmentGameBinding

class GameFragment : Fragment(), View.OnClickListener {


    private val viewModel: GameViewModel by viewModels()

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var userAnswer = "test"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.option1.setOnClickListener(this)
        binding.option2.setOnClickListener(this)
        binding.option3.setOnClickListener(this)
        binding.option4.setOnClickListener(this)

        viewModel.currentImage.observe(viewLifecycleOwner) { newImage ->
            binding.itemImage.setImageResource(newImage)
        }
        viewModel.options1.observe(viewLifecycleOwner) { newOption1 ->
            binding.option1.text = newOption1
        }
        viewModel.options2.observe(viewLifecycleOwner) { newOption2 ->
            binding.option2.text = newOption2
        }
        viewModel.options3.observe(viewLifecycleOwner) { newOption3 ->
            binding.option3.text = newOption3
        }
        viewModel.options4.observe(viewLifecycleOwner) { newOption4 ->
            binding.option4.text = newOption4
        }
        viewModel.stepCount.observe(viewLifecycleOwner) { newStepCount ->
            binding.currentStep.text = getString(R.string.step_label, newStepCount, MAX_OF_STEPS)
        }
        viewModel.score.observe(viewLifecycleOwner) { newScore ->
            binding.currentScore.text = getString(R.string.score_label, newScore)
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.option1 -> {
                userAnswer = binding.option1.text.toString()
            }
            binding.option2 -> {
                userAnswer = binding.option2.text.toString()
            }
            binding.option3 -> {
                userAnswer = binding.option3.text.toString()
            }
            binding.option4 -> {
                userAnswer = binding.option4.text.toString()
            }
        }
        if (viewModel.isUserAnswerIsCorrect(userAnswer)) {
            if (!viewModel.nextQuest()) {
                showFinalDialog()
            }
        } else {
            sendErrorMessage()
            if (!viewModel.nextQuest()) {
                showFinalDialog()
            }
        }
    }

    private fun sendErrorMessage() {
        Snackbar.make(binding.gameFragmentLayout, R.string.error_msg, Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun showFinalDialog() {
        Log.d("XD", "LDWD")
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.congratulations)
            .setMessage(getString(R.string.score_result, viewModel.score.value))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.exit)) { _, _ ->
                exitGame()
            }
            .setNegativeButton(getString(R.string.restart)) { _, _ ->
                restartGame()
            }
            .show()

    }

    private fun exitGame() {
        activity?.finish()
    }

    private fun restartGame() {
        viewModel.reinitializeData()
    }
}