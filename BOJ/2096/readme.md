# 백준 2096번: 내려가기

> 문제: https://www.acmicpc.net/problem/2096

### 문제 풀이

DP

min[i][j] = i, j 칸에 도달할 때 최소 점수
max[i][j] = i, j 칸에 도달할 때 최대 점수

min[i][j] = min[i-1][j-1], min[i-1][j], min[i-1][j+1] 중 최솟값
max[i][j] = max[i-1][j-1], max[i-1][j], max[i-1][j+1] 중 최댓값

min[N-1], max[N-1]에서 최소 최댓값을 찾으면 정답

### 풀이 설명

게임 조건에 따라 첫 줄에서 마지막 줄까지 한 줄씩 내려가는 게임이고, 한 줄을 내려갈 때 바로 아래 칸이나 그 칸과 인접한 칸으로만 내려갈 수 있기 때문에 현재 칸에 도달할 수 있는 칸은 윗 줄에 바로 윗칸이나 인접한 칸이다.

따라서 현재 칸에 도달할 수 있는 최소 점수는 윗 줄에서 현재 칸에 도달할 수 있는 칸의 최소 점수에 현재 칸의 점수를 더한 값이고, 최대 점수는 윗 줄에서 현재 칸에 도달할 수 있는 칸의 최대 점수에 현재 칸의 점수를 더한 값이다.

문제에서 구해야 하는 값이 게임을 통해 얻을 수 있는 최대, 최소 점수이기 때문에 마지막 줄의 모든 칸에 도달할 때 얻을 수 있는 최소, 최대 점수를 확인하면 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val arr = Array(N){IntArray(3)}
    val min = Array(N){IntArray(3)}
    val max = Array(N){IntArray(3)}
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        arr[i][0] = st.nextToken().toInt()
        arr[i][1] = st.nextToken().toInt()
        arr[i][2] = st.nextToken().toInt()
    }
    min[0][0] = arr[0][0]
    min[0][1] = arr[0][1]
    min[0][2] = arr[0][2]
    max[0][0] = arr[0][0]
    max[0][1] = arr[0][1]
    max[0][2] = arr[0][2]
    for(i in 1 until N){
        for(j in 0..2){
            var minScore = Int.MAX_VALUE
            var maxScore = 0
            for(k in j-1..j+1){
                if(k < 0 || k >= 3) continue
                if(min[i-1][k] < minScore) minScore = min[i-1][k]
                if(max[i-1][k] > maxScore) maxScore = max[i-1][k]
            }
            min[i][j] = minScore + arr[i][j]
            max[i][j] = maxScore + arr[i][j]
        }
    }
    var minResult = Int.MAX_VALUE
    var maxResult = 0
    for(j in 0..2){
        if(min[N-1][j] < minResult) minResult = min[N-1][j]
        if(max[N-1][j] > maxResult) maxResult = max[N-1][j]
    }
    println("$maxResult $minResult")
}
```