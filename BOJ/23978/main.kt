package BOJ_23978

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toLong()
    st = StringTokenizer(br.readLine())
    val days = IntArray(N)
    for(i in 0 until N){
        days[i] = st.nextToken().toInt()
    }
    var left = 1L
    var right = 1500000000L
    while(left <= right){
        val mid = (left + right) / 2
        var sum = mid * (mid + 1) / 2
        for (i in 0 until N-1) {
            val diff = days[i + 1] - days[i]
            val d = minOf(mid, diff.toLong())
            sum += mid * d - (d - 1) * d / 2
        }
        if(sum < K){
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    println(left)
}