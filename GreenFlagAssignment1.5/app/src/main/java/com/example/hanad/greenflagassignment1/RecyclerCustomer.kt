package com.example.hanad.greenflagassignment1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.hanad.greenflagassignment1.controller.RealmHelper
//import com.example.hanad.greenflagassignment1.model.AccountCustomerModel;
import com.example.hanad.greenflagassignment1.model.CustomerModel

import java.util.ArrayList

import io.realm.Realm

class RecyclerCustomer : AppCompatActivity() {
    private var realm: Realm? = null
    private val realm2: Realm? = null
    private var realmHelper: RealmHelper? = null
    private var arrayList: ArrayList<CustomerModel>? = null
    //private ArrayList<AccountCustomerModel> customerModelArrayList;
    internal var stringArrayList: ArrayList<String>? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_customer)

        initRealm()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.rvCustomer)
        //how should i display the items

        recyclerView!!.layoutManager = LinearLayoutManager(this)
        //recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView!!.adapter = PersonAdapter(applicationContext, R.layout.row_customer, arrayList!!)
        //recyclerView.setAdapter(new AccountAdapter(getApplicationContext(),R.layout.row_customer,customerModelArrayList));

    }

    fun initRealm() {
        realm = Realm.getDefaultInstance()
        //realmHelper = RealmHelper(realm)

        arrayList = ArrayList()
        arrayList = realmHelper!!.customerModels

        // realm2= Realm.getDefaultInstance();
        //  realmHelper = new RealmHelper(realm2);
        //customerModelArrayList = new ArrayList<>();
        //customerModelArrayList= realmHelper.getAccountCustomerModels();

    }
}
