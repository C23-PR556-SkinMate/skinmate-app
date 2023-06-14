package com.kdt.skinmate.data.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kdt.skinmate.data.ui.login.ViewModelFactory
import com.kdt.skinmate.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var binding_: FragmentHomeBinding? = null
    private val binding get() = binding_!!
    private lateinit var factory: ViewModelFactory
    private val viewModel: HomeViewModel by activityViewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding_ = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        factory = ViewModelFactory.getInstance(requireActivity())
//        binding.swipeRefreshLayout.setOnRefreshListener {
//            getScan()
//        }
//        viewModel.message.observe(viewLifecycleOwner) {
//            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
//        }
//        getScan()
//        adapter = ScanAdapter()
//        override fun onDestroyView() {
//            super.onDestroyView()
//            binding_ = null
//        }
    }
