# 백준 1167번: 트리의 지름

> 문제: https://www.acmicpc.net/problem/1167

### 문제 풀이

트리, DFS

graph[i] = i번 노드와 연결된 노드의 번호와 그 노드까지의 거리

visit[i] = i번 노드 방문 여부

end = 지름의 양 끝 노드 중 한 노드

maxDist = 지름의 끝 노드에서 가장 먼 노드까지의 거리

지름의 양 끝 노드 중 한 노드를 구하기 위해 1번 노드에서부터 dfs를 이용해 가장 먼 노드인 end를 구함

end에서부터 가장 먼 경로인 트리의 지름을 구하기 위해 dfs를 이용해 구하고 출력하면 정답

### 풀이 설명

트리의 지름이란, 트리에서 임의의 두 점 사이의 거리 중 가장 긴 것을 말한다. 트리의 지름을 구하는 프로그램을 작성하시오.

트리가 입력으로 주어진다. 먼저 첫 번째 줄에서는 트리의 정점의 개수 V가 주어지고 (2 ≤ V ≤ 100,000)둘째 줄부터 V개의 줄에 걸쳐 간선의 정보가 다음과 같이 주어진다. 정점 번호는 1부터 V까지 매겨져 있다.

먼저 정점 번호가 주어지고, 이어서 연결된 간선의 정보를 의미하는 정수가 두 개씩 주어지는데, 하나는 정점번호, 다른 하나는 그 정점까지의 거리이다. 예를 들어 네 번째 줄의 경우 정점 3은 정점 1과 거리가 2인 간선으로 연결되어 있고, 정점 4와는 거리가 3인 간선으로 연결되어 있는 것을 보여준다. 각 줄의 마지막에는 -1이 입력으로 주어진다. 주어지는 거리는 모두 10,000 이하의 자연수이다.

트리의 지름이 임의의 두 점 사이의 거리 중 가장 긴 것이므로 임의의 한 점에서 가장 먼 점을 구하면 그 점이 지름을 이루는 임의의 두 점 중 하나의 점이 되게 된다. 따라서 1번 정점에서 DFS를 이용해 가장 먼 점인 end를 구한다.

구한 end가 트리의 지름을 이루는 두 점중 하나이므로 이 점에서부터 가장 먼 점까지의 거리를 구하면 그 거리가 트리의 지름이 되므로 다시 DFS를 이용해 가장 먼 거리 maxDist를 구하면 이 거리가 트리의 지름이 되므로 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val V = nextInt()
    val graph = ArrayList<ArrayList<IntArray>>()
    var visit = BooleanArray(V + 1)
    for(i in 0..V){
        graph.add(arrayListOf())
    }
    for(i in 1..V){
        val u = nextInt()
        while (true){
            val v = nextInt()
            if(v == -1){
                break
            }
            val dist = nextInt()
            graph[u].add(intArrayOf(v, dist))
            graph[v].add(intArrayOf(u, dist))
        }
    }
    fun dfs(u: Int, dist: Int): IntArray{
        var result = intArrayOf(u, dist)
        visit[u] = true
        for((v, vDist) in graph[u]){
            if(!visit[v]){
                val next = dfs(v, dist + vDist)
                if(next[1] > result[1]) result = next
            }
        }
        return result
    }
    val (end, _) = dfs(1, 0)
    visit = BooleanArray(V + 1)
    val (_, maxDist) = dfs(end, 0)
    println(maxDist)
}
```