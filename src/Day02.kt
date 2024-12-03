fun main() {
    fun isSafe(report: List<Int>): Boolean {
        var differences = report.zipWithNext { a, b -> a-b }
        return differences.all { it in -3..3 } && (differences.all { it > 0 } || differences.all { it < 0 })
    }

    fun part1(input: List<String>) = input.count { line ->
        val report = line.split("\\s+".toRegex()).map(String::toInt)
        isSafe(report)
    }

    fun part2(input: List<String>) = input.count { line ->
        val report = line.split("\\s+".toRegex()).map(String::toInt)
        isSafe(report) || report.indices.any { i ->
            report.filterIndexed { index, _ -> index != i }.let(::isSafe)
        }
    }

    val testInput = readInput("data/Day02_test")
    part1(testInput).println()
    check(part1(testInput) == 2)
    part2(testInput).println()
    check(part2(testInput) == 4)

    println("--------------------------")

    val input = readInput("data/Day02")
    part1(input).println()
    part2(input).println()
}
