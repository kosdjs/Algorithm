# 백준 16398번: 행성 연결

> 문제: https://www.acmicpc.net/problem/16398

### 문제 풀이

Kruskal

간선을 비용을 기준으로 오름차순으로 정렬하고 간선의 두 정점이 연결되었는지 확인

연결되지 않았다면 그 때 비용을 답에 더하고 두 정점을 연결

위의 방식으로 모든 정점이 연결되도록 N - 1개의 간선을 연결하면 그 때 비용의 총 합이 정답

### 풀이 설명

행성을 정점이라고 생각하고 플로우를 간선이라고 생각하면 모든 행성을 이을 수 있게 최소 비용이 들도록 플로우를 연결하는 문제는 정점을 최소 비용으로 잇는 그래프를 구하는 최소 스패닝 트리를 구하는 것이 됨

따라서 Kruskal 알고리즘을 사용해 최소 스패닝 트리를 구하고 그 때의 비용을 출력하면 되는 문제

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val root = IntArray(N + 1){it}
    val graph = ArrayList<IntArray>()
    for(i in 1..N){
        val st = StringTokenizer(br.readLine())
        for(j in 1..N){
            val flow = st.nextToken().toInt()
            if(i != j){
                graph.add(intArrayOf(i, j, flow))
            }
        }
    }
    graph.sortWith{ o1, o2 -> o1[2] - o2[2]}
    var answer = 0L
    var count = 0
    for(edge in graph){
        if(count == N - 1) break
        if(find(root, edge[0]) != find(root, edge[1])){
            answer += edge[2].toLong()
            count++
            union(root, edge[0], edge[1])
        }
    }
    println(answer)
}

fun union(root: IntArray, a: Int, b: Int){
    val aFind = find(root, a)
    val bFind = find(root, b)
    if(aFind < bFind){
        root[bFind] = aFind
    } else if(aFind > bFind){
        root[aFind] = bFind
    }
}

fun find(root: IntArray, idx: Int): Int{
    if(root[idx] == idx){
        return root[idx]
    } else {
        root[idx] = find(root, root[idx])
        return root[idx]
    }
}
```