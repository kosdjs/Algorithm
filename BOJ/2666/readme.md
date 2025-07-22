# 백준 2666번: 벽장문의 이동

> 문제: https://www.acmicpc.net/problem/2666

### 문제 풀이

DP

$dp[level][door1][door2]$ = $level$ 단계에서 $door1, door2$가 열려있을 때 최소 이동거리

$arr[level]$ = $level$ 단계에서 사용해야 하는 벽장

$door1$을 옮겨서 사용하는 경우

$dp[level + 1][arr[level]][door2] = min(dp[level + 1][arr[level]][door2], dp[level][i][j] + abs(i - arr[level])$

$door2$을 옮겨서 사용하는 경우

$dp[level + 1][door1][arr[level]] = min(dp[level + 1][door1][arr[level]], dp[level][i][j] + abs(j - arr[level])$

이를 모든 $level, door1, door2$에 대해 계산하고 모든 $i, j$에 대해 $dp[t][i][j]$의 최솟값을 구하면 정답

### 풀이 설명

구해야 하는 값이 벽장을 주어진 순서대로 사용하는 벽장문의 최소 이동 횟수이고, 벽장을 주어진 순서대로 사용해야 하기 때문에 벽장을 하나 사용할 때마다 현재 어느 벽장이 열려있는지에 따라 이동 횟수가 달라진다.

벽장문은 두개만 열려있고 벽장을 사용하기 위해 열려면 열려있는 벽장까지 문을 옮기기 때문에 벽장을 사용하고 나면 열려있던 벽장 중 하나가 닫히고 현재 사용한 벽장이 열리게 된다.

따라서 현재 순서에서 몇번째 벽장을 열었는지 나타내는 $level$, 열린 벽장을 $door1, door2$라고 하면 $dp[level][door1][door2]$는 $level$ 순서까지 벽장을 열었을 때 열린 벽장이 $door1, door2$ 일 때 최소 이동거리로 정의할 수 있다.

열려있는 두 벽장중 하나를 선택해서 그 벽장까지 문을 옮겨 사용할 벽장을 열어야하기 때문에

$door1$을 옮겨서 사용하는 경우

$dp[level + 1][arr[level]][door2] = min(dp[level + 1][arr[level]][door2], dp[level][i][j] + abs(i - arr[level])$

$door2$을 옮겨서 사용하는 경우

$dp[level + 1][door1][arr[level]] = min(dp[level + 1][door1][arr[level]], dp[level][i][j] + abs(j - arr[level])$

와 같은 점화식이 나온다. 이에 따라 dp 테이블을 계산하면 벽장 순서의 길이 t, 문 i, j에 대해 $dp[t][i][j]$의 최솟값을 확인하면 사용할 벽장을 모두 순서대로 사용했을 때 열린 문의 위치 i, j일 때의 최소 이동거리 중 최솟값이기 때문에 정답이 된다.

### 소스 코드
```kotlin
import kotlin.math.abs
import kotlin.math.min

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val doors = br.readLine().split(" ").map { it -> it.toInt() }
    val t = br.readLine().toInt()
    val arr = IntArray(t)
    for(i in 0 until t){
        arr[i] = br.readLine().toInt()
    }
    val dp = Array(t + 1){Array(n + 1){ IntArray(n + 1){Int.MAX_VALUE} } }
    dp[0][doors[0]][doors[1]] = 0
    for(level in 0 until t){
        for(i in 1..n){
            for(j in 1..n){
                if(i == j || dp[level][i][j] == Int.MAX_VALUE) continue
                val countI = dp[level][i][j] + abs(i - arr[level])
                if(dp[level + 1][arr[level]][j] > countI){
                    dp[level + 1][arr[level]][j] = countI
                }
                val countJ = dp[level][i][j] + abs(j - arr[level])
                if(dp[level + 1][i][arr[level]] > countJ)
                    dp[level + 1][i][arr[level]] = countJ
            }
        }
    }
    var answer = Int.MAX_VALUE
    for(i in 1..n){
        for(j in 1..n){
            if(answer > dp[t][i][j]) answer = dp[t][i][j]
        }
    }
    println(answer)
}
```