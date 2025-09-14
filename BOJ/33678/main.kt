package BOJ_33678

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val X = st.nextToken().toInt()
    val a = IntArray(N + 1){0}
    st = StringTokenizer(br.readLine())
    for(i in 1..N){
        a[i] = a[i - 1] + st.nextToken().toInt()
    }
    var left = 0
    var right = N
    while(left <= right){
        val mid = (left + right) / 2
        var i = 0
        while(N - mid - i >= 0){
            val sum = a[N - mid - i] * X + a[N] - a[N - i]
            if(sum >= K){
                left = mid + 1
                break
            }
            i++
        }
        if(N - mid - i < 0){
            right = mid - 1
        }
    }
    if(right <= 0){
        println(-1)
    } else {
        println(right)
    }
}