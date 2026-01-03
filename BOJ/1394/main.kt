package BOJ_1394

fun main() = with(System.`in`.bufferedReader()){
    val s = readLine()
    val password = readLine()
    val rank = IntArray(128)
    for(i in 0 until s.length){
        rank[s[i].code] = i + 1
    }
    var answer = 0L
    val mod = 900528L
    for(c in password){
        answer = (answer * s.length + rank[c.code]) % mod
    }
    println(answer)
}