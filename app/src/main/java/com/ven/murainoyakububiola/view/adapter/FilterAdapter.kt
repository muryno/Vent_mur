package com.ven.murainoyakububiola.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ven.murainoyakububiola.R
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.view.base.BaseViewHolder
import com.ven.murainoyakububiola.view.base.CustomItemClickListener
import kotlinx.android.synthetic.main.layout_filter.view.*


class FilterAdapter(private val listener: CustomItemClickListener<FilterEntity>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    var mSecuritiesList: ArrayList<FilterEntity>  = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> SecurityViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_filter,
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
        return if (mSecuritiesList?.size!! > 0) {
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

    fun addItems(securitiesList: List<FilterEntity>) {
        mSecuritiesList.addAll(securitiesList)
        notifyDataSetChanged()
    }





    inner class SecurityViewHolder(itemView: View) :
        BaseViewHolder(itemView) {
        val txt_start: TextView = itemView.txt_start
        val txt_stop : TextView = itemView.txt_stop
        val txt_color: TextView = itemView.txt_color
        val txt_country : TextView = itemView.txt_country
        val txt_gender = itemView.txt_gender
        //gender
        val img_view : ImageView = itemView.img_view


        //color
        val color_view: LinearLayout = itemView.color_view
        val first_view : View = itemView.first_view

        //country
        val country_view: LinearLayout = itemView.country_view
        val sec_view : View = itemView.sec_view

        override fun clear() {
        }


        override fun onBind(position: Int) {
            super.onBind(position)
            val securities: FilterEntity? = mSecuritiesList?.get(position)



            //hide view if empty
            if( securities?.gender.isNullOrEmpty()){
                txt_gender.visibility= View.GONE
                img_view.visibility= View.GONE
            }
            if( securities?.colors.isNullOrEmpty()){
                color_view.visibility= View.GONE
                first_view.visibility= View.GONE
            }
            if( securities?.countries.isNullOrEmpty()){
                country_view.visibility= View.GONE
                sec_view.visibility= View.GONE
            }


            // kotlin handle nullable
            txt_start.text = securities?.startYear?.toString()
            txt_stop.text = securities?.endYear?.toString()
            txt_gender.text = securities?.gender
            txt_color.text = securities?.colors.toString()
            txt_country.text = securities?.countries.toString()


            //onclick on view
            itemView.setOnClickListener { securities?.let { it1 -> listener.onItemClick(it1) } }


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