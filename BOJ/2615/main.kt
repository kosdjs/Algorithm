package BOJ_2615

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val map = Array(20){ IntArray(20) }
    val visit = Array(4){Array(20){ BooleanArray(20) }}
    for(i in 1..19){
        val st = StringTokenizer(br.readLine())
        for(j in 1..19){
            map[i][j] = st.nextToken().toInt()
        }
    }
    var answer = 0
    var y = 0
    var x = 0
    for(j in 1..19){
        for(i in 1..19){
            if(map[i][j] != 0){
                var count = 1
                if(!visit[0][i][j]){
                    visit[0][i][j] = true
                    var tx = j + 1
                    while(tx <= 19 && map[i][tx] == map[i][j]){
                        visit[0][i][tx] = true
                        count++
                        tx++
                    }
                    if(count == 5){
                        answer = map[i][j]
                        y = i
                        x = j
                    }
                }
                if(!visit[1][i][j]){
                    count = 1
                    var ty = i + 1
                    while(ty <= 19 && map[ty][j] == map[i][j]){
                        visit[1][ty][j] = true
                        count++
                        ty++
                    }
                    if(count == 5){
                        answer = map[i][j]
                        y = i
                        x = j
                    }
                }
                if(!visit[2][i][j]){
                    count = 1
                    var tx = j + 1
                    var ty = i + 1
                    while(tx <= 19 && ty <= 19 && map[ty][tx] == map[i][j]){
                        visit[2][ty][tx] = true
                        count++
                        tx++
                        ty++
                    }
                    if(count == 5){
                        answer = map[i][j]
                        y = i
                        x = j
                    }
                }
                if(!visit[3][i][j]){
                    count = 1
                    var tx = j + 1
                    var ty = i - 1
                    while(ty >= 1 && tx <= 19 && map[ty][tx] == map[i][j]){
                        visit[3][ty][tx] = true
                        count++
                        ty--
                        tx++
                    }
                    if(count == 5){
                        answer = map[i][j]
                        y = i
                        x = j
                    }
                }
            }
        }
    }
    println(answer)
    if(answer != 0){
        println("$y $x")
    }
}