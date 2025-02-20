package com.example.bataillenavale.repository

import com.example.bataillenavale.model.BattleShipCell
import com.example.bataillenavale.model.BattleShipGame
import com.example.bataillenavale.model.FireResult

class BattleShipRepository(private val gridSize: Int) {

    private val game: BattleShipGame = BattleShipGame(gridSize)

    fun getBattleShipCells(): List<BattleShipCell> {
        return game.cells
    }

    fun fire(row: Int, column: Int): FireResult {
        return game.fire(row, column)
    }

    fun resetGame() {
        game.reset()
    }
}