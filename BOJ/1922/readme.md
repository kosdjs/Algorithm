# 백준 1922번: 네트워크 연결

> 문제: https://www.acmicpc.net/problem/1922

### 문제 풀이

Kruskal

선을 비용을 기준으로 오름차순으로 정렬 후 두 정점이 연결되어있지 않다면 연결

선을 N - 1개 연결하면 모든 정점이 연결됨

### 풀이 설명

모든 컴퓨터가 이어질 수 있게 최소 비용을 사용해 선으로 연결한다는 점에서 최소 스패닝 트리를 만드는 문제라는 것을 알 수 있다

따라서 선을 비용을 기준으로 오름차순 정렬을 한 후에 하나씩 확인하며 두 컴퓨터가 연결되어있지 않다면 연결을 한다

정점이 N개이므로 모든 정점이 연결되려면 최소 N - 1개의 간선이 필요하다

따라서 연결한 선이 N - 1개가 될때까지 연결을 해서 최소 비용을 구할 수 있다

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val M = br.readLine().toInt()
    val root = IntArray(N + 1){it}
    val edges = ArrayList<IntArray>()
    for(i in 1..M){
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        edges.add(intArrayOf(a, b, c))
    }
    edges.sortWith{o1, o2 -> o1[2] - o2[2]}
    var count = 0
    var answer = 0
    for(edge in edges){
        if(find(root, edge[0]) != find(root, edge[1])){
            union(root, edge[0], edge[1])
            answer += edge[2]
            count++
        }
        if(count == N - 1) break
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