# 백준 15482번: 한글 LCS

> 문제: https://www.acmicpc.net/problem/15482

### 문제 풀이

DP

- $dp[i][j]$ = 첫 번째 문자열의 $i$번째까지, 두 번째 문자열의 $j$번째까지의 LCS 길이

- 점화식:

  - $dp[i][j] = dp[i-1][j-1] + 1$ (문자가 같을 때)

  - $dp[i][j] = \max(dp[i-1][j], dp[i][j-1])$ (문자가 다를 때)

### 풀이 설명

[백준 9251번: LCS
](https://velog.io/@kosdjs/%EB%B0%B1%EC%A4%80-9251%EB%B2%88-LCS)

기본적으로 이 문제와 풀이가 같다. 입력이 한글 UTF-8로 들어온다는 점만 다르다. kotlin 및 자바 기반의 언어는 기본적으로 한글 한글자가 char 하나로 처리되기 때문에 특별히 처리해야 할 점이 없다.

### 소스 코드
```kotlin
fun main(){
    val br = System.`in`.bufferedReader()
    val s1 = br.readLine()
    val s2 = br.readLine()
    val dp = Array(s1.length + 1){ IntArray(s2.length + 1) }

    for(i in 1..s1.length){
        for(j in 1..s2.length){
            if(s1[i - 1] == s2[j - 1]){
                dp[i][j] = dp[i - 1][j - 1] + 1
            } else {
                dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }
    println(dp[s1.length][s2.length])
}
```