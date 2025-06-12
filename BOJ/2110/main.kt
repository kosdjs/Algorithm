package BOJ_2110

import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val arr = IntArray(N)
    for(i in 0 until N){
        arr[i] = br.readLine().toInt()
    }
    arr.sort()

    var left = 1
    var right = 1000000000
    while(left <= right){
        val mid = (left + right) / 2
        var count = 1
        var prevIdx = 0
        for (i in 1 until N){
            if(arr[i] - arr[prevIdx] >= mid){
                count++
                prevIdx = i
            }
        }
        if(count >= C){
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    println(right)
}