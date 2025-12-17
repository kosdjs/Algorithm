# 백준 15666번: N과 M (12)

> 문제: https://www.acmicpc.net/problem/15666

### 문제 풀이

백트래킹

pick = 수열의 조건에 맞도록 뽑은 수

dfs(level, idx) = 현재 level개를 뽑았고 마지막에 뽑은 수의 인덱스가 idx일 때 나머지 수들을 뽑는 재귀 함수

last = 현재 level에서 이미 뽑은 수인지 확인하기 위한 변수

level이 M과 같아진다면 수열 M개를 뽑은 것이므로 뽑은 수열을 출력하고 함수 반환

그게 아니라면 아직 수열의 숫자를 덜 뽑은 것이므로 더 뽑아야 하지만 현재 단계에서 같은 수를 두 번 이상 뽑는다면 중복되는 수열이 생기므로 마지막에 뽑은 수를 저장하기 위해 last 정의

현재 뽑을 수 있는 인덱스 idx부터 arr에서 수를 하나씩 뽑아 last와 같지 않으면 수열에 들어갈 수 있는 수이므로 그 수를 pick에 저장하고 last에 값을 대입해 그 수가 현재 단계에서 더 뽑히지 않게 함

이를 dfs(0, 0)부터 시작하면 조건에 맞는 모든 수열을 출력할 수 있으므로 정답

### 풀이 설명

N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

- N개의 자연수 중에서 M개를 고른 수열
- 같은 수를 여러 번 골라도 된다.
- 고른 수열은 비내림차순이어야 한다.
  - 길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.

수열이 오름차순으로 정렬되어야 하기 때문에 N개의 자연수를 먼저 오름차순으로 정렬하고 뽑아야 한다. 같은 수를 여러 번 골라도 되지만 같은 수열이 여러번 출력되지 않게 하기 위해 일정 단계에서 같은 수를 여러번 뽑지 않도록 해야 한다.

따라서 재귀함수를 사용하기 위해 먼저 N개의 자연수를 저장한 배열 arr을 오름차순으로 정렬을 한다. 그 이후에 현재 뽑은 갯수 level과, 수열이 비내림차순이 되도록 뽑을 수 있는 arr의 시작 인덱스 idx를 인자로 받아 수열을 고르는 재귀 함수 dfs를 정의한다.

수열은 같은 수를 여러 번 골라도 되지만 같은 자리에 같은 수를 여러번 뽑으면 중복된 수열이 만들어지기 때문에 현재 자리에 마지막으로 뽑힌 수를 저장하기 위한 변수 last를 정의하고 초기값은 자연수가 아닌 0으로 초기화해 처음 수가 정상적으로 뽑힐 수 있도록 해야 한다.

그 이후에 현재 자리에 들어갈 수 있는 모든 수를 확인하기 위해 arr에서 idx부터 끝까지 모든 수를 확인하며 last와 같지 않다면 그 수를 수열을 위해 뽑는다. 현재 수가 뽑혔다는 것을 나타내기 위해 pick[level]에 현재 자리에 뽑은 수를 저장하고, 같은 수가 현재 자리에 다시 뽑히는 것을 막기 위해 last에 현재 뽑은 수를 저장하고 다음 자리의 수를 뽑기 위해 dfs(level + 1, i)를 호출한다.

이렇게 dfs(level, i)를 정의하고 모든 수열을 찾기 위해 dfs(0, 0)부터 출력하면 N개의 자연수 중 M개를 조건에 맞도록 뽑는 모든 수열을 출력할 수 있으므로 정답이 된다.

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
    val arr = IntArray(N)
    val pick = IntArray(M)
    for(i in 0 until N){
        arr[i] = nextInt()
    }
    arr.sort()
    val sb = StringBuilder()
    fun dfs(level: Int, idx: Int){
        if(level == M){
            for(i in 0 until M){
                sb.append(pick[i]).append(" ")
            }
            sb.append("\n")
            return
        }
        var last = 0
        for(i in idx until N){
            if(arr[i] == last) continue
            last = arr[i]
            pick[level] = arr[i]
            dfs(level + 1, i)
        }
    }
    dfs(0, 0)
    print(sb)
}
```