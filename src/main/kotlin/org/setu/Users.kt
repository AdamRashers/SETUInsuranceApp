package org.setu

abstract class Users(
    val name: String,
    val email: String
    val phone: String,
    val county: String,
) {
    abstract override fun toString(): String
}

