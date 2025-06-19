package BOJ_2230

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    var answer = Int.MAX_VALUE
    var left = 0
    var right = 0
    val arr = IntArray(N)
    for(i in 0 until N){
        arr[i] = br.readLine().toInt()
    }
    arr.sort()
    while(right < N){
        val diff = arr[right] - arr[left]
        if(diff > M){
            if(diff < answer){
                answer = diff
            }
            left++
        } else if(diff < M){
            right++
        } else {
            answer = diff
            break
        }
    }
    println(answer)
}