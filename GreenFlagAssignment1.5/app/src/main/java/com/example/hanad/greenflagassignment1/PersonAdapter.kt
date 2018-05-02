package com.example.hanad.greenflagassignment1

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import com.example.hanad.greenflagassignment1.model.CustomerModel
import com.squareup.picasso.Picasso

import java.util.ArrayList

/**
 * Created by hanad on 05/02/2018.
 *
 * Steps to follow:
 * 1. initialize the objects in the constructor
 * 2. Create Innter ViewHolder class
 * 2.1. MyViewHolder extend RecyclerView.ViewHolder
 */

internal class PersonAdapter(private val applicationContext: Context, private val row_customer: Int, private val arrayList: ArrayList<CustomerModel>) : RecyclerView.Adapter<PersonAdapter.MyViewHolder>() {

    /**
     * Load the row layout
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(row_customer, parent, false))

    }

    /**
     * Load data into individual UI
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mName.text = arrayList[position].name
        holder.mUsername.text = arrayList[position].username
        holder.mPassword.text = arrayList[position].password
        holder.mAge.text = arrayList[position].age
        holder.mBirthday.text = arrayList[position].birthday
        holder.mCountry.text = arrayList[position].country
        holder.mRadio.text = arrayList[position].radio

        Picasso.with(applicationContext)
                .load(arrayList[position].image)
                .resize(100, 100)
                .centerCrop()
                .into(holder.mImageView)

        holder.callItemClick(ItemClickListener { view, position ->
            Toast.makeText(applicationContext, arrayList[position].name, Toast.LENGTH_LONG).show()
            Toast.makeText(applicationContext, arrayList[position].username, Toast.LENGTH_LONG).show()
            Toast.makeText(applicationContext, arrayList[position].password, Toast.LENGTH_LONG).show()
            Toast.makeText(applicationContext, arrayList[position].age, Toast.LENGTH_LONG).show()
            Toast.makeText(applicationContext, arrayList[position].birthday, Toast.LENGTH_LONG).show()
            Toast.makeText(applicationContext, arrayList[position].radio, Toast.LENGTH_LONG).show()
        })
    }

    override fun getItemCount(): Int {
        return arrayList.size

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal val mName: TextView
        internal val mUsername: TextView
        internal val mPassword: TextView
        internal val mAge: TextView
        internal val mBirthday: TextView
        internal val mImageView: ImageView
        internal val mCountry: TextView
        internal val mRadio: TextView


        private var itemClickListener: ItemClickListener? = null

        init {
            mName = itemView.findViewById(R.id.mName)
            mUsername = itemView.findViewById(R.id.mUsername)
            mPassword = itemView.findViewById(R.id.mPassword)
            mAge = itemView.findViewById(R.id.mAge)
            mBirthday = itemView.findViewById(R.id.mBirthday)
            mImageView = itemView.findViewById(R.id.mImageView)
            mCountry = itemView.findViewById(R.id.mCountry)
            mRadio = itemView.findViewById(R.id.mRadio)




            itemView.setOnClickListener(this)
        }

        fun callItemClick(itemClickListener: ItemClickListener) {
            this.itemClickListener = itemClickListener
        }

        override fun onClick(view: View) {
            itemClickListener!!.OnClick(view, position)
        }
    }
}
