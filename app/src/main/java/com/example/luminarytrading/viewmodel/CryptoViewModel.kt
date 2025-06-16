package com.example.luminarytrading.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luminarytrading.database.Trade
import com.example.luminarytrading.database.TradeType
import com.example.luminarytrading.repository.CryptoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CryptoViewModel(private val repository: CryptoRepository) : ViewModel() {
    private val _cryptoPrices = MutableStateFlow<Map<String, Double>>(emptyMap())
    val cryptoPrices: StateFlow<Map<String, Double>> = _cryptoPrices.asStateFlow()

    private val _portfolioValue = MutableStateFlow(0.0)
    val portfolioValue: StateFlow<Double> = _portfolioValue.asStateFlow()

    fun updatePrices(ids: List<String>) {
        viewModelScope.launch {
            repository.getCryptoPrices(ids).collect { prices ->
                _cryptoPrices.value = prices
                updatePortfolioValue()
            }
        }
    }

    fun addTrade(cryptoId: String, amount: Double, price: Double, type: TradeType) {
        viewModelScope.launch {
            repository.addTrade(cryptoId, amount, price, type)
            updatePortfolioValue()
        }
    }

    private suspend fun updatePortfolioValue() {
        var totalValue = 0.0
        for ((cryptoId, price) in _cryptoPrices.value) {
            val balance = repository.getCryptoBalance(cryptoId)
            totalValue += balance * price
        }
        _portfolioValue.value = totalValue
    }

    fun getCryptoBalance(cryptoId: String): Double {
        var balance = 0.0
        viewModelScope.launch {
            balance = repository.getCryptoBalance(cryptoId)
        }
        return balance
    }
} 