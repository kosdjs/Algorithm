# 백준 14712번: 넴모넴모 (Easy)

> 문제: https://www.acmicpc.net/problem/14712

### 문제 풀이

브루트 포스, 백트래킹

map[i][j] = (i, j) 칸에 넴모가 있는지 여부

check(count) = 현재 count칸 만큼 넴모를 놓거나 놓지 않았을 때 다음 칸에 넴모를 놓거나 놓지 않는 재귀 함수

answer = N x M 칸에 2 x 2 사각형이 생기지 않도록 넴모를 놓는 경우의 수

넴모를 한 칸씩 놓거나 놓지 않으며 2 x 2 사각형이 없는 경우의 수를 세어야 함

왼쪽 위 칸부터 순서대로 칸에 넴모를 놓거나 놓지 않는 식으로 모든 칸을 확인할 수 있음

넴모를 놓을 때 왼쪽, 위, 왼쪽 위 칸에 넴모가 있으면 2 x 2 사각형이 생기므로 넴모를 놓을 수 없음

check(count) 함수를 이용해 맨 왼쪽 위 칸부터 한 칸씩 넴모를 놓거나 놓지 않으며 마지막 칸까지 확인한 경우의 수를 answer를 1 증가시켜 저장함

그렇게 answer에 저장된 값이 구하는 경우의 수이므로 출력하면 정답

### 풀이 설명

N x M 칸이 있을 때 "넴모"가 2 x 2 사각형을 이루지 않도록 올릴 수 있는 경우의 수를 구하는 문제이다.

문제에서 칸이 최대 25칸이라고 했으므로 전체 경우의 수는 $2^{25}$가 되고 이는 3천만정도 횟수가 되므로 모든 경우의 수를 탐색해 확인할 수 있는 경우의 수가 된다.

넴모를 2 x 2 사각형을 이루지 않도록 놓으려면 왼쪽 칸, 위쪽 칸, 왼쪽 위 칸에 넴모가 있으면 그 칸에는 넴모를 놓을 수 없게 되는 것이다.

즉, 넴모를 놓으려면 왼쪽, 왼쪽 위, 위 칸에 넴모가 있는지 확인해야 하므로 맨 왼쪽 위 칸부터 오른쪽으로 한 칸씩 넴모를 놓거나 놓지 않는 경우의 수를 확인해보면 된다.

경우의 수를 구하기 위해 이를 저장할 변수 answer를 선언하고, 넴모가 판에 놓여있는지 정보를 저장할 배열 map을 선언한다.

그러면 맨 왼쪽 위 칸에서부터 한 칸씩 넴모를 놓거나 놓지 않아야 하므로 이를 한 칸씩 확인하는 check(count) 재귀 함수를 만들 수 있다.

이렇게 만들어진 재귀 함수를 이용해 2 x 2 사각형이 생기지 않는 경우의 수를 answer에 저장하고 출력하면 정답이 된다.

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
    val map = Array(N){ BooleanArray(M) }
    var answer = 0
    fun check(count: Int){
        if(count == N * M){
            answer++
            return
        }
        val i = count / M
        val j = count % M
        map[i][j] = false
        check(count + 1)
        if(i == 0 || j == 0 || !(map[i - 1][j - 1] && map[i - 1][j] && map[i][j - 1])){
            map[i][j] = true
            check(count + 1)
            map[i][j] = false
        }
    }
    check(0)
    println(answer)
}
```