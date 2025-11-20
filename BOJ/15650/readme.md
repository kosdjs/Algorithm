# 백준 15650번: N과 M (2)

> 문제: https://www.acmicpc.net/problem/15650

### 문제 풀이

재귀, 비트마스킹, 조합

level = 현재 뽑은 숫자의 개수

num = 뽑은 수 중 가장 큰 수

bits = 뽑은 수들을 비트마스킹으로 나타낸 값

solve(level, num, bits) = 뽑은 수가 M개가 될때까지 num보다 크고 N보다 작거나 같은 수를 하나씩 뽑아 재귀호출을 하는 조합을 뽑는 재귀함수

solve 함수를 이용해 1부터 N까지의 수에서 M개를 뽑는 모든 경우를 출력하면 정답

### 풀이 설명

자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

- 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
- 고른 수열은 오름차순이어야 한다.

즉, 1부터 N까지의 자연수에서 M개를 뽑는 조합의 모든 경우를 출력하면 된다. 여기서 N이 8까지이므로 숫자를 뽑았는지 여부를 비트마스킹으로 표현하면 더 빠르게 구현할 수 있다.

조합을 만드는 방법 중 하나로 재귀함수를 이용하는 방법이 있다. 재귀 함수의 인수로 뽑은 숫자의 개수 level, 마지막에 뽑힌 숫자 num, 뽑은 숫자들을 비트마스킹으로 나타낸 값 bits를 받아 level이 M이 될때까지 num보다 크고 N보다 작거나 같은 수를 하나씩 뽑아 함수를 재귀호출해 그 숫자를 뽑았다는 것을 나타낸다.

이 함수의 인자로 level에 0, num에 0, bits에 0을 전달하면 1부터 N까지의 자연수에서 M개를 오름차순으로 뽑는 모든 경우를 탐색할 수 있다. 따라서 solve(0, 0, 0)을 호출해 M개를 뽑는 모든 경우를 오름차순으로 정렬한 결과를 출력하면 정답이 된다.

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
    val bw = System.out.bufferedWriter()
    fun solve(level: Int, num: Int, bits: Int){
        if(level == M){
            for(i in 1..N){
                if((1 shl i) and bits != 0){
                    bw.append("$i ")
                }
            }
            bw.newLine()
            return
        }
        for(i in num + 1..N){
            solve(level + 1, i, bits or (1 shl i))
        }
    }
    solve(0, 0, 0)
    bw.flush()
    bw.close()
}
```