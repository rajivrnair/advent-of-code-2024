fun main() {

    fun multiply(operations: List<String>) = operations
        .sumOf { op ->
            op.replace("[mul()]".toRegex(), "")
                .split(",")
                .map(String::toInt)
                .let { (a, b) -> a * b }
        }

    fun part1(input: List<String>): Int {
        val pattern = "mul\\(\\d+,\\d+\\)".toRegex()
        return input.flatMap { line -> pattern.findAll(line) }
            .map { it.value }
            .let { multiply(it) }
    }

    fun multiplySpecial(operations: List<String>): Int {
        var doIt = true
        return operations.sumOf { op ->
            when (op) {
                "do()" -> {
                    doIt = true
                    0
                }

                "don't()" -> {
                    doIt = false
                    0
                }

                else -> op.replace("[mul()]".toRegex(), "")
                    .split(",")
                    .map(String::toInt)
                    .let { (a, b) -> if (doIt) a * b else 0 }
            }
        }
    }

    fun part2(input: List<String>) = input.count { line ->
        val pattern = "mul\\(\\d+,\\d+\\)|do(?:n't)?\\(\\)".toRegex()
        return input.flatMap { line -> pattern.findAll(line) }
            .map { it.value }
            .let { multiplySpecial(it) }
    }

    val testInput = readInput("data/Day03_test")
    part1(testInput).println()
    check(part1(testInput) == 161)
    part2(testInput).println()
    check(part2(testInput) == 48)

    println("--------------------------")

    val input = readInput("data/Day03")
    part1(input).println()
    part2(input).println()
}
