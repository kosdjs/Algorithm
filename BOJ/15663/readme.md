# 백준 15663번: N과 M (9)

> 문제: https://www.acmicpc.net/problem/15663

### 문제 풀이

백트래킹

num = N개의 자연수

pick = 뽑은 M개의 수

visit = N개의 자연수 중 현재까지 뽑힌 숫자가 어느 것인지 나타내는 배열

level = 재귀 함수에서 뽑힌 숫자가 몇개인지 나타내는 인수

last = 현재 단계에서 마지막으로 뽑힌 숫자

수열을 사전순으로 증가하는 순서로 뽑기 위해 num을 오름차순으로 정렬함

현재 뽑힌 숫자를 나타내는 level을 인수로 받는 dfs 재귀 함수를 다음과 같이 정의함

- 뽑힌 숫자의 개수를 확인해 M개를 뽑았다면 pick 배열을 확인해 뽑은 수열을 출력하고 함수 반환함

- N개의 수를 모두 확인해 이전에 뽑히지 않았고 마지막에 뽑힌 숫자와 같지 않은 숫자를 뽑아 다음 level로 넘김

정의한 재귀 함수 dfs를 이용해 모든 수열을 출력하면 정답

### 풀이 설명

N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

- N개의 자연수 중에서 M개를 고른 수열

N개의 자연수 중 M개를 뽑았을 때 순서에 따라 수열이 달라지므로 N개에서 M개를 뽑는 순열을 모두 출력하는 문제인데 N개의 자연수 중 중복된 숫자가 있을 수 있는 문제다.

먼저 출력에서 사전순으로 증가하는 순서대로 출력해야 한다고 했으므로 N개의 자연수를 오름차순으로 정렬해놓고 수를 뽑아야 한다.

순열은 재귀 함수로 뽑을 수 있고 중복된 수열을 만들지 않아야 하므로 같은 위치에서 같은 수를 뽑는 것을 막아야 한다.

이를 위해 현재 단계에서 마지막으로 뽑은 수를 last라는 변수에 저장해서 현재 확인하는 수와 비교해 같을 때 현재 수를 넘어가는 방식으로 조합을 뽑으면 중복되는 수열을 건너뛸 수 있다. 또한 순열을 뽑는 것이므로 현재까지 뽑힌 수를 제외한 나머지를 모두 확인해야 하므로 현재 뽑은 수를 나타내기 위한 BooleanArray를 사용해야 한다.

이에 맞게 정의한 재귀함수를 이용해 수열들을 출력하면 정답이 된다.

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
    val num = IntArray(N)
    for(i in 0 until N){
        num[i] = nextInt()
    }
    num.sort()
    val pick = IntArray(M)
    val bw = System.out.bufferedWriter()
    val visit = BooleanArray(N)
    fun dfs(level: Int){
        if(level == M){
            for(i in 0 until M){
                bw.append("${pick[i]} ")
            }
            bw.newLine()
            return
        }
        var last = 0 // 현재 level에서 마지막으로 사용한 숫자
        for(i in 0 until N){
            val cur = num[i]
            if(visit[i]) continue
            if(last == cur) continue // 현재 숫자가 마지막으로 사용한 숫자와 같으면 건너뜀

            visit[i] = true
            last = cur
            pick[level] = cur
            dfs(level + 1)
            visit[i] = false
        }
    }
    dfs(0)
    bw.flush()
    bw.close()
}
```