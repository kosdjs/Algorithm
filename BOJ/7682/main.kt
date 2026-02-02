package BOJ_7682

fun main() = System.`in`.bufferedReader().run {
    val sb = StringBuilder()
    while(true){
        val s = readLine()
        if(s == "end") break
        var xCount = 0
        var oCount = 0
        for(c in s){
            if(c == 'X') xCount++
            else if(c == 'O') oCount++
        }
        fun checkLine(indices: IntArray, c: Char): Boolean{
            return s[indices[0]] == c && s[indices[1]] == c && s[indices[2]] == c
        }
        fun calLines(c: Char): Int{
            var result = 0
            if(checkLine(intArrayOf(0, 1, 2), c)) result++
            if(checkLine(intArrayOf(3, 4, 5), c)) result++
            if(checkLine(intArrayOf(6, 7, 8), c)) result++
            if(checkLine(intArrayOf(0, 3, 6), c)) result++
            if(checkLine(intArrayOf(1, 4, 7), c)) result++
            if(checkLine(intArrayOf(2, 5, 8), c)) result++
            if(checkLine(intArrayOf(0, 4, 8), c)) result++
            if(checkLine(intArrayOf(2, 4, 6), c)) result++
            return result
        }
        var isValid = true
        val oLines = calLines('O')
        val xLines = calLines('X')
        if(oLines > 0 && xLines > 0) isValid = false
        if(xCount + oCount != 9 && oLines == 0 && xLines == 0) isValid = false
        if(oLines > 0 && xLines == 0 && xCount - oCount == 1) isValid = false
        if(oLines == 0 && xLines > 0 && xCount - oCount == 0) isValid = false
        if(xCount - oCount > 1 || xCount - oCount < 0) isValid = false
        sb.append(if(isValid) "valid" else "invalid").append("\n")
    }
    print(sb)
}