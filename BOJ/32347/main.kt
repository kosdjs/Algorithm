package BOJ_32347

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val power = IntArray(N + 1)
    st = StringTokenizer(br.readLine())
    for(i in 1..N){
        power[i] = st.nextToken().toInt()
    }
    for(i in N downTo 0){
        if(power[i] == 1){
            power[i] = i
        } else {
            power[i] = power[i + 1]
        }
    }
    var left = 1
    var right = N
    while(left <= right){
        val mid = (left + right) / 2
        var day = N
        var count = 0
        while(day > 1){
            if(power[day] == day){
                day -= mid
                count++
            } else {
                day = power[day]
            }
            if(count > K){
                break
            }
        }
        if(count > K){
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    println(left)
}