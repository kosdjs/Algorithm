# 백준 16500번: 문자열 판별

> 문제: https://www.acmicpc.net/problem/16500

### 문제 풀이

DP

dp[i] = S의 인덱스 i전까지 A의 문자열을 이어 붙여서 만들 수 있는지 여부

i를 S의 맨 앞 0번 인덱스의 문자부터 끝의 문자까지 반복하며 dp[i]가 true일 때만 i부터 A[j]가 일치하는지 확인

i번 인덱스부터 A와 일치하는 문자열 A[j]를 찾는다면 dp[i + A[j].length]를 true로 변경함

반복을 마쳤을 때 dp[S.length]가 true면 1 출력, false면 0 출력하면 정답

### 풀이 설명

알파벳 소문자로 이루어진 문자열 S와 단어 목록 A가 주어졌을 때, A에 포함된 단어를 여러 번 사용할 수 있을 때, S를 A에 포함된 문자열을 한 개 이상 공백없이 붙여서 만들 수 있는지 없는지 구해야 한다.

A에 포함된 문자열을 공백없이 붙여서 만들 수 있으려면 S의 처음부터 일치하는 A에 포함된 문자열을 찾고 그 뒤에 문자열부터 다시 A에 포함된 문자열과 일치하는지 확인해야 한다. 이를 인덱스로 표현하면 A에 포함된 문자열과 일치하는지 인덱스 i부터 확인하려면 그 전 인덱스 i - 1까지가 A에 포함된 문자열을 이어 붙여 만든 문자열이어야 한다.

즉, S가 A에 포함된 문자열을 이어 붙여 만든 문자열이라면 맨 뒤의 A에 포함된 문자열과 앞쪽 문자열로 나눴을 때 이 앞쪽 문자열도 A에 포함된 문자열을 이어 붙여 만든 문자열이라는 것이 된다.

따라서 dp[i]를 S의 인덱스 i전까지 A의 문자열을 이어 붙여서 만들 수 있는지 여부로 정의하고 dp[0]은 앞에 문자열이 없고 이는 A의 문자열을 사용하지 않으면 만들 수 있는 것이므로 true로 초기화 해준다.

그 이후에 i를 0부터 S의 마지막 인덱스까지 반복하며 dp[i]값을 확인해 i번 인덱스 전까지 A에 포함된 문자열로 만들 수 있다면 i번 인덱스부터 A에 포함된 문자열과 일치하는 문자열을 찾는다. 일치하는 문자열 A[j]를 찾는다면 i번 인덱스가 A[j]의 0번 인덱스와 일치하는 것이므로 A[j]와 일치하는 S의 마지막 인덱스가 i + A[j].length - 1이 된다. 그러므로 인덱스 i + A[j].length - 1까지 A에 포함된 문자열을 이어붙여 만들 수 있다는 뜻이 되므로 dp[i + A[j].length]에 true를 대입하고 다음 인덱스를 살펴본다.

이렇게 S의 모든 인덱스에 대해 반복하면 dp[S.length]에 S를 A에 포함된 문자열을 이어 붙여 만들 수 있는지 여부가 저장된다. 이 값에 따라 true라면 S를 A에 포함된 문자열을 이어 붙여 만들 수 있는 것이므로 1을 출력하고, false라면 불가능한 것이므로 0을 출력해주면 정답이 된다.

### 소스 코드
```kotlin
fun main(){
    val br = System.`in`.bufferedReader()
    val S = br.readLine()
    val N = br.readLine().toInt()
    val A = Array<String>(N){""}
    for(i in 0 until N){
        A[i] = br.readLine()
    }
    val dp = BooleanArray(S.length + 1)
    dp[0] = true
    for(i in 0 until S.length){
        if(dp[i]){
            for(j in 0 until N){
                if(i + A[j].length > S.length){
                    continue
                }
                var find = true
                for(k in 0 until A[j].length){
                    if(A[j][k] != S[i + k]){
                        find = false
                        break
                    }
                }
                if(find){
                    dp[i + A[j].length] = true
                }
            }
        }
    }
    if(dp[S.length]){
        println(1)
    } else {
        println(0)
    }
}
```