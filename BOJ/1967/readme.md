# 백준 1967번: 트리의 지름

> 문제: https://www.acmicpc.net/problem/1967

### 문제 풀이

트리, DFS

graph[i] = i번 노드와 연결된 노드의 번호와 그 노드까지의 거리

visit[i] = i번 노드 방문 여부

end = 지름의 양 끝 노드 중 한 노드

maxDist = 지름의 끝 노드에서 가장 먼 노드까지의 거리

지름의 양 끝 노드 중 한 노드를 구하기 위해 1번 노드에서부터 dfs를 이용해 가장 먼 노드인 end를 구함

end에서부터 가장 먼 경로인 트리의 지름을 구하기 위해 dfs를 이용해 구하고 출력하면 정답

### 풀이 설명

트리(tree)는 사이클이 없는 무방향 그래프이다. 트리에서는 어떤 두 노드를 선택해도 둘 사이에 경로가 항상 하나만 존재하게 된다. 트리에서 어떤 두 노드를 선택해서 양쪽으로 쫙 당길 때, 가장 길게 늘어나는 경우가 있을 것이다. 이럴 때 트리의 모든 노드들은 이 두 노드를 지름의 끝 점으로 하는 원 안에 들어가게 된다.

이런 두 노드 사이의 경로의 길이를 트리의 지름이라고 한다. 정확히 정의하자면 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이를 말한다. 입력으로 루트가 있는 트리를 가중치가 있는 간선들로 줄 때, 트리의 지름을 구해서 출력하는 프로그램을 작성하시오.

트리의 지름이 임의의 두 노드 사이의 경로가 가장 긴 거리라고 했으므로 트리의 한 점에서 가장 먼 노드는 항상 트리의 지름을 이루는 끝 노드가 된다. 또한 지름의 양 끝 노드는 항상 단말 노드이어야 한다.

따라서 루트 노드인 1번에서부터 DFS를 이용해 탐색을 해서 가장 먼 노드를 구하면 그 점이 지름의 양 끝 노드 중 하나이고 이 노드에서부터 가장 먼 경로를 찾으면 그 경로가 트리의 지름이 된다. 이 경로의 길이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

lateinit var graph: ArrayList<ArrayList<IntArray>>
lateinit var visit: BooleanArray

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val n = nextInt()
    graph = arrayListOf()
    for(i in 0..n){
        graph.add(arrayListOf())
    }
    for(i in 1 until n){
        val parent = nextInt()
        val child = nextInt()
        val dist = nextInt()
        graph[parent].add(intArrayOf(child, dist))
        graph[child].add(intArrayOf(parent, dist))
    }
    visit = BooleanArray(n + 1)
    val (end, _) = dfs(1, 0)
    visit = BooleanArray(n + 1)
    val (_, maxDist) = dfs(end, 0)
    println(maxDist)
}

fun dfs(u: Int, dist: Int): IntArray{
    var result = intArrayOf(u, dist)
    visit[u] = true
    for((v, newDist) in graph[u]){
        if(!visit[v]){
            val next = dfs(v, dist + newDist)
            if(next[1] > result[1]){
                result = next
            }
        }
    }
    return result
}
```