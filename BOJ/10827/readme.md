# 백준 10827번: a^b

> 문제: https://www.acmicpc.net/problem/10827

### 문제 풀이

분할 정복

라이브러리 BigDecimal을 이용해 $a^b$를 구하고 출력하면 정답

### 풀이 설명

소수점 최대 9자리까지인 a와 1이상 100이하의 정수 b가 주어질 때 $a^b$를 정확히 구해서 출력하는 문제이다.

일반적으로 사용하는 Double이나 Float 같은 부동 소수점 방식은 계산을 할 때마다 오차가 생긴다. 그러므로 오차가 생기지 않도록 계산할 다른 방식이 필요하다.

Java에서 이미 이런 경우에 사용할 수 있도록 BigDecimal이라는 라이브러리를 제공한다. 따라서 a를 BigDecimal로 저장하고 $a^b$를 구하면 된다.

거듭제곱을 계산하는 방식은 분할 정복을 이용해 b를 절반으로 나누며 계산하면 a를 직접 b번 곱하는 방식보다 빠르게 계산할 수 있다.

이를 재귀 함수로 구현하면 다음과 같이 구현될 수 있다.

```kotlin
fun pow(a: BigDecimal, b: Int): BigDecimal{
    if(arr[b] == BigDecimal(0)) {
        if(b % 2 == 1){
            arr[b] = a * pow(a, b / 2) * pow(a, b / 2)
        } else {
            arr[b] = pow(a, b / 2) * pow(a, b / 2)
        }
    }
    return arr[b]
}
```

하지만 이런 방식으로 계산을 한다면 특정 지수의 값을 여러번 계산하는 문제가 있다. 이를 개선하기 위해 반복문을 통해 계산하는 방법도 있다.

```kotlin
var res = java.math.BigDecimal.ONE
var base = a
var exp = b

// 반복문을 이용한 고속 거듭제곱
while (exp > 0) {
    if (exp % 2 == 1) res = res.multiply(base)
    base = base.multiply(base)
    exp /= 2
}
```

이 방식은 $a^m \times a^n = a^{m + n}$ 과 같은 지수 법칙과 원래 분할 정복이 지수를 절반으로 나눈다는 특성을 이용해 지수를 이진수로 나누어 반복문으로 계산하는 방법이다. 그러나 현재는 BigDecimal 라이브러리가 거듭제곱을 JVM에 최적하게 계산하는 pow() 메소드가 있으므로 이를 사용해 $a^b$를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    nextToken()
    val a = nval.toBigDecimal()
    val b = nextInt()
    println(a.pow(b).toPlainString())
}
```