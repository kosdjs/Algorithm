package BOJ_14891

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val gears = Array(5){IntArray(8)}
    for(i in 1..4){
        val s = br.readLine()
        for(j in 0 until 8){
            gears[i][j] = s[j] - '0'
        }
    }
    //각 기어의 9시 방향
    val gearPoint = IntArray(5){2}
    val K = br.readLine().toInt()
    repeat(K){
        val st = StringTokenizer(br.readLine())
        val num = st.nextToken().toInt()
        val direction = IntArray(5){0}
        direction[num] = st.nextToken().toInt() * -1
        var left = num
        var right = num
        while (left > 1){
            if (gears[left][(gearPoint[left] + 4) % 8] != gears[left - 1][gearPoint[left - 1]]){
                direction[left - 1] = direction[left] * -1
            } else {
                break
            }
            left--
        }
        while (right < 4){
            if(gears[right][gearPoint[right]] != gears[right + 1][(gearPoint[right + 1] + 4) % 8]){
                direction[right + 1] = direction[right] * -1
            } else {
                break
            }
            right++
        }
        for(i in 1..4){
            gearPoint[i] += direction[i]
            if(gearPoint[i] < 0){
                gearPoint[i] += 8
            } else if(gearPoint[i] >= 8){
                gearPoint[i] -= 8
            }
        }
    }
    var answer = 0
    var point = 1
    for(i in 1..4){
        if(gears[i][(gearPoint[i] + 6) % 8] == 1){
            answer += point
        }
        point *= 2
    }
    println(answer)
}