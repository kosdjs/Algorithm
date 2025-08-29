package BOJ_30459

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val R = st.nextToken().toInt() * 2
    st = StringTokenizer(br.readLine())
    val x = IntArray(N)
    for(i in 0 until N){
        x[i] = st.nextToken().toInt()
    }
    x.sort()
    val maxWidth = x[N - 1] - x[0]
    val possibleWidth = BooleanArray(maxWidth + 1){false}
    for(i in 0 until N){
        for(j in i + 1 until N){
            possibleWidth[x[j] - x[i]] = true
        }
    }
    val sticks = IntArray(M)
    st = StringTokenizer(br.readLine())
    for(i in 0 until M){
        sticks[i] = st.nextToken().toInt()
    }
    sticks.sort()
    var max = 0
    for(width in 1..maxWidth){
        if(!possibleWidth[width]){
            continue
        }
        var left = 0
        var right = M - 1
        while(left <= right){
            val mid = (left + right) / 2
            if(sticks[mid] * width <= R){
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        if(right >= 0){
            max = maxOf(max, width * sticks[right])
        }
    }
    if(max > 0){
        println("%.1f".format(max.toDouble() / 2))
    } else {
        println(-1)
    }
}