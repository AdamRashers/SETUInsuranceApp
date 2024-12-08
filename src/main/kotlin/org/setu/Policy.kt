package org.setu

data class Policy(
    val car: String,
    val user: User,
    val price: Double,
    val policyNumber: Int?
) {
    val monthlyCost: Double = price / 12

    override fun toString(): String {
        return "Car: $car\nName: ${user.name}\nEmail: ${user.email}\nPhone: ${user.phone}\nCounty: ${user.county}\nAnnual Price: $price\nMonthly Cost: $monthlyCost\nPolicy Number: $policyNumber\n\n"
    }
}

data class User(
    val name: String,
    val email: String,
    val phone: String,
    val county: String
)