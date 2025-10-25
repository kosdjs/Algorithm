package BOJ_2493

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val answer = IntArray(N)
    val st = StringTokenizer(br.readLine())
    val stack = Array(N){ IntArray(2) }
    var top = -1
    for(i in 0 until N){
        val tower = st.nextToken().toInt()
        while(top >= 0 && stack[top][0] <= tower){
            top--
        }
        if(top >= 0){
            answer[i] = stack[top][1] + 1
        }
        top++
        stack[top][0] = tower
        stack[top][1] = i
    }
    val bw = System.out.bufferedWriter()
    for(i in 0 until N){
        bw.write("${answer[i]} ")
    }
    bw.flush()
    bw.close()
}