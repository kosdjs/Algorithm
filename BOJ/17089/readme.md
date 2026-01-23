# 백준 17089번: 세 친구

> 문제: https://www.acmicpc.net/problem/17089

### 문제 풀이

브루트포스

friends[i] = i의 친구인 사람들의 리스트

answer = 친구 수의 합 중 최솟값

친구인 세 사람을 뽑는 모든 경우를 확인해보기 위해 먼저 모든 사람인 a에 대해 친구인 리스트 friends[a]에서 두 사람을 뽑아 서로 친구인지 확인함

이때 두 사람을 중복 없이 빠르게 뽑기 위해 b, c는 인덱스를 사용함

그렇게 뽑은 b, c는 인덱스이므로 실제 사람의 번호는 friends[a][b], friends[a][c]이고 두 사람이 친구라면 서로의 리스트에 존재할 것이므로 한 사람의 리스트에 존재하는지만 확인하면 됨

그렇게 b, c에 대해 뽑은 세 사람이 모두 친구라면 서로의 리스트에 다른 두 친구들이 존재할 것이므로 인당 2명씩을 빼야하므로 친구의 총 합에서 6명을 빼면 됨

그렇게 뽑힌 인원의 친구 수를 계산해 최솟값을 answer에 저장함

모든 인원에 대해 반복이 끝났을 때 answer에 초기값인 Int.MAX_VALUE가 저장되어 있다면 세 친구를 못 찾은 것이므로 -1, 아니라면 친구 수의 합 중 최솟값이 저장된 것이므로 출력하면 정답

### 풀이 설명

세 사람 A, B, C가 모두 친구이고 친구 수에서 서로를 빼기 때문에 각 사람의 친구 수에서 2명씩 빼기 때문에 A, B, C의 친구 수 합에서 6을 빼면 된다.

그럼 이제 A, B, C를 어떻게 고를지를 고민해야 하는데 친구 관계가 주어지므로 A의 친구 둘을 뽑아 그 둘이 친구인지를 확인하면 된다.

그렇게 모든 A에 대해서 친구 둘씩을 뽑아 서로 친구 관계라면 조건에 맞는 세 사람을 뽑은 것이므로 A, B, C의 친구 수 총 합에서 6을 뺀 값의 최솟값을 뽑아 출력하면 된다. 그런 세 사람이 한 번도 뽑히지 않는다면 정답을 저장하는 값이 초기값에서 바뀌지 않았을 것이므로 초기값에서 바뀌지 않았다면 -1을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val friends = Array(N + 1){mutableListOf<Int>()}
    repeat(M){
        val A = nextInt()
        val B = nextInt()
        friends[A].add(B)
        friends[B].add(A)
    }
    var answer = Int.MAX_VALUE
    for(a in 1..N){
        for(b in friends[a].indices){
            for(c in b + 1 until friends[a].size){
                if(friends[a][c] in friends[friends[a][b]]){
                    answer = minOf(answer, friends[a].size + friends[friends[a][b]].size + friends[friends[a][c]].size - 6)
                }
            }
        }
    }
    println(if(answer != Int.MAX_VALUE) answer else -1)
}
```