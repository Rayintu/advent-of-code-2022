package day2

import java.io.BufferedReader
import java.io.FileReader

enum class RpsStrategy {
    Lose, Draw, Win
}

fun main(args: Array<String>) {
    println("===== Advent of code 2022 day 2 =====")
    var firstPartOutput = partOne()

    println("== Part One ==")
    println(firstPartOutput)

    var secondPartOutput = partTwo()

    println("== Part Two ==")
    println(secondPartOutput)
}

fun iLose(theirMove: Int?, myMove: Int?): Boolean {
    val losesTo = mutableMapOf<Int, Int>(1 to 2, 2 to 3, 3 to 1)

    return losesTo[myMove] == theirMove
}

fun getResult(theirShape: Int?, myShape: Int?): Int {
    return if (theirShape == myShape) { //It is a draw
        3 + myShape!!
    } else { // It is not a draw
        if (iLose(theirShape, myShape)) {
            myShape!!
        } else {
            6 + myShape!!
        }
    }
}

fun partOne(): Int {
    val dataSetFile = BufferedReader(FileReader("strategyGuide.txt"))
    val theirShapeScore = mutableMapOf<String, Int>("A" to 1, "B" to 2, "C" to 3)
    val myShapeScore = mutableMapOf<String, Int>("X" to 1, "Y" to 2, "Z" to 3)
    var totalScore: Int = 0

    while(true){
        val strategyLine = dataSetFile.readLine() ?: break
        if(strategyLine==null){
            break;
        } else {
            val splitStrategies: List<String> = strategyLine.split(" ")
            var theirMove = splitStrategies[0]
            var myMove = splitStrategies[1]

            var result = getResult(theirShapeScore[theirMove], myShapeScore[myMove])
            totalScore += result
        }
    }

    return totalScore
}

fun getResultTwo(theirShape: String, myStrategy: RpsStrategy?): Int {
    val theirShapeScore = mutableMapOf<String, Int>("A" to 1, "B" to 2, "C" to 3)
    val myScoreIfTheyLose = mutableMapOf<String, Int>("A" to 2, "B" to 3, "C" to 1)
    val myScoreIfTheyWin = mutableMapOf<String, Int>("A" to 3, "B" to 1, "C" to 2)

    return if (myStrategy == RpsStrategy.Draw){
        3 + theirShapeScore[theirShape]!!
    } else {
        if (myStrategy == RpsStrategy.Win) {
            6 + myScoreIfTheyLose[theirShape]!!
        } else {
            0 + myScoreIfTheyWin[theirShape]!!
        }
    }
}

fun partTwo(): Int {
    val dataSetFile = BufferedReader(FileReader("strategyGuide.txt"))

    var strategies = mutableMapOf<String, RpsStrategy>("X" to RpsStrategy.Lose, "Y" to RpsStrategy.Draw, "Z" to RpsStrategy.Win)
    var totalScore: Int = 0

    while(true){
        val strategyLine = dataSetFile.readLine() ?: break
        if(strategyLine==null){
            break;
        } else {
            val splitStrategies: List<String> = strategyLine.split(" ")
            var theirMove = splitStrategies[0]
            var myStrategy = splitStrategies[1]

            var result = getResultTwo(theirMove, strategies[myStrategy])
            totalScore += result
        }
    }

    return totalScore
}