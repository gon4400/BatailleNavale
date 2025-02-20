import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bataillenavale.R
import com.example.bataillenavale.model.BattleShipCell
import com.example.bataillenavale.model.CellStatus

class BattleShipAdapter(var cells: List<BattleShipCell>, private val onItemClick: (Int, Int) -> Unit) :
    RecyclerView.Adapter<BattleShipAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell = cells[position]
        holder.bind(cell)
    }

    override fun getItemCount(): Int {
        return cells.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cellImageView: ImageView = itemView.findViewById(R.id.image_view_cell)

        fun bind(cell: BattleShipCell) {
            itemView.setOnClickListener {
                onItemClick(cell.row, cell.column)
            }

            when (cell.status) {
                CellStatus.EMPTY -> {
                    cellImageView.setImageResource(R.drawable.water_background)
                }
                CellStatus.SHIP -> {
                    if (cell.name == "Croiseur") {
                        cellImageView.setImageResource(R.drawable.croiseur)
                    }
                    if (cell.name == "Escorteur") {
                        cellImageView.setImageResource(R.drawable.escorteur)
                    }
                    if (cell.name == "Torpilleur") {
                        cellImageView.setImageResource(R.drawable.torpilleur)
                    }
                    if (cell.name == "Sous-marin") {
                        cellImageView.setImageResource(R.drawable.sousmarin)
                    }
                }
                CellStatus.HIT -> {
                    cellImageView.setImageResource(R.drawable.target_background)
                }
                CellStatus.MISS -> {
                    cellImageView.setImageResource(R.drawable.missed_target)
                }
            }
        }
    }
}
