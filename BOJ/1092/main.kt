package BOJ_1092

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val cranes = IntArray(N)
    var st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        cranes[i] = st.nextToken().toInt()
    }
    val M = br.readLine().toInt()
    st = StringTokenizer(br.readLine())
    val boxes = IntArray(M)
    for(j in 0 until M){
        boxes[j] = st.nextToken().toInt()
    }
    cranes.sort()
    boxes.sortDescending()
    val using = IntArray(N)
    var capable = N
    var time = 0
    var count = 0
    for(box in boxes){
        for(i in 0 until N){
            if(box <= cranes[i]){
                capable = i
                break
            }
        }
        if(capable == N){
            time = -1
            break
        }
        if(count >= (N - capable) * time){
            time++
        }
        for(j in capable until N){
            if(using[j] < time){
                using[j]++
                count++
                break
            }
        }
    }
    println(time)
}