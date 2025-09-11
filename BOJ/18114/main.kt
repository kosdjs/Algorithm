package BOJ_18114

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val w = IntArray(N)
    st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        w[i] = st.nextToken().toInt()
        if(w[i] == C){
            println(1)
            return
        }
    }
    w.sort()
    var left = 0
    var right = N - 1
    while(left < right){
        val sum = w[left] + w[right]
        if(sum == C){
            println(1)
            return
        } else if(sum < C){
            left++
        } else {
            right--
        }
    }
    for(i in 0 until N - 2){
        val c = C - w[i]
        var left = i + 1
        var right = N - 1
        while(left < right){
            val sum = w[left] + w[right]
            if(sum == c){
                println(1)
                return
            } else if(sum < c){
                left++
            } else {
                right--
            }
        }
    }
    println(0)
}