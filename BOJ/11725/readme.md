# 백준 11725번: 트리의 부모 찾기

> 문제: https://www.acmicpc.net/problem/11725

### 문제 풀이

DFS

parents[i] = i번 노드의 부모 노드

visit[i] = i번 노드 방문 여부

dfs(i) = i번 노드부터 DFS로 트리를 탐색하며 i번 노드의 자식 노드를 찾는 함수

루트 노드부터 DFS로 탐색하며 연결된 자식 노드의 번호마다 parents 배열에 현재 노드의 값을 저장하면 루트 노드를 제외한 모든 노드의 부모 노드 번호를 저장 가능함

이에 따라 parents 배열에 저장된 값을 2번 노드부터 출력하면 정답

### 풀이 설명

루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.

트리의 특성상 부모 노드와 자식 노드는 연결되어 있다. 따라서 DFS를 이용해 루트 노드에서부터 트리를 탐색하며 방문처리를 하면 현재 노드와 연결되어있는 노드 중 방문하지 않은 노드들이 현재 노드의 자식 노드이므로 그 노드의 부모 노드를 현재 노드로 저장하면 된다.

따라서 트리를 인접 리스트의 형태로 저장한 후에 부모 노드를 저장하기 위한 배열 parents, 방문 여부를 저장하는 visit, DFS로 탐색을 하며 자식 노드를 찾아 부모 노드를 저장하는 함수 dfs를 이용해 루트 노드인 1번 노드부터 탐색을 해서 1번 노드를 제외한 모든 노드의 부모 노드를 parents 배열에 저장하고 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt():Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val graph = ArrayList<ArrayList<Int>>()
    for(i in 0..N){
        graph.add(arrayListOf())
    }
    for(i in 1 until N){
        val u = nextInt()
        val v = nextInt()
        graph[u].add(v)
        graph[v].add(u)
    }
    val parents = IntArray(N + 1)
    val visit = BooleanArray(N + 1)
    fun dfs(u: Int){
        visit[u] = true
        for(v in graph[u]){
            if(!visit[v]){
                parents[v] = u
                dfs(v)
            }
        }
    }
    dfs(1)
    val bw = System.out.bufferedWriter()
    for(i in 2..N){
        bw.append("${parents[i]}")
        bw.newLine()
    }
    bw.flush()
    bw.close()
}
```