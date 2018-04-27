package com.example.hanad.greenflagassignment1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.hanad.greenflagassignment1.controller.RealmBackupRestore
import com.example.hanad.greenflagassignment1.controller.RealmHelper

import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.Length
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Password

import io.realm.Realm//import com.example.hanad.greenflagassignment1.model.AccountCustomerModel;

class Main2Activity : AppCompatActivity(), Validator.ValidationListener {

    @Email
    @NotEmpty(message = "Requires email")
    @Length(min = 5, max = 15, message = "Must be greater then 5 & less then 15 characters")
    internal lateinit var email: EditText

    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS, message = "Password required min 6 characters")
    internal lateinit var password: EditText

    @ConfirmPassword
    internal lateinit var repeatPassword: EditText
    internal var button: Button? = null

    internal lateinit var validator: Validator

    internal lateinit var realm: Realm
    internal lateinit var realmHelper: RealmHelper
    internal lateinit var realmBackupRestore: RealmBackupRestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)

        supportActionBar!!.title = "Create an account"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        email = findViewById(R.id.mEmail)
        password = findViewById(R.id.password)
        repeatPassword = findViewById(R.id.repeatePassword)

        validator = Validator(this)
        validator.setValidationListener(this)

        //init();
        initRealm()
        realmBackupRestore = RealmBackupRestore(this)

    }

    //@RequiresApi(api = Build.VERSION_CODES.O)
    fun onValidate(view: View) {//onClick
        //validator.validate(true);
        Log.i("Test", "OnClick validation")
        val myIntent = Intent(this, Main3Activity::class.java)
        startActivity(myIntent)

        //         Button button = (Button)findViewById(R.id.button);
        //         button.setOnClickListener(new View.OnClickListener() {
        //             @Override
        //             public void onClick(View v) {
        //                 validator.validate(true);
        //
        //             }
        //         });
    }

    override fun onValidationSucceeded() {
        Toast.makeText(this, "Correct details - Validated", Toast.LENGTH_SHORT).show()

    }

    override fun onValidationFailed(errors: List<ValidationError>) {
        for (error in errors) {
            val view = error.view
            val message = error.getCollatedErrorMessage(this)

            // Display error messages ;)
            if (view is EditText) {
                view.error = message
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }


    fun initRealm() {
        realm = Realm.getDefaultInstance()
        //realmHelper = RealmHelper(realm)
    }
}

// @SuppressLint("WrongViewCast")
//public void init() {

//        EditText email = (EditText)findViewById(R.id.mEmail);
//        if( email.getText().toString().length() == 0 )
//            email.setError( "Email is required!" );

//        email = findViewById(R.id.mEmail);
//        repeatPassword = findViewById(R.id.mRepeatPassword);
//}


//    private void intent() {
//        Intent myIntent = new Intent(this, Main3Activity.class);//DisplayContent
//        startActivity(myIntent);
//    }
//
//    button.setOnClickListener(new OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            validator.validate();
//        }
//    });

// realmHelper.saveAccountCustomer(accountCustomerModel);
//realmBackupRestore.backup();


//    public void onClick(View view) {
//        Intent myIntent = new Intent(this, Main3Activity.class);
//        startActivity(myIntent);
//    }

