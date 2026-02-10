# 백준 7511번: 소셜 네트워킹 어플리케이션

> 문제: https://www.acmicpc.net/problem/7511

### 문제 풀이

Union Find

root[i] = i번 정점과 연결된 정점의 번호 중 가장 빠른 번호

find(x) = x번 정점과 연결된 정점의 번호 중 가장 빠른 번호를 찾는 함수

union(a, b) = a, b번 정점을 연결하는 함수

입력으로 주어지는 친구 관계인 a, b 정점마다 union(a, b) 함수를 실행해 두 정점을 연결함

u, v 정점과 연결된 정점의 번호 중 가장 빠른 번호를 각각 find(u), find(v)로 찾아 비교한 이후 같다면 연결된 것이므로 1, 아니면 0을 출력하면 정답

### 풀이 설명

친구 관계가 주어졌을 때 두 사람이 친구 관계로 연결되어 있는지를 출력하는 문제이다. 친구 관계를 그래프로 생각하면 두 정점이 연결되어 있는지 여부를 확인하는 문제가 된다.

각 정점마다 연결된 가장 빠른 번호의 정점이 몇 번인지 저장해 놓고 이 번호가 같다면 연결되어 있는 것으로 생각하면 된다. 이는 Union Find를 이용해 구현할 수 있다.

이를 구현하기 위해 두 정점 a, b를 연결하는 함수 union(a, b) 함수와 정점 x와 연결된 정점의 번호 중 가장 빠른 번호를 찾는 함수 find(x)를 정의한다.

그 이후에 친구 관계로 주어지는 a, b 정점을 union(a, b) 함수를 이용해 모두 연결하고 친구 관계 그래프상 연결되어 있는지 판단해야 하는 u, v 정점에 대해 find(u), find(v) 값을 비교해 같다면 연결되어 있으므로 1을 출력, 아니라면 0을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val t = nextInt()
    val sb = StringBuilder()
    for(i in 1..t){
        sb.append("Scenario $i:\n")
        val n = nextInt()
        val k = nextInt()
        val root = IntArray(n){it}
        fun find(x: Int): Int{
            if(root[x] != x){
                root[x] = find(root[x])
            }
            return root[x]
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
        repeat(k){
            val a = nextInt()
            val b = nextInt()
            union(a, b)
        }
        val m = nextInt()
        repeat(m){
            val u = nextInt()
            val v = nextInt()
            sb.append(if(find(u) == find(v)) 1 else 0).append("\n")
        }
        sb.append("\n")
    }
    print(sb)
}
```