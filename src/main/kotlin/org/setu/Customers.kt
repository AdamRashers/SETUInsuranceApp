package org.setu

class Customers(
    val name: String,
    val email: String,
    val phone: String
) {
    override fun toString(): String {
        return """
            Name: $name
            Email: $email
            Phone: $phone
        """.trimIndent()
    }
}