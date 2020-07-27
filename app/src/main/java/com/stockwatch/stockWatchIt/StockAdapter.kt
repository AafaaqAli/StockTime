package com.stockwatch.stockWatchIt

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.stock_item.view.*

class StockAdapter(
        private val context: Context,
        private val stockList: ArrayList<RawStock>
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
                StockApplicationClass.removeSelectedRawStockItem(stockList[position])

                stockList[position].isSelected = false
                holder.imageViewCheck.visibility = View.INVISIBLE
                holder.relativeLayoutStockItem.setBackgroundColor(Color.parseColor("#454444"))
                Log.d("bugLog", "item Removed at: ${position}, And item is: ${stockList[position]}" )

            } else {
                StockApplicationClass.addSelectedRawStockItem(stockList[position])

                stockList[position].isSelected = true
                holder.relativeLayoutStockItem.setBackgroundColor(context.getColor(R.color.green))
                holder.imageViewCheck.visibility = View.VISIBLE
                Log.d("bugLog", "item Added at: ${position}, And item is: ${stockList[position]}" )

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
    val relativeLayoutStockItem = view.relativeLayoutStockItem!!
}







