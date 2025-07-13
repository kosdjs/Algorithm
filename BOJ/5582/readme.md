# 백준 5582번: 공통 부분 문자열

> 문제: https://www.acmicpc.net/problem/5582

### 문제 풀이

DP

두 문자열을 각각 $A$, $B$라고 하고 $A[i]$를 $A$의 $i$번째 문자, $B[j]$를 $B$의 $j$번째 문자라고 하면

$dp[i][j] = A[i]$, $B[j]$가 마지막 문자인 공통 부분 문자열의 길이

$dp[i][j] = 0$ $(A[i] \neq B[j])$

$dp[i][j] = dp[i - 1][j - 1] + 1$ $(A[i] = B[j])$

모든 i, j에 대해서 dp 테이블 값을 구하고 최댓값을 찾으면 정답

### 풀이 설명

A[i - 1], B[j - 1]이 공통 부분 문자열의 마지막 문자인 부분 문자열을 X라고 하면 각각 A와 B에서 문자 하나씩 추가를 한 부분 문자열은 각각 X + A[i], X + B[j]가 될 것이고 이 두 문자열이 공통 부분 문자열이 되려면 A[i], B[j]가 같아야 한다.

즉 부분 문자열은 연속된 문자이어야 하고 공통 부분 문자열에 문자를 추가할 때 마지막 문자가 같아야 한다는 점에서 이를 DP로 볼 수 있다.

따라서 점화식을 세워보면 위에 세웠던 점화식이 되고 이 조건을 통해 모든 dp 테이블을 채워보면서 테이블에 들어가는 최댓값을 찾으면 공통 부분 문자열의 최대 길이이므로 정답이 나온다.

### 소스 코드
```kotlin
fun main(){
    val br = System.`in`.bufferedReader()
    val A = br.readLine()
    val B = br.readLine()
    val dp = Array(A.length){ IntArray(B.length){0} }
    var result = 0
    for(i in 0 until A.length){
        for(j in 0 until B.length){
            if(A[i] == B[j]){
                dp[i][j]++
                if(i > 0 && j > 0){
                    dp[i][j] += dp[i - 1][j - 1]
                }
                if(dp[i][j] > result) result = dp[i][j]
            }
        }
    }
    println(result)
}
```