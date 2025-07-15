package BOJ_5557

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    var prev: LongArray
    var curr = LongArray(21){0}
    curr[st.nextToken().toInt()] = 1
    for(i in 2 until N){
        prev = curr
        curr = LongArray(21){0}
        val num = st.nextToken().toInt()
        for(j in 0..20){
            if (prev[j] == 0.toLong()) continue
            if(j - num >= 0){
                curr[j - num] += prev[j]
            }
            if(j + num <= 20){
                curr[j + num] += prev[j]
            }
        }
    }
    val result = st.nextToken().toInt()
    println(curr[result])
}