package com.qornanali.footballclub_kotlin.detail_club

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclub_kotlin.model.ItemClub
import com.squareup.picasso.Picasso
import org.jetbrains.anko.setContentView

class DetailClubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailClubUI().setContentView(this)
        val club = intent.extras.getSerializable("club") as ItemClub
        try{
            val imgClubLogo: ImageView = findViewById(R.id.iv_club_logo) as ImageView
            Picasso.get().load(club.logo).resize(300, 300).into(imgClubLogo)
            supportActionBar?.setTitle(club.name+"")
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}