package day5

class CrateMoverContext(private val strategy: CrateMoverStrategy) : CrateMoverStrategy {

    override fun execute(crateMap: MutableMap<Int, MutableList<Char>>, amount: Int, fromStack: Int, toStack: Int): MutableMap<Int, MutableList<Char>>  {
        return strategy.execute(crateMap, amount, fromStack, toStack)
    }
}