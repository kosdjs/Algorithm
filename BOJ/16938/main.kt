package BOJ_16938

import java.util.StringTokenizer

var N = 0
var L = 0
var R = 0
var X = 0
var answer = 0
lateinit var A: IntArray

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    L = st.nextToken().toInt()
    R = st.nextToken().toInt()
    X = st.nextToken().toInt()
    A = IntArray(N)
    st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        A[i] = st.nextToken().toInt()
    }
    for(i in 0 until N){
        solve(A[i], A[i], A[i], i)
    }
    println(answer)
}

fun solve(sum: Int, min:Int, max: Int, idx: Int){
    if(sum > R){
        return
    } else {
        if(sum >= L && max - min >= X){
            answer++
        }
        for(i in idx + 1 until N){
            solve(sum + A[i], minOf(min, A[i]), maxOf(max, A[i]), i)
        }
    }
}