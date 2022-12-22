package day1

import java.io.BufferedReader
import java.io.FileReader

fun main(args: Array<String>) {
    println("===== Advent of code 2022 day 1 =====")

    val dataSetFile = BufferedReader(FileReader("dataset.txt"))

    // Volgende idee: ipv toe te voegen aan array doe al meteen calculation per elf
    val allStrings: MutableList<Int> = ArrayList()
    var numberToAdd: Int? = null
    while(true){
        val tmp = dataSetFile.readLine() ?: break
        if (tmp.isEmpty()) {
            if (numberToAdd != null) {
                allStrings.add(numberToAdd)
            }
            numberToAdd=null;
        } else if(tmp==null){
            break;
        } else {
            if (numberToAdd == null) {
                numberToAdd = tmp.toInt()
            } else {
                numberToAdd += tmp.toInt()
            }
        }
    }
    allStrings.sortDescending()
    println(allStrings)

    println("Elf carrying most calories is carrying: ${allStrings[0]} calories")

    println("The top 3 elves are carrying a total of: ${allStrings[0] + allStrings[1] + allStrings[2]} calories")
}