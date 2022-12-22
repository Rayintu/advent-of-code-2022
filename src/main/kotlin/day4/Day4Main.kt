package day4

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths




fun main(args: Array<String>) {
    println("===== Advent of code 2022 day 4 =====")

    println("== Part One ==")
    println(challengeOne())

    println("== Part Two ==")
    println(challengeTwo())
}

fun challengeOne(): Int {
    var total = 0

    val fileName = "assignmentPairs.txt"
    try {
        val lines = Files.readAllLines(Paths.get(fileName))
        lines.forEach{ pairsLine ->
            val assignmentPairs = pairsLine.split(",")
            val rangeOneSplit = assignmentPairs[0].split("-")
            val rangeTwoSplit = assignmentPairs[1].split("-")
            val ranges: List<IntRange> = listOf(IntRange(rangeOneSplit[0].toInt(), rangeOneSplit[1].toInt()), IntRange(rangeTwoSplit[0].toInt(), rangeTwoSplit[1].toInt()))

            var notInRange = ranges[0].find {it !in ranges[1] }

            var notInRangeInverse = ranges[1].find { it !in ranges[0] }

            if  (notInRange == null || notInRangeInverse == null) {
                total++
            }
        }

    } catch (e: IOException) {
        e.printStackTrace()
    }

    return total
}

fun challengeTwo(): Int {
    var total = 0

    val fileName = "assignmentPairs.txt"
    try {
        val lines = Files.readAllLines(Paths.get(fileName))
        lines.forEach{ pairsLine ->
            val assignmentPairs = pairsLine.split(",")
            val rangeOneSplit = assignmentPairs[0].split("-")
            val rangeTwoSplit = assignmentPairs[1].split("-")
            val ranges: List<IntRange> = listOf(IntRange(rangeOneSplit[0].toInt(), rangeOneSplit[1].toInt()), IntRange(rangeTwoSplit[0].toInt(), rangeTwoSplit[1].toInt()))

            var inRange = ranges[0].find {it in ranges[1] }

            var inRangeInverse = ranges[1].find { it in ranges[0] }

            if  (inRange != null || inRangeInverse != null) {
                total++
            }
        }

    } catch (e: IOException) {
        e.printStackTrace()
    }

    return total
}




