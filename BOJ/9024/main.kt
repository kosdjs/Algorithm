package BOJ_9024

import java.util.StringTokenizer
import kotlin.math.abs

fun main(){
    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()
    repeat(t){
        System.gc()
        var st = StringTokenizer(br.readLine())
        val n = st.nextToken().toInt()
        val K = st.nextToken().toInt()
        val arr = IntArray(n)
        st = StringTokenizer(br.readLine())
        for(i in 0 until n){
            arr[i] = st.nextToken().toInt()
        }
        arr.sort()
        var left = 0
        var right = n - 1
        var count = 0
        var min = Int.MAX_VALUE
        while(left < right){
            val sum = arr[left] + arr[right]
            val diff = abs(K - sum)
            if(sum < K){
                left++
            } else if(sum > K) {
                right--
            } else {
                left++
                right--
            }
            if(diff < min){
                min = diff
                count = 1
            } else if(diff == min){
                count++
            }
        }
        println(count)
    }
}