# 백준 23978번: 급상승

> 문제: https://www.acmicpc.net/problem/23978

### 문제 풀이

이분 탐색

현금화를 K원 이상할 수 있도록 코인 가격을 이분 탐색으로 찾는다.

mid = 현재 탐색하는 코인 가격

diff = 다음 코인 가격이 상승하는 날짜와 현재 날짜와의 차이

diff가 mid보다 크다면 현재 상승한 날부터 코인이 0원이 될때까지 모두 현금화 가능하므로 현금화 가능한 금액은 $mid + (mid - 1) + (mid - 2) + ... + 1 = mid \times mid - (1 + 2 + ... + mid - 1) = mid \times mid - (mid - 1) \times mid / 2$

만약 diff가 mid보다 작다면 현금화 가능한 금액은 다음과 같음 $mid + (mid - 1) + ... + (mid - (diff - 1)) = mid \times diff - (1 + 2 + ... + diff - 1) = mid \times diff - (diff - 1) \times diff / 2$

따라서 현금화 식이 다음과 같이 된다.

$d = min(mid, diff)$

$mid \times d - (d - 1) \times d / 2$

이를 상승 날짜 배열에 대해 차이만큼 모두 더해주면 마지막 날의 현금화 가능 금액이 남기 때문에 이를 처음 sum을 초기화 할 때 저장해주는 것

이에 따라서 sum이 K원 미만이면 left 증가, 이상이라면 right를 감소해 left가 K원 이상이 되는 가장 작은 값이 되므로 left를 출력해주면 정답

### 풀이 설명

효석이가 정한 날짜에 영일 코인은 X원으로 오르고, 그 후 0원이 될 때까지 하루에 1원씩 가격이 낮아진다고 했으므로 코인의 가격이 오르고 0원이 될때까지 모두 현금화를 한다면 얻을 수 있는 금액은 X + (X - 1) + (X - 2) + ... + 1이 된다. 하지만 이는 0원이 되기 전에 가격이 상승하지 않을때만 성립하기 때문에 그 전에 가격이 오르면 얻을 수 있는 금액이 달라진다.

만약 다음 코인 가격이 상승하는 날짜까지의 차이가 diff라고 하고 코인이 0원이 되기 전에 가격이 상승한다고 하면 이때 현금화 할 수 있는 가격은 X + (X - 1) + (X - 2) + ... + (X - (diff - 1))이 된다. 이때 알 수 있는 것은 현금화 할 수 있는 가격의 항이 diff개가 되고, 전부 X에서 값을 빼는 형태이고, 빼는 값이 0부터 diff - 1까지의 합인 것을 알 수 있다.

이에 따라 현금화할 때 얻을 수 있는 가격에 식을 얻을 수 있고 이를 이용해 가격을 이분탐색하면 K원 이상 현금화 할 수 있는 최소 금액인 X를 구할 수 있다. 주의해야 할 점은 K의 범위가 매우 크기 때문에 현금화 하는 금액의 합이 long의 범위를 넘어설 수 있다는 점이다. 따라서 이분 탐색의 범위를 조절을 잘 해야 한다. X가 1,414,213,562일 때 금액을 한번만 올려도 현금화할 수 있는 가격이 $10^{18}$이 넘으므로 최대 범위를 이정도로 설정하면 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toLong()
    st = StringTokenizer(br.readLine())
    val days = IntArray(N)
    for(i in 0 until N){
        days[i] = st.nextToken().toInt()
    }
    var left = 1L
    var right = 1500000000L
    while(left <= right){
        val mid = (left + right) / 2
        var sum = mid * (mid + 1) / 2
        for (i in 0 until N-1) {
            val diff = days[i + 1] - days[i]
            val d = minOf(mid, diff.toLong())
            sum += mid * d - (d - 1) * d / 2
        }
        if(sum < K){
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    println(left)
}
```