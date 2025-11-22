# 백준 1629번: 곱셈

> 문제: https://www.acmicpc.net/problem/1629

### 문제 풀이

분할 정복, 재귀

solve(A, B, C) = $A^B \bmod C$를 분할 정복으로 구하는 재귀 함수

half = $A^{B / 2} \bmod C$

B가 짝수라면 (half * half) % C를 반환하고 B가 홀수라면 (((half * half) % C) * (A % C)) % C를 반환하는 재귀함수 solve(A, B, C)를 이용해 구하는 값 $A^B \bmod C$를 구해 출력하면 정답

### 풀이 설명

자연수 A를 B번 곱한 수를 알고 싶다. 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.

A, B, C는 모두 2,147,483,647 이하의 자연수이다.

구해야 하는 값은 $A^B$를 C로 나눈 나머지이다. $A^B$를 그냥 반복문으로 구하기엔 B의 범위가 크기 때문에 더 효율적으로 구하는 방법이 필요하다. 이를 구하기 위해 제곱의 성질을 이용하면 된다.

$A^B$는 B가 짝수라면 $A^{B / 2} \times A^{B / 2}$, B가 홀수라면 $A^{B / 2} \times A^{B / 2} \times A$로 나눠서 구할 수 있다. 따라서 재귀함수를 이용하면 제곱을 더 빠르게 구할 수 있다.

하지만 $A^B$를 바로 구하는 것은 Long의 범위를 벗어날 수 있다. 그러므로 나머지 계산의 성질을 이용해야 한다. $A \times B \bmod C$를 구한다고 했을 때 $A = aC + x$, $B = bC + y$라고 나타내면 식이 $(aC + x) \times (bC + y) \bmod C = x + y = A \bmod C + B \bmod C$가 된다.

따라서 나머지를 분배할 수 있다는 뜻이므로 $A^B \bmod C$를 B가 짝수일때 $A^{B / 2} \bmod C \times A^{B / 2} \bmod C$로 나눌 수 있다. 이에 따라 재귀함수를 정의해 구하는 값을 출력하면 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextLong(): Long{
        nextToken()
        return nval.toLong()
    }
    val A = nextLong()
    val B = nextLong()
    val C = nextLong()
    fun solve(A: Long, B: Long, C: Long): Long{
        if(B == 1L){
            return A % C
        }
        val half = solve(A, B / 2, C) % C
        if(B % 2 == 0L){
            return (half * half) % C
        } else {
            return (((half * half) % C) * (A % C)) % C
        }
    }
    println(solve(A, B, C))
}
```