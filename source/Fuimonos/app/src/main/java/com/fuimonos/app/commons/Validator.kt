package com.fuimonos.app.commons

import androidx.lifecycle.LiveData

object Validator {

    fun <T, E> validate(liveData: LiveData<T>, init: Validation<T, E>.() -> Unit): List<E> {
        val builder = Validation<T, E>()
        builder.apply(init)
        return validateWith(builder.validables, liveData)
    }

    private fun <T, E> validateWith(validables: List<Validable<T?, E>>, liveData: LiveData<T>): List<E> {

        val validationEnums = mutableListOf<E>()

        for (validable in validables) {
            if(validable.validate(liveData.value)) {
                validationEnums.add(validable.validationEnum)
                break
            }
        }

        return validationEnums
    }

}

class Validation<T, E> {

    var validables = mutableListOf<Validable<T?, E>>()

    fun checkIf(validate: T?.() -> Boolean) = validate

    infix fun (T?.() -> Boolean).set(valiadtionEnum: E) {
        validables.add(Validable(this, valiadtionEnum))
    }

}

class Validable<T, E>(val validate: T?.() -> Boolean, val validationEnum: E)
