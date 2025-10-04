package BOJ_16974

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    var X = st.nextToken().toLong()
    val patty = LongArray(N + 1)
    val layer = LongArray(N + 1)
    patty[0] = 1
    layer[0] = 1
    for(i in 1..N){
        patty[i] = patty[i - 1] * 2 + 1
        layer[i] = layer[i - 1] * 2 + 3
    }
    var answer = 0L
    var idx = N
    while(X > 0 && idx >= 0){
        if(X == layer[idx]){
            answer += patty[idx]
            break
        }
        val half = layer[idx] / 2 + 1
        if(X > half){
            answer += patty[idx - 1] + 1
            X -= half
            idx--
        } else if(X == half){
            answer += patty[idx - 1] + 1
            X = 0
        } else {
            X--
            idx--
        }
    }
    println(answer)
}