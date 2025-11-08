# 백준 1456번: 거의 소수

> 문제: https://www.acmicpc.net/problem/1456

### 문제 풀이

에라토스테네스의 채

isPrime[i] = i가 소수인지 여부

rootB = $\sqrt{B}$

count = 거의 소수의 개수

소수는 1과 자기 자신의 수로 나누어지지 않는 수이므로 $\sqrt{B}$까지의 수에서 이미 소수로 판명난 수의 배수들을 제외하면 소수임

따라서 isPrime을 true로 초기화하고 2부터 rootB까지 isPrime인 값만 배수의 isPrime을 false로 대입하고 거의 소수를 구해야 하기 때문에 제곱수부터 A보다 크거나 같고 B보다 작거나 같은 수인지 확인함

이때 소수의 제곱수를 확인하다가 Long의 범위를 넘어설 수 있으니 Long.MAX_VALUE를 현재 소수로 나눈 값이 현재 확인하는 제곱수보다 큰지 확인해야 함

이에 따라 제곱수의 개수를 저장해놓은 count를 출력하면 정답.

### 풀이 설명

어떤 수가 소수의 N제곱(N ≥ 2) 꼴일 때, 그 수를 거의 소수라고 한다.

두 정수 A와 B가 주어지면, A보다 크거나 같고, B보다 작거나 같은 거의 소수가 몇 개인지 출력한다.

소수의 N제곱수가 거의 소수라고 했으므로 B보다 작거나 같은 거의 소수는 $\sqrt{B}$보다 작은 소수의 제곱수이어야 한다.

따라서 $\sqrt{B}$까지의 소수를 에라토스테네스의 채를 이용해 미리 구해놓고 그 소수들의 A보다 크거나 같고 B보다 작거나 같은 제곱수의 수를 구하면 된다.

이때 B가 $10^{14}$까지라고 했으므로 Long으로 계산해야 하며 제곱수를 계산할 때 Long의 범위도 벗어날 수 있으므로 소수를 i라고 했을 때 Long.MAX_VALUE를 i로 나눈 값보다 현재 제곱수가 더 큰지 확인해서 오버플로우를 방지해줘야 한다.

이에 따라 구해놓은 거의 소수의 개수를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer
import kotlin.math.sqrt

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val A = st.nextToken().toLong()
    val B = st.nextToken().toLong()
    val rootB = sqrt(B.toDouble()).toInt()
    val isPrime = BooleanArray(rootB + 1){true}
    var count = 0L
    for(i in 2..rootB){
        if(!isPrime[i]) continue
        var num = i.toLong() * i.toLong()
        if(num <= rootB){
            for(j in i.toLong() * i.toLong()..rootB step i.toLong()){
                isPrime[j.toInt()] = false
            }
        }
        while(num <= B){
            if(num >= A){
                count++
            }
            if(Long.MAX_VALUE / i < num) break
            num *= i
        }
    }
    println(count)
}
```