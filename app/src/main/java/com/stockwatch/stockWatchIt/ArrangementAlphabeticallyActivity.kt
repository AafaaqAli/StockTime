package com.stockwatch.stockWatchIt

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_arangement_alphabetically.*

class ArrangementAlphabeticallyActivity : WearableActivity(), View.OnClickListener {
    private var stockMarketID: Int = -1
    private var stockMarketName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arangement_alphabetically)
        initAlphabetViews()
        getIntentData()

        textViewStockMarketName.text = stockMarketName
    }


    private fun getIntentData() {
        stockMarketID = intent.getIntExtra("stock_exchange_id", -1)
        stockMarketName = intent.getStringExtra("stock_exchange_name").toString()


    }

    private fun initAlphabetViews() {
        alphabetViewA.loadText("A")
        alphabetViewA.loadTag("A")
        alphabetViewA.cardView.setOnClickListener(this)

        alphabetViewB.loadText("B")
        alphabetViewB.loadTag("B")
        alphabetViewB.cardView.setOnClickListener(this)

        alphabetViewC.loadText("C")
        alphabetViewC.loadTag("C")
        alphabetViewC.cardView.setOnClickListener(this)

        alphabetViewD.loadText("D")
        alphabetViewD.loadTag("D")
        alphabetViewD.cardView.setOnClickListener(this)

        alphabetViewE.loadText("E")
        alphabetViewE.loadTag("E")
        alphabetViewE.cardView.setOnClickListener(this)

        alphabetViewF.loadText("F")
        alphabetViewF.loadTag("F")
        alphabetViewF.cardView.setOnClickListener(this)

        alphabetViewG.loadText("G")
        alphabetViewG.loadTag("G")
        alphabetViewG.cardView.setOnClickListener(this)

        alphabetViewH.loadText("H")
        alphabetViewG.loadTag("H")
        alphabetViewG.cardView.setOnClickListener(this)

        alphabetViewI.loadText("I")
        alphabetViewI.loadTag("I")
        alphabetViewI.cardView.setOnClickListener(this)

        alphabetViewJ.loadText("J")
        alphabetViewJ.loadTag("J")
        alphabetViewJ.cardView.setOnClickListener(this)

        alphabetViewK.loadText("K")
        alphabetViewK.loadTag("K")
        alphabetViewK.cardView.setOnClickListener(this)

        alphabetViewL.loadText("L")
        alphabetViewL.loadTag("L")
        alphabetViewL.cardView.setOnClickListener(this)


        alphabetViewM.loadText("M")
        alphabetViewM.loadTag("M")
        alphabetViewM.cardView.setOnClickListener(this)

        alphabetViewN.loadText("N")
        alphabetViewN.loadTag("N")
        alphabetViewN.cardView.setOnClickListener(this)


        alphabetViewO.loadText("O")
        alphabetViewO.loadTag("O")
        alphabetViewO.cardView.setOnClickListener(this)

        alphabetViewP.loadText("P")
        alphabetViewP.loadTag("P")
        alphabetViewP.cardView.setOnClickListener(this)

        alphabetViewQ.loadText("Q")
        alphabetViewQ.loadTag("Q")
        alphabetViewQ.cardView.setOnClickListener(this)

        alphabetViewR.loadText("R")
        alphabetViewR.loadTag("R")
        alphabetViewR.cardView.setOnClickListener(this)

        alphabetViewS.loadText("S")
        alphabetViewS.loadTag("S")
        alphabetViewS.cardView.setOnClickListener(this)

        alphabetViewT.loadText("T")
        alphabetViewT.loadTag("T")
        alphabetViewT.cardView.setOnClickListener(this)

        alphabetViewU.loadText("U")
        alphabetViewU.loadTag("U")
        alphabetViewU.cardView.setOnClickListener(this)

        alphabetViewV.loadText("V")
        alphabetViewV.loadTag("V")
        alphabetViewV.cardView.setOnClickListener(this)

        alphabetViewW.loadText("W")
        alphabetViewW.loadTag("W")
        alphabetViewW.cardView.setOnClickListener(this)

        alphabetViewX.loadText("X")
        alphabetViewX.loadTag("X")
        alphabetViewX.cardView.setOnClickListener(this)


        alphabetViewY.loadText("Y")
        alphabetViewY.loadTag("Y")
        alphabetViewY.cardView.setOnClickListener(this)

        alphabetViewZ.loadText("Z")
        alphabetViewZ.loadTag("Z")
        alphabetViewZ.cardView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val intent = Intent(this, StocksActivity::class.java)
        intent.putExtra("stockListID", stockMarketID)
        intent.putExtra("stockListAlphabet", view!!.tag.toString())
        startActivity(intent)
    }
}