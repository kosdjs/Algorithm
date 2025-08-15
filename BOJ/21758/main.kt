package BOJ_21758

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val arr = IntArray(N)
    val st = StringTokenizer(br.readLine())
    var sum = 0L
    for(i in 0 until N){
        arr[i] = st.nextToken().toInt()
        sum += arr[i]
    }
    var answer = 0L
    var left = 0
    var right = N - 1
    var pot = 1
    for(pot in left + 1 until right){
        answer = maxOf(answer, sum - arr[left] - arr[right] + arr[pot])
    }
    right = 1
    pot = N - 1
    var rightSum = sum - arr[left]
    for(right in left + 1 until pot){
        rightSum -= arr[right]
        answer = maxOf(answer, sum - arr[left] - arr[right] + rightSum)
    }
    pot = 0
    right = N - 1
    var leftSum = sum - arr[right]
    for(left in right - 1 downTo pot + 1){
        leftSum -= arr[left]
        answer = maxOf(answer, sum - arr[left] - arr[right] + leftSum)
    }
    println(answer)
}