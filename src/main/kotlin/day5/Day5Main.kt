package day5

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    println("===== Advent of code 2022 day 5 =====")

    println("== Part One ==")
    val resultOne = challenge(CrateMoverContext(CrateMover9000Strategy()))
    println(resultOne)
    resultOne.forEach { it ->
        print(it.value.first())
    }
    println(" ")

    println("== Part Two ==")
    val resultTwo = challenge(CrateMoverContext(CrateMover9001Strategy()))
    println(resultTwo)
    resultTwo.forEach { it ->
        print(it.value.first())
    }
    println(" ")
}

fun challenge(crateMoverContext: CrateMoverContext): MutableMap<Int, MutableList<Char>> {
    var crateMap: MutableMap<Int, MutableList<Char>> = fillCrateMap()

    val fileName = "rearrangementProcedure.txt"
    try {
        val lines = Files.readAllLines(Paths.get(fileName))
        lines.forEach { instructionLine ->

            val instructions = "\\d+".toRegex().findAll(instructionLine).map { it.value.toInt() }.toList()
            crateMap = crateMoverContext.execute(crateMap, instructions[0], instructions[1], instructions[2])
        }

    } catch (e: IOException) {
        e.printStackTrace()
    }

    return crateMap
}

fun fillCrateMap(): MutableMap<Int, MutableList<Char>> {
    val crateMap = mutableMapOf<Int, MutableList<Char>>(
        1 to mutableListOf<Char>(),
        2 to mutableListOf<Char>(),
        3 to mutableListOf<Char>(),
        4 to mutableListOf<Char>(),
        5 to mutableListOf<Char>(),
        6 to mutableListOf<Char>(),
        7 to mutableListOf<Char>(),
        8 to mutableListOf<Char>(),
        9 to mutableListOf<Char>()
    )

    val fileName = "startingArrangement.txt"
    try {
        val lines = Files.readAllLines(Paths.get(fileName))
        lines.forEach { line ->
            val chunkedLines = line.chunked(4)
            chunkedLines.forEachIndexed { index, container ->
                val cleanedCharacter = container.replace("[", "").replace("]", "").replace(" ", "")

                if (cleanedCharacter.isNotEmpty()) {
                    crateMap[index + 1]?.add(cleanedCharacter[0])
                }
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return crateMap
}




