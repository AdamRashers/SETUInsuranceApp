package org.setu

import java.io.File

fun main() {
    println("Enter Tech details:")
    print("Name: ")
    val name = readLine() ?: ""
    print("Email: ")
    val email = readLine() ?: ""
    print("Phone: ")
    val phone = readLine() ?: ""
    print("County: ")
    val county = readLine() ?: ""
    print("Tech Level: ")
    val techLevel = readLine()?: ""
    val tech = Tech(name, email, phone, county, techLevel)
    println(tech)
    val xmlContent = """
        <Tech>
            <Name>${tech.name}</Name>
            <Email>${tech.email}</Email>
            <Phone>${tech.phone}</Phone>
            <County>${tech.county}</County>
            <TechLevel>${tech.techLevel}</TechLevel>
        </Tech>
    """.trimIndent()

    // xml wil be writen and called tech.xml
    val file = File("tech.xml")
    file.writeText(xmlContent)

    println("Tech details have been saved to file")
}