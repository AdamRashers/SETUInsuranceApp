package org.setu

class Customers(
    name: String,
    val email: String,
    val phone: String
) {


    override fun toString(): String {
        return """
            Email: $email
            Phone: $phone
        """.trimIndent()
    }
}