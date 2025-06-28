# 백준 1939번: 중량제한

> 문제: https://www.acmicpc.net/problem/1939

### 문제 풀이

Union Find

다리를 중량제한의 내림차순으로 정렬한 후 하나씩 연결하면서 두 공장이 연결되었는지 확인하고 처음 연결되었을 때 그 다리의 중량제한이 공장 사이에서 옮길 수 있는 물품의 최대 중량이 됨

### 풀이 설명

한 번 이동에서 옮길 수 있는 물품의 최대 중량은 공장 사이의 경로 중에서 다리의 중량제한의 최솟값 중에 최댓값이 되는 값을 찾는 것이다

다시 말해 다리의 중량제한이 최대한 큰것만 사용해서 경로를 만들면 된다

다리의 정보를 받아 리스트에 저장하고 중량제한을 기준으로 내림차순으로 정렬한다

정렬한 다리를 Union Find를 이용해 하나씩 연결하면서 공장이 연결되었는지 두 섬에 대해 Find를 진행해 루트 노드가 같다면 그 때 연결한 다리가 연결된 경로 중 중량제한이 최솟값이 되는 다리이므로 정답이 된다

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val root = IntArray(N+1){it}
    val bridge = ArrayList<IntArray>()
    for(i in 1..M){
        st = StringTokenizer(br.readLine())
        val A = st.nextToken().toInt()
        val B = st.nextToken().toInt()
        val C = st.nextToken().toInt()
        bridge.add(intArrayOf(A, B, C))
    }
    st = StringTokenizer(br.readLine())
    var answer = 0
    val start = st.nextToken().toInt()
    val end = st.nextToken().toInt()
    bridge.sortWith {o1, o2 -> o2[2] - o1[2]}
    for(b in bridge){
        union(root, b[0], b[1])
        if(find(root, start) == find(root, end)){
            answer = b[2]
            break
        }
    }
    println(answer)
}

fun union(root: IntArray, a: Int, b:Int){
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
        return idx
    } else {
        root[idx] = find(root, root[idx])
        return root[idx]
    }
}
```