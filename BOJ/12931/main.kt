package BOJ_12931

import java.util.StringTokenizer

fun main(){
    val N = readLine()!!.toInt()
    val arr = IntArray(N)
    val st = StringTokenizer(readLine())
    for(i in 0 until N){
        arr[i] = st.nextToken().toInt()
    }
    var answer = 0
    var count = 0
    while(count != N){
        count = 0
        for(i in 0 until N){
            if(arr[i] == 0){
                count++
            } else {
                if(arr[i] % 2 == 1){
                    arr[i]--
                    answer++
                    if(arr[i] == 0){
                        count++
                    }
                }
            }
        }
        if(count == N){
            break
        } else {
            answer++
            for(i in 0 until N){
                arr[i] /= 2
            }
        }
    }
    println(answer)
}