package BOJ_20444

import java.util.StringTokenizer

fun main() = System.`in`.bufferedReader().run {
    val st = StringTokenizer(readLine())
    val n = st.nextToken().toLong()
    val k = st.nextToken().toLong()
    var left = 0L
    var right = n / 2
    var isPossible = false
    while(left <= right){
        val mid = (left + right) / 2
        val count = (mid + 1) * (n - mid + 1)
        if(count > k){
            right = mid - 1
        } else if(count == k){
            isPossible = true
            break
        } else {
            left = mid + 1
        }
    }
    println(if(isPossible) "YES" else "NO")
}