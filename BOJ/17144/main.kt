package BOJ_17144

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val R = nextInt()
    val C = nextInt()
    val T = nextInt()
    val map = Array(R){IntArray(C)}
    val add = Array(R){IntArray(C){0} }
    var purifierY = 0 // 하단 좌표
    var purifierX = 0
    for(i in 0 until R){
        for(j in 0 until C){
            map[i][j] = nextInt()
            if(map[i][j] == -1){
                purifierY = i
                purifierX = j
            }
        }
    }
    fun spread(){
        val dy = intArrayOf(-1, 0, 1, 0)
        val dx = intArrayOf(0, -1, 0, 1)
        for(i in 0 until R){
            for(j in 0 until C){
                if(map[i][j] >= 5){
                    val spreadAmount = map[i][j] / 5
                    var totalSpreadAmount = 0
                    for(k in 0 until 4){
                        val ny = i + dy[k]
                        val nx = j + dx[k]
                        if(ny >= 0 && ny < R && nx >= 0 && nx < C && map[ny][nx] >= 0){
                            add[ny][nx] += spreadAmount
                            totalSpreadAmount += spreadAmount
                        }
                    }
                    map[i][j] -= totalSpreadAmount
                }
            }
        }
        for(i in 0 until R){
            for(j in 0 until C){
                if(add[i][j] != 0){
                    map[i][j] += add[i][j]
                    add[i][j] = 0
                }
            }
        }
    }
    fun purify(){
        //상단 반시계방향
        var y = purifierY - 1
        var x = purifierX
        var first = true // 공기청정기 칸은 덮어씌우면 안됨
        while(x > 0){
            if(first){
                first = false
                x--
                continue
            }
            map[y][x] = map[y][x - 1]
            x--
        }
        while(y > 0){
            if(first){
                first = false
                y--
                continue
            }
            map[y][x] = map[y - 1][x]
            y--
        }
        while(x < C - 1){
            map[y][x] = map[y][x + 1]
            x++
        }
        while(y < purifierY - 1) {
            map[y][x] = map[y + 1][x]
            y++
        }
        var last = false // 정화된 마지막 칸이 이미 나왔는지
        if(x == purifierX){
            last = true
            map[y + 1][x] = 0
        }
        while(x > purifierX){
            map[y][x] = map[y][x - 1]
            x--
        }
        if(!last){
            last = true
            map[y][x + 1] = 0
        }
        //하단 시계방향
        y = purifierY
        x = purifierX
        first = true // 공기청정기 칸은 덮어씌우면 안됨
        while(x > 0){
            if(first){
                first = false
                x--
                continue
            }
            map[y][x] = map[y][x - 1]
            x--
        }
        while(y < R - 1){
            if(first){
                first = false
                y++
                continue
            }
            map[y][x] = map[y + 1][x]
            y++
        }
        while(x < C - 1){
            map[y][x] = map[y][x + 1]
            x++
        }
        while(y > purifierY){
            map[y][x] = map[y - 1][x]
            y--
        }
        last = false // 정화된 마지막 칸이 이미 나왔는지
        if(x == purifierX){
            last = true
            map[y - 1][x] = 0
        }
        while(x > purifierX){
            map[y][x] = map[y][x - 1]
            x--
        }
        if(!last){
            last = true
            map[y][x + 1] = 0
        }
    }
    repeat(T){
        spread()
        purify()
    }
    var count = 0
    for(i in 0 until R){
        for(j in 0 until C){
            if(map[i][j] > 0){
                count += map[i][j]
            }
        }
    }
    println(count)
}