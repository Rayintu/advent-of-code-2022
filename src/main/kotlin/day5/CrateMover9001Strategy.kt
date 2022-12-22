package day5

class CrateMover9001Strategy : CrateMoverStrategy {
    override fun execute(
        crateMap: MutableMap<Int, MutableList<Char>>,
        amount: Int,
        fromStack: Int,
        toStack: Int
    ): MutableMap<Int, MutableList<Char>> {
        if (fromStack < 1) {
            throw error("from stack is minimum 1")
        }

        val cratesToMove = crateMap[fromStack]?.take(amount)
        val stacksWithoutCrates = crateMap[fromStack]?.subList(amount, crateMap[fromStack]!!.size)

        if (stacksWithoutCrates != null) {
            crateMap[fromStack] = stacksWithoutCrates
        }

        cratesToMove?.reversed()?.forEach { it ->
            crateMap[toStack]?.add(0, it)
        }

        return crateMap
    }
}