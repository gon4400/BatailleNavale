package com.example.bataillenavale.ui.activities

import BattleShipAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bataillenavale.viewmodel.BattleShipViewModel
import com.example.bataillenavale.R
import com.example.bataillenavale.databinding.ActivityMainBinding
import com.example.bataillenavale.repository.BattleShipRepository
import com.example.bataillenavale.viewmodel.BattleShipViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: BattleShipAdapter

    private lateinit var viewModel: BattleShipViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gridSize = 10
        val repository = BattleShipRepository(gridSize)
        viewModel = ViewModelProvider(this, BattleShipViewModelFactory(repository))[BattleShipViewModel::class.java]

        adapter = BattleShipAdapter(emptyList()) { row, column ->
            handleCellClick(row, column)
        }

        binding.gridView.layoutManager = GridLayoutManager(this,4)
        binding.gridView.adapter = adapter

        binding.resetButton.setOnClickListener {
            resetGame()
        }
        viewModel.battleShipCells.observe(this) { cells ->
            adapter.cells = cells
            adapter.notifyDataSetChanged()
        }

        resetGame()
    }

    private fun resetGame() {
        viewModel.resetGame()
    }

    private fun handleCellClick(row: Int, column: Int) {
        viewModel.fire(row, column)
    }
}