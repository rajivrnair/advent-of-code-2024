import kotlin.Pair

fun main() {
    fun processInputs(input: List<String>): Pair<Map<Int, List<Int>>, List<List<Int>>> {
        val pageOrders = mutableMapOf<Int, MutableList<Int>>()
        val updates = mutableListOf<List<Int>>()

        val regex = """\d\d\|\d\d""".toRegex()
        for(s in input) {
            if(s.matches(regex)) {
                val (key, value) = s.split("|").map(String::toInt)
                pageOrders.getOrPut(key) { mutableListOf() }.add(value)
            } else {
                if(s.trim().isEmpty()) continue
                updates.add(s.split(",").map(String::toInt))
            }
        }

        return Pair(pageOrders, updates)
    }

    fun part1(input: List<String>): Int {
        val (pageOrders: Map<Int, List<Int>>, updates: List<List<Int>>) = processInputs(input)
        val correctUpdates = mutableListOf<List<Int>>()
        for(update in updates) {
            val pagesBefore = mutableListOf<Int>()
            for(page in update) {
                val pagesAfter = pageOrders.getOrDefault(page, emptyList())
                if(pagesAfter.isNotEmpty() && pagesAfter.any { it in pagesBefore}) {
                    break
                }
                pagesBefore.add(page)
            }
            if(pagesBefore.containsAll(update)) correctUpdates.add(update)
        }
        return correctUpdates.sumOf {
            it[it.size / 2]
        }
    }

    fun part2(input: List<String>): Int {
        val (pageOrders: Map<Int, List<Int>>, updates: List<List<Int>>) = processInputs(input)
        val inCorrectUpdates = mutableListOf<List<Int>>()
        for(update in updates) {
            val pagesBefore = mutableListOf<Int>()
            val list = mutableListOf<Int>()
            for(page in update) {
                val pagesAfter = pageOrders.getOrDefault(page, emptyList())
                if(pagesAfter.isNotEmpty() && pagesAfter.any { it in pagesBefore}) {
                    if(list.isEmpty()) list.addAll(update)
                    val minIndex = list.filter { pagesAfter.indexOf(it) != -1 }.minOfOrNull { list.indexOf(it) }
                    minIndex?.let { i ->
                        list.remove(page)
                        list.add(i, page)
                    }
                }
                pagesBefore.add(page)
            }
            if(list.isNotEmpty()) inCorrectUpdates.add(list)
        }

        return inCorrectUpdates.sumOf {
            it[it.size / 2]
        }
    }

    val testInput = readInput("data/Day05_test")
    part1(testInput).println()
    check(part1(testInput) == 143)
    part2(testInput).println()
    check(part2(testInput) == 123)

    println("--------------------------")

    val input = readInput("data/Day05")
    part1(input).println()
    part2(input).println()
}
