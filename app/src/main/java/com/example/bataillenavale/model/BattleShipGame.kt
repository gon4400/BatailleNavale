package com.example.bataillenavale.model

import kotlin.random.Random


class BattleShipGame(private val gridSize: Int) {

    private val grid: Array<Array<BattleShipCell>> = Array(gridSize) { row ->
        Array(gridSize) { column -> BattleShipCell(row, column) }
    }
    private val fleet: MutableList<BattleShip> = mutableListOf(
        BattleShip("Croiseur", 4),
        BattleShip("Escorteur", 3),
        BattleShip("Escorteur", 3),
        BattleShip("Torpilleur", 2),
        BattleShip("Torpilleur", 2),
        BattleShip("Torpilleur", 2),
        BattleShip("Sous-marin", 1),
        BattleShip("Sous-marin", 1),
        BattleShip("Sous-marin", 1),
        BattleShip("Sous-marin", 1)
    )

    val cells: List<BattleShipCell>
        get() = grid.flatten()

    init {
        placeShipsRandomly()
    }

    fun fire(row: Int, column: Int): FireResult {
        val cell = grid[row][column]
        if (cell.status == CellStatus.SHIP) {
            cell.status = CellStatus.HIT
            return FireResult.HIT
        } else if (cell.status == CellStatus.EMPTY) {
            cell.status = CellStatus.MISS
            return FireResult.MISS
        }
        return FireResult.INVALID
    }

    fun reset() {
        for (row in 0 until gridSize) {
            for (column in 0 until gridSize) {
                grid[row][column].status = CellStatus.EMPTY
            }
        }
        fleet.clear()
        placeShipsRandomly()
    }

    private fun placeShipsRandomly() {
        val ships = listOf(
            BattleShip("Croiseur", 4),
            BattleShip("Escorteur", 3),
            BattleShip("Escorteur", 3),
            BattleShip("Torpilleur", 2),
            BattleShip("Torpilleur", 2),
            BattleShip("Torpilleur", 2),
            BattleShip("Sous-marin", 1),
            BattleShip("Sous-marin", 1),
            BattleShip("Sous-marin", 1),
            BattleShip("Sous-marin", 1)
        )

        ships.forEach { ship ->
            var isPlaced = false

            while (!isPlaced) {
                val x = Random.nextInt(0, gridSize)
                val y = Random.nextInt(0, gridSize)
                val isHorizontal = Random.nextBoolean()

                if (canPlaceShip(x, y, ship.size, isHorizontal)) {
                    placeShip(x, y, ship, isHorizontal)
                    isPlaced = true
                }
            }
        }
    }

    private fun canPlaceShip(x: Int, y: Int, size: Int, isHorizontal: Boolean): Boolean {
        val positions = if (isHorizontal) {
            (x until x + size).map { it to y }
        } else {
            (y until y + size).map { x to it }
        }

        return positions.all { (px, py) ->
            px in 0 until gridSize && py in 0 until gridSize && grid[px][py].status == CellStatus.EMPTY &&
                    (px - 1 !in 0 until gridSize || grid[px - 1][py].status == CellStatus.EMPTY) &&
                    (px + 1 !in 0 until gridSize || grid[px + 1][py].status == CellStatus.EMPTY) &&
                    (py - 1 !in 0 until gridSize || grid[px][py - 1].status == CellStatus.EMPTY) &&
                    (py + 1 !in 0 until gridSize || grid[px][py + 1].status == CellStatus.EMPTY)
        }
    }

    private fun placeShip(x: Int, y: Int, ship: BattleShip, isHorizontal: Boolean) {
        val positions = if (isHorizontal) {
            (x until x + ship.size).map { it to y }
        } else {
            (y until y + ship.size).map { x to it }
        }

        positions.forEachIndexed { index, (px, py) ->
            grid[px][py].status = CellStatus.SHIP
            grid[px][py].name = ship.name
        }
        fleet.add(ship)
    }

}

data class BattleShip(val name: String, val size: Int)