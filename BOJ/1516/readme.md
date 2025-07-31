# 백준 1516번: 게임 개발

> 문제: https://www.acmicpc.net/problem/1516

### 문제 풀이

DP + 위상 정렬

$dp[i] = i$ 번째 건물이 완성되기까지 걸리는 최소 시간

$prev[i] = i$ 번째 건물을 짓기 전에 지어야 하는 건물 수

$times[i] = i$ 번째 건물을 짓는데 걸리는 시간

$next[i] = i$ 번째 건물이 먼저 지어져야 하는 건물들의 리스트

$dp[i] = max(dp[j]) + times[i]$ $(j$는 $i$를 짓기 위해서 먼저 지어야하는 건물의 번호$)$

$prev[i]$값이 $0$인 $i$를 큐에 넣고 다음 단계를 반복해 $dp$ 테이블을 채움

1. 큐에서 $i$를 꺼내고 $dp[i]$에 $times[i]$를 더함

2. $next[i]$에서 다음 건물 $j$에 대해 $prev[j]$값을 1 뺌

3. $dp[j]$가 $dp[i]$보다 작다면 $dp[j] = dp[i]$

4. $prev[j]$가 0이 되었으면 큐에 $j$ 삽입

모든 $i$에 대해 $dp[i]$를 출력하면 정답

### 풀이 설명

건물이 완성되기까지 걸리는 최소 시간은 먼저 지어져야 하는 건물들이 완성되기까지 걸리는 최소 시간에 건물을 짓는데 걸리는 시간을 더하면 된다.

따라서 $dp[i]$를 $i$ 번째 건물이 완성되기까지 걸리는 최소 시간으로 정의할 수 있고

$dp[i] = max(dp[j]) + times[i]$ $(j$는 $i$를 짓기 위해서 먼저 지어야하는 건물의 번호$)$

와 같은 점화식이 나오게 된다.

$prev[i]$값이 $0$이 되었다는 뜻은 $i$ 번째 건물을 짓기 전에 먼저 지어야하는 건물을 모두 지었다는 뜻으로 값이 0이 되었을 때 큐에 집어넣어 $dp$값을 계산한다.

이때 $i$ 번째 건물을 지은 후에 지어야 하는 건물을 $j$라고 하면 $dp[j]$의 값과 $dp[i]$의 값을 비교하고 더 큰 값을 저장해 $j$를 짓기 위해 지어야 하는 건물들의 완성 시간을 비교해서 $j$를 짓기 위해 지어야 하는 모든 건물들이 완성되는 시간을 구할 수 있다.

그 후에 $j$를 짓기 위해 지어야 하는 건물 $i$를 짓는데 완료했기 때문에 $prev[j]$의 값을 1 줄여준다. 이때 $prev[j]$의 값이 0이 되면 $j$를 짓기 위해 지어야 하는 모든 건물들이 지어졌다는 뜻이므로 $j$를 큐에 넣는다.

이렇게 모든 건물에 대해 $dp$ 테이블을 구하고 출력하면 정답이다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val dp = IntArray(N + 1){0}
    val times = IntArray(N + 1){0}
    val prev = IntArray(N + 1){0}
    val next = Array(N + 1){ArrayList<Int>()}
    for(i in 1..N){
        val st = StringTokenizer(br.readLine())
        times[i] = st.nextToken().toInt()
        var j = st.nextToken().toInt()
        while(j != -1){
            prev[i]++
            next[j].add(i)
            j = st.nextToken().toInt()
        }
    }
    val queue = ArrayDeque<Int>()
    for(i in 1..N){
        if(prev[i] == 0){
            queue.add(i)
        }
    }
    while(queue.isNotEmpty()){
        val i = queue.removeFirst()
        dp[i] += times[i]
        for(j in next[i]){
            prev[j]--
            if(dp[j] < dp[i]){
                dp[j] = dp[i]
            }
            if(prev[j] == 0){
                queue.add(j)
            }
        }
    }
    for(i in 1..N){
        println(dp[i])
    }
}
```