> 문제: https://www.acmicpc.net/problem/1052

### 문제 풀이

그리디

N개 보다 작은 2의 거듭제곱을 K - 1번동안 구해서 N에서 빼줌

마지막 한병이 남았을 때 2의 거듭제곱 중 N보다 크거나 같은 값을 구하고 그 값에서 N을 뺀 값이 사야하는 물병의 최솟값

### 풀이 설명

물병을 합칠 때 같은 양의 물병만 합칠 수 있고, 물 한병에 1L이므로 1L 두병을 합쳐 2L, 2L 두병을 합치면 4L, 4L 두병을 합치면 8L, 이런식으로 합쳐지게 되고 물병의 든 L 수는 합치는데 든 물병의 갯수랑 동일해진다

즉, 물병을 합치려면 갯수가 2의 거듭제곱 수가 되어야 한다

그렇다면 물병을 합치기 위해 물병을 사야한다면 현재 갯수보다 크거나 같은 2의 거듭제곱 수 중 최솟값과의 차이만큼 물병을 사야 합칠 수 있다는 뜻이다

그렇기 때문에 물병을 최소한으로 사려면 남은 물병의 갯수를 최소한으로 줄여야 2의 거듭제곱 수와의 차이가 줄어들 것이므로 한번 물병을 합칠 때 최대한 많은 갯수를 사용해 합쳐야 한다

이에 따라 앞에서부터 최대한 많이 물병을 합쳐놓고 마지막에 남은 물병의 갯수 보다 크거나 같은 2의 거듭제곱 수와의 차이가 답이 된다

### 소스 코드
```kotlin
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    var N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    var answer = 0
    for(i in 1..K){
        var water = 1
        while(water < N){
            water *= 2
        }
        if(i == K){
            answer = water - N
        } else {
            water /= 2
            N -= water
        }
    }
    println(answer)
}
```