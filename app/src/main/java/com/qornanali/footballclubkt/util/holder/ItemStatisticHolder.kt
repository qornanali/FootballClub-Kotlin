package com.qornanali.footballclubkt.util.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.model.Statistic

class ItemStatisticHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvStatisticDataname: TextView = itemView.findViewById(R.id.tv_statistic_dataname)
    private val tvStatisticLeftvalue: TextView = itemView.findViewById(R.id.tv_statistic_leftvalue)
    private val tvStatisticRightvalue: TextView = itemView.findViewById(R.id.tv_statistic_rightvalue)

    fun bind(statistic: Statistic) {
        tvStatisticDataname.text = statistic.dataName
        tvStatisticLeftvalue.text = statistic.leftValue
        tvStatisticRightvalue.text = statistic.rightValue
    }
}
