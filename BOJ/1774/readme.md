# 백준 1774번: 우주신과의 교감

> 문제: https://www.acmicpc.net/problem/1774

### 문제 풀이

Kruskal

통로의 길이가 최소가 되어야 하고 모든 정점이 연결되어있어야 하기 때문에 최소 스패닝 트리

이미 연결된 통로 외에 나머지 통로를 길이를 기준으로 오름차순으로 정렬

정렬된 통로를 두 정점이 연결되지 않은 통로만 연결

연결한 통로의 길이를 모두 더하면 정답

### 풀이 설명

아직 연결되지 않은 우주신과 연결을 해야 하고 연결하는 통로의 비용이 최소가 되어야 하기 때문에 Kruskal 알고리즘을 사용해 연결할 수 있는 통로 중 비용이 최소로 드는 통로만 연결한다면 최소 비용을 찾을 수 있음

Union Find를 이용해 이미 연결된 관계를 나타내고 통로를 연결되지 않은 경우에만 리스트에 저장한다

그 후 비용을 기준으로 오름차순으로 정렬한 후 통로를 확인해 두 정점이 연결되지 않았을 때만 연결함

이 과정을 모든 정점이 연결될 때까지 하면 모든 정점이 연결되고 이때 드는 비용이 정답이 됨

### 소스 코드
```kotlin
import java.util.StringTokenizer
import kotlin.math.pow
import kotlin.math.sqrt

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val root = IntArray(N + 1){it}
    val node = Array<IntArray>(N + 1){IntArray(2)}
    for(i in 1..N){
        st = StringTokenizer(br.readLine())
        node[i][0] = st.nextToken().toInt()
        node[i][1] = st.nextToken().toInt()
    }
    for(i in 1..M){
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        union(root, a, b)
    }
    val graph = ArrayList<DoubleArray>()
    for(i in 1..N){
        for(j in i + 1..N){
            if(find(root, i) != find(root, j)){
                graph.add(doubleArrayOf(i.toDouble(), j.toDouble(), area(node, i, j)))
            }
        }
    }
    graph.sortWith{ o1, o2 -> if (o1[2] < o2[2]) -1 else if (o1[2] == o2[2]) 0 else 1 }
    var answer = 0.0
    for(edge in graph){
        if(find(root, edge[0].toInt()) != find(root, edge[1].toInt())){
            answer += edge[2]
            union(root, edge[0].toInt(), edge[1].toInt())
        }
    }
    println("%.2f".format(answer))
}

fun area(node: Array<IntArray>, a: Int, b: Int): Double{
    var result = 0.0
    result += (node[a][0] - node[b][0]).toDouble().pow(2)
    result += (node[a][1] - node[b][1]).toDouble().pow(2)
    result = sqrt(result)
    return result
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