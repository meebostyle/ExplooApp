package com.example.explooapp.ru.ui.test

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.explooapp.databinding.FragmentTestBinding
import com.example.explooapp.ru.ui.base.BaseFragment
import com.example.explooapp.ru.ui.test.adapter.TestAdapter
import kotlinx.coroutines.launch

class TestFragment : BaseFragment<FragmentTestBinding>() {

    private val adapter = TestAdapter()
    private val viewModel by viewModels<TestViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTestBinding {
        return FragmentTestBinding.inflate(inflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            button.setOnClickListener {
                viewModel.getStatistic()
            }

            list.adapter = adapter

            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                    viewModel.contentFlow.collect {
                        adapter.submitList(it)
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                    viewModel.statisticFlow.collect {
                        text.text = it
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                    viewModel.errorFlow.collect {
                        Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}