package com.qornanali.footballclubkt.feature.detailplayer

import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BaseActivity
import com.qornanali.footballclubkt.model.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find


class DisplayDetailPlayerActivity :
        BaseActivity<DisplayDetailPlayerAPresenter, DisplayDetailPlayerAView>(),
        DisplayDetailPlayerAView {

    private lateinit var toolbar: Toolbar
    private lateinit var ivPlayerCutout: ImageView
    private lateinit var ivPlayerFanart: ImageView
    private lateinit var tvPlayerName: TextView
    private lateinit var tvPlayerBody: TextView
    private lateinit var tvPlayerDesc: TextView
    private lateinit var tvPlayerSigned: TextView
    private lateinit var tvPlayerTeam: TextView
    private lateinit var tvPlayerPosition: TextView
    private lateinit var tvPlayerWage: TextView
    private var player: Player? = null

    override fun attachLayout(): Int {
        return R.layout.activity_displaydetailplayer
    }

    override fun onInitializeViews() {
        player = intent.extras.getSerializable("player") as Player

        toolbar = find(R.id.toolbar)
        ivPlayerCutout = find(R.id.iv_player_cutout)
        ivPlayerFanart = find(R.id.iv_player_fanart)
        tvPlayerName = find(R.id.tv_player_name)
        tvPlayerBody = find(R.id.tv_player_body)
        tvPlayerDesc = find(R.id.tv_player_desc)
        tvPlayerSigned = find(R.id.tv_player_signed)
        tvPlayerTeam = find(R.id.tv_player_team)
        tvPlayerPosition = find(R.id.tv_player_position)
        tvPlayerWage = find(R.id.tv_player_wage)
        tvPlayerPosition = find(R.id.tv_player_position)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.loadPlayerInfo(player)
    }

    override fun attachPresenter(): DisplayDetailPlayerAPresenter {
        return DisplayDetailPlayerAPresenter(Gson(), ApiRepository(), this)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    override fun displayActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    override fun displayInfo(name: String?, height: String?, weight: String?) {
        tvPlayerName.text = name
        tvPlayerBody.text = "${resources.getString(R.string.height)} : ${height}\n${resources.getString(R.string.weight)} : ${weight}"
    }

    override fun displayTeam(name: String?, position: String?, wage: String?) {
        tvPlayerTeam.text = "${resources.getString(R.string.team)} : ${name}"
        tvPlayerWage.text = "${resources.getString(R.string.wage)} : ${wage}"
        tvPlayerPosition.text = "${resources.getString(R.string.position)} : ${position}"
    }

    override fun displaySigned(signingDate: String?, signingPrice: String?) {
        tvPlayerSigned.text = "${resources.getString(R.string.signed)} : ${signingDate}, ${signingPrice}"
    }

    override fun displayDesc(description: String?) {
        tvPlayerDesc.text = description
    }

    override fun loadFanart(fanart: String?) {
        Picasso.get().load(fanart).resize(300, 300).into(ivPlayerFanart)
    }

    override fun loadCutout(cutout: String?) {
        Picasso.get().load(cutout).resize(300, 300).into(ivPlayerCutout)
    }
}
