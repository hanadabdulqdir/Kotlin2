package com.example.hanad.greenflagassignment1

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.hanad.greenflagassignment1.controller.RealmBackupRestore
import com.example.hanad.greenflagassignment1.controller.RealmHelper

import java.text.DateFormat
import java.util.Calendar

import io.realm.Realm

import com.example.hanad.greenflagassignment1.model.CustomerModel


class Main3Activity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    internal lateinit var editTextName: EditText
    internal lateinit var editTextUsername: EditText
    internal lateinit var editTextPassword: EditText
    internal lateinit var editTextAge: EditText
    internal lateinit var editBirthday: TextView
    internal lateinit var GengerRadioGroup: RadioGroup

    internal lateinit var realm: Realm
    internal lateinit var realmHelper: RealmHelper
    internal lateinit var realmBackupRestore: RealmBackupRestore

    internal lateinit var spinner: Spinner
    internal lateinit var adapter: ArrayAdapter<CharSequence>

    internal lateinit var btn: Button
    internal lateinit var imageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        supportActionBar!!.title = "Enter Information"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        askForPermission(Manifest.permission.CAMERA, READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE)
        askForPermission(Manifest.permission.CAMERA, WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE)
        askForPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_REQUEST_CODE)

        init()
        initRealm()
        realmBackupRestore = RealmBackupRestore(this)

        //spinner country
        spinner = findViewById<View>(R.id.spinner) as Spinner
        adapter = ArrayAdapter.createFromResource(this, R.array.Country_names, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, l: Long) {
                Toast.makeText(baseContext, parent.getItemAtPosition(position).toString() + "Selected", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }
        //end

        //Datepicker
        btn = findViewById<View>(R.id.btnImageSearch) as Button
        imageView = findViewById<View>(R.id.uploadImage) as ImageView

        val button = findViewById<View>(R.id.btnBirthday) as Button
        button.setOnClickListener {
            val datePicker = DatePickerFragment()
            datePicker.show(supportFragmentManager, "date picker")
        }
        //end

        GengerRadioGroup = findViewById(R.id.firstGroup)
        GengerRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            onRadioButtonClicked(checkedId)
            //Toast.makeText(Main3Activity.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    fun onRadioButtonClicked(checkedId: Int) {
        // Is the button now checked?
        //boolean checked = ((RadioButton) view).isChecked();
        val checked = true

        // Check which radio button was clicked
        when (checkedId) {
            R.id.radioButton -> if (checked)
                Toast.makeText(this, "Selected Male ", Toast.LENGTH_SHORT).show()
            R.id.radioButton2 -> if (checked)
                Toast.makeText(this, "Selected Female ", Toast.LENGTH_SHORT).show()
            R.id.radioButton3 -> if (checked)
                Toast.makeText(this, "Not Specified ", Toast.LENGTH_SHORT).show()
        }
        //        String currentSelected = onRadioButtonClicked(checkedId);
        //        TextView currentSelceted = (TextView)findViewById(R.id.firstGroup);
        //        currentSelceted.setText(currentSelected);
        //        return currentSelected;
    }

    override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_WEEK, day)
        val currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.time)

        val textView = findViewById<View>(R.id.txtBirthday) as TextView
        textView.text = currentDateString
    }

    fun onClick(view: View) {
        openCamera()

        // onRadioButtonClicked(view);
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        if (grantResults.size > 0) {

            when (requestCode) {
                WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE -> {
                }
                READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE -> {
                }
                CAMERA_PERMISSION_REQUEST_CODE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                }
            }//read from I/O
            //open I/O
            // permission was granted, yay! Do the
            // contacts-related task you need to do.
        } else {

            Toast.makeText(this, "Failed to permit camera access.", Toast.LENGTH_SHORT).show()

            // permission denied, boo! Disable the
            // functionality that depends on this permission.
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val photo = data.extras!!.get("data") as Bitmap
            imageView.setImageBitmap(photo)
        }
    }


    private fun askForPermission(permission: String, requestCode: Int?): Boolean {
        if (ContextCompat.checkSelfPermission(this@Main3Activity, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@Main3Activity, permission)) {
                Log.i("First check", "Banana")
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(this@Main3Activity, arrayOf(permission), requestCode!!)
                Log.i("Second check", "Banana")
            } else {

                ActivityCompat.requestPermissions(this@Main3Activity, arrayOf(permission), requestCode!!)
                Log.i("Thrid check", "Banana")
            }
            return false
        }
        return true
    }

    @SuppressLint("WrongViewCast")
    fun init() {
        editTextName = findViewById(R.id.name)
        editTextUsername = findViewById(R.id.Username)
        editTextPassword = findViewById(R.id.password)
        editTextAge = findViewById(R.id.age)
        editBirthday = findViewById(R.id.txtBirthday)
        imageView = findViewById(R.id.uploadImage)
        spinner = findViewById(R.id.mCountry)
        GengerRadioGroup = findViewById(R.id.mRadio)

    }

    fun initRealm() {
        realm = Realm.getDefaultInstance()
        //realmHelper = RealmHelper(realm)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun SaveData(view: View) {
        val customerModel = CustomerModel(
                editTextName.text.toString(), editTextUsername.text.toString(),
                editTextPassword.text.toString(), editTextAge.text.toString(),
                editBirthday.text.toString(), spinner.selectedItem.toString(),
                imageView.drawable.toString(), " dummy gender"
        )

        realmHelper.saveCustomer(customerModel)
        realmBackupRestore.backup()

        val intent = Intent(this@Main3Activity, RecyclerCustomer::class.java)//DisplayContent
        startActivity(intent)
    }

    companion object {
        private val CAMERA_REQUEST = 123
        private val CAMERA_PERMISSION_REQUEST_CODE = 0x5
        private val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 0x2
        private val WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 0x3
    }

}