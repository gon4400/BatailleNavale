package com.example.bataillenavale.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bataillenavale.model.BattleShipCell
import com.example.bataillenavale.repository.BattleShipRepository

class BattleShipViewModel(private val repository: BattleShipRepository) : ViewModel() {

    private val _battleShipCells = MutableLiveData<List<BattleShipCell>>()
    val battleShipCells: LiveData<List<BattleShipCell>> get() = _battleShipCells

    init {
        _battleShipCells.value = repository.getBattleShipCells()
    }

    fun fire(row: Int, column: Int) {
        val result = repository.fire(row, column)
    }

    fun resetGame() {
        repository.resetGame()
        _battleShipCells.value = repository.getBattleShipCells()
    }
}

