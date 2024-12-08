import java.io.File

public fun getQuotes() {
    var insuranceCost = 0
    var phoneNumber = ""
    val quotes = mutableListOf<Pair<Int, String>>()
    fun askQuestion(question: String, options: List<String>, min: Int, max: Int): Int {
        println(question)
        options.forEachIndexed { index, option -> println("${index + 1}. $option") }
        print("Enter your choice ($min-$max): ")
        return readLine()?.toIntOrNull() ?: 0
    }

    insuranceCost += when (askQuestion("What make of car do you have?", listOf("Mazda", "Opel", "BMW", "Nissan", "Honda", "Suzuki", "Toyota", "Dacia"), 1, 8)) {
        1 -> 100
        2 -> 200
        3 -> 300
        4 -> 400
        5 -> 500
        6 -> 600
        7 -> 700
        else -> 800
    }

    insuranceCost += when (askQuestion("What litre is the car?", listOf("1.0L", "1.2L", "1.4L", "1.6L", "1.8L", "2.0L"), 1, 6)) {
        1, 2 -> 200
        3, 4 -> 250
        else -> 300
    }

    insuranceCost += when (askQuestion("What age are you?", listOf("17-19", "20-22", "23-26", "27-35", "36-60", "60+"), 1, 6)) {
        1 -> 800
        2 -> 400
        3 -> 300
        4 -> 200
        5 -> 150
        else -> 700
    }

    insuranceCost += when (askQuestion("What horsepower range is your car?", listOf("50-75 HP", "76-100 HP", "101-150 HP", "151-200 HP", "201-300 HP", "300+ HP"), 1, 6)) {
        1 -> 100
        2 -> 150
        3 -> 200
        4 -> 250
        5 -> 350
        else -> 500
    }
    insuranceCost += when (askQuestion("Is your car electric, diesel, or petrol?", listOf("Electric", "Diesel", "Petrol"), 1, 3)) {
        1 -> 150
        else -> 300
    }
    insuranceCost += if (askQuestion("Do you have any engine modifications (Yes or No)?", listOf("Yes", "No"), 1, 2) == 1) 300 else 0
    insuranceCost += if (askQuestion("Have you had previous insurance (Yes or No)?", listOf("Yes", "No"), 1, 2) == 2) 200 else 0

    insuranceCost += when (askQuestion("How many years of no claims do you have?", listOf("0 years", "1 year", "2 years", "3 years", "4 years or more"), 0, 4)) {
        1 -> -100
        2 -> -200
        3 -> -300
        4 -> -500
        else -> 0
    }
    insuranceCost += if (askQuestion("Do you want comprehensive or third-party fire and theft insurance?", listOf("Comprehensive", "Third-party fire and theft"), 1, 2) == 1) 500 else 300
    println("Your estimated insurance cost is €$insuranceCost.")

    // Ask for phone number after all questions are answered
    println("Please enter your phone number:")
    phoneNumber = readLine()?.toString() ?: ""

    println("Your phone number is: $phoneNumber")


    quotes.add(Pair(insuranceCost, phoneNumber))

    // Write details to a file
    val file = File("quote_details.txt")
    file.writeText("Insurance Cost\tPhone Number\n")
    quotes.forEach { (cost, number) ->
        file.appendText("$cost\t$number\n")
    }

    println("")
    println("Quote details saved to quote_details.txt")
    println("")
    println("")
    println("Broker will call back and add you to the system shortly")
    println("")
    println("")
    main()
}

