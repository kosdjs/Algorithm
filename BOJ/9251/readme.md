>문제: https://www.acmicpc.net/problem/9251

최장 공통 부분 수열의 길이를 찾는 문제

### 문제 풀이
- $dp[i][j]$ = 첫 번째 문자열의 $i$번째까지, 두 번째 문자열의 $j$번째까지의 LCS 길이

- 점화식:

  - $dp[i][j] = dp[i-1][j-1] + 1$ (문자가 같을 때)

  - $dp[i][j] = \max(dp[i-1][j], dp[i][j-1])$ (문자가 다를 때)
  
  
### 풀이 설명

점화식을 사용하지 않고 ACAYKP와 CAPCAK의 최장 공통 부분 수열을 찾는다고 생각해보자.

완전 탐색을 이용하는 방법

1. 두 수열을 X, Y라고 할 때 X의 모든 부분 수열을 찾는다.
2. 앞서 찾은 X의 부분 수열이 Y의 부분 수열이 될 수 있는지 검사한다.
3. 2번 과정에서 찾은 부분 수열 중 제일 긴 부분 수열이 답이 된다.

위의 방법으로 X의 부분 수열 중 공통 수열인 ACA와 ACAK가 부분 수열이 되는지 검색하는 부분을 보자.

- ACA의 경우

1. ACA의 첫 번째 문자 A와 Y의 첫 번째 문자 C가 다름. Y의 다음 문자 확인
2. ACA의 첫 번째 문자 A와 Y의 두 번째 문자 A가 같음. 둘 다 다음 문자 확인
3. ACA의 두 번째 문자 C와 Y의 세 번째 문자 P가 다름. Y의 다음 문자 확인
4. ACA의 두 번째 문자 C와 Y의 네 번째 문자 C가 같음. 둘 다 다음 문자 확인
5. ACA의 세 번째 문자 A와 Y의 다섯 번째 문자 A가 같음. Y의 부분 수열이 확인 됨

- ACAK의 경우

1. ACAK의 첫 번째 문자 A와 Y의 첫 번째 문자 C가 다름. Y의 다음 문자 확인
2. ACAK의 첫 번째 문자 A와 Y의 두 번째 문자 A가 같음. 둘 다 다음 문자 확인
3. ACAK의 두 번째 문자 C와 Y의 세 번째 문자 P가 다름. Y의 다음 문자 확인
4. ACAK의 두 번째 문자 C와 Y의 네 번째 문자 C가 같음. 둘 다 다음 문자 확인
5. ACAK의 세 번째 문자 A와 Y의 다섯 번째 문자 A가 같음. 둘 다 다음 문자 확인
6. ACAK의 네 번째 문자 K와 Y의 여섯 번째 문자 K가 같음. Y의 부분 수열이 확인 됨

다음과 같이 1~5번 과정이 중복 되게 된다. 이 두 수열을 보면 ACAK를 각각 접두사와 접미사, ACA와 K로 나누어 생각한다면 접두사인 ACA인 부분을 검사한 결과가 저장되어 있다면 중복 계산을 할 필요가 없어진다. 따라서 테이블에 저장되는 값을 현재 수열까지의 LCS의 길이라고 생각하면 현재 비교하는 마지막 문자를 서로 비교했을 때 같으면 이전까지 구했던 LCS 길이에 1이 추가됨. 같지 않다면 두 수열의 마지막 문자 중 하나는 LCS의 마지막 문자였을 수 있기 때문에 각각 마지막 문자 하나를 제외했을때의 LCS 길이를 가져가면 된다.

### 소스 코드

```kotlin
import java.io.*;

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val s1 = br.readLine()
    val s2 = br.readLine()
    val arr = Array(1001){IntArray(1001){0}}
    for (i in 1..s1.length){
        for (j in 1..s2.length){
            if(s1[i-1] == s2[j-1]){
                arr[i][j] = arr[i-1][j-1] + 1
            } else {
                arr[i][j] = if(arr[i-1][j] > arr[i][j-1]) arr[i-1][j] else arr[i][j-1]
            }
        }
    }
    println(arr[s1.length][s2.length])
}
```