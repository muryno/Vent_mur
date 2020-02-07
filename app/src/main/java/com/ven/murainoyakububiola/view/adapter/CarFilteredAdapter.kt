package com.ven.murainoyakububiola.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ven.murainoyakububiola.R
import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.view.base.BaseViewHolder
import kotlinx.android.synthetic.main.layout_filter.view.txt_country
import kotlinx.android.synthetic.main.layout_filter.view.txt_gender
import kotlinx.android.synthetic.main.layout_filter_details.view.*


class CarFilteredAdapter() :
    RecyclerView.Adapter<BaseViewHolder>() {
    var mSecuritiesList: ArrayList<Car>  = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> SecurityViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_filter_details,
                    parent,
                    false
                )
            )
            VIEW_TYPE_EMPTY -> EmptyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_empty_view,
                    parent,
                    false
                )
            )
            else -> EmptyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_empty_view,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (mSecuritiesList.size > 0) {
            VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_EMPTY
        }
    }

    override fun getItemCount(): Int {
        return if (mSecuritiesList.size > 0) {
            mSecuritiesList.size
        } else {
            1
        }
    }

    fun addItems(securitiesList: ArrayList<Car>) {
        mSecuritiesList.addAll(securitiesList)
        notifyDataSetChanged()
    }





    inner class SecurityViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        val txt_fullname: TextView = itemView.txt_fullname
        val txt_email : TextView = itemView.txt_email
        val txt_job_title: TextView = itemView.txt_job_title
        val txt_model_car : TextView = itemView.txt_model_car
        val txt_model_year = itemView.txt_model_year

        val txt_model_color  = itemView.txt_model_color


        val txt_bio = itemView.txt_bio

        val txt_country  = itemView.txt_country

        val txt_gender = itemView.txt_gender

        override fun clear() {
        }


        override fun onBind(position: Int) {
            super.onBind(position)
            val securities: Car? = mSecuritiesList[position]

            txt_fullname.text = securities?.fullname()
            txt_email.text = securities?.email
            txt_job_title.text = securities?.job_title

            txt_model_car.text = securities?.car_model
            txt_model_year.text = securities?.car_model_year
            txt_model_color.text = securities?.car_color

            txt_bio.text = securities?.bio
            txt_country.text = securities?.country
            txt_gender.text = securities?.gender





        }


    }

    private inner class EmptyViewHolder(itemView: View?) :
        BaseViewHolder(itemView) {
        override fun clear() {}
    }

    companion object {
        const val VIEW_TYPE_EMPTY = 0
        const val VIEW_TYPE_NORMAL = 1
    }


}