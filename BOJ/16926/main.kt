package BOJ_16926

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val R = st.nextToken().toInt()
    val arr = Array(N){ IntArray(M) }
    for(i in 0 until N){
        st = StringTokenizer(br.readLine())
        for(j in 0 until M){
            arr[i][j] = st.nextToken().toInt()
        }
    }
    val deque = ArrayDeque<Int>()
    for(i in 0 until minOf(N, M) / 2){
        arrayToDeque(i, arr, deque)//배열을 데크로 변환
        repeat(R % deque.size){//데크 회전 처리
            deque.addLast(deque.removeFirst())
        }
        dequeToArray(i, arr, deque)//데크를 다시 배열로 변환
    }
    val sb = StringBuilder()
    for(i in 0 until N){
        for(j in 0 until M){
            sb.append("${arr[i][j]} ")
        }
        sb.append("\n")
    }
    print(sb.toString())
}

fun arrayToDeque(layer: Int, arr: Array<IntArray>, deque: ArrayDeque<Int>){
    var x = layer
    var y = layer
    while(x < arr[y].size - layer){
        deque.addLast(arr[y][x])
        x++
    }
    x--
    y++
    while(y < arr.size - layer){
        deque.addLast(arr[y][x])
        y++
    }
    y--
    x--
    while(x >= layer){
        deque.addLast(arr[y][x])
        x--
    }
    x++
    y--
    while(y > layer){
        deque.addLast(arr[y][x])
        y--
    }
}

fun dequeToArray(layer: Int, arr: Array<IntArray>, deque: ArrayDeque<Int>){
    var x = layer
    var y = layer
    while(x < arr[y].size - layer){
        arr[y][x] = deque.removeFirst()
        x++
    }
    x--
    y++
    while(y < arr.size - layer){
        arr[y][x] = deque.removeFirst()
        y++
    }
    y--
    x--
    while(x >= layer){
        arr[y][x] = deque.removeFirst()
        x--
    }
    x++
    y--
    while(y > layer){
        arr[y][x] = deque.removeFirst()
        y--
    }
}