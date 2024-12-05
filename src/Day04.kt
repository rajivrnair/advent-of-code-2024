fun main() {

    fun left(grid: Array<CharArray>, x: Int, y: Int, target: String): Boolean {
        val wLen = target.length - 1
        if ((y-wLen) < 0) return false

        val wordArray = mutableListOf<Char>()
        val endIndex = y - wLen
        for (i in y downTo endIndex)
            wordArray.add(grid[x][i])

        return wordArray.joinToString("") == target
    }

    fun top(grid: Array<CharArray>, x: Int, y: Int, target: String): Boolean {
        val wLen = target.length - 1
        if ((x-wLen) < 0) return false

        val wordArray = mutableListOf<Char>()
        val endIndex = x - wLen
        for (i in x downTo endIndex)
            wordArray.add(grid[i][y])

        return wordArray.joinToString("") == target
    }

    fun right(grid: Array<CharArray>, x: Int, y: Int, target: String): Boolean {
        val wLen = target.length
        val rowLen = grid[0].size
        if ((y+wLen) > rowLen) return false

        val wordArray = mutableListOf<Char>()
        val endIndex = y + wLen - 1
        for (i in y..endIndex)
            wordArray.add(grid[x][i])

        return wordArray.joinToString("") == target
    }

    fun down(grid: Array<CharArray>, x: Int, y: Int, target: String): Boolean {
        val wLen = target.length
        val colLen = grid.size
        if((x+wLen) > colLen) return false

        val wordArray = mutableListOf<Char>()
        val endIndex = x + wLen - 1

        for (i in x..endIndex)
            wordArray.add(grid[i][y])

        return wordArray.joinToString("") == target
    }

    fun topLeft(grid: Array<CharArray>, x: Int, y: Int, target: String): Boolean {
        val wLen = target.length - 1
        if ((y-wLen) < 0 || (x-wLen) < 0) return false

        val wordArray = mutableListOf<Char>()
        var i = x
        val xEnd = x - wLen
        var j = y
        val yEnd = y - wLen
        while(i >= xEnd && j >= yEnd) {
            wordArray.add(grid[i][j])
            i--
            j--
        }

        return wordArray.joinToString("") == target
    }

    fun bottomLeft(grid: Array<CharArray>, x: Int, y: Int, target: String): Boolean {
        val wLen = target.length - 1
        val colLen = grid.size - 1
        if ((y-wLen) < 0 || (x+wLen) > colLen) return false

        val wordArray = mutableListOf<Char>()
        var i = x
        val xEnd = x + wLen
        var j = y
        val yEnd = y - wLen
        while(i <= xEnd && j >= yEnd) {
            wordArray.add(grid[i][j])
            i++
            j--
        }

        return wordArray.joinToString("") == target
    }

    fun topRight(grid: Array<CharArray>, x: Int, y: Int, target: String): Boolean {
        val wLen = target.length - 1
        val rowLen = grid[0].size - 1
        if ((x-wLen) < 0 || (y+wLen) > rowLen) return false

        val wordArray = mutableListOf<Char>()
        var i = x
        val xEnd = x - wLen
        var j = y
        val yEnd = y + wLen
        while(i >= xEnd && j <= yEnd) {
            wordArray.add(grid[i][j])
            i--
            j++
        }

        return wordArray.joinToString("") == target
    }

    fun bottomRight(grid: Array<CharArray>, x: Int, y: Int, target: String): Boolean {
        val wLen = target.length
        val rowLen = grid[0].size
        val colLen = grid.size
        if((y+wLen) > rowLen || (x+wLen) > colLen) return false

        val wordArray = mutableListOf<Char>()
        var i = x
        val xEnd = x + wLen - 1
        var j = y
        val yEnd = y + wLen - 1
        while(i <= xEnd && j <= yEnd) {
            wordArray.add(grid[i][j])
            i++
            j++
        }

        return wordArray.joinToString("") == target
    }

    fun part1(input: List<String>): Int {
        val target = "XMAS"
        val rows = input.size
        val cols = input[0].length
        val grid = Array(rows) { row -> input[row].toCharArray() }
        var totalMatches = 0

        for(i in 0..rows-1) {
            for(j in 0..cols-1) {
                if(grid[i][j] == 'X') {
                    val matches = arrayOf(
                        left(grid, i, j, target),
                        right(grid, i, j, target),
                        top(grid, i, j, target),
                        down(grid, i, j, target),
                        topLeft(grid, i, j, target),
                        bottomLeft(grid, i, j, target),
                        topRight(grid, i, j, target),
                        bottomRight(grid, i, j, target),
                    ).count { it == true }
                    totalMatches += matches
                }
            }
        }

        return totalMatches
    }

    fun part2(input: List<String>): Int {
        val target = "MAS"
        val len = target.length - 1
        val rows = input.size - 1
        val cols = input[0].length - 1
        val grid = Array(rows+1) { row -> input[row].toCharArray() }
        var totalMatches = 0

        for(startX in 0..rows) {
            val endX = startX + len
            if(endX > rows) continue

            for(startY in 0..cols) {
                val endY = startY + len
                if(endY > cols) continue
                if(grid[startX + 1][startY + 1] == 'A'
                    && ((grid[startX][startY] == 'M' && grid[endX][endY] == 'S') || (grid[startX][startY] == 'S' && grid[endX][endY] == 'M'))
                    && ((grid[startX][endY] == 'M' && grid[endX][startY] == 'S') || (grid[startX][endY] == 'S' && grid[endX][startY] == 'M'))) {
                    totalMatches += 1
                }
            }
        }

        return totalMatches
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
