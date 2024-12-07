package org.setu

class Tech(
    name: String,
    email: String,
    phone: String,
    county: String,
    var techLevel: Int
) : Users(name, email, phone, county) {

    override fun toString(): String {
        return """
            Tech Level: $techLevel
            Name: $name
            Email: $email
            Phone: $phone
            County: $county
        """.trimIndent()
    }
}