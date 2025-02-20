package com.example.bataillenavale.model

data class BattleShipCell(
    val row: Int,
    val column: Int,
    var status: CellStatus = CellStatus.EMPTY,
    var name: String = ""
)


enum class CellStatus {
    EMPTY,
    SHIP,
    HIT,
    MISS
}

enum class FireResult {
    HIT,
    MISS,
    INVALID
}
