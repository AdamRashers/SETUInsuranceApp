import java.io.File

interface UserInterface {
    fun displayWelcomeScreen()
    fun handleOptionSelection(option: Int)
}

class MainUserInterface : UserInterface {
    override fun displayWelcomeScreen() {
        println("Welcome to Insure4Sure!")
        println("Please select an option:")
        println("1. Customer Shop Around!")
        println("2. User Login")
        println("3. Broker Login")


        val option = readLine()?.toIntOrNull() ?: 0
        handleOptionSelection(option)
    }

    override fun handleOptionSelection(option: Int) {
        when (option) {
            1 -> showCustomerScreen()
            2 -> showUserScreen()
            3 -> showBrokerScreen()
            4 -> showTechScreen()
            else -> println("Invalid option. Please try again.")
        }
    }


    private fun showCustomerScreen() {
        println("Welcome to Insure4Sure!, please choose an option! (app in beta)")
        println("")
        println("1. Get a Quote")
        println("2. Search Quotes by phone number")
        println("3. Contact Us!")
        println("4. Retrieve Letter of Denial")
        println("5. Report bugs")
        println("0. Go Back")

        val option = readLine()?.toIntOrNull() ?: 0

        when (option) {
            1 -> getQuote()
            2 -> searchQuotesByPhoneNumber()
            3 -> contactUs()
            4 -> retrieveDenialLetter()
            5 -> reportBug()
            0 -> return // Go back to previous screen
            else -> println("Invalid option. Please try again.")
        }
    }

    private fun getQuote() {
        println("Let's get you a quote! Please answer the following questions:")
        getQuotes() // Call the main function from your quiz
    }


    private fun searchQuotesByPhoneNumber() {
        println("Enter the phone number to search:")
        val searchPhoneNumber = readLine()?.toString() ?: ""

        val file = File("quote_details.txt")
        val lines = file.readLines()

        for (line in lines) {
            val parts = line.split("\t")
            if (parts.size >= 2 && parts[1] == searchPhoneNumber) {
                println("Found quote for phone number $searchPhoneNumber:")
                println("Insurance Cost: ${parts[0]}")
                showCustomerScreen()
            }
        }
    }


    private fun contactUs() {
        println("Insure4Sure - Ways to contact")
        println("")
        println("help@insure4sure.ie")
        println("1800780780")
        showCustomerScreen()
    }

    private fun retrieveDenialLetter() {
        println("Please enter your name:")
        val name = readLine() ?: ""

        println("Please enter your email address:")
        val email = readLine() ?: ""

        val file = File("denials.txt")
        file.appendText("Name: $name\nEmail: $email\n\n")

        println("Denial letter request submitted. Awaiting broker approval.")

        showCustomerScreen()
    }

    private fun reportBug() {
        println("Please describe any bugs you encountered")
        val bugDescription = readLine() ?: ""

        // Write bug report to a file
        val file = File("bugs.txt")
        file.appendText("Bug Report:\n$bugDescription\n\n")

        println("Bug report saved to bugs.txt. Thank you for making Insure4Sure a safer and better place!!!!")

        showCustomerScreen()
    }


    private fun showBrokerScreen() {
        println("Welcome to Insure4Sure!, please choose an option! (app in beta)")
        println("")
        println("1. View Clients")
        println("2. View pending policy changes")
        println("3. Check your bank")
        println("4. Send letters of denial on behalf of Insure4Sure")
        println("0. Go Back")

        val option = readLine()?.toIntOrNull() ?: 0

        when (option) {
            1 -> viewClients()
            2 -> viewPendingPolicyChanges()
            3 -> checkBank()
            4 -> sendDenialLetters()
            0 -> return
            else -> println("Invalid option.")
        }
    }

    private fun viewClients() {
        println("Viewing clients...")
    }

    private fun viewPendingPolicyChanges() {
        val file = File("quote_details.txt")
        val lines = file.readLines()

        if (lines.isEmpty()) {
            println("No pending policy changes found.")
            return
        }

        println("Pending Policy Changes:")
        for (line in lines) {
            val parts = line.split("\t")
            if (parts.size >= 2) {
                val insuranceCost = parts[0]
                val phoneNumber = parts[1]
                println("Insurance Cost: $insuranceCost, Phone Number: $phoneNumber")
                println("")
                println("Call back^^^^^^")
                showBrokerScreen()
            }
        }
    }

    private fun checkBank() {
        println("Next payment: Thursday")
        showBrokerScreen()
    }

    private fun sendDenialLetters() {
        val file = File("denials.txt")
        val lines = file.readLines()

        val names = mutableListOf<String>()

        for (line in lines) {
            val parts = line.split(":")
            if (parts.size >= 2) {
                val name = parts[1].trim()
                names.add(name)
            }
        }

        if (names.isEmpty()) {
            println("No denial letters to send.")
            return
        }

        println("Denial letters sent to:")
        names.forEach { name ->
            println("- $name")
        }
    }


    private fun showUserScreen() {
        println("Welcome to Insure4Sure!, please choose an option! (app in beta)")
        println("")
        println("1. View policy") //broker will sell policys
        println("2. Change policy") //will send a notification to the broker for call back
        println("3. Contact Us!")
        println("4. View Rewards")
        println("0. Go Back")

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
        println("3. Delete details by number")

        val option = readLine()?.toIntOrNull() ?: 0

        when (option) {
            1 -> addTechUser()
            2 -> removeTechUser()
            3 -> deleteDetailsByNumber()
            else -> println("Invalid option. Please try again.")
        }
    }

    private fun addTechUser() {
        println("Enter the name of the tech user:")
        val name = readLine() ?: ""

        println("Enter the phone number of the tech user:")
        val phoneNumber = readLine() ?: ""

        val file = File("tech_users.txt")
        file.appendText("$name\t$phoneNumber\n")

        println("Tech user added successfully.")
    }

    private fun removeTechUser() {
        println("Enter the phone number of the tech user to remove:")
        val phoneNumberToRemove = readLine() ?: ""

        val file = File("tech_users.txt")
        val lines = file.readLines()
        val newLines = mutableListOf<String>()

        for (line in lines) {
            val parts = line.split("\t")
            if (parts.size >= 2 && parts[1] != phoneNumberToRemove) {
                newLines.add(line)
            }
        }

        file.writeText("") // Clear the file
        newLines.forEach { line ->
            file.appendText("$line\n")
        }

        println("Tech user removed successfully.")
    }

    private fun deleteDetailsByNumber() {
        println("Enter the phone number to delete details for:")
        val phoneNumberToDelete = readLine() ?: ""

        val file = File("quote_details.txt")
        val lines = file.readLines()
        val newLines = mutableListOf<String>()

        for (line in lines) {
            val parts = line.split("\t")
            if (parts.size >= 2 && parts[1] != phoneNumberToDelete) {
                newLines.add(line)
            }
        }

        file.writeText("") // Clear the file
        newLines.forEach { line ->
            file.appendText("$line\n")
        }

        println("Details deleted successfully.")
    }
}
