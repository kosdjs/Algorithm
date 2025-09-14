# 백준 33678번: 로마의 휴일

> 문제: https://www.acmicpc.net/problem/33678

### 문제 풀이

누적합, 이분 탐색

$a[i] = i$일까지의 일급 누적합

$a[N - mid - i] * X + a[N] - a[N - i] =$ 휴가를 mid일 사용하고 휴가를 다녀온 이후 i일 일하는 일급의 합

위의 식을 이용해 휴가 사용 일수를 이분 탐색으로 찾으면 정답

### 풀이 설명

$N$일 중 한 번만 연속된 기간을 선택하여 휴가를 사용할 수 있고, 휴가를 다녀온 후에는 $i$일에 일을 하면 $a_{i}$만큼 일급을 받고, 휴가를 다녀오기 전에는 $i$일에 일을 하면 보너스가 적용되어 $a_{i} \times X$만큼 일급을 받으며, 휴가 중에는 일을 하여 일급을 받을 수 없으며, 첫날부터 휴가를 다녀올 수 있다고 했으므로 일급의 합은 휴가 사용 일수, 휴가를 다녀온 이후 일을 한 날짜의 수를 알면 쉽게 구할 수 있다.

먼저 휴가를 다녀오기 전에 한 일의 일급의 합은 휴가 사용일이 $mid$이고 휴가를 다녀온 후에 $i$일 일한다고 생각하면 $N$일 중 $mid$일을 일하지 않고, $i$일 만큼 휴가 뒤에 일을 하기 때문에 첫 날부터 $N - mid - i$일을 연속으로 일한다고 생각하면 된다. 따라서 $(a_1+a_2+\cdots+a_{N - mid - 1})\times K$가 된다.

휴가를 다녀오고 나서 i일 일할 때의 일급의 합은 $a_{N - i + 1}+a_{N - i + 2}+\cdots+a_N$이 된다. 식을 확인했을 때 휴가를 다녀오기 전과 휴가를 다녀온 후의 일급이 각각 연속된 항의 합이라는 것을 알 수 있다. 따라서 이를 쉽게 구하기 위해 누적합을 미리 구해놓는다.

$a[i]$ 를 $a_1$부터 $a_i$까지의 누적합이라고 해놓으면 휴가 전의 일급의 합은 $a[N - mid - 1] \times K$가 되고, 휴가 후의 일급의 합은 $a[N] - a[N - i]$가 된다. 이렇게 구한 식으로 최대 휴가 사용 일수를 이분 탐색으로 찾고 1일 이상 사용 가능할때만 사용 일수를 출력하고 휴가를 사용하지 못할때는 -1을 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val X = st.nextToken().toInt()
    val a = IntArray(N + 1){0}
    st = StringTokenizer(br.readLine())
    for(i in 1..N){
        a[i] = a[i - 1] + st.nextToken().toInt()
    }
    var left = 0
    var right = N
    while(left <= right){
        val mid = (left + right) / 2
        var i = 0
        while(N - mid - i >= 0){
            val sum = a[N - mid - i] * X + a[N] - a[N - i]
            if(sum >= K){
                left = mid + 1
                break
            }
            i++
        }
        if(N - mid - i < 0){
            right = mid - 1
        }
    }
    if(right <= 0){
        println(-1)
    } else {
        println(right)
    }
}
```