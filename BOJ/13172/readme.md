# 백준 13172번: Σ

> 문제: https://www.acmicpc.net/problem/13172

### 문제 풀이

분할 정복

pow(base, exp) = $base^{exp} \bmod 1,000,000,007$을 분할 정복으로 구하는 함수

mod = 이 문제에서의 X인 1,000,000,007

answer = 모든 주사위의 기댓값의 합을 mod로 나눈 나머지 값

지수 거듭제곱 방식으로 거듭제곱을 구하는 pow(base, exp) 함수를 이용해 $N_i \times S_i^{X - 2} \bmod X$를 구해 answer에 더한 후 mod로 나눈 값을 출력하면 정답

### 풀이 설명

M개의 주사위가 있어서 이 중 i번째 주사위가 $N_i$면체 주사위이고 모든 면에 적힌 수를 더한 값이 $S_i$일 때, 각 주사위에 대해서 주사위를 던졌을 때 주사위의 각 면이 나올 확률이 동일하다고 가정한 상태에서 모든 주사위를 각각 한 번씩 던졌을 때 나온 수들의 합의 기댓값을 구하는 문제를 만들었다. 확률변수 X의 기댓값을 E(X)로 나타내면, 기댓값의 선형성에 의해서 두 확률변수 X, Y에 대해 E(X + Y) = E(X) + E(Y)가 성립하므로, 이 문제의 답을 아래와 같이 간단하게 나타낼 수 있다.

$S_1/N_1 + S_2/N_2 + ... + S_M/N_M$

즉, 각 주사위에서 나오게 되는 수의 기댓값을 모두 더하면 답이 되는 것이다. 이 답을 정확하게 출력하기 위해, 모든 분수(여기서는 각 주사위의 기댓값)를 통분한다고 생각해보자. 이 분수의 분모와 분자의 값이 어떤 범위까지 치솟게 될 것인가? 즉, 분모와 분자를 모두 저장하고 있게 되면, 두 분수의 합을 구할 때 분모와 분자를 적정한 범위 내에서 계산해낼 수 없다는 문제에 부딪히게 된다.

어떤 분수가 기약분수로 나타냈을 때 a/b이면, 이 분수는 $a \times b^{-1} \bmod X$ (X는 소수)으로 대신 계산하도록 한다. 여기서 $b^{-1}$은 $b$의 모듈러 곱셈에 대한 역원이다. $b$의 모듈러 곱셈에 대한 역원 $b^{-1}$은 대체 어떤 수인 것일까? 이 수는 다음과 같은 성질을 만족하는 정수이다.

$b^{-1} × b ≡ 1(\bmod\ X)$

소수 모듈러에서만 성립하는 페르마의 소정리에 의해 $b^{X - 1} ≡ 1 (\bmod\ X)$가 성립하기에, $b^{X - 2} ≡ b^{-1} (\bmod\ X)$ 역시 성립함을 알 수 있다. 이에 따라 $a \times b^{-1} \bmod X$를 $a \times b^{X - 2} \bmod X$로 나타낼 수 있다.

구해야 하는 값이 $S_1/N_1 + S_2/N_2 + ... + S_M/N_M$이고 이를 다시 나타내면 $\sum^{M}_{i = 1}S_i/N_i$이 되고 이를 다시 나타내면 $\sum^{M}_{i = 1}N_i \times S_i^{X - 2} \bmod X$가 된다. 또한 문제에서 기댓값의 합이 a/b의 형태가 되면 $(a \times ^{b-1}) \bmod 1,000,000,007$를 출력하라고 했으므로 $\sum^{M}_{i = 1}N_i \times S_i^{X - 2} \bmod X$을 구하고 나서 다시 X로 나눈 나머지를 출력해야 한다.

이 문제에서 X는 1,000,000,007이므로 $S_i^{X - 2}$는 $S_i^{1,000,000,005}$가 된다. 따라서 거듭제곱을 효율적으로 구하는 방법을 사용해야 하고 이는 분할 정복을 이용해 지수 거듭제곱을 구현하면 된다.

이에 따라 주사위마다 거듭제곱을 이용해 $N_i \times S_i^{X - 2} \bmod X$를 구해 answer에 더하고 이때마다 X로 나눈 나머지를 저장하고 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextLong(): Long{
        nextToken()
        return nval.toLong()
    }
    val M = nextLong()
    val mod = 1_000_000_007L
    var answer = 0L
    fun pow(base: Long, exp: Long): Long{
        var res = 1L
        var bPow = base % mod
        var e = exp
        while(e>0L){
            if(e % 2L == 1L) res = (res * bPow) % mod
            bPow = (bPow * bPow) % mod
            e /= 2
        }
        return res
    }
    repeat(M.toInt()){
        val N = nextLong()
        val S = nextLong()
        answer += S * pow(N, mod - 2)
        answer %= mod
    }
    println(answer)
}
```