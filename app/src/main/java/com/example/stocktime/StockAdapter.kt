package com.example.stocktime

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stocks.Stock
import com.example.stocks.StockApplicationClass
import kotlinx.android.synthetic.main.selected_stock_item.view.*
import kotlinx.android.synthetic.main.stock_item.view.*

class StockAdapter(
        val context: Context,
        val stockList: ArrayList<RawStock>
) : RecyclerView.Adapter<ViewHolder>() {


    override fun getItemCount() = stockList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.stock_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textViewUnselectedName.text = stockList[position].symbol
        if (stockList[position].isSelected) holder.relativeLayoutStockItem.setBackgroundColor(
            context.getColor(R.color.green)
        ) else holder.relativeLayoutStockItem.setBackgroundColor(Color.parseColor("#454444"))
        if (stockList[position].isSelected) holder.imageViewCheck.visibility =
            View.VISIBLE else holder.imageViewCheck.visibility = View.INVISIBLE

        holder.itemView.setOnClickListener {
            if (stockList[position].isSelected) {
                stockList[position].isSelected = false
                StockApplicationClass.removeSelectedRawStockItem(stockList[position])

            } else {
                stockList[position].isSelected = true
                StockApplicationClass.addSelectedRawStockItem(stockList[position])

            }

            if (stockList[position].isSelected) holder.relativeLayoutStockItem.setBackgroundColor(
                context.getColor(R.color.green)
            ) else holder.relativeLayoutStockItem.setBackgroundColor(Color.parseColor("#454444"))
            if (stockList[position].isSelected) holder.imageViewCheck.visibility = View.VISIBLE else holder.imageViewCheck.visibility = View.INVISIBLE

        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textViewUnselectedName = view.textViewStockName!!
    val imageViewCheck = view.imageViewCheck!!
    val relativeLayoutStockItem = view.relativeLayoutStockItem
}







