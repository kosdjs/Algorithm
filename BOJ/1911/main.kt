package BOJ_1911

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val L = st.nextToken().toInt()
    val arr = Array(N){ IntArray(2) }
    for(i in 0 until N){
        st = StringTokenizer(br.readLine())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        arr[i] = intArrayOf(start, end)
    }
    arr.sortWith { o1, o2 -> o1[0] - o2[0] }
    var answer = 0
    var cur = 0
    for(i in 0 until N){
        if(cur < arr[i][0]){
            cur = arr[i][0]
        }
        if(cur >= arr[i][1]) continue
        val count = (arr[i][1] - cur) / L + if((arr[i][1] - cur) % L > 0) 1 else 0
        answer += count
        cur += count * L
    }
    println(answer)
}