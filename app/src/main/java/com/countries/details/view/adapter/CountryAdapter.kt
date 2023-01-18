package com.countries.details.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.countries.details.databinding.ItemBinding
import com.countries.details.model.Country


class CountryAdapter(private val mCountryList: List<Country>,
                              private val mItemClickListener: ItemClick)  : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.country = mCountryList.get(position)
        holder.binding.listener = mItemClickListener

    }
    override fun getItemCount(): Int {
        return mCountryList.size
}
    interface ItemClick {
        fun onClick(country: Country)
    }
}