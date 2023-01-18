package com.countries.details.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.countries.details.R
import com.countries.details.databinding.FragmentDetailBinding
import com.countries.details.model.Country

class DetailFragment : Fragment() {
    private var mcountry : Country? = null
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater, R.layout.fragment_detail, container, false)
        dataBinding.fragment=this

        dataBinding.country = args.country
        mcountry = dataBinding.country
        return dataBinding.root
    }
    fun loadMap() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mcountry!!.maps!!.googleMaps))
        startActivity(intent)
    }

}