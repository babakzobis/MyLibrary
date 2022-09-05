package com.vanzoconsulting.mylibrary.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BindingViewModelFragment<VB : ViewDataBinding, VM : StateViewModel<U>, U : UiState<*>>(
    private val inflate: Inflate<VB>
) :
    Fragment() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM
    abstract val dismissActionLabel: Int
    abstract val dismissAction: () -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                viewModel.uiState.collect { uiState ->
                    updateUI(uiState)
                }
            }
        }
    }

    abstract fun updateUI(uiState: U)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate.invoke(inflater, container, false)

        return binding.root
    }

    protected fun showUserMessage(message: String) {
        val snackbar = Snackbar
            .make(binding.root, message, LENGTH_INDEFINITE)
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    viewModel.userMessageShown()
                }
            })

        if (showDismissAction()) {
            snackbar.setAction(dismissActionLabel) {
                dismissAction.invoke()
            }
        }

        snackbar.show()
    }

    abstract fun showDismissAction(): Boolean
}