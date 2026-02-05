# 백준 17352번: 여러분의 다리가 되어 드리겠습니다!

> 문제: https://www.acmicpc.net/problem/17352

### 문제 풀이

Union Find

root[i] = i번 섬과 연결되어 있는 섬 중 가장 빠른 번호

find(x) = x번 섬과 연결되어 있는 섬 중 가장 빠른 번호인 섬의 번호를 찾는 함수

union(a, b) = a, b번 섬을 연결하는 함수

N개의 섬이 1번 부터 N번 까지 있고 N - 2번 연결되어 있으므로 입력받은 섬들을 union 함수로 연결해 다리로 연결된 섬들을 연결함

이렇게 섬들을 연결하면 find로 찾은 가장 빠른 번호가 다르면 다른 집합에 있는 것이므로 이 번호가 다른 두 섬을 연결하면 됨

이 두 섬을 뽑는 가장 쉬운 방법은 root[i]의 저장된 번호가 i인 섬, 즉 자기 자신이 연결된 섬들 중 가장 번호가 빠른 섬을 뽑으면 됨

그러므로 1부터 N까지 반복해 find(i) 함수로 찾은 가장 빠른 번호가 i인 섬을 찾아 i를 출력해주면 정답

### 풀이 설명

두 집합으로 나뉘게 된 N개의 섬 중 두 섬을 이어 한 집합이 될 수 있도록 두 섬을 뽑는 문제이다.

그러면 두 집합에서 각각 섬을 하나씩 뽑아서 출력하면 되므로, 섬이 어떤 집합에 속하는지 찾는 방법이 필요하다. Union Find를 이용하면 배열에 그 섬과 연결된 가장 빠른 번호인 섬의 번호를 저장하므로 이를 통해 집합을 나눌 수 있다.

그러므로 N - 2개의 다리 정보가 주어지는 대로 union(a, b) 함수를 이용하면 root배열에 그 섬과 연결된 가장 빠른 번호를 저장하게 된다. 그럼 이를 이용해 각 집합에서 한 섬만 뽑는 방법을 생각해야 한다.

root 배열에 저장되는 번호가 그 섬과 연결된 섬 중 번호가 가장 빠른 섬의 번호라고 했으므로 이 번호가 본인의 번호가 같은 섬은 집합에서 한 개밖에 없다는 점을 이용하면 된다. 따라서 i를 1부터 N까지 반복하며 i와 root[i]를 비교해서 같은 섬의 번호를 출력하면 된다.

그러나 합치는 과정에서 root 배열이 제대로 갱신되지 않을 수 있으므로 이 배열에 접근할 때는 항상 find(x) 함수를 이용해 값을 가져오는 것이 좋다. 따라서 i와 find(i)를 비교해 같을 때 i를 출력해주면 두 집합에서 각각 한 섬의 번호만 출력되므로 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val root = IntArray(N + 1){it}
    fun find(x: Int): Int{
        if(root[x] == x){
            return x
        } else {
            root[x] = find(root[x])
            return root[x]
        }
    }
    fun union(a: Int, b: Int){
        val aFind = find(a)
        val bFind = find(b)
        if(aFind < bFind){
            root[bFind] = aFind
        } else {
            root[aFind] = bFind
        }
    }
    repeat(N - 2){
        val u = nextInt()
        val v = nextInt()
        union(u, v)
    }
    for(i in 1..N){
        if(i == find(i)){
            print("$i ")
        }
    }
}
```