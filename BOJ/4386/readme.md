# 백준 4386번: 별자리 만들기

> 문제: https://www.acmicpc.net/problem/4386

### 문제 풀이

Kruskal

별에 번호를 매겨 Union Find를 사용하기 편하게 만들기

모든 별 사이의 거리를 구하고 거리를 오름차순으로 정렬해 두 별이 연결되지 않았을 때만 연결

n - 1개의 선을 연결하고 연결된 선의 거리를 모두 더해서 소수 둘째 자리까지 출력하면 정답

### 풀이 설명

별자리를 이룰 때 모든 별이 연결되어있어야 하고 두 별을 잇는 선의 비용이 최소가 되게 이어야하기 때문에 최소 스패닝 트리로 풀 수 있음

별이 2차원 좌표계에 있기 때문에 Union Find를 이용하기 쉽게 하도록 입력받는 순서대로 번호를 붙여서 사용함

별을 잇는 선의 비용이 두 별 사이의 거리라고 했으므로 앞서 붙였던 번호를 이용해 모든 별 사이의 거리를 저장하고 오름차순으로 정렬함

정렬된 선들 사이에서 현재 살펴보는 두 별이 연결되지 않았을 경우에만 연결하고 이렇게 연결된 선들의 비용을 전부 더하면 정답

### 소스 코드
```kotlin
import java.util.ArrayList
import java.util.StringTokenizer
import kotlin.math.pow
import kotlin.math.sqrt

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val root = IntArray(n){it}
    val node = Array(n){DoubleArray(2)}
    for(i in 0 until n){
        val st = StringTokenizer(br.readLine())
        node[i][0] = st.nextToken().toDouble()
        node[i][1] = st.nextToken().toDouble()
    }
    val graph = ArrayList<DoubleArray>()
    for(i in 0 until n - 1){
        for(j in i+1 until n){
            graph.add(doubleArrayOf(i.toDouble(), j.toDouble(), distance(node, i, j)))
        }
    }
    graph.sortWith{o1, o2 -> (o1[2] - o2[2]).toInt()}
    var count = 0
    var answer = 0.0
    for(edge in graph){
        if(count == n - 1) break
        val a = edge[0].toInt()
        val b = edge[1].toInt()
        if(find(root, a) != find(root, b)){
            union(root, a, b)
            answer += edge[2]
            count++
        }
    }
    println(String.format("%.2f", answer))
}

fun distance(node: Array<DoubleArray>, i: Int, j: Int): Double{
    var result = 0.0
    if(node[i][0] > node[j][0]){
        result += (node[i][0] - node[j][0]).pow(2)
    } else {
        result += (node[j][0] - node[i][0]).pow(2)
    }

    if(node[i][1] > node[j][1]){
        result += (node[i][1] - node[j][1]).pow(2)
    } else {
        result += (node[j][1] - node[i][1]).pow(2)
    }
    return sqrt(result)
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