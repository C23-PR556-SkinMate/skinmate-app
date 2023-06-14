package com.kdt.skinmate.data.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.kdt.skinmate.R
import com.kdt.skinmate.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var binding_: FragmentRegisterBinding? = null
    private val binding get() = binding_!!
    private lateinit var message: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding_ = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel : SignViewModel by viewModels{
            factory
        }
        viewModel.message.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                message = it
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                isLoading(it)
            }
        }

        binding.btnregister.setOnClickListener {
            val name = binding.edtNama.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.register(name, email, password, description = "DUMMY DESCRIPTION")
                /* TODO CHANGE DESCRIPTION ABOVE FROM DUMMY TO INPUT FROM USER */
                viewModel.error.observe(viewLifecycleOwner) { event ->
                    event.getContentIfNotHandled()?.let { error ->
                        if (!error) {
                            activity?.supportFragmentManager?.commit {
                                replace(R.id.fragment_container, LoginFragment())
                                Toast.makeText(
                                    activity,
                                    "Akun Berhasil Dibuat",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Akun Gagal Dibuat",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.btnregister.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.btnregister.isEnabled = true
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}
