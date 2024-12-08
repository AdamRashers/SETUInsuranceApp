data class Tech(
    val name: String,
    val email: String,
    val phone: String,
    val county: String,
    val techLevel: String
) {
    override fun toString(): String {
        return "$name\t$email\t$phone\t$county\t$techLevel"
    }
}