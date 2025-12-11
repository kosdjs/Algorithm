# 백준 15652번: N과 M (4)

> 문제: https://www.acmicpc.net/problem/15652

### 문제 풀이

백트래킹

pick = 뽑은 수를 저장할 배열

dfs(level, last) = 현재 level개의 수를 뽑았고 마지막으로 뽑은 수가 last

sb = 뽑은 수열을 문자열로 저장할 StringBuilder

level이 M이 될때까지 last보다 크거나 같고 N보다 작거나 같은 수를 뽑아 pick에 저장하도록 재귀함수를 구성하고 dfs(0, 1)을 호출하면 1부터 뽑는 모든 경우를 확인할 수 있음

이에 따라 수열을 뽑은 결과가 sb에 저장되므로 저장된 문자열을 출력하면 정답

### 풀이 설명

자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

- 1부터 N까지 자연수 중에서 M개를 고른 수열
- 같은 수를 여러 번 골라도 된다.
- 고른 수열은 비내림차순이어야 한다.
  - 길이가 K인 수열 A가 $A_1$ ≤ $A_2$ ≤ ... ≤ $A_{K-1}$ ≤ $A_K$를 만족하면, 비내림차순이라고 한다.

같은 수를 여러번 골라도 되지만 수열이 비내림차순(오름차순)이어야 하므로 수를 뽑을때 항상 앞에 뽑은 수보다 크거나 같은 수를 뽑아야 한다.

이에 따라 재귀함수의 인수로 현재 몇개의 숫자를 뽑았는지인 level과 마지막으로 뽑았던 수 last를 넘겨서 last보다 크거나 같고 N보다 작거나 같은 모든 수를 한번씩 뽑았을 때를 확인해 모든 경우를 확인하면 된다.

이때 뽑은 수를 저장할 배열이 필요하므로 pick이라는 배열을 만들어두고 뽑은 수를 저장해놓는다. 그 이후에 level이 M이 되면 M개의 수를 모두 뽑은 것이므로 뽑은 수열을 출력해야 한다. 이 문제는 출력이 길기 때문에 바로 출력하는 것보다 StringBuilder를 이용해 출력해야 할 문자열을 저장해놓고 한 번에 출력하는 것이 더 빠르다.

이에 따라 출력할 수열을 StringBuilder에 저장해놓고 마지막에 출력하면 정답이 된다.

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
    val pick = IntArray(M)
    val sb = StringBuilder()
    fun dfs(level: Int, last: Int){
        if(level == M){
            for(i in 0 until M){
                sb.append(pick[i]).append(" ")
            }
            sb.append("\n")
            return
        }
        for(i in last..N){
            pick[level] = i
            dfs(level + 1, i)
        }
    }
    dfs(0, 1)
    print(sb)
}
```