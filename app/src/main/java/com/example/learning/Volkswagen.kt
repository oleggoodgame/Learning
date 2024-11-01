package com.example.learning

import android.util.Log
//https://medium.com/@appdevinsights/companion-object-in-kotlin-c3a1203cd63c
//https://medium.com/@riztech.dev/understanding-companion-objects-in-kotlin-a93f1a5880a7
//https://medium.com/@domen.lanisnik/data-objects-in-kotlin-1a549bfad657
//https://medium.com/@shelar.akshata/objects-in-kotlin-aeae831ce21a
open class Vehicle(val name: String) {
    open fun drive() {
        Log.d("VEHICLE", "$name is starting to drive")
    }

    open fun availableBrands(): List<String> {
        return listOf(name) // Повертає лише базову назву як бренд за замовчуванням
    }
}

open class Car(name: String) : Vehicle(name) {
    override fun availableBrands(): List<String> {
        return listOf("Volkswagen", "Toyota", "Honda")
    }
    override fun drive() {
        Log.d("CAR", "$name is driving")
    }
}

open class Plane(name: String) : Vehicle(name) {
    override fun availableBrands(): List<String> {
        return listOf("Boeing", "Airbus")
    }
    override fun drive() {
        Log.d("PLANE", "$name is driving")
    }
}

open class Ship(name: String) : Vehicle(name) {
    override fun availableBrands(): List<String> {
        return listOf("Titanic", "Queen Mary 2")
    }
    override fun drive() {
        Log.d("SHIP", "$name is driving")
    }
}
