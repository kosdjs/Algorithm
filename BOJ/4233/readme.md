# 백준 4233번: 가짜소수

> 문제: https://www.acmicpc.net/problem/4233

### 문제 풀이

수학

isPrime(n) = n이 소수인지 여부를 판별하는 함수

res = $a^p$를 p로 나눈 나머지

base = $a^p$를 분할 정복으로 구하기 위한 곱해야 하는 밑 값

e = $a^p$를 분할 정복으로 구할 때 남은 지수

p를 먼저 isPrime(n) 함수를 이용해 2와 3으로 나누어 떨어지는지 확인함

2와 3으로 나누어 떨어지지 않는다면 6의 약수가 2와 3이라는 것을 이용해 6으로 나눈 나머지 중 1과 5만 2와 3으로 나누어 떨어지지 않는다는 점을 이용해 2부터 $\sqrt{p}$까지의 수 중 정수 k에 대해 $6k \pm 1$이 되는 값들로만 나누어 떨어지는 지 확인해 소수인지 판별함

p가 소수라면 $a^p$를 p로 나눈 나머지를 판별할 이유가 없으므로 no를 출력함

소수가 아니라면 $a^p$를 p로 나눈 나머지를 구하기 위해 거듭제곱을 지수의 합으로 나눌 수 있다는 점을 이용해 분할 정복으로 값을 구함

또한 지수를 이진법으로 생각해보면 곱해야 하는 수를 더 쉽게 나눌 수 있음

남은 지수가 e이므로 이 값이 0이 될때까지 2로 나누면서 그때 나눈 나머지가 1일때만 그때의 곱해야 하는 값 base를 res에 곱해줌

이때 e를 이진법으로 생각한다고 했으므로 base는 매번 제곱해야 함

이에 따라 $a^p$를 분할 정복으로 그대로 구하면 Long의 범위도 넘어가므로 계속 p로 나누면서 구해야 함

res를 매번 base를 곱할때마다 p로 나누면 res에 $a^p$를 p로 나눈 나머지가 저장되므로 이 값이 a라면 yes, 아니라면 no를 출력해주면 정답

### 풀이 설명

페르마의 소정리에 따라 p가 소수라면 임의의 양의 정수 a에 대해 $a^p$를 p로 나눈 나머지가 a가 된다.

그러나 p가 소수가 아니더라도 특정 정수 a에 대해 위의 식을 만족하는 경우가 있고 이 때 p를 a를 밑으로 하는 가짜소수라고 한다.

p와 a가 주어졌을 때 p가 가짜소수인지 판단하는 문제이다.

p가 소수가 아닐때 식을 만족한다면 p가 가짜소수라고 했으므로 먼저 p가 소수인지를 확인해야 한다.

p가 소수인지 확인하는 방법은 2부터 $\sqrt{p}$까지의 정수로 나누어 떨어지는지 확인하면 된다.

이를 6의 약수가 2와 3이므로 6으로 나눈 나머지가 0부터 5까지 있을 때 1과 5만 2와 3으로 나누어 떨어지지 않는다는 점이 있으므로 2와 3으로 먼저 나누어 떨어지는지 확인하면  $6k \pm 1$으로만 나누어 떨어지는지 확인하도록 개선할 수 있다.

그렇게 먼저 p를 소수인지 확인한 이후에 소수가 아니라면 $a^p$를 p로 나눈 나머지를 구해 a인지 확인하면 된다.

먼저 거듭제곱을 계산할 때 단순히 a를 p번 곱하는 것은 횟수가 너무 많기 때문에 분할 정복을 사용해야 한다. 그리고 곱셈의 나머지를 계산할 때 매번 곱할때 나머지 계산을 해도 값이 같게 나오므로 매번 계산할때마다 나머지를 계산해주면 된다.

매번 p와 a가 주어질때마다 소수가 아닐 때 $a^p$를 p로 나눈 나머지가 a일때만 yes, 아닐때는 no를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val sb = StringBuilder()
    fun isPrime(n: Long): Boolean {
        if (n < 2) return false
        if (n == 2L || n == 3L) return true
        if (n % 2 == 0L || n % 3 == 0L) return false
        var i = 5L
        while (i * i <= n) {
            if (n % i == 0L || n % (i + 2) == 0L) return false
            i += 6
        }
        return true
    }
    while(true){
        val p = nextInt()
        val a = nextInt()
        if(a == 0 && p == 0) break
        if(isPrime(p.toLong())) sb.append("no").append("\n")
        else{
            var res = 1L
            var base = a.toLong()
            var e = p.toLong()
            while(e > 0){
                if(e % 2 == 1L){
                    res = (res * base) % p
                }
                base = (base * base) % p
                e /= 2
            }
            sb.append(if(res.toInt() == a) "yes" else "no").append("\n")
        }
    }
    print(sb)
}
```