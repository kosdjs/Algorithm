package BOJ_2866

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val R = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val arr = Array(R){ CharArray(C) }
    for(r in 0 until R){
        val str = br.readLine()
        for(c in 0 until C){
            arr[r][c] = str[c]
        }
    }
    var left = 0
    var right = R - 1
    while(left <= right){
        val mid = (left + right) / 2
        val set = HashSet<String>()
        for(c in 0 until C){
            val sb = StringBuilder()
            for(i in mid + 1 until R){
                sb.append(arr[i][c])
            }
            set.add(sb.toString())
        }
        if(set.size == C){
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    println(right + 1)
}