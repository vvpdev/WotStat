package com.vvp.wotstat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vvp.wotstat.R
import com.vvp.wotstat.model.Player
import com.vvp.wotstat.presenters.DetailsPresenter
import com.vvp.wotstat.views.DetailsView
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : MvpAppCompatFragment(), DetailsView {


    @InjectPresenter
    lateinit var presenter: DetailsPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedPlayer: Player? = arguments!!.getParcelable("selectedPlayer")

        activity!!.title = selectedPlayer!!.nickname




    }






}
