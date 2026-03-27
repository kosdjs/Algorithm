package BOJ_15671

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val board = Array(6){ IntArray(6) }
    board[2][2] = 2
    board[2][3] = 1
    board[3][2] = 1
    board[3][3] = 2
    var blackTurn = true
    val dr = intArrayOf(1, 1, 0, -1, -1, -1, 0, 1)
    val dc = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1)
    val sb = StringBuilder()
    for(i in 0 until N){
        val r = nextInt() - 1
        val c = nextInt() - 1
        val stone = if(blackTurn) 1 else 2
        board[r][c] = stone
        for(k in 0 until 8){
            var nr = r + dr[k]
            var nc = c + dc[k]
            while(nr < 6 && nr >= 0 && nc < 6 && nc >= 0){
                if(board[nr][nc] == 0) break
                if(board[nr][nc] == stone){
                    while(nr != r || nc != c){
                        nr -= dr[k]
                        nc -= dc[k]
                        board[nr][nc] = stone
                    }
                    break
                }
                nr += dr[k]
                nc += dc[k]
            }
        }
        blackTurn = !blackTurn
    }
    var count = IntArray(2)
    for(r in 0 until 6){
        for(c in 0 until 6){
            when(board[r][c]){
                0 -> {
                    sb.append(".")
                }
                1 -> {
                    sb.append("B")
                    count[0]++
                }
                2 -> {
                    sb.append("W")
                    count[1]++
                }
            }
        }
        sb.append("\n")
    }
    sb.append(if(count[0] > count[1]) "Black" else "White")
    print(sb)
}