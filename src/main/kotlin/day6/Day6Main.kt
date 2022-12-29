package day6

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    println("===== Advent of code 2022 day 6 =====")

    println("== Part One ==")
    println(findProtocolMarker(4))

    println("== Part Two ==")
    println(findProtocolMarker(14))
}

fun findProtocolMarker(chunkSize: Int): Int {
    var protocolMarkerIndex: Int = 0

    val fileName = "dataset.txt"
    try {
        val lines = Files.readAllLines(Paths.get(fileName))
        lines.forEach { communicationString ->
            val communicationWindowed = communicationString.toList().windowed( size = chunkSize, step = 1)

            communicationWindowed.forEachIndexed { index, charChunk ->
                if (charChunk.distinct().size == chunkSize) {
                    println(charChunk)
                    return index + charChunk.size
                }
            }
            println(communicationWindowed)
        }

    } catch (e: IOException) {
        e.printStackTrace()
    }

    return protocolMarkerIndex
}




