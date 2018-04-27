//package com.example.hanad.greenflagassignment1.model
//
//import android.app.ListActivity
//import android.os.Bundle
//import android.widget.ArrayAdapter
//import com.example.hanad.greenflagassignment1.controller.RealmHelper
//
//import java.util.ArrayList
//
//import io.realm.Realm
//
//class Main2Activity : ListActivity() {
//    private var realm: Realm? = null
//    private var realmHelper: RealmHelper? = null
//    private var arrayList: ArrayList<CustomerModel>? = null
//    internal var stringArrayList: ArrayList<String>? = null
//    //private ArrayList<AccountCustomerModel> accountArrayList;
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //  setContentView(R.layout.activity_main2);
//
//        realm = Realm.getDefaultInstance()
//        realmHelper = RealmHelper(realm)
//        arrayList = ArrayList()
//        arrayList = realmHelper!!.customerModels
//        stringArrayList = ArrayList()
//        for (i in arrayList!!.indices) {
//
//            stringArrayList!!.add(arrayList!![i].getName())
//            stringArrayList!!.add(arrayList!![i].getUsername())
//            stringArrayList!!.add(arrayList!![i].getPassword())
//            stringArrayList!!.add(arrayList!![i].getAge())
//            stringArrayList!!.add(arrayList!![i].getBirthday())
//            stringArrayList!!.add(arrayList!![i].getCountry())
//            stringArrayList!!.add(arrayList!![i].getImage())
//            stringArrayList!!.add(arrayList!![i].getRadio())
//        }
//
//        /**
//         * Get Model
//         * Put the model in the Adapter
//         * Assign the adapter to the view
//         */
//
//        val arrayAdapter = ArrayAdapter(
//                this@Main2Activity,
//                android.R.layout.simple_list_item_1,
//                stringArrayList)
//        listView.adapter = arrayAdapter
//
//    }
//
//}