# 백준 1504번: 특정한 최단 경로

> 문제: https://www.acmicpc.net/problem/1504

### 문제 풀이

다익스트라

start = 시작 정점

dist[i][j] = i번 정점부터 j번 정점까지의 최단 거리

start에서부터 이동할 수 있는 정점들을 이동하면서 여태까지의 최단거리보다 짧은 거리가 나오면 거리 정보를 저정하는 다익스트라 알고리즘을 이용해 1번 정점, v1, v2 정점을 시작점으로 했을때의 최단 거리를 구해놓는다.

1번에서 v1, v2를 거쳐 N번으로 이동하는 최단 거리 dist[1][v1] + dist[v1][v2] + dist[v2][N]와 1번에서 v2, v1을 거쳐 N번으로 이동하는 최단 거리 dist[1][v2] + dist[v2][v1] + dist[v1][N]를 비교해 더 짧은 최단거리를 출력하거나 불가능하다면 -1을 출력하면 정답

### 풀이 설명

방향성이 없는 그래프가 주어진다. 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 또한 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다. 임의의 두 정점 u와 v사이에는 간선이 최대 1개 존재한다.

한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.

1번 정점에서 임의의 정점 v1, v2를 거쳐 N번 정점으로 이동하는 최단 거리를 구하는 방법은 먼저 1번에서 v1, v2를 순서대로 거쳐서 N번 정점으로 이동하는 것과, 1번에서 v2, v1의 순서대로 거쳐서 N번 정점으로 이동하는 방법이 있다.

따라서 다익스트라 알고리즘을 이용해 1, v1, v2번 정점을 시작점으로 다른 정점까지의 최단 거리를 구해놓고 1번에서 v1, v2를 순서대로 거쳐서 N번 정점으로 이동하는 것과 1번에서 v2, v1의 순서대로 거쳐서 N번 정점으로 이동하는 방법을 비교하면 된다.

따라서 다익스트라 알고리즘을 거친 후 1번에서 v1, v2를 순서대로 거쳐서 N번 정점으로 이동하는 최단거리 dist[1][v1] + dist[v1][v2] + dist[v2][N]가 가능한지, 1번에서 v2, v1의 순서대로 거쳐서 N번 정점으로 이동하는 최단거리 dist[1][v2] + dist[v2][v1] + dist[v1][N]가 가능한지 판별해 가능한 최단 거리가 있다면 더 짧은 최단 거리를 출력하면 되고, 불가능하다면 -1을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun readInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = readInt()
    val E = readInt()
    val graph = ArrayList<ArrayList<IntArray>>()
    for(i in 0..N){
        graph.add(arrayListOf())
    }
    val dist = Array(N + 1){ IntArray(N + 1){Int.MAX_VALUE} }
    for(i in 0 until E){
        val a = readInt()
        val b = readInt()
        val c = readInt()
        graph[a].add(intArrayOf(b, c))
        graph[b].add(intArrayOf(a, c))
    }
    val v1 = readInt()
    val v2 = readInt()
    dijkstra(1, dist, graph)
    dijkstra(v1, dist, graph)
    dijkstra(v2, dist, graph)
    var answer = Int.MAX_VALUE
    if(dist[1][v1] != Int.MAX_VALUE && dist[v1][v2] != Int.MAX_VALUE && dist[v2][N] != Int.MAX_VALUE){
        answer = dist[1][v1] + dist[v1][v2] + dist[v2][N]
    }
    if(dist[1][v2] != Int.MAX_VALUE && dist[v2][v1] != Int.MAX_VALUE && dist[v1][N] != Int.MAX_VALUE){
        answer = minOf(answer, dist[1][v2] + dist[v2][v1] + dist[v1][N])
    }
    println(if(answer != Int.MAX_VALUE) answer else -1)
}

fun dijkstra(start: Int, dist: Array<IntArray>, graph:ArrayList<ArrayList<IntArray>>){
    val queue = PriorityQueue<IntArray>{o1, o2 -> o1[1] - o2[1]}
    dist[start][start] = 0
    queue.add(intArrayOf(start, 0))
    while (queue.isNotEmpty()){
        val (cur, cost) = queue.poll()
        if(dist[start][cur] < cost){
            continue
        }
        for((next, newCost) in graph[cur]){
            val newDist = cost + newCost
            if(dist[start][next] > newDist){
                dist[start][next] = newDist
                queue.add(intArrayOf(next, newDist))
            }
        }
    }
}
```