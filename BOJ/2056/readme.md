# 백준 2056번: 작업

> 문제: https://www.acmicpc.net/problem/2056

### 문제 풀이

DP + 위상 정렬

$dp[i] = i$ 번째 작업을 완료하는 최소 시간

$dp[i] = max(dp[j]) + times[i]$ $(j =$ 선행 관계에 작업$)$

점화식에 맞게 $dp$ 테이블을 구하고 그 중 최댓값을 찾으면 모든 작업이 완료되는 최소 시간

### 풀이 설명

문제 조건에 따라 작업을 시작하려면 모든 선행 관계에 있는 작업이 끝나야 한다. 그리고 작업을 최소 시간으로 완료하려면 작업이 걸리는 시간이 정해져 있으므로 선행 관계에 있는 작업들이 끝나자 마자 시작해야 최소 시간에 작업을 완료할 수 있다.

따라서 $dp[i]$ 를 $i$ 번째 작업을 완료하는 최소 시간으로 정의하면 모든 선행 관계에 있는 작업들의 번호를 j라고 하면 $dp[i] = max(dp[j]) + times[i]$ 과 같은 점화식이 나온다.

모든 작업을 완료하기 위한 최소 시간을 구해야 하기 때문에 구해진 $dp$ 테이블에서 최댓값을 구하면 작업들 중 최소 시간이 가장 큰 값, 즉 최소 시간에 완료되는 작업들 중 마지막으로 완료되는 작업의 완료되는 최소 시간이기 때문에 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    var answer = 0
    val times = IntArray(N + 1){0}
    val dp = IntArray(N + 1){0}
    for(i in 1..N){
        val st = StringTokenizer(br.readLine())
        times[i] = st.nextToken().toInt()
        val count = st.nextToken().toInt()
        var max = 0
        for(j in 1..count){
            val idx = st.nextToken().toInt()
            if(max < dp[idx]){
                max = dp[idx]
            }
        }
        dp[i] = max + times[i]
        if(answer < dp[i]){
            answer = dp[i]
        }
    }
    println(answer)
}
```