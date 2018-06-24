package com.qornanali.footballclubkt.feature.detailclub

import com.qornanali.footballclub_kotlin.R
import org.jetbrains.anko.*

class DetailClubUI : AnkoComponent<DetailClubActivity> {
    override fun createView(ui: AnkoContext<DetailClubActivity>) = with(ui) {
        verticalLayout {
            toolbar {
                id = R.id.toolbar
            }.lparams(width = matchParent, height = wrapContent)

            relativeLayout {
                lparams(width = matchParent, height = matchParent)
                padding = dip(16)

                imageView {
                    id = R.id.iv_club_logo
                }.lparams(width = wrapContent, height = wrapContent) {
                    centerInParent()
                }
            }
        }
    }

}