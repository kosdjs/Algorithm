# 백준 1647번: 도시 분할 계획

> 문제: https://www.acmicpc.net/problem/1647

### 문제 풀이

Kruskal

길을 유지비 기준 오름차순으로 정렬 후 N - 2개 연결하고 유지비를 모두 더하면 정답

### 풀이 설명

최소 스패닝 트리가 모든 정점을 최소 비용으로 잇는 그래프이기 때문에 최소 스패닝 트리에서 비용이 제일 높은 간선을 제거한다면 문제 상황에 필요한 것처럼 정점을 두 집합으로 나눠 최소 비용으로 잇는 상황이 됨

따라서 최소 스패닝 트리를 구하는 Kruskal 알고리즘을 이용하고 원래 최소 스패닝 트리는 간선을 N - 1개 사용하니까 문제 상황에 맞게 간선을 N - 2개만 연결하면 정답이 나옴

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val root = IntArray(N + 1){it}
    val graph = ArrayList<IntArray>()
    for(i in 1..M){
        st = StringTokenizer(br.readLine())
        val A = st.nextToken().toInt()
        val B = st.nextToken().toInt()
        val C = st.nextToken().toInt()
        graph.add(intArrayOf(A, B, C))
    }
    graph.sortWith{o1, o2 -> o1[2] - o2[2]}
    var answer = 0
    var count = 0
    for(edge in graph){
        if(count == N - 2){
            break
        }
        if(find(root, edge[0]) != find(root, edge[1])){
            union(root, edge[0], edge[1])
            answer += edge[2]
            count++
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