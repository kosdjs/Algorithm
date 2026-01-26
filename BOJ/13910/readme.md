# 백준 13910번: 개업

> 문제: https://www.acmicpc.net/problem/13910

### 문제 풀이

DP

dp[i] = 짜장면 i 그릇을 만드려면 해야하는 요리 횟수의 최솟값

woks = 웍이 요리할 수 있는 짜장면의 그릇 수

size = 한 번 요리로 만들 수 있는 짜장면의 그릇 수

요리를 한 번 할때 짜장면 s 그릇을 만든다고 하면 다음과 같이 점화식이 만들어짐

$dp[i] = min(dp[i], dp[i - s] + 1)$ (단, $dp[i - s]$가 Int.MAX_VALUE가 아니어야 함)

한 번 요리로 만들 수 있는 짜장면의 그릇 수 s를 구하기 위해 size에 웍을 1개, 2개를 사용했을 때 만들 수 있는 짜장면의 그릇 수를 모두 추가함

요리하지 않은 상태는 짜장면이 없는 것이므로 dp[0]을 0으로 초기화하고 배열의 나머지 부분은 정확히 짜장면을 만들 수 없다는 의미로 Int.MAX_VALUE로 초기화함

그 이후에 s가 오름차순으로 정렬되어야 정확히 만들 수 있는 그릇 수를 제대로 처리할 수 있으므로 size.toSortedSet()을 이용해 size를 sortedSet으로 변환해서 만들 수 있는 그릇 수를 오름차순으로 반복함

i를 s부터 N까지 반복하면서 dp[i - s]가 Int.MAX_VALUE가 아닌 경우에만 dp[i]를 점화식대로 구함

그러면 dp[N]이 Int.MAX_VALUE가 아닌 경우에만 정확히 N 그릇을 만들 수 있는 것이므로 최소 요리 횟수 dp[N]을 출력하고 아니라면 -1을 출력해 불가능하다는 것을 알리면 정답

### 풀이 설명

요리를 한 번 할 때 웍을 2개까지 동시에 사용 가능할 때 정확히 주문 받은 짜장면의 그릇 수만큼 요리하려면 요리를 최소 몇 번해야 하는지 구하는 문제이다.

짜장면을 N개 만들어야 한다고 했을 때 요리 한 번으로 K개를 만들었다고 하면 그 뒤에는 N - K개를 최소 횟수로 만들어야 하는 것이므로 문제를 다시 작은 문제로 나눌 수 있으므로 DP로 풀 수 있다.

따라서 요리를 한 번 할 때 만들 수 있는 짜장면의 그릇 수들을 먼저 구해야 한다. 단, 같은 크기의 웍을 여러 개 가지고 있을 수 있다고 했으므로 한 번 요리할 때 만들어지는 짜장면의 그릇 수는 중복이 될 수 있다.

이를 HashSet을 이용해 한 번 요리할 수 있는 짜장면의 그릇 수를 중복없이 처리할 수 있다. 이렇게 한 번에 요리할 수 있는 그릇을 size에 저장하고 나면 여기에 저장된 s 그릇을 요리 한번에 만들 수 있는 것이므로 i 그릇을 만드는 요리 최소 횟수가 다음 점화식처럼 성립하게 된다.

$dp[i] = min(dp[i], dp[i - s] + 1)$ (단, i - s 그릇을 만들 수 있어야 함)

하지만 문제에 나와있듯이 정확히 그 양을 만들 수 없다면 만들지 못하는 것으로 간주하기 때문에 불가능 할 때는 Int.MAX_VALUE가 배열에 저장되도록 해야 한다. 따라서 dp 배열을 Int.MAX_VALUE로 초기화하고 dp[0]은 짜장면이 0 그릇일 때고 이는 요리를 하지 않으면 되기 때문에 0을 대입해 초기화한다.

그 이후에 HashSet은 원소 순서대로 정렬되지 않으므로 .toSortedSet() 메소드를 이용해 SortedSet으로 변환해 준 이후에 원소 s를 오름차순으로 정렬해 사용한다. 그렇게 한 번에 만들 수 있는 그릇 수를 작은 수부터 점화식에 적용해 실제로 만들 수 있는 그릇 수가 불가능하다고 넘어가지 않도록 해준다.

그렇게 점화식대로 dp 배열을 채우면 dp[N]에 저장된 값이 N 그릇을 만들 때 필요한 최소 요리 횟수 또는 Int.MAX_VALUE가 저장되므로 값을 그대로 출력하거나 -1을 출력해 불가능하다는 것을 알려주면 정답이 된다.

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
    val woks = IntArray(M)
    val dp = IntArray(N + 1){Int.MAX_VALUE}
    dp[0] = 0
    for(i in 0 until M){
        woks[i] = nextInt()
    }
    val size = HashSet<Int>()
    for(i in 0 until M){
        size.add(woks[i])
        for(j in i + 1 until M){
            size.add(woks[i] + woks[j])
        }
    }
    for(s in size.toSortedSet()){
        for(i in s..N){
            if(dp[i - s] == Int.MAX_VALUE) continue
            dp[i] = minOf(dp[i], dp[i - s] + 1)
        }
    }
    println(if(dp[N] != Int.MAX_VALUE) dp[N] else -1)
}
```