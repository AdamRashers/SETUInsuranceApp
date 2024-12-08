import org.setu.Policy
import org.setu.User
import java.io.File

interface UserInterface {
    fun displayWelcomeScreen()
    fun handleOptionSelection(option: Int)
}

class MainUserInterface : UserInterface {
    override fun displayWelcomeScreen() {
        println("""
            **Welcome to Insure4Sure!**

            Please select an option:

            1. Customer Shop Around
            2. User Login
            3. Broker Login
        """.trimIndent())

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
        println("""
        **Welcome to Insure4Sure!**
        (App in Beta)

        Please choose an option:

        1. Get a Quote
        2. Search Quotes by Phone Number
        3. Contact Us
        4. Retrieve Letter of Denial
        5. Report a Bug
        0. Go Back
    """.trimIndent())

        val option = readLine()?.toIntOrNull() ?: 0

        when (option) {
            1 -> getQuote()
            2 -> searchQuotesByPhoneNumber()
            3 -> contactUs()
            4 -> retrieveDenialLetter()
            5 -> reportBug()
            0 -> displayWelcomeScreen()
            else -> println("Invalid option. Please try again.")
        }
    }

    private fun getQuote() {
        println("")
        println("Let's get you a quote! Please answer the following questions:")
        println("")
        getQuotes()
    }


    private fun searchQuotesByPhoneNumber() {
        println("")
        println("Enter the phone number to search:")
        println("")
        val searchPhoneNumber = readLine()?.toString() ?: ""

        val file = File("quote_details.txt")
        val lines = file.readLines()
        println("")

        var quoteFound = false
        for (line in lines) {
            val parts = line.split("\t")
            if (parts.size >= 2 && parts[1] == searchPhoneNumber) {
                quoteFound = true
                println("Found quote for phone number $searchPhoneNumber:")
                println("Insurance Cost: ${parts[0]}")
                println("")
                showCustomerScreen()
            }
        }

        if (!quoteFound) {
            println("")
            println("No quote found for the given phone number. Please try again.")
            println("")
            showCustomerScreen()
        }
    }


    private fun contactUs() {
        println("")
        println("Insure4Sure - Ways to contact")
        println("")
        println("help@insure4sure.ie")
        println("1800780780")
        println("")
        showCustomerScreen()
    }

    private fun retrieveDenialLetter() {
        println("")
        println("Please enter your name:")
        val name = readLine() ?: ""
        println("")

        println("Please enter your email address:")
        val email = readLine() ?: ""
        println("")
        val file = File("denials.txt")
        file.appendText("Name: $name\nEmail: $email\n\n")
        println("")
        println("Denial letter request submitted. Awaiting broker approval.")
        println("")
        showCustomerScreen()
    }

    private fun reportBug() {
        println("Please describe any bugs you encountered")
        val bugDescription = readLine() ?: ""

        println("")
        val file = File("bugs.txt")
        file.appendText("Bug Report:\n$bugDescription\n\n")
        println("")
        println("Bug report saved to bugs.txt. Thank you for making Insure4Sure a safer and better place!!!!")
        println("")
        showCustomerScreen()
    }


    private fun showBrokerScreen() {
        println("""
        **Welcome to Insure4Sure, Broker Portal** 
        (App in Beta)

        Please choose an option:

        1. View Clients
        2. View Pending Policy Changes
        3. Check Bank
        4. Send Denial Letters
        5. Create Insurance Policy
        0. Go Back
    """.trimIndent())

        val option = readLine()?.toIntOrNull() ?: 0

        when (option) {
            1 -> viewClients()
            2 -> viewPendingPolicyChanges()
            3 -> checkBank()
            4 -> sendDenialLetters()
            5 -> createPolicy()
            0 -> displayWelcomeScreen()
            else -> println("Invalid option.")
        }
    }

    private fun viewClients() {
        println("Displaying clients")

        val file = File("policies.txt")
        val lines = file.readLines()

        for (line in lines) {
            if (line.contains("Name: ")) {
                val name = line.substringAfter("Name: ").trim()
                println(name)
                println("")
                showBrokerScreen()
            }
        }
    }



    fun createPolicy() {
        println("Enter car model and year: ")
        val car = readLine()!!

        println("Enter full name: ")
        val name = readLine()!!

        println("Enter email address: ")
        val email = readLine()!!

        println("Enter phone number: ")
        val phone = readLine()!!

        println("Enter county: ")
        val county = readLine()!!

        println("Enter annual insurance price: ")
        val price = readLine()!!.toDouble()

        println("Enter a policy number: ")
        val policyNumber = readLine()?.toIntOrNull()

        val user = User(name, email, phone, county)
        val policy = Policy(car, user, price, policyNumber)

        val file = File("policies.txt")
        file.appendText(policy.toString() + "\n")

        println("Policy added to system and email sent to $email")
        println("")
        showBrokerScreen()
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
        println("""
        **Welcome to Insure4Sure!** 
        (App in Beta)

        Please choose an option:

        1. View Policy
        2. Change Policy
        3. Contact Us
        4. View Rewards
        0. Go Back
    """.trimIndent())

        val choice = readLine()?.toIntOrNull() ?: 0

        when (choice) {
            1 -> viewPolicy()
            2 -> changePolicy()
            3 -> contactUs()
            4 -> viewRewards()
            0 -> displayWelcomeScreen()
            else -> println("Invalid choice")
        }
    }

    private fun viewPolicy() {
        println("Enter policy number to view:")
        val policyNumberToSearch = readLine()?.toIntOrNull() ?: 0

        val file = File("policies.txt")
        val lines = file.readLines()

        var policyFound = false
        var currentPolicyLines = mutableListOf<String>()

        for (line in lines) {
            if (line.contains("Policy Number: $policyNumberToSearch")) {
                policyFound = true
                currentPolicyLines.add(line)
            } else if (policyFound && line.isNotBlank()) {
                currentPolicyLines.add(line)
            } else if (policyFound && line.isBlank()) {
                break
            }
        }

        if (policyFound) {
            currentPolicyLines.forEach { println(it) }
        } else {
            println("Policy not found.")
        }
    }








    private fun changePolicy() {
        println("Enter policy number to change:")
        val policyNumberToChange = readLine()?.toIntOrNull() ?: 0

        val file = File("policies.txt")
        val lines = file.readLines()
        val newLines = mutableListOf<String>()

        var policyFound = false

        for (line in lines) {
            if (line.contains("Policy Number: $policyNumberToChange")) {
                policyFound = true
                println("Enter new car model and year:")
                val newCar = readLine()!!
                newLines.add(line.replaceFirst("Car: .+", "Car: $newCar"))
            } else {
                newLines.add(line)
            }
        }

        if (policyFound) {
            file.writeText(newLines.joinToString("\n"))
            println("Policy updated successfully.")
            showUserScreen()
        } else {
            println("Policy not found.")
        }
    }




    private fun viewRewards() {
        println("""
        **Your Exclusive Rewards**

        * **20% OFF** Halfords car parts
        * **10% OFF** Circle K fuel cards
    """.trimIndent())

        println("\nPress any key to return to the main menu.")
        readLine()
        displayWelcomeScreen()
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
        println("Enter the name of the user:")
        val name = readLine() ?: ""

        println("Enter the email of the user:")
        val email = readLine() ?: ""

        println("Enter the phone number of theuser:")
        val phoneNumber = readLine() ?: ""

        println("Enter the county of the user:")
        val county = readLine() ?: ""

        println("Enter the tech level:")
        val techLevel = readLine() ?: ""

        val tech = Tech(name, email, phoneNumber, county, techLevel)


        val file = File("tech_users.txt")
        file.appendText("$tech\n")

        println("Tech user added successfully.")
        showTechScreen()
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

        println("Tech user removed .")
    }

    private fun deleteDetailsByNumber() {
        println("Enter the phone number to delete details for:")
        val phoneNumberToDelete = readLine() ?: ""

        val file = File("tech_users.txt")
        val tempFile = File("temp.txt")

        val lines = file.readLines()
        val newLines = mutableListOf<String>()

        for (line in lines) {
            val parts = line.split("\t")
            if (parts.size >= 2 && parts[1] != phoneNumberToDelete) {
                newLines.add(line)
            }
        }
        tempFile.writeText("")
        newLines.forEach { line ->
            tempFile.appendText("$line\n")
        }
        file.delete()
        tempFile.renameTo(file)
        println("Details deleted.")
        showTechScreen()
    }
}
