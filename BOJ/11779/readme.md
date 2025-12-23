# 백준 11779번: 최소비용 구하기 2

> 문제: https://www.acmicpc.net/problem/11779

### 문제 풀이

다익스트라

adjList = 버스를 인접 리스트 형식으로 나타낸 리스트

dist[i] = A에서부터 i까지의 거리

prev[i] = A에서부터 i까지의 경로 중 i에 도달하기 직전에 도착한 도시

path = A에서 B로 가는 경로

다익스트라 알고리즘을 이용해 A부터 다른 도시까지의 거리를 구함

현재 다익스트라 알고리즘에 따라 확인하는 점이 u라고 했을 때 u를 거쳐서 거리가 갱신되는 점 v가 있다고 하면 A에서 v까지 가는 경로는 A에서 u까지 가는 경로의 마지막에 v가 추가되는 형태이므로 prev[v]에 u를 저장함

이렇게 A에서부터 모든 점까지의 거리 dist 및 이전 도시 prev를 저장하면 A부터 B까지의 거리는 dist[B]에 저장되고 prev[B]부터 역추적하면 A에서 B까지 가는 경로를 구할 수 있음

B에서부터 prev가 0이 나올때까지 path에 저장하면 경로가 역방향으로 저장되므로 path.reverse()를 통해 리스트를 뒤집으면 경로가 정방향으로 저장됨

최단 경로의 길이 dist[B], 최단 경로의 도시 개수 path.size, 최단 경로의 도시를 path에서 순서대로 출력하면 정답

### 풀이 설명

n(1≤n≤1,000)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1≤m≤100,000)개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. 그러면 A번째 도시에서 B번째 도시 까지 가는데 드는 최소비용과 경로를 출력하여라. 항상 시작점에서 도착점으로의 경로가 존재한다.

시작점에서 특정 점까지의 거리는 다익스트라 알고리즘으로 구할 수 있다. 그러나 경로 자체를 구하려면 추가로 정보가 필요하다.

다익스트라 알고리즘의 특성상 시작 점에서부터 거리가 가까운 순으로 주변 점들을 확인하므로 거리를 갱신할 때 어떤 점에서 갱신했는지를 저장해놓으면 마지막 점에서부터 역으로 추적해 경로를 구할 수 있다.

따라서 A에서 특정 점까지의 최단 거리를 dist에 저장하고 경로에 도달하기 위한 직전 점을 prev에 저장하면 dist[B]가 구하는 최단 경로의 길이가 되고, prev[B]에서부터 경로를 역으로 추적해 path에 저장하면 path에 저장된 원소의 개수가 최단 경로의 도시 개수이고, path를 뒤집어서 출력하면 최단 경로가 되므로 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val n = nextInt()
    val m = nextInt()
    val adjList = Array<MutableList<IntArray>>(n + 1){mutableListOf()}
    repeat(m){
        val u = nextInt()
        val v = nextInt()
        val d = nextInt()
        adjList[u].add(intArrayOf(v, d))
    }
    val dist = IntArray(n + 1){Int.MAX_VALUE}
    val prev = IntArray(n + 1)
    val start = nextInt()
    val end = nextInt()
    dist[start] = 0
    val queue = PriorityQueue<IntArray>{o1, o2 -> o1[1] - o2[1]}
    queue.add(intArrayOf(start, 0))
    while(queue.isNotEmpty()){
        val (u, d) = queue.poll()
        if(dist[u] != d) continue
        for((v, w) in adjList[u]){
            if(dist[v] > dist[u] + w){
                dist[v] = dist[u] + w
                prev[v] = u
                queue.add(intArrayOf(v, dist[v]))
            }
        }
    }
    val path = mutableListOf<Int>()
    var v = end
    while(v != 0){
        path.add(v)
        v = prev[v]
    }
    path.reverse()
    println(dist[end])
    println(path.size)
    for(u in path){
        print("$u ")
    }
}
```