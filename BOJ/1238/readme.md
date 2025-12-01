# 백준 1238번: 파티

> 문제: https://www.acmicpc.net/problem/1238

### 문제 풀이

다익스트라

graph = 도로를 인접 리스트로 나타냄

revGraph = 도로를 역방향으로 인접 리스트로 나타냄

dist[i] = X번 마을에서 i번 마을까지 가는 최단 거리

revDist[i] = i번 마을에서 X번 마을까지 가는 최단 거리

다익스트라가 특정 시작점에서 다른 점까지의 최단 거리를 구하는 알고리즘이므로 이를 통해 X번 마을에서 다른 마을까지 가는 최단 거리를 구함

역방향 그래프를 이용하면 다른 마을에서 X번 마을까지 가는 최단 거리를 다익스트라 알고리즘 한 번을 통해 구할 수 있음

이에 따라 왕복 거리가 제일 큰 마을의 왕복 거리를 출력하면 정답

### 풀이 설명

N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다. 이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 Ti(1 ≤ Ti ≤ 100)의 시간을 소비한다.

각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다. 하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.

이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다. N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.

이에 따라 구해야 하는 것은 단방향 그래프에서 한 점에서 X번 정점까지의 왕복 거리가 가장 긴 거리이다. 이 왕복 거리는 한 점에서 X번 정점까지 가는 거리와 X번 정점에서 다시 한 점까지 돌아가는 거리로 나눌 수 있다.

X번 정점에서 다른 점까지 가는 최단 거리는 다익스트라 알고리즘을 이용해 쉽게 구할 수 있다. 하지만 다른 점에서 X번 정점까지 가는 거리를 구하려면 모든 점에서 X번 정점까지 가는 거리를 구해야 하는데 이를 더 쉽게 구할 수 있는 방법이 있다.

먼저 다익스트라 알고리즘이 한 점에서 다른 점까지 가는 최단 거리를 구하는 알고리즘이므로 그대로 생각하면 다른 점에서 한 점까지 가는 거리를 구하지 못한다고 생각할 수 있으나 그래프의 방향을 뒤집으면 다익스트라 알고리즘을 적용해 최단 거리를 구할 수 있다.

예를 들어 1번 마을에서 3번 마을까지 가는 최단 경로가 1 -> 2 -> 3이라고 하자. 이 경로의 방향을 바꾸면 1 <- 2 <- 3이 된다. 이를 통해 알 수 있는 것은 그래프의 방향을 바꾸면 1번에서 3번까지 가는 경로를 3번에서 1번까지 가는 경로로 바꿀 수 있다는 점이다.

이 점과 다익스트라가 특정 점을 시작으로 하는 알고리즘이라는 것을 이용하면 모든 점에서 X번 정점까지 가는 거리를 그래프의 방향을 바꿔서 X번 정점에서 다른 정점까지 가는 최단 거리를 다익스트라 알고리즘으로 구하면 이 최단 거리가 실제로는 다른 정점에서 X번 정점까지 가는 최단 거리가 된다는 뜻이다.

이에 따라 다익스트라 알고리즘을 통해 모든 점에서 X번 정점까지 가는 최단 거리, X번 정점에서 다른 정점까지 가는 최단 거리를 구하고 이를 통해 제일 긴 왕복 거리를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val X = nextInt()
    val graph = ArrayList<ArrayList<IntArray>>()
    val revGraph = ArrayList<ArrayList<IntArray>>()
    for(i in 0..N){
        graph.add(arrayListOf())
        revGraph.add(arrayListOf())
    }
    repeat(M){
        val u = nextInt()
        val v = nextInt()
        val dist = nextInt()
        graph[u].add(intArrayOf(v, dist))
        revGraph[v].add(intArrayOf(u, dist))
    }
    var max = Int.MIN_VALUE
    val dist = IntArray(N + 1){Int.MAX_VALUE}
    val revDist = IntArray(N + 1){Int.MAX_VALUE}
    dist[X] = 0
    val queue = PriorityQueue<IntArray>{o1, o2 -> o1[1] - o2[1]}
    queue.add(intArrayOf(X, 0))
    while(queue.isNotEmpty()){
        val (u, curDist) = queue.poll()
        for((v, newDist) in graph[u]){
            if(dist[v] > curDist + newDist){
                dist[v] = curDist + newDist
                queue.add(intArrayOf(v, dist[v]))
            }
        }
    }
    queue.add(intArrayOf(X, 0))
    revDist[X] = 0
    while(queue.isNotEmpty()){
        val(u, curDist) = queue.poll()
        for((v, newDist) in revGraph[u]){
            if(revDist[v] > curDist + newDist){
                revDist[v] = curDist + newDist
                queue.add(intArrayOf(v, revDist[v]))
            }
        }
    }
    for(i in 1..N){
        if(i == X) continue
        max = maxOf(max, dist[i] + revDist[i])
    }
    println(max)
}
```