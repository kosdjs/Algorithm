#백준 20040번: 사이클 게임

> 문제: https://www.acmicpc.net/problem/20040

### 문제 풀이

Union Find

배열에 연결된 노드 중 번호가 가장 빠른 노드(루트 노드)의 번호를 저장함

두 노드를 연결할 때 루트 노드가 같다면 두 노드가 이미 연결되어 있기 때문에 이 두 노드를 연결하면 사이클이 생김 따라서 이 때 횟수가 정답이 됨

### 풀이 설명

문제에서 한번에 한 간선을 만들고, 그래프에서 간선을 만들 때 사이클이 만들어지려면 연결하려는 두 노드가 이미 간선을 통해 연결되어 있어야 한다

따라서 연결하려는 두 노드가 그래프로 이미 연결되었는지 확인하는 알고리즘이 필요하다

Union Find를 이용해 루트 노드가 같다면 두 노드가 이미 연결되어있는 것이고 이 사이에 간선을 생성하면 사이클이 생기기 때문에 그 횟수가 정답이 된다

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val root = IntArray(n){it}
    var answer = 0
    for(i in 1..m){
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        if(union(root, a, b)){
            answer = i
            break
        }
    }
    println(answer)
}

fun find(root: IntArray, idx: Int): Int{
    if(root[idx] == idx){
        return idx
    } else {
        root[idx] = find(root, root[idx])
        return root[idx]
    }
}

fun union(root: IntArray, a: Int, b: Int): Boolean{
    val aFind = find(root, a)
    val bFind = find(root, b)
    if(aFind == bFind){
        return true
    } else {
        if(aFind > bFind){
            root[aFind] = bFind
        } else {
            root[bFind] = aFind
        }
        return false
    }
}
```