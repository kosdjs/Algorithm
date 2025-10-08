# 백준 3933번: 라그랑주의 네 제곱수 정리

> 문제: https://www.acmicpc.net/problem/3933

### 문제 풀이

DP

$dp[i][j] =$ 정수 $j$를 제곱수 $i$개로 나타내는 경우의 수

$square = 2^{15}$보다 작은 제곱수

$dp[i][j] = \sum dp[i - 1][j - sqaure]$ ($j$보다 작은 $square$에 한해서)

dp[i][n]의 합을 출력하면 정답

### 풀이 설명

n이 주어졌을 때 4개 이하의 양의 제곱수의 합으로 n을 만들 수 있는 경우의 수를 구하려고 한다. 경우의 수를 구할 때 제곱수의 순서가 바뀌는 경우는 같은 경우로 본다.

N이 25일 때 4개 이하의 제곱수의 합으로 표현 할 수 있는 경우는 $1^2 + 2^2 + 2^2 + 4^2,\ 3^2 + 4^2,\ 5^2$ 이렇게 3가지이다.

여기에서 알 수 있는 것은 순서가 바뀌면 같은 경우라고 했으므로 합을 나타낼 때 제곱수를 오름차순으로 정렬했다는 것과 합의 마지막 제곱수를 빼면 그 제곱수를 뺀 값을 제곱수의 합으로 나타냈다는 것을 알 수 있다.

따라서 N을 4개 이하의 제곱수의 합으로 나타낸 후에 마지막 제곱수 X를 빼면 N - X를 3개 이하의 제곱수의 합으로 나타낸 것으로 볼 수 있다. 이를 통해 DP를 사용하면 경우의 수를 구할 수 있다는 것을 알아낼 수 있다.

따라서 $dp[i][j]$를 정수 $j$를 제곱수 $i$개로 나타내는 경우의 수로 정의하고 $dp[0][0]$은 0을 제곱수 0개로 나타내는 것이므로 1을 대입하고 나머지를 0으로 초기화한 후에 점화식에 맞게 계산을 해준다.

여기서 순서가 바뀌는 경우는 같은 경우이므로 여러번 중복 계산되는 것을 막기 위해 작은 제곱수부터 순서대로 점화식에 맞게 계산을 해주면 $dp[i][N]$에 N을 4개 이하의 제곱수의 합으로 만들 수 있는 경우의 수가 저장되게 된다. 이를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import kotlin.math.pow

fun main(){
    val br = System.`in`.bufferedReader()
    val max = 2.0.pow(15).toInt()
    var num = 1
    val dp = Array(5){ IntArray(max) }
    dp[0][0] = 1
    while(num * num < max){
        val square = num * num
        for(i in 1..4){
            for(j in square until max){
                dp[i][j] += dp[i - 1][j - square]
            }
        }
        num++
    }
    val sb = StringBuilder()
    while(true){
        val n = br.readLine().toInt()
        if(n == 0){
            break
        }
        var sum = 0
        for(i in 0..4){
            sum += dp[i][n]
        }
        sb.append("$sum\n")
    }
    print(sb)
}
```