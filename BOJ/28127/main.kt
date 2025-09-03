package BOJ_28127

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val q = br.readLine().toInt()
    val sb = StringBuilder()
    repeat(q){
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toLong()
        val d = st.nextToken().toLong()
        val x = st.nextToken().toLong()
        var left = 1L
        var right = 1500L
        while(left <= right){
            val mid = (left + right) / 2
            val curStart = 1 + a * (mid - 1) + d * (mid - 1) * (mid - 2) / 2
            if(curStart > x){
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        val start = 1 + a * (right - 1) + d * (right - 1) * (right - 2) / 2
        sb.append("$right ${x - start + 1}\n")
    }
    print(sb)
}