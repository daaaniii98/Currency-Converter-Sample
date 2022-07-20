package com.example.currencychallenge.features.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.currencychallenge.R
import com.example.currencychallenge.core.base.BaseFragment
import com.example.currencychallenge.core.exception.NetworkException
import com.example.currencychallenge.core.exception.ServerException
import com.example.currencychallenge.core.extentions.*
import com.example.currencychallenge.data.network.model.response.CurrenciesModel
import com.example.currencychallenge.databinding.FragmentHomeBinding
import com.example.currencychallenge.features.home.ui.adapter.CurrencyExchangeAdapter
import com.example.currencychallenge.features.home.viewmodel.HomeFragmentErrorTypes
import com.example.currencychallenge.features.home.viewmodel.HomeFragmentUIStates
import com.example.currencychallenge.features.home.viewmodel.HomeViewModel
import com.example.currencychallenge.utils.formatAmount
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val homeViewModel by viewModels<HomeViewModel>()
    lateinit var currenciesAdapter: ArrayAdapter<String>
    private val currencyExchangeRateAdapter = CurrencyExchangeAdapter {
        showSnackBarMsg("${it.data.first} :: ${it.data.second.formatAmount()}")
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun myInit() {
        binding.actCurrency.inputType = 0
        binding.rvExchangeRates.adapter = currencyExchangeRateAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.homeUiStates.collect {
                    when (it) {
                        is HomeFragmentUIStates.Loading -> {
                            binding.loadingView.root.show()
                            binding.errorInfoView.root.hide()
                            binding.currencySelectorContainer.hide()
                            binding.tilCurrency.hide()
                        }
                        is HomeFragmentUIStates.Error -> {
                            binding.currencySelectorContainer.show()
                            binding.tilCurrency.show()
                            binding.currencySelectorContainer.disable()
                            binding.tilCurrency.disable()
                            binding.errorInfoView.root.show()
                            binding.loadingView.root.hide()
                            when (it.errorType) {
                                is HomeFragmentErrorTypes.CurrenciesError -> {
                                    binding.errorInfoView.txTitle.text =
                                        getString(R.string.no_data_found)
                                    binding.errorInfoView.txSubTitle.text =
                                        getString(R.string.no_data_desc)
                                }
                                HomeFragmentErrorTypes.CurrencyFieldEmptyError -> {
                                    binding.errorInfoView.root.hide()
                                    binding.currencySelectorContainer.enable()
                                    binding.tilCurrency.enable()
                                    showSnackBarMsg(getString(R.string.fill_all_data_first))
                                }
                                is HomeFragmentErrorTypes.GeneralError -> {
                                    when (it.errorType.exception) {
                                        is NetworkException -> {
                                            binding.errorInfoView.txTitle.text =
                                                getString(R.string.no_internet_connection)
                                            binding.errorInfoView.txSubTitle.text =
                                                getString(R.string.no_internet_connection_desc)
                                        }
                                        is ServerException -> {
                                            binding.errorInfoView.txTitle.text =
                                                getString(R.string.server_error)
                                            binding.errorInfoView.txSubTitle.text =
                                                getString(R.string.server_error_desc)

                                        }
                                        else -> {
                                            binding.errorInfoView.txTitle.text =
                                                getString(R.string.error_occurred)
                                            binding.errorInfoView.txSubTitle.text =
                                                it.errorType.exception?.message
                                        }

                                    }
                                }
                            }

                        }
                        is HomeFragmentUIStates.ShowCurrencies -> {
                            binding.currencySelectorContainer.show()
                            binding.tilCurrency.show()
                            binding.currencySelectorContainer.enable()
                            binding.tilCurrency.enable()
                            binding.errorInfoView.root.hide()
                            binding.loadingView.root.hide()

                            currenciesAdapter = ArrayAdapter<String>(
                                requireContext(),
                                android.R.layout.select_dialog_item,
                                CurrenciesModel.getAvailableCurrencies(it.data)
                            )
                            binding.actCurrency.setAdapter(currenciesAdapter)
                        }

                        is HomeFragmentUIStates.ShowCurrencyExchangeRates -> {
                            binding.currencySelectorContainer.show()
                            binding.tilCurrency.show()
                            binding.currencySelectorContainer.enable()
                            binding.tilCurrency.enable()
                            binding.errorInfoView.root.hide()
                            binding.loadingView.root.hide()
                            currencyExchangeRateAdapter.submitList(it.data)
                        }
                    }
                }
            }
        }
    }


    override fun myListeners() {
        binding.errorInfoView.btnRetry.setOnClickListener {
            homeViewModel.retry()
        }
        binding.actCurrency.setOnItemClickListener { _, _, position, _ ->
            hideKeyboard()
            val selectedItem = currenciesAdapter.getItem(position)
            homeViewModel.updateSelectCurrencySymbol(selectedItem)
        }
        binding.tilCurrency.editText?.addTextChangedListener {
            try {
                if (it.toString().isNotEmpty()) {
                    val amount = it.toString().toDouble()
                    homeViewModel.updateSelectedCurrencyAmount(amount)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}