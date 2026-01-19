# 백준 3067번: Coins

> 문제: https://www.acmicpc.net/problem/3067

### 문제 풀이

DP

coins = N 가지 동전의 금액

dp[i] = i원을 동전으로 만드는 방법의 수

동전을 사용하지 않으면 0원이므로 dp[0] = 1

모든 동전의 단위 coin에 대해 현재 동전을 사용해 만들 수 있는 금액 i를 coin부터 M까지 반복하며 dp[i]에 dp[i - coin]을 더해줌

dp[M]을 출력하면 정답

### 풀이 설명

동전의 종류가 주어졌을 때 주어진 금액을 동전으로 나타낼 수 있는 방법의 수를 세는 문제이다.

문제에서 주어진 금액이 M원이고 주어진 동전 중 하나가 K원짜리 동전이라고 했을 때 M원을 주어진 동전들로 나타내는 방법을 생각해보면 K원짜리 동전 하나와 나머지 M - K원을 동전으로 나타내는 방법으로 나눌 수 있다.

K원짜리 동전 하나를 사용하는 부분은 더 이상 나눌 수 없으므로 이 때의 방법의 수는 M - K원을 동전으로 나타내는 방법의 수에 따라 나오게 된다. 그러므로 M원을 동전으로 나타내는 방법의 수는 모든 동전의 금액 K에 대해 M - K원을 동전으로 나타내는 방법을 구하면 된다.

이 M - K원을 동전으로 나타내는 방법은 다시 M - K원에서 K원을 뺀 M - 2 * K원을 동전으로 나타내는 방법이 되므로 0원부터 M원까지의 모든 수에 대해 동전으로 나타내는 방법을 구하면 된다.

이를 구하기 위해 dp[i]를 i원을 동전으로 나타내는 방법의 수로 정의하고 dp[0]이 0원을 동전으로 나타내는 방법이고 이는 동전을 사용하지 않는 한 가지 방법밖에 없으므로 1로 초기화를 하면 된다.

그 이후에 모든 동전의 금액 K에 대해 K원 동전을 사용할 수 있는 금액 i를 K부터 M까지 반복하며 dp[i]에 i - K원을 동전으로 나타내는 방법 dp[i - K]를 더해주면 된다. 이를 모든 동전에 대해 반복한 이후에 dp[M]을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val sb = StringBuilder()
    repeat(T){
        val N = nextInt()
        val coins = IntArray(N)
        for(i in 0 until N){
            coins[i] = nextInt()
        }
        val M = nextInt()
        val dp = IntArray(M + 1)
        dp[0] = 1
        for(coin in coins){
            for(i in coin..M){
                dp[i] += dp[i - coin]
            }
        }
        sb.append(dp[M]).append("\n")
    }
    print(sb)
}
```