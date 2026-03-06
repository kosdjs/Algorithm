# 백준 11560번: 다항식 게임

> 문제: https://www.acmicpc.net/problem/11560

### 문제 풀이

DP

dp[i][j] = k가 i일 때 $p_k(x)$에서의 $x^j$의 계수

다항식 $p_k(x)$를 두 부분으로 나누면 k - 1일때의 다항식 $p_{k - 1}(x)$와 1부터 $x^k$까지 더한 식으로 나눌 수 있고 두 식을 곱해서 계수를 구할 수 있음

따라서 이전 k - 1일때의 계수를 알아야 현재 다항식의 계수를 구할 수 있으므로 DP로 문제를 풀 수 있음

dp[i][j]를 k가 i일 때 $p_k(x)$에서의 $x^j$의 계수로 정의하고, j를 1부터 i까지, k를 $p_{i - 1}(x)$에서 존재하는 x의 모든 지수라고 하면 dp[i][j + k]는 다항식의 곱에 의해 모든 dp[i - 1][k]를 더한 값이 됨

이에 따라 dp 배열을 범위 내에서 모두 구해놓고 입력받는 k, N에 대해 dp[k][N]을 출력하면 정답

### 풀이 설명

 특정 k값이 주어졌을 때 다음 다항식의 $x^N$의 계수를 구하는 문제이다.
$p(x) = (1+x)(1+x+x^2)(1+x+x^2+x^3)\dots(1+x+\dots+x^{k-1}+x^k)$

다항식을 살펴보면 맨 끝에 식 $(1+x+\dots+x^{k-1}+x^k)$과 앞의 식으로 식을 나눌 수 있다. 이때 앞의 식은 k - 1일때의 $p(x)$이므로 이전 단계의 작은 문제를 구해 놓아야 현재 단계의 큰 문제를 풀 수 있다는 점에서 DP로 문제를 풀 수 있다는 것을 알 수 있다.

그러므로 문제에서 주어지는 다항식 $p(x)$의 규칙을 더 쉽게 보기 위해 k를 추가해 $p_k(x)$로 나타내어서 다항식을 다시 쓴다면 다음과 같이 쓸 수 있다.

$p_k(x) = p_{k - 1}(x)(1+x+\dots+x^{k-1}+x^k)$

위 다항식을 계산하는 과정에서 $p_{k - 1}(x)$의 항과 $(1+x+\dots+x^{k-1}+x^k)$의 항을 곱해서 계산하기 때문에 두 항의 지수는 더해지고 $(1+x+\dots+x^{k-1}+x^k)$의 항의 계수가 항상 1이므로 곱한 항의 계수는 $p_{k - 1}(x)$의 항의 계수가 된다.

그러므로 계수를 저장하기 위해 2차원 배열 dp를 정의하고, dp[i][j]가 $p_i(x)$에서 $x^j$항의 계수를 의미하도록 만들고 다항식을 계산하는 과정에 맞게 이전 단계의 계수를 누적해서 계산해 저장하면 문제에서 요구하는 계수가 dp[k][N]에 저장된 값이 되므로 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val dp = Array(21){LongArray(211)}
    dp[1][0] = 1
    dp[1][1] = 1
    for(i in 2..20){
        for(j in 0..i){
            for(k in 0..(i - 1) * i / 2){
                dp[i][j + k] += dp[i - 1][k]
            }
        }
    }
    val sb = StringBuilder()
    repeat(T){
        val k = nextInt()
        val N = nextInt()
        sb.append(dp[k][N]).append("\n")
    }
    print(sb)
}
```