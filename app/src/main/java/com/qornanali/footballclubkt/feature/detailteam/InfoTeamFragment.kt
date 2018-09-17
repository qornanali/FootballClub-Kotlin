package com.qornanali.footballclubkt.feature.detailteam

import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BaseFragment
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class InfoTeamFragment :
        BaseFragment<InfoTeamFPresenter, InfoTeamFView>(),
        InfoTeamFView {

    private lateinit var tvManager: TextView
    private lateinit var tvDesc: TextView
    private lateinit var tvName: TextView
    private lateinit var tvCountry: TextView
    private lateinit var tvStadium: TextView
    private lateinit var ivStadium: ImageView
    private lateinit var ivFanart1: ImageView
    private lateinit var ivFanart2: ImageView

    override fun attachLayout(): Int {
        return R.layout.fragment_infoteam
    }

    override fun onInitializeViews() {
        tvManager = rootView.find(R.id.tv_team_manager)
        tvDesc = rootView.find(R.id.tv_team_desc)
        tvName = rootView.find(R.id.tv_team_name)
        tvCountry = rootView.find(R.id.tv_team_country)
        tvStadium = rootView.find(R.id.tv_team_stadium)
        ivStadium = rootView.find(R.id.iv_team_stadium)
        ivFanart1 = rootView.find(R.id.iv_team_fanart_1)
        ivFanart2 = rootView.find(R.id.iv_team_fanart_2)

        presenter.loadTeamInfo((activity as DisplayDetailTeamActivity).team)
    }

    override fun attachPresenter(): InfoTeamFPresenter {
        return InfoTeamFPresenter(Gson(), ApiRepository(), this)
    }

    override fun displayManager(manager: String?) {
        tvManager.text = "${resources.getString(R.string.manager)} : ${manager}"
    }

    override fun displayDescription(description: String?) {
        tvDesc.text = description
    }

    override fun displayCountry(country: String?) {
        tvCountry.text = "${resources.getString(R.string.country)} : ${country}"
    }

    override fun displayName(fullName: String?, shortName: String?, alternate: String?) {
        tvName.text = "${fullName}\n${resources.getString(R.string.shortname)}/${resources.getString(R.string.alternate)} : ${shortName}/${alternate}"
    }

    override fun displayStadium(stadium: String?) {
        tvStadium.text = stadium
    }

    override fun loadStadiumImage(url: String?) {
        Picasso.get().load(url).resize(300, 300).into(ivStadium)
    }

    override fun loadFanart(fanart1Url: String?, fanart2Url: String?) {
        Picasso.get().load(fanart1Url).resize(300, 300).into(ivFanart1)
        Picasso.get().load(fanart2Url).resize(300, 300).into(ivFanart2)
    }

}