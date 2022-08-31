package mob.dau.cityguesser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import mob.dau.cityguesser.data.dataset
import mob.dau.cityguesser.databinding.FragmentInitialOptionsBinding

class InitialOptionsFragment : Fragment() {
    private var _binding: FragmentInitialOptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInitialOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.playButton.setOnClickListener {
            val action =
                InitialOptionsFragmentDirections.actionInitialOptionsFragmentToGameFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}