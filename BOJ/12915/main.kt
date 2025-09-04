package BOJ_12915

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val e = st.nextToken().toInt()
    val em = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val mh = st.nextToken().toInt()
    val h = st.nextToken().toInt()
    var left = 0
    var right = 200000
    while(left <= right){
        val mid = (left + right) / 2
        if(e + em < mid || mh + h < mid){
            right = mid - 1
        } else {
            var curM = m
            if(e < mid){
                curM += em - (mid - e)
            } else {
                curM += em
            }
            if(h < mid){
                curM += mh - (mid - h)
            } else {
                curM += mh
            }
            if(curM < mid){
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
    }
    println(right)
}