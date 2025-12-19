# 백준 14938번: 서강그라운드

> 문제: https://www.acmicpc.net/problem/14938

### 문제 풀이

최단 경로, 플로이드 워셜

t[i] = i번 지역에서 습득 가능한 아이템의 개수

dist[i][j] = i번 지역에서 j번 지역까지의 거리

answer = 얻을 수 있는 아이템의 최대 개수

count = 한 지역에 낙하했을 때 얻을 수 있는 아이템의 개수

먼저 특정 지점을 경유해 다른 지점을 도달하면 원래 거리보다 더 짧은지 확인해 최단 경로를 찾는 플로이드 워셜 알고리즘을 사용해 모든 지점들 간의 최단 경로를 구해놓음

모든 지역에 대해 현재 지역인 i번 지역에 낙하했을 때 얻을 수 있는 아이템의 개수 t[i]를 대입하고, 다른 지역에 대한 최단 거리인 dist[i][j]와 수색 범위 m을 비교해 작거나 같으면 j번 지역의 아이템도 습득 가능한 것이므로 count에 t[j]를 더해줌

이러면 i번 지역에 낙하하면 얻을 수 있는 아이템의 개수가 count에 저장되므로 answer에 저장된 값과 비교해 최댓값을 answer에 저장하면 얻을 수 있는 아이템의 최대 개수가 저장되므로 answer를 출력하면 정답

### 풀이 설명

각 지역은 일정한 길이 l (1 ≤ l ≤ 15)의 길로 다른 지역과 연결되어 있고 이 길은 양방향 통행이 가능하다. 낙하한 지역을 중심으로 거리가 수색 범위 m (1 ≤ m ≤ 15) 이내의 모든 지역의 아이템을 습득 가능하다고 할 때, 얻을 수 있는 아이템의 최대 개수를 알려주자.

낙하한 지역을 포함해 수색 범위 이내의 모든 지역의 아이템을 습득 가능하다고 했으므로 한 지점에서부터 모든 지역까지의 최단 거리를 구해야 한다. 그러나 낙하할 지역이 정해져있는 것이 아니므로 모든 지점에서 다른 모든 지역까지의 최단 거리를 구해야 하므로 플로이드 워셜 알고리즘을 사용할 수 있다.

모든 지역들 간의 최단 경로의 거리를 구해놓으면 모든 지점들에 대해 그 지점에 낙하했을 때 얻을 수 있는 아이템의 개수를 구해야 한다. 따라서 현재 지역에 낙하했을 때 얻을 수 있는 아이템의 개수 count를 정의하고 그 지점의 아이템의 개수 t[i]로 초기화한다.

그 이후에 현재 지점과 다른 지점까지의 거리 dist[i][j]를 확인해 수색 범위 m보다 작거나 같으면 그 지점의 아이템도 습득이 가능한 것이므로 count에 t[j]를 더해준다. 이를 i를 제외한 모든 지점에 대해 확인하면 count에 i번 지점에 낙하했을 때의 습득 가능한 아이템의 개수가 저장된다.

이를 모든 지점 i에 대해 반복하면서 최댓값을 찾아 answer에 저장하면 얻을 수 있는 아이템의 최대 개수가 저장되므로 이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val n = nextInt()
    val m = nextInt()
    val r = nextInt()
    val t = IntArray(n + 1)
    val dist = Array(n + 1){IntArray(n + 1){Int.MAX_VALUE} }
    for(i in 1..n){
        t[i] = nextInt()
    }
    repeat(r){
        val u = nextInt()
        val v = nextInt()
        val d = nextInt()
        dist[u][v] = d
        dist[v][u] = d
    }
    for(k in 1..n){
        for(i in 1..n){
            for(j in 1..n){
                if(dist[i][k] != Int.MAX_VALUE && dist[k][j] != Int.MAX_VALUE){
                    dist[i][j] = minOf(dist[i][j], dist[i][k] + dist[k][j])
                }
            }
        }
    }
    var answer = 0
    for(i in 1..n){
        var count = t[i]
        for(j in 1..n){
            if(i == j) continue
            if(dist[i][j] <= m){
                count += t[j]
            }
        }
        answer = maxOf(answer, count)
    }
    println(answer)
}
```