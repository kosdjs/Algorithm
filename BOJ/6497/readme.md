# 백준 6497번: 전력난

> 문제: https://www.acmicpc.net/problem/6497

### 문제 풀이

Kruskal

도로를 길이 기준으로 오름차순으로 정렬하고 두 집이 연결되지 않았을 때만 연결

도로를 m - 1개 연결하면 모든 집이 연결됨

이 때 나오는 비용을 전체 비용에서 빼면 정답

### 풀이 설명

모든 집이 연결되어야 하고 비용을 최소로 해야 되기 때문에 최소 스패닝 트리를 구하면 됨

비용이 도로의 길이와 같기 때문에 길이를 기준으로 정렬해 최소 스패닝 트리를 구할 수 있음

이렇게 구한 최소 스패닝 트리의 비용을 전체 비용에서 빼면 절약할 수 있는 최대 비용이 됨으로 정답이 된다

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    while (true){
        var st = StringTokenizer(br.readLine())
        val m = st.nextToken().toInt()
        val n = st.nextToken().toInt()
        if(m == 0 && n == 0) break
        val root = IntArray(m){it}
        val graph = ArrayList<IntArray>()
        var answer = 0
        for(i in 1..n){
            st = StringTokenizer(br.readLine())
            val x = st.nextToken().toInt()
            val y = st.nextToken().toInt()
            val z = st.nextToken().toInt()
            answer += z
            graph.add(intArrayOf(x, y, z))
        }
        graph.sortWith{ o1, o2 ->
            o1[2] - o2[2]
        }
        var min = 0
        var count = 0
        for(edge in graph){
            if(count == m - 1) break
            if(find(root, edge[0]) != find(root, edge[1])){
                min += edge[2]
                count++
                union(root, edge[0], edge[1])
            }
        }
        answer -= min
        println(answer)
    }
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