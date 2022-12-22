package day3

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Assume that each set of rucksacks only has one matching item
 *
 * 1. Get the contents of each row
 * 2. Split the row into two
 * 3. It is possible to test for the matching character with a nested loop bruteforce but there should be a better way...
 * 4. Define priorities (hashmap good idea? Does kotlin offer something that can make this easier?)
 * 5.
 *
 *
 * Maybe split string with regex to get a list of all lowercase and all uppercase
 * Compare lowercase and uppercase at the same time with multitreading could be funny
 * **/


fun main(args: Array<String>) {
    println("===== Advent of code 2022 day 3 =====")

    println("== Part One ==")
    println(challengeOne())

    println("== Part Two ==")
    println(challengeTwo())
}

fun challengeTwo(): Int {
    val prioMap = "*abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    var total = 0

    val fileName = "rucksackContents.txt"
    try {
        val lines = Files.readAllLines(Paths.get(fileName))
        val chunkedLines = lines.chunked(3)

        chunkedLines.forEach { chunk ->
            val result = findMatchingCharacterPartTwo(chunk[0], chunk[1], chunk[2])
            if (result != null) {
                total += prioMap.indexOf(result)
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return total
}

fun findMatchingCharacterPartTwo(sackOne: String, sackTwo: String, sackThree: String): String? {
    val regexLowerCase = Regex("[a-z]")
    val regexUpperCase = Regex("[A-Z]")

    val sacksLowerCase: List<Set<MatchResult>> = listOf(regexLowerCase.findAll(sackOne).toSet(), regexLowerCase.findAll(sackTwo).toSet(), regexLowerCase.findAll(sackThree).toSet())
    val sacksUppercase: List<Set<MatchResult>> = listOf(regexUpperCase.findAll(sackOne).toSet(), regexUpperCase.findAll(sackTwo).toSet(), regexUpperCase.findAll(sackThree).toSet())

    val matchingLowerCase = findMatchingCharacterThreeSacks(sacksLowerCase[0], sacksLowerCase[1], sacksLowerCase[2])
    if (matchingLowerCase != null) {
        return matchingLowerCase.toString()
    }

    val matchingUpperCase = findMatchingCharacterThreeSacks(sacksUppercase[0], sacksUppercase[1], sacksUppercase[2])
    if (matchingUpperCase !== null) {
        return matchingUpperCase.toString()
    }

    return null
}

fun findMatchingCharacterThreeSacks(setOne: Set<MatchResult>, setTwo: Set<MatchResult>, setThree: Set<MatchResult>): String? {
    setOne.forEach { setOneChar ->
        val result = setTwo.find{
            setOneChar.value == it.value
        }

        val resultThree = setThree.find {
            setOneChar.value == it.value
        }

        if (result != null && resultThree != null) {
            return result.value
        }
    }

    return null
}

fun findMatchingCharacter(setOne: Set<MatchResult>, setTwo: Set<MatchResult>): String? {
    setOne.forEach { setOneChar ->
        val result = setTwo.find{
            setOneChar.value == it.value
        }

        if (result != null) {
            return result.value
        }
    }

    return null
}

fun challengeOne(): Int {
    val dataSetFile = BufferedReader(FileReader("rucksackContents.txt"))
    val prioMap = "*abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    var total = 0

    while(true){
        val rucksackLine = dataSetFile.readLine() ?: break
        if(rucksackLine==null){
            break;
        } else {
            val regexLowerCase = Regex("[a-z]")
            val regexUpperCase = Regex("[A-Z]")
            val mid: Int = rucksackLine.length / 2 //get the middle of the String
            val parts = arrayOf<String>(rucksackLine.substring(0, mid), rucksackLine.substring(mid))
            val compartmentOne = parts[0]
            val compartmentTwo = parts[1]

            val compartmentOneLowerCaseString = regexLowerCase.findAll(compartmentOne)
            val compartmentOneUpperCaseString = regexUpperCase.findAll(compartmentOne)

            val compartmentTwoLowerCaseString = regexLowerCase.findAll(compartmentTwo)
            val compartmentTwoUpperCaseString = regexUpperCase.findAll(compartmentTwo)

            val matchingLowerCase = findMatchingCharacter(compartmentOneLowerCaseString.toSet(), compartmentTwoLowerCaseString.toSet())
            if (matchingLowerCase != null) {
                total += prioMap.indexOf(matchingLowerCase.toString())
            }

            val matchingUpperCase = findMatchingCharacter(compartmentOneUpperCaseString.toSet(), compartmentTwoUpperCaseString.toSet())
            if (matchingUpperCase !== null) {
                total += prioMap.indexOf(matchingUpperCase.toString())
            }
        }
    }

    return total
}