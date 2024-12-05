// This is how the pros do it :(
fun main() {
    fun part1(input: List<String>): Int = input.flatMapIndexed { y, s ->
        s.mapIndexed { x, c -> x to c }
            .filter { it.second == 'X' }.map { it.first to y }
    }.sumOf { p ->
        listOf(0 to -1, 1 to -1, 1 to 0, 1 to 1, 0 to 1, -1 to 1, -1 to 0, -1 to -1)
            .count { o ->
                "XMAS".indices.map {
                    input.getOrNull(p.second + it * o.second)
                        ?.getOrNull(p.first + it * o.first) ?: ""
                }.joinToString("") == "XMAS"
            }
    }

    fun part2(input: List<String>): Int = input.flatMapIndexed { y, s ->
        s.mapIndexed { x, c -> x to c }
            .filter { it.second == 'A' }.map { it.first to y }
    }.count { p ->
        listOf(
            listOf(-1 to -1, 0 to 0, 1 to 1),
            listOf(1 to -1, 0 to 0, -1 to 1)
        ).map {
            it.map { s ->
                input.getOrNull(p.second + s.second)
                    ?.getOrNull(p.first + s.first) ?: ""
            }.joinToString("").let { it == "MAS" || it == "SAM" }
        }.all { it }
    }

    val testInput = readInput("data/Day04_test")
    part1(testInput).println()
    check(part1(testInput) == 18)
    part2(testInput).println()
    check(part2(testInput) == 9)

    println("--------------------------")

    val input = readInput("data/Day04")
    part1(input).println()
    part2(input).println()
}
