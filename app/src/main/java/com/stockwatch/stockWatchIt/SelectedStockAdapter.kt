package com.stockwatch.stockWatchIt

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.selected_stock_item.view.*

class SelectedStockAdapter(
        val context: Context,
        private val listOfSelectedStocks: MutableList<Stock>
) : RecyclerView.Adapter<SelectedStockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedStockViewHolder {
        return (SelectedStockViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.selected_stock_item, parent, false)
        ))
    }

    override fun getItemCount(): Int {
        return listOfSelectedStocks.size
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SelectedStockViewHolder, position: Int) {
        if (listOfSelectedStocks[position].isUp) {
            holder.imageViewUp.setImageResource(R.drawable.ic_up)
            holder.textViewStockNameSelected.text =
              listOfSelectedStocks[position].symbol
            holder.textViewStockNameSelected.setTextColor(context.getColor(R.color.green))
            holder.textViewPoints.text = "$" +  listOfSelectedStocks[position].currentPrice + "   +" + StockCalculator.calculatePercentage(listOfSelectedStocks[position].openPrice, listOfSelectedStocks[position].currentPrice) + "%"
            holder.textViewPoints.setTextColor(context.getColor(R.color.green))
        } else {
            holder.imageViewUp.setImageResource(R.drawable.ic_down)
            holder.textViewStockNameSelected.text =
               listOfSelectedStocks[position].symbol
            holder.textViewStockNameSelected.setTextColor(context.getColor(R.color.red_a200))
            holder.textViewPoints.text = "$" + listOfSelectedStocks[position].currentPrice + "    -" + StockCalculator.calculatePercentage(listOfSelectedStocks[position].openPrice, listOfSelectedStocks[position].currentPrice) + "%"
            holder.textViewPoints.setTextColor(Color.parseColor("#F44336"))
        }
    }

}

class SelectedStockViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textViewStockNameSelected = view.textViewStockNameSelected
    val imageViewUp = view.imageViewIsUp!!
    val textViewPoints = view.textViewPoints!!
}

