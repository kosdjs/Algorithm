# 백준 11909번: 배열 탈출

> 문제: https://www.acmicpc.net/problem/11909

### 문제 풀이

DP

$map[i][j] = A[i + 1][j + 1]$의 원소

$dist[i][j] = A[1][1]$에서 $A[i + 1][j + 1]$까지 가는데 드는 최소 비용

$dist[i][j] = min(dist[i - 1][j] + cost, dist[i][j - 1] + cost)$ $($단, $cost$는 이전 칸에서 현재 칸으로 이동할 때 드는 비용이므로 두 값이 다를 수 있음$)$

모든 $i, j$에 대해 $dist$ 배열을 구하고 $dist[n - 1][n - 1]$값을 출력하면 정답

### 풀이 설명

문제 조건에 따라 배열의 인덱스가 커지는 방향으로만 이동할 수 있으므로 A[n][n]까지 가는 비용이 A[n - 1][n]에서 온 경우와 A[n][n - 1]에서 온 경우로 나뉘게 된다. 즉, 이전 칸까지의 비용으로 현재 칸의 비용을 계산할 수 있기 때문에 DP 알고리즘을 이용해 문제를 풀 수 있다.

따라서 $map[i][j]$를 $A[i + 1][j + 1]$의 원소, $dist[i][j]$를 $A[1][1]$에서 $A[i + 1][j + 1]$까지 가는데 드는 최소 비용으로 정의하면 이전 칸까지의 비용에 현재 칸으로 이동하는 비용을 더한 값이 현재 칸까지 가는데 드는 비용이므로 다음과 같은 점화식이 나온다.

$dist[i][j] = min(dist[i - 1][j] + cost, dist[i][j - 1] + cost)$

점화식에 따라 계산하기 위해 인덱스가 가장 작은 값부터 계산해야 한다. 이에 따라 계산을 하고 나면 $dist[n - 1][n - 1]$이 $A[1][1]$에서 $A[n][n]$까지 가는데 드는 최소 비용이기 때문에 이 값을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val map = Array(n){ IntArray(n) }
    for(i in 0 until n){
        val st = StringTokenizer(br.readLine())
        for(j in 0 until n){
            map[i][j] = st.nextToken().toInt()
        }
    }
    val dist = Array(n){ IntArray(n){Int.MAX_VALUE} }
    dist[0][0] = 0
    for(i in 0 until n){
        for(j in 0 until n){
            if(i > 0){
                val cost = if (map[i][j] >= map[i - 1][j]) map[i][j] - map[i - 1][j] + 1 else 0
                if(dist[i][j] > dist[i - 1][j] + cost){
                    dist[i][j] = dist[i - 1][j] + cost
                }
            }
            if(j > 0){
                val cost = if (map[i][j] >= map[i][j - 1]) map[i][j] - map[i][j - 1] + 1 else 0
                if(dist[i][j] > dist[i][j - 1] + cost){
                    dist[i][j] = dist[i][j - 1] + cost
                }
            }
        }
    }
    println(dist[n - 1][n - 1])
}
```