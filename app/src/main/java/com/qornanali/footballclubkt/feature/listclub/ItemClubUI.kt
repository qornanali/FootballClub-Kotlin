package com.qornanali.footballclubkt.feature.listclub

import android.content.Context
import android.view.View
import com.qornanali.footballclub_kotlin.R
import org.jetbrains.anko.*

class ItemClubUI : AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout() {
            topPadding = dip(8)
            leftPadding = dip(16)
            rightPadding = dip(16)
            bottomPadding = dip(8)

            imageView {
                id = R.id.iv_club_logo
            }.lparams(width = 72, height = 72){
                rightMargin = dip(8)
            }

            textView {
                id = R.id.tv_club_name
                textSize = 24f
            }.lparams(width = matchParent, height = wrapContent) {
                rightOf(R.id.iv_club_logo)
                centerVertically()
            }
        }
    }

}