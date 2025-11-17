package BOJ_2448

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val arr = Array(N){ CharArray(2 * N - 1){' '} }
    fun star(y: Int, x: Int){
        if(y >= N) return
        arr[y][x] = '*'
        arr[y + 1][x - 1] = '*'
        arr[y + 1][x + 1] = '*'
        for(i in 0..2){
            arr[y + 2][x - i] = '*'
            arr[y + 2][x + i] = '*'
        }
    }
    fun solve(N: Int, y: Int, x: Int){
        if(N == 3){
            star(y, x)
        } else {
            solve(N / 2, y, x)
            solve(N / 2, y + N / 2, x + N / 2)
            solve(N / 2, y + N / 2, x - N / 2)
        }
    }
    solve(N, 0, N - 1)
    val bw = System.out.bufferedWriter()
    for(charArray in arr){
        bw.write(charArray)
        bw.newLine()
    }
    bw.flush()
    bw.close()
}