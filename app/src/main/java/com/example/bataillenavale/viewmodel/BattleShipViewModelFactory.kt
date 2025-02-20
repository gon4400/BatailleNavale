package com.example.bataillenavale.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bataillenavale.repository.BattleShipRepository

class BattleShipViewModelFactory(private val repository: BattleShipRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BattleShipViewModel::class.java)) {
            return BattleShipViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.canonicalName}")
    }
}