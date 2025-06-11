package BOJ_2580

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

val board = Array(9){IntArray(9)}
val emptyList = ArrayList<IntArray>()

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    for(i in 0 until 9){
        var st = StringTokenizer(br.readLine())
        for(j in 0 until 9){
            board[i][j] = st.nextToken().toInt()
            if(board[i][j] == 0){
                emptyList.add(intArrayOf(i, j))
            }
        }
    }
    solve(0)
    for (i in 0 until 9){
        for (j in 0 until 9){
            print("${board[i][j]} ")
        }
        println()
    }
}

fun check(x:Int, y:Int): IntArray{
    val result = IntArray(10){0}
    for(i in 0 until 9){
        if(result[board[i][y]] == 0){
            result[board[i][y]] = 1
        }
    }
    for(j in 0 until 9){
        if(result[board[x][j]] == 0){
            result[board[x][j]] = 1
        }
    }
    val nx = x / 3 * 3
    val ny = y / 3 * 3
    for (i in nx..nx+2){
        for(j in ny..ny+2){
            if(result[board[i][j]] == 0){
                result[board[i][j]] = 1
            }
        }
    }
    return result
}

fun solve(idx: Int): Boolean{
    if(idx == emptyList.size){
        return true
    }
    val x = emptyList[idx][0]
    val y = emptyList[idx][1]
    val available = check(x, y)
    var condition = false
    for (i in 1..9){
        if(available[i] == 0){
            board[x][y] = i
            condition = solve(idx + 1)
            if(condition){
                break
            } else {
                board[x][y] = 0
            }
        } else {
            continue
        }
    }
    return condition
}