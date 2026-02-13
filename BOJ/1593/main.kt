package BOJ_1593

fun main() = System.`in`.bufferedReader().run {
    val (g, S) = readLine().split(" ").map { it.toInt() }
    val W = readLine()
    val s = readLine()
    var left = 0
    var right = g - 1
    var answer = 0
    val WCount = IntArray(52)
    val curCount = IntArray(52)
    fun convertToIndex(c: Char): Int{
        if(c.isUpperCase()){
            return 26 + c.code - 'A'.code
        } else {
            return c.code - 'a'.code
        }
    }
    for(c in W){
        WCount[convertToIndex(c)]++
    }
    for(i in left until right){
        val c = s[i]
        curCount[convertToIndex(c)]++
    }
    while(right < S){
        val rightChar = s[right]
        curCount[convertToIndex(rightChar)]++
        if (WCount.contentEquals(curCount)){
            answer++
        }
        val leftChar = s[left]
        curCount[convertToIndex(leftChar)]--
        left++
        right++
    }
    println(answer)
}