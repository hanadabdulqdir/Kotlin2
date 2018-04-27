package com.example.hanad.greenflagassignment1

import android.app.ListActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.hanad.greenflagassignment1.controller.RealmHelper

import java.util.ArrayList

import io.realm.Realm

//import com.example.hanad.greenflagassignment1.model.AccountCustomerModel;
import com.example.hanad.greenflagassignment1.model.CustomerModel

class DisplayContent : ListActivity() {
    private var realm: Realm? = null
    private var realmHelper: RealmHelper? = null

    private var arrayList: ArrayList<CustomerModel>? = null

    //private ArrayList<AccountCustomerModel> customerModelArrayList;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_recycler_customer);

        realm = Realm.getDefaultInstance()
        //realmHelper = RealmHelper(this)
        arrayList = ArrayList()
        arrayList = realmHelper!!.customerModels

        /**
         * Get model
         * put the model in the adapter
         * assign the adapter to the view
         *
         */
        val arrayAdapter = ArrayAdapter(
                this@DisplayContent,
                android.R.layout.simple_list_item_1, arrayList!!)

        listView.adapter = arrayAdapter

        /**
         *
         */

        //        customerModelArrayList = new ArrayList<>();
        //        customerModelArrayList = realmHelper.getAccountCustomerModels();
        //
        //        ArrayAdapter<AccountCustomerModel> arrayAccountAdapter = new ArrayAdapter<AccountCustomerModel>(
        //                DisplayContent.this,
        //                android.R.layout.simple_list_item_2);
        //
        //        getListView().setAdapter(arrayAccountAdapter);


    }
}
