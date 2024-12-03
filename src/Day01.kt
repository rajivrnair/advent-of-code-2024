import kotlin.math.abs

fun main() {
    fun inputsPair(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
        val listOne: MutableList<Int> = mutableListOf()
        val listTwo: MutableList<Int> = mutableListOf()

        for (s: String in input) {
            val split = s.split("\\s+".toRegex()).map { it.toInt() }
            listOne.add(Integer.valueOf(split[0]))
            listTwo.add(Integer.valueOf(split[1]))
        }
        return Pair(listOne, listTwo)
    }

    fun part1(input: List<String>): Int {
        val (listOne: MutableList<Int>, listTwo: MutableList<Int>) = inputsPair(input)
        listOne.sort()
        listTwo.sort()

        if(listOne.size != listTwo.size) throw RuntimeException("Sizes don't match!")

        var sum = 0
        listOne.forEachIndexed { index, el -> sum += abs(el - listTwo[index]) }

        return sum
    }

    fun part2(input: List<String>): Int {
        val (listOne: MutableList<Int>, listTwo: MutableList<Int>) = inputsPair(input)

        var sum = 0
        for(i: Int in listOne) {
            sum += (i * listTwo.count {it == i})
        }
        return sum
    }

    val testInput = readInput("data/Day01_test")
    part1(testInput).println()
    check(part1(testInput) == 11)
    part2(testInput).println()
    check(part2(testInput) == 31)

    println("--------------------------")

    val input = readInput("data/Day01")
    part1(input).println()
    part2(input).println()
}
