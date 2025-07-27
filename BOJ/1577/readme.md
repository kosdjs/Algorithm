# 백준 1577번: 도로의 개수

> 문제: https://www.acmicpc.net/problem/1577

### 문제 풀이

DP

$dp[i][j] = (0,$ $0)$ 부터 $(i,$ $j)$ 까지 가는 경우의 수

$(i - 1,$ $j)$ 부터 $(i,$ $j)$ 까지의 도로가 공사중이지 않다면

$dp[i][j] = dp[i][j] + dp[i - 1][j]$

$(i,$ $j - 1)$ 부터 $(i,$ $j)$ 까지의 도로가 공사중이지 않다면

$dp[i][j] = dp[i][j] + dp[i][j - 1]$

위의 점화식에 따라 $dp$ 테이블을 구하면 $dp[N][M]$ 이 정답

### 풀이 설명

$(0,$ $0)$ 부터 $(N,$ $M)$ 까지 가는 최단 거리의 서로 다른 경우의 수를 구하려면 먼저 최단 거리로 가야 한다고 했으니 두 가지 경우로 나눌 수 있다.

첫 번째 경우는 $(0,$ $0)$ 부터 $(N - 1,$ $M)$ 까지 최단 거리로 간 후 $(N - 1,$ $M)$ 에서 $(N,$ $M)$ 까지 가는 경우가 있다.

두 번째 경우는 $(0,$ $0)$ 부터 $(N,$ $M - 1)$ 까지 최단 거리로 간 후 $(N,$ $M - 1)$ 에서 $(N,$ $M)$ 까지 가는 경우가 있다.

그렇기 때문에 $dp[i][j] = (0,$ $0)$ 부터 $(i,$ $j)$ 까지 가는 경우의 수라고 하면 공사중인 도로가 없다면 $(0,$ $0)$ 부터 $(N - 1,$ $M)$ 까지 가는 최단 거리의 경우의 수와 $(0,$ $0)$ 부터 $(N,$ $M - 1)$ 까지 가는 최단 거리의 경우의 수를 더하면 된다.

따라서 $dp[i][j]$ 의 값을 계산할 때 $(i - 1,$ $j)$ 부터 $(i,$ $j)$ 까지의 도로가 공사중인지, $(i,$ $j - 1)$ 부터 $(i,$ $j)$ 까지의 도로가 공사중인지에 따라 이에 맞게 값을 더해주면 된다.

따라서 $(i - 1,$ $j)$ 부터 $(i,$ $j)$ 까지의 도로가 공사중이지 않다면

$dp[i][j] = dp[i][j] + dp[i - 1][j]$

$(i,$ $j - 1)$ 부터 $(i,$ $j)$ 까지의 도로가 공사중이지 않다면

$dp[i][j] = dp[i][j] + dp[i][j - 1]$

으로 점화식이 나오고 이 점화식대로 $dp$ 테이블을 구하고 $dp[N][M]$ 을 출력하면 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val dp = Array(N + 1){ LongArray(M + 1){0} }
    val K = br.readLine().toInt()
    val roads = Array(N + 1){Array(M + 1){ArrayList<IntArray>()} }
    for(i in 1..K){
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        val d = st.nextToken().toInt()
        if(a > c || b > d){
            roads[a][b].add(intArrayOf(c, d))
        } else {
            roads[c][d].add(intArrayOf(a, b))
        }
    }
    for(i in 0..N){
        for(j in 0..M){
            if(i == 0 && j == 0){
                dp[i][j] = 1
                continue
            }
            if(i > 0){
                var condition = true
                for(road in roads[i][j]){
                    if(road[0] == i - 1 && road[1] == j){
                        condition = false
                        break
                    }
                }
                if(condition){
                    dp[i][j] += dp[i - 1][j]
                }
            }
            if(j > 0){
                var condition = true
                for(road in roads[i][j]){
                    if(road[0] == i && road[1] == j - 1){
                        condition = false
                        break
                    }
                }
                if(condition) {
                    dp[i][j] += dp[i][j - 1]
                }
            }
        }
    }
    println(dp[N][M])
}
```