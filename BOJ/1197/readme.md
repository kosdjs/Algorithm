# 백준 1197번: 최소 스패닝 트리

> 문제: https://www.acmicpc.net/problem/1197

### 문제 풀이

Kruskal

간선을 가중치 기준 오름차순으로 정렬 후 사이클이 생기지 않도록 연결

### 풀이 설명

최소 스패닝 트리의 정의에 따라 모든 정점들을 연결하지만 사이클이 생겨서는 안된다

또한 가중치가 최소로 되어야 하기 때문에 간선을 가중치 기준 오름차순으로 정렬해야 함

정렬된 간선을 Union Find를 이용해 간선의 두 정점이 연결되어있는지 확인함

연결되어있지 않다면 두 정점을 잇는 간선 중 가중치가 가장 낮은 간선이기 때문에 선택해 연결함

연결되어있다면 사이클이 생기므로 건너뜀

이 과정을 모든 정점이 연결될 때 까지, 정점이 V개라면 간선이 V - 1개 선택될 때까지 반복한다

과정을 통해 연결된 간선의 가중치의 합을 구하면 정답

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())

    val V = st.nextToken().toInt()
    val E = st.nextToken().toInt()
    val root = IntArray(V + 1){it}
    val edges = ArrayList<IntArray>()
    for(i in 1..E){
        st = StringTokenizer(br.readLine())
        val A = st.nextToken().toInt()
        val B = st.nextToken().toInt()
        val C = st.nextToken().toInt()
        edges.add(intArrayOf(A, B, C))
    }
    var answer = 0
    edges.sortWith{ o1, o2 ->
        o1[2] - o2[2]
    }
    for(edge in edges){//간선이 선택된 개수를 고려하지 않았지만 고려하면 실행 속도가 빨라질 수 있음
        if(find(root, edge[0]) != find(root, edge[1])){
            answer += edge[2]
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

>참고: https://4legs-study.tistory.com/111