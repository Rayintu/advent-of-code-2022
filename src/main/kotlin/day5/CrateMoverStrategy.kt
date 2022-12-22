package day5

interface CrateMoverStrategy {
    fun execute(
        crateMap: MutableMap<Int, MutableList<Char>>,
        amount: Int,
        fromStack: Int,
        toStack: Int
    ): MutableMap<Int, MutableList<Char>>
}