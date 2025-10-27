package BOJ_2343

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val arr = IntArray(N)
    st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        arr[i] = st.nextToken().toInt()
    }
    var left = 1
    var right = 1000000000
    while(left <= right){
        val mid = (left + right) / 2
        var sum = 0
        var count = 1
        var possible = true
        for(i in 0 until N){
            if(arr[i] > mid){
                left = mid + 1
                possible = false
                break
            }
            sum += arr[i]
            if(sum > mid){
                sum = arr[i]
                count++
            }
        }
        if(!possible) continue
        if(count > M){
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    println(left)
}