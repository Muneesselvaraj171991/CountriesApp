package com.countries.details.view

import CheckInternetConnection
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.countries.details.R
import com.countries.details.model.Country
import com.countries.details.view.adapter.CountryAdapter
import com.countries.details.viewmodel.CountryViewModel
import com.google.android.material.snackbar.Snackbar

class CountryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as Context
        val networkConnection = CheckInternetConnection(activity)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_circular)
        val model = ViewModelProvider(this).get(CountryViewModel::class.java)
        val textviewCheckConnection = view.findViewById<TextView>(R.id.textview_checkConnection)
        networkConnection.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                textviewCheckConnection.visibility = View.GONE
                model.init()
                val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
                model.countryLiveData.observe(viewLifecycleOwner) { countryList: List<Country> ->
                    if (countryList.isNotEmpty()) {
                        val adapter =
                            CountryAdapter(countryList, object : CountryAdapter.ItemClick {
                                override fun onClick(country: Country) {
                                    val countryDetails =
                                        CountryFragmentDirections.actionCountryFragmentToDetailFragment(
                                            country
                                        )
                                    findNavController(view).navigate(countryDetails)
                                }
                            })
                        recyclerView.adapter = adapter
                    } else {
                        val snack =
                            Snackbar.make(view, "Unable to fetch countryList", Snackbar.LENGTH_LONG)
                        snack.show()
                    }
                    progressBar.visibility = View.GONE

                }
                recyclerView.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )

            } else {
                progressBar.visibility = View.GONE

                textviewCheckConnection.visibility = View.VISIBLE
            }

        }

    }
}