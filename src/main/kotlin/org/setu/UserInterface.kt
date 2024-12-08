interface UserInterface {
    fun displayWelcomeScreen()
    fun handleOptionSelection(option: Int)
}

class MainUserInterface : UserInterface {
    override fun displayWelcomeScreen() {
        println("Welcome to Insure4Sure!")
        println("Please select an option:")
        println("1. Customer")
        println("2. User")


        val option = readLine()?.toIntOrNull() ?: 0
        handleOptionSelection(option)
    }

    override fun handleOptionSelection(option: Int) {
        when (option) {
            1 -> showCustomerScreen()
            2 -> showUserScreen()
            3 -> showTechScreen()
            else -> println("Invalid option. Please try again.")
        }
    }

    private fun showCustomerScreen() {
        println("Welcome to Insure4Sure!, please choose an option! (app in beta)")
        println("")
        println("1. Retrieve a Quote")
        println("2. Search Quotes by phone number")
        println("3. Contact Us!")
        println("4. Retrieve Letter of Denial")
        println("5. Report bugs")
        println("0. Go Back")

    }




    private fun showUserScreen() {
        println("User screen")

    }

    private fun showTechScreen() {
        print("Enter password: ")
        val password = readLine() ?: ""

        if (password == "999") {
            println("Access granted!")
            showTechOptionsScreen()
        } else {
            println("Access denied.")
        }
    }

    private fun showTechOptionsScreen() {
        println("Select an option:")
        println("1. Add Tech User")
        println("2. Remove Tech User")

        val option = readLine()?.toIntOrNull() ?: 0

        when (option) {
            1 -> println("Add Tech User")
            2 -> println("Remove Tech User")
            else -> println("Invalid option. Please try again.")
        }
    }
}