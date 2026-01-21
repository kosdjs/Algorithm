# 백준 9764번: 서로 다른 자연수의 합

> 문제: https://www.acmicpc.net/problem/9764

### 문제 풀이

DP

dp[i] = i를 서로 다른 자연수의 합으로 나타내는 방법의 수

max = T개의 자연수 중 최댓값

서로 다른 자연수의 합이므로 자연수 하나와 나머지 서로 다른 자연수의 합으로 나타낼 수 있으므로 다음 점화식이 성립함

$dp[j] = dp[j] + dp[j - i]$ $(i$는 $j$보다 작거나 같은 자연수$)$

이 때 합에 자연수가 하나도 없으면 0이 되므로 dp[0]을 1로 초기화해야 자연수 하나만 있을 때를 정상적으로 구할 수 있음

i를 1부터 max까지 반복하고 i가 합에 한 번씩만 들어가야 하므로 j를 max부터 i까지의 내림차순 순서로 반복하도록 해서 점화식에 맞게 배열을 채워야 함

그렇게 배열을 채운 이후에 입력으로 들어오는 모든 자연수 N에 대해 dp[N]을 출력하면 정답

### 풀이 설명

N을 서로 다른 자연수의 합으로 나타내는 방법의 수를 구하는 문제이다. 서로 다른 자연수의 합이라고 했으므로 같은 자연수를 두 개 이상 사용할 수 없다. 

N을 서로 다른 자연수의 합으로 나타내는 것은 두 부분으로 나눌 수 있다. 자연수 K가 있다고 하면 N - K를 K를 제외한 서로 다른 자연수의 합으로 나타낸 것과 자연수 K로 나눌 수 있다. N - K를 K를 제외한 서로 다른 자연수의 합으로 나타내면 합에 들어가는 자연수를 또 하나 선택해 두 부분으로 나눌 수 있으므로 DP로 문제를 풀 수 있다는 점이 된다.

그러므로 dp[i]를 i를 서로 다른 자연수의 합으로 나타내는 방법의 수로 정의하고 아무 자연수도 없을 때는 0이므로 dp[0]을 1로 초기화해준다.

구해야 하는 자연수의 최댓값을 max라고 하면 들어갈 수 있는 자연수 i는 1부터 max까지이고 이 자연수 i가 한 번씩만 들어가도록 j를 max부터 i까지 반복하며 다음 점화식을 이용해 배열을 채운다.

$dp[j] = dp[j] + dp[j - i]$

이렇게 필요한 dp배열을 채운 이후에 입력된 N들에 대해 dp[N] 값들을 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val N = IntArray(T)
    for(i in 0 until T){
        N[i] = nextInt()
    }
    val max = N.max()
    val dp = IntArray(max + 1)
    val mod = 100999
    dp[0] = 1
    for(i in 1..max){
        for(j in max downTo i){
            dp[j] += dp[j - i]
            dp[j] %= mod
        }
    }
    val sb = StringBuilder()
    for(n in N){
        sb.append(dp[n]).append("\n")
    }
    print(sb)
}
```