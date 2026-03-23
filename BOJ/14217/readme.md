# 백준 14217번: 그래프 탐색

> 문제: https://www.acmicpc.net/problem/14217

### 문제 풀이

BFS

graph[i] = i번 도시와 도로로 연결된 도시들

dist[i] = i번 도시부터 1번 도시까지 최단 경로의 길이, 경로가 없으면 Int.MAX_VALUE

queue = 현재 방문한 도시를 담는 큐

매번 도로를 추가하거나 없애므로 거리를 다시 계산해야 함

도로의 가중치가 1이므로 1번 도시에서부터 BFS로 도시를 탐색하는 것이 가장 빠른 방법임

매번 도로가 없어지거나 생기는 정보를 graph에 반영하고 dist 배열을 매번 Int.MAX_VALUE로 초기화 한 이후에 BFS로 1번 도시부터 다른 도시로 탐색하며 거리를 찾고 출력하면 정답

### 풀이 설명

1번부터 n번까지의 도시가 존재하며, 도시 사이에는 m개의 양방향 도로가 있다. 모든 도로의 길이는 1로 동일하다.

이후 총 q번에 걸쳐 두 도시 사이의 도로를 새로 추가하거나 기존 도로를 제거하는 정비 계획이 차례대로 주어진다.

각 정비가 이루어질 때마다 1번 도시를 기점으로 나머지 모든 도시까지의 최단 거리를 구해야 한다. 만약 특정 도시로 가는 경로가 존재하지 않는다면 -1을 출력해야 한다.

즉, 경로가 매번 달라지므로 매번 1번 도시까지의 거리를 다시 계산해야 한다는 것이다. 이때 도로의 가중치가 1이므로 1번 도시부터 BFS로 거리를 판단하는 것이 가장 빠르다.

도로 정보를 저장하는 것은 ArrayList를 이용해 graph에 인접 리스트 형태로 저장하고, 1번 도시까지의 최단 거리를 dist에 배열로 저장한다.

이때 dist 배열을 Int.MAX_VALUE로 초기화해 BFS로 탐색되지 않은 도시는 경로가 없다는 것을 나타낸다. 그 이후에 매번 도로 정보를 graph에 반영할때마다 dist[1]에 0을 대입하고 1번 도시부터 BFS를 통해 탐색 가능한 도시의 최단거리 정보를 dist 배열에 반영해준다.

이렇게 반영된 dist 배열을 참고해 각 도시까지의 최단 거리를 출력해주면 정답이 된다.

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
    val graph = Array(n + 1){ArrayList<Int>()}
    for(i in 0 until m){
        val a = nextInt()
        val b = nextInt()
        graph[a].add(b)
        graph[b].add(a)
    }
    val dist = IntArray(n + 1){Int.MAX_VALUE}
    val sb = StringBuilder()
    val queue = ArrayDeque<Int>()
    for(k in 0 until nextInt()){
        val a = nextInt()
        val i = nextInt()
        val j = nextInt()
        if(a == 1){
            graph[i].add(j)
            graph[j].add(i)
        } else {
            graph[i].remove(j)
            graph[j].remove(i)
        }
        dist.fill(Int.MAX_VALUE)
        dist[1] = 0
        //bfs
        queue.add(1)
        while(queue.isNotEmpty()){
            val x = queue.removeFirst()
            for(y in graph[x]){
                if(dist[y] == Int.MAX_VALUE){
                    dist[y] = dist[x] + 1
                    queue.add(y)
                }
            }
        }
        for(l in 1..n){
            sb.append(if(dist[l] != Int.MAX_VALUE) dist[l] else -1).append(" ")
        }
        sb.append("\n")
    }
    print(sb)
}
```