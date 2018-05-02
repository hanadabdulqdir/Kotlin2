package com.example.hanad.greenflagassignment1.model

import io.realm.RealmObject

/**
 * Created by hanad on 02/02/2018.
 * 1. Extend realm object
 * 2. create an empty contructor & Other Construstor
 * 3. Getters and Setters
 * 4. classname is TAble and Varaibles are columns
 * 5.Annotations and objects
 */

class CustomerModel : RealmObject {
    lateinit var name: String
    lateinit var username: String
    lateinit var password: String
    lateinit var age: String
    lateinit var birthday: String
    lateinit var country: String
    lateinit var image: String
    lateinit var radio: String

    constructor() {

    }

    constructor(name: String, username: String, password: String, age: String, birthday: String, country: String, image: String, radio: String) {
        this.name = name
        this.username = username
        this.age = age
        this.birthday = birthday
        this.password = password
        this.country = country
        this.image = image
        this.radio = radio
    }

}