# 백준 4803번: 트리

> 문제: https://www.acmicpc.net/problem/4803

### 문제 풀이

Union Find

배열에 연결된 노드 중 번호가 가장 빠른 노드(루트 노드)의 번호를 저장함

루트 노드의 개수가 트리의 개수, 사이클이 생기면 루트 노드를 0으로 처리해 트리가 성립하지 않음을 알림

### 풀이 설명

트리의 개수를 세려면 트리를 구성하는 루트 노드의 개수를 세면 된다

Union Find를 이용해 배열에 본인의 번호가 저장되어있다면 그 번호의 노드가 루트 노드이므로 트리의 개수를 쉽게 셀 수 있다

합칠 때 루트 노드가 같은 두 노드를 합친다면 사이클이 생기는 것이므로 이 노드의 루트 노드를 0으로 바꿔 사이클이 생긴 그래프를 트리로 세지 않게 한다

이렇게 해서 최종적으로 배열에 본인 번호가 저장되어있는 노드만 세면 트리의 갯수가 된다

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var case = 1
    while(true){
        var st = StringTokenizer(br.readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()
        if(n == 0 && m == 0){
            break
        }
        var count = 0
        val root = IntArray(n + 1){it}
        for(i in 1..m){
            st = StringTokenizer(br.readLine())
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            union(root, a, b)
        }
        for(i in 1..n){
            if(find(root, i) == i){
                count++
            }
        }
        if(count == 0){
            println("Case ${case}: No trees.")
        } else if(count == 1){
            println("Case ${case}: There is one tree.")
        } else {
            println("Case ${case}: A forest of ${count} trees.")
        }
        case++
    }
}

fun union(root: IntArray, a: Int, b: Int){
    val aFind = find(root, a)
    val bFind = find(root, b)
    if(aFind == bFind){
        root[aFind] = 0
        root[bFind] = 0
    } else {
        if(aFind < bFind){
            root[bFind] = aFind
        } else {
            root[aFind] = bFind
        }
    }
}

fun find(root: IntArray, idx: Int): Int{
    if(root[idx] == idx){
        return idx
    } else {
        root[idx] = find(root, root[idx])
        return root[idx]
    }
}
```