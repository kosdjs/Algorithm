# 백준 16169번: 수행 시간

> 문제: https://www.acmicpc.net/problem/16169

### 문제 풀이

DP + 위상 정렬

$dp[i] = i$ 번째 컴퓨터가 동작 완료하는데 걸리는 시간

$dp[i] = max(dp[j] + (i - j)^2) + speed[i]$ $(j$ 는 $i$ 보다 계급이 한 단계 낮은 컴퓨터 번호$)$

낮은 계급부터 $dp$ 값을 구해 테이블을 채운 후 최댓값을 출력하면 정답

### 풀이 설명

문제가 설명이 좀 어렵게 나와있는데 이해하기 어려운 조건을 다시 설명하자면

3번의 경우 계급이 가장 낮은 컴퓨터를 제외한 모든 컴퓨터는 자신보다 한 단계 낮은 계급의 컴퓨터가 존재한다는 뜻이다.

4번의 경우 컴퓨터가 동작하려면 자신보다 한 단계 낮은 계급의 모든 컴퓨터에게 전송을 받아야 동작할 수 있다는 뜻이고, 컴퓨터가 동작을 완료하는데 걸리는 시간이 동작 속도라는 뜻으로 다시 말하면 동작 속도가 컴퓨터의 동작 시간이라는 뜻이다.

이로써 문제를 다시 생각해보면 1 부터 r까지의 계급이 있는 컴퓨터 n개가 있고 컴퓨터마다 동작 완료에 걸리는 시간이 주어지고 자신보다 한 단계 낮은 계급의 모든 컴퓨터에게 전송을 받아야 컴퓨터가 동작을 할 수 있을 때 모든 컴퓨터가 동작 완료되는 최소 시간을 구하는 것이다.

이제 컴퓨터가 동작 완료를 하는데 걸리는 시간을 두 부분으로 나누면 자신보다 한 단계 낮은 계급의 모든 컴퓨터가 동작 완료 후 전송을 받는데 걸리는 시간과, 컴퓨터가 동작을 완료하는 시간으로 나눌 수 있다.

이에 따라 dp[i]를 i 번째 컴퓨터가 동작 완료하는데 걸리는 시간으로 정의한다면 자신보다 한 단계 낮은 컴퓨터가 모두 전송을 해야 동작을 시작할 수 있기 때문에

$dp[i] = max(dp[j] + (i - j)^2) + speed[i]$ $(j$ 는 $i$ 보다 계급이 한 단계 낮은 컴퓨터 번호$)$

와 같이 점화식이 나온다.

이 점화식에 따라 계급이 낮은 컴퓨터부터 차례대로 모든 컴퓨터의 동작 시간을 구하면 모든 컴퓨터가 동작 완료되는 최소 시간을 구할 수 있다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val rank = Array(n + 2){ArrayList<Int>()}
    val speed = IntArray(n + 1)
    val dp = IntArray(n + 1){0}
    var answer = 0
    for(i in 1..n){
        val st = StringTokenizer(br.readLine())
        rank[st.nextToken().toInt()].add(i)
        speed[i] = st.nextToken().toInt()
    }
    for(i in 1 .. n){
        for(j in rank[i]){
            dp[j] += speed[j]
            for(k in rank[i + 1]){
                val transfer = (k - j) * (k - j)
                if(dp[k] < dp[j] + transfer){
                    dp[k] = dp[j] + transfer
                }
            }
            if(dp[j] > answer){
                answer = dp[j]
            }
        }
    }
    println(answer)
}
```