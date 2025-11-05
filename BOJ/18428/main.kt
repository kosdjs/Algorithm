package BOJ_18428

import java.util.StringTokenizer

var N = 0
lateinit var map: Array<IntArray>
var teachers = ArrayList<IntArray>()
var answer = false

fun main(){
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()
    map = Array(N){IntArray(N)}
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        for(j in 0 until N){
            val c = st.nextToken()
            if(c == "T"){
                teachers.add(intArrayOf(i, j))
            }
            map[i][j] = if(c == "X") 0 else if(c == "S") 1 else 2
        }
    }
    wall(0, 0, 0)
    println(if(answer) "YES" else "NO")
}

fun wall(i: Int, j: Int, level: Int){
    if(level == 3){
        var find = false
        for((y, x) in teachers){
            if(check(y, x)){
                find = true
            }
        }
        if(!find){
            answer = true
        }
        return
    }
    for(x in j until N){
        if(map[i][x] == 0){
            map[i][x] = 3
            wall(i, x, level + 1)
            map[i][x] = 0
        }
    }
    for(y in i + 1 until N){
        for(x in 0 until N){
            if(map[y][x] == 0){
                map[y][x] = 3
                wall(y, x, level + 1)
                map[y][x] = 0
            }
        }
    }
}

fun check(i: Int, j: Int): Boolean{
    var y = i
    var x = j
    var find = false
    while(y > 0 && !find){
        y--
        if(map[y][x] == 1){
            find = true
        }
        if(map[y][x] == 3){
            break
        }
    }
    y = i
    while(x > 0 && !find){
        x--
        if(map[y][x] == 1){
            find = true
        }
        if(map[y][x] == 3){
            break
        }
    }
    x = j
    while(y < N - 1 && !find){
        y++
        if(map[y][x] == 1){
            find = true
        }
        if(map[y][x] == 3){
            break
        }
    }
    y = i
    while(x < N - 1 && !find){
        x++
        if(map[y][x] == 1){
            find = true
        }
        if(map[y][x] == 3){
            break
        }
    }
    return find
}