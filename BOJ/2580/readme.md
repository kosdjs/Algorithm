> 문제: https://www.acmicpc.net/problem/2580

### 문제 풀이

백트래킹

빈 칸을 리스트에 저장하고 한 칸씩 가능한 숫자를 채워나감

한 칸이라도 입력 가능한 숫자가 없다면 모든 빈칸을 지우고 돌아옴

모든 빈 칸을 채울때까지 반복해 정답을 찾음

### 풀이 설명

스도쿠라는 조건 특성상 가로, 세로, 정사각형의 다른 칸들의 숫자에 의해 칸에 들어갈 숫자가 정해짐

빈칸 하나의 숫자를 임의로 정하면 그 칸의 가로, 세로, 정사각형에 있는 다른 빈칸의 숫자에 영향을 끼치고 이게 이어지다 보면 전체 판의 다른 빈칸에도 영향이 감

숫자를 잘못 넣어 빈 칸에 넣을 수 있는 수가 없다면 다음 빈 칸을 확인할 필요가 없음

따라서 백트래킹을 사용해 반복 횟수를 줄일 수 있음

### 소스 코드
```kotlin
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
```