# 백준 11660번: 구간 합 구하기 5

> 문제: https://www.acmicpc.net/problem/11660

### 문제 풀이

DP

dp[x][y] = 1행 1열부터 x행 y열까지의 합

1행 1열부터 i - 1행 j열까지의 합과 1행 1열부터 i행 j - 1열까지의 합을 생각하면 1행 1열부터 i - 1행 j - 1열까지의 합이 중복되어 들어가므로 이 값을 빼주어야 하기 때문에 dp[i][j]의 점화식이 다음과 같이 나옴

dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + i행 j열의 값 - dp[i - 1][j - 1]

이 점화식에 따라 dp테이블을 구한 후 (x1, y1)부터 (x2, y2)까지 합을 구하는 것은 dp[x2][y2]에서 해당되지 않는 범위의 값 dp[x1 - 1][y2], dp[x2][y1 - 1]를 빼주어야 하는데 이 값에 dp[x1 - 1][y1 - 1]이 중복으로 빠져서 더해주어야 하므로 식이 다음과 같이 나옴

dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1]

따라서 점화식에 따라 dp테이블의 값을 구하고 입력으로 들어온 x1, y1, x2, y2에 따라 식의 값을 출력하면 정답

### 풀이 설명

N×N개의 수가 N×N 크기의 표에 채워져 있다. (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램을 작성하시오. (x, y)는 x행 y열을 의미한다.

(x1, y1)부터 (x2, y2)까지 합은 1행 1열부터 x2행 y2열까지의 합에서 1행 1열부터 x1 - 1행 y2열까지의 합과 1행 1열부터 x2행 y1 - 1열까지의 합을 빼고 여기서 중복으로 빠진 1행 1열부터 x - 1행 y - 1열까지의 합을 더해주면 구할 수 있다.

여기서 1행 1열부터 특정 행, 열까지의 합이 계속 나오므로 이를 미리 구해놓으면 계산이 편해진다. 따라서 dp[x][y]를 1행 1열부터 x행 y열까지의 합으로 정의하면 이를 구하는 방법을 생각해야 한다.

dp[x][y]를 구하려면 간단히 생각해보면 x행 y열의 값에 dp[x - 1][y], dp[x][y - 1]를 더해주면 되는데 이 때 dp[x - 1][y - 1]이 두번 더해지므로 이 값을 빼주면 구할 수 있다.

이제 아까 생각했던 (x1, y1)부터 (x2, y2)까지 합을 구하는 방법을 dp배열의 값을 사용하도록 식으로 쓰면 다음과 같이 식이 나온다.

dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1]

이에 따라 dp배열을 먼저 구하고 식에 맞게 (x1, y1)부터 (x2, y2)까지 합을 출력하면 정답이 된다.

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
    val dp = Array(N + 1){IntArray(N + 1)}
    for(i in 1..N){
        for(j in 1..N){
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + nextInt() - dp[i - 1][j - 1]
        }
    }
    val bw = System.out.bufferedWriter()
    repeat(M){
        val x1 = nextInt()
        val y1 = nextInt()
        val x2 = nextInt()
        val y2 = nextInt()
        bw.append("${dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1]}\n")
    }
    bw.flush()
    bw.close()
}
```