package com.qornanali.footballclub_kotlin.detail_club

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclub_kotlin.model.ItemClub
import org.jetbrains.anko.setContentView

class DetailClubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailClubUI().setContentView(this)

        val club = intent.extras.getSerializable("club") as ItemClub
        try{
            val imgClubLogo: ImageView = findViewById(R.id.iv_club_logo) as ImageView
            imgClubLogo.setImageResource(club.logo)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}