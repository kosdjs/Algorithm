package BOJ_1802

fun main() = System.`in`.bufferedReader().run {
    val T = readLine().toInt()
    fun fold(s: String, length: Int): Boolean{
        if(length == 1){
            return true
        }
        var idx = 0
        var canFold = true
        while(idx < length / 2 && canFold){
            val oppositeIdx = length - idx - 1
            if(s[idx] == '0' && s[oppositeIdx] == '0'){
                canFold = false
            }
            if(s[idx] == '1' && s[oppositeIdx] == '1'){
                canFold = false
            }
            idx++
        }
        if(canFold){
            return fold(s, length / 2)
        } else {
            return false
        }
    }
    val sb = StringBuilder()
    repeat(T){
        val s = readLine()
        sb.append(if(fold(s, s.length)) "YES" else "NO").append("\n")
    }
    print(sb)
}