> 문제: https://www.acmicpc.net/problem/11404

### 문제 풀이

최단 경로, 플로이드 워셜

dp[i][j]는 i에서 j까지 가는 최단 경로, 이때 갈 수 없는 경로는 비용의 최댓값보다 커야함으로 이 문제에서는 정점의 갯수 n * 간선 하나의 비용의 최댓값 100,000으로 지정

i에서 j까지 갈 때 경유지점을 k라고 하면 dp[i][j]와 dp[i][k] + dp[k][j]를 비교해 k를 경유해서 가는 경로가 더 빠른지 확인하고 빠르다면 값 저장

이렇게 모든 i, j, k의 경우를 확인하면 최단 경로를 구할 수 있음 (이 때, 반복문을 경유지점을 기준으로 해야 함)

경로가 없는 경우의 출력을 0으로 하라고 했으니 마지막 출력 전에 n * 100,000인 경로를 0으로 변경하고 출력

### 풀이 설명

모든 도시 쌍의 최단 경로를 구해야하기 때문에 한 시작점에서 다른 점까지의 최단 경로를 구하는 다익스트라보다는 모든 경로의 최단 거리를 구하는 플로이드 워셜 알고리즘이 더 적합하다

반복문을 경유지점 k를 기준으로 해야하는 이유는 이 알고리즘이 경유지점을 통해서 최단 경로가 갱신되기 때문에 순서가 변경되면 dp[i][j]의 정의대로 최단 경로가 갱신이 안될 수 있기 때문이다

출력할 때 갈 수 없는 경우에 0을 출력해야 하기 때문에 마지막에 확인해서 INF 값이면 0으로 변경해서 출력해야 한다

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val dp = Array(n){IntArray(n){n*100000} }
    for(i in 1..m){
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        if(dp[a-1][b-1] > c){
            dp[a-1][b-1] = c
        }
    }
    for(k in 0 until n){
        for(i in 0 until n){
            for(j in 0 until n){
                if(i == j){
                    dp[i][j] = 0
                }
                if(dp[i][k] + dp[k][j] < dp[i][j]){
                    dp[i][j] = dp[i][k] + dp[k][j]
                }
            }
        }
    }
    for (i in 0 until n){
        for (j in 0 until n){
            if(dp[i][j] == n*100000){
                dp[i][j] = 0
            }
            print("${dp[i][j]} ")
        }
        println()
    }
}
```