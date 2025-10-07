# 백준 22115번: 창영이와 커피

> 문제: https://www.acmicpc.net/problem/22115

### 문제 풀이

DP

dp[i] = i만큼의 카페인을 섭취하기 위한 커피의 최소 개수, 불가능하면 Int.MAX_VALUE

c = 카페인 함유량 $C_i$

모든 커피에 대해 dp[c]에 1 대입하고 i를 K부터 1까지 반복하며 dp[i]가 Int.MAX_VALUE가 아닌 i를 찾아 dp[i + c]에 대해 기존값과 dp[i] + 1을 비교해 작은 값을 대입

이를 반복하면 dp[K]에 i만큼의 카페인을 섭취하기 위한 커피의 최소 개수가 저장됨

만약 Int.MAX_VALUE라면 불가능한 것이므로 -1 출력, 아니라면 dp[K]값 출력하면 정답

### 풀이 설명

회사에는 N개의 커피가 각각 하나씩 준비되어 있고, 각 커피에는 카페인 함유량 Ci가 있다. N개의 커피 중 몇 개를 골라 정확히 K만큼의 카페인을 섭취하려고 한다. 이 때 최소 개수를 구하려면 DP를 사용해 카페인에 따라 마셔야 하는 커피의 최소 개수를 구하면 된다.

따라서 dp[i]를 i만큼의 카페인을 섭취하기 위한 커피의 최소 개수로 정의하고 불가능할 수 있으므로 초기 값을 Int.MAX_VALUE로 설정한다. K의 범위가 0부터 시작하기 때문에 dp[0]은 0으로 초기화 한다.

각 커피마다 카페인을 c라고 하면 c만큼의 카페인을 섭취하기 위해 커피 하나를 마시면 되기 때문에 dp[c]에 1을 대입한다. 이제 dp 배열을 확인해 섭취 가능한 카페인 i에 대해 dp[i + c]와 dp[i] + 1을 비교해 더 적은 개수로 카페인 i + c를 채울 수 있는지 확인해야 한다.

여기서 커피를 한 번씩만 마실 수 있다고 했으므로 i를 반복할 때 1부터 순차적으로 확인한다면 현재 커피를 여러번 마시게 된다. 따라서 i를 K부터 1까지의 내림차순 순서로 반복해 dp[i]가 Int.MAX_VALUE인지 확인한다.

Int.MAX_VALUE가 아니어야 다른 커피를 섭취해 i만큼의 카페인을 섭취할 수 있다는 것이므로 현재 커피까지 섭취하는 카페인이 i + c가 된다. 따라서 현재 커피를 섭취했을 때의 개수 dp[i] + 1과 배열에 저장된 카페인 i + c를 섭취하기 위한 최소 횟수를 비교해 더 작은 값을 dp[i + c]에 대입한다.

이를 모든 커피에 대해 반복하면 dp[K]에 카페인 K를 섭취하기 위한 커피의 최소 개수가 저장된다. 이 값이 Int.MAX_VALUE라면 커피를 통해 카페인 K를 섭취할 수 없는 것이므로 -1을 출력하고, 아니라면 그 값이 최소 개수이므로 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val C = IntArray(N)
    for(i in 0 until N){
        C[i] = st.nextToken().toInt()
        if(C[i] == K){
            println(1)
            return
        }
    }
    val dp = IntArray(K + 1){Int.MAX_VALUE}
    dp[0] = 0
    for(c in C){
        if(c <= K){
            dp[c] = 1
        }
        for(i in K downTo 1){
            if(dp[i] != Int.MAX_VALUE){
                if(i + c <= K){
                    dp[i + c] = minOf(dp[i + c], dp[i] + 1)
                }
            }
        }
    }
    println(if(dp[K] != Int.MAX_VALUE) dp[K] else -1)
}
```