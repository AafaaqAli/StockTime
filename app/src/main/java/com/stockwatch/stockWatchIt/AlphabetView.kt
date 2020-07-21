package com.stockwatch.stockWatchIt

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView

class AlphabetView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    var textViewAlphabet: TextView
    var cardView: CardView

    init {
        LayoutInflater.from(context).inflate(R.layout.stock_item_alphabetically, this)
        textViewAlphabet = findViewById(R.id.textViewStockArrangementAlphabetically)
        cardView = findViewById(R.id.cardViewStockArrangementAlphabetically)
    }


    fun loadText(alphabet: String){
        textViewAlphabet.text = alphabet
    }

    fun loadTag(alphabetTag: String){
        cardView.tag = alphabetTag
    }

}