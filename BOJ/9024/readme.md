# 백준 9024번: 두 수의 합

> 문제: https://www.acmicpc.net/problem/9024

### 문제 풀이

투 포인터

min = K와 두 정수의 합의 차이중 최솟값

sum = 두 정수의 합

diff = K와 두 정수의 합의 차이

배열을 오름차순으로 정렬 후 최대한 두 정수의 합이 K와 비슷해지도록 다음과 같이 찾음

가장 작은 값과 가장 큰 값의 합부터 확인함

두 정수의 합이 K보다 작다면 합을 더 크게 만들어야 하므로 작은 값을 늘림

두 정수의 합이 K보다 크다면 합을 더 작게 만들어야 하므로 큰 값을 줄임

두 정수의 합이 K와 같다면 다음 쌍을 봐야 하므로 작은 값을 늘리고 큰 값도 줄임

여기서 나온 diff 값이 가장 작은 쌍의 수를 세야 하므로 diff < min일 때 합이 K와 가장 비슷한 새로운 쌍을 찾은 것이므로 min에 diff 대입 후 count를 1로 초기화, diff == min이면 합이 K와 비슷한 쌍이 또 있는 것이므로 count++

이때 count가 합이 가장 K에 가까운 쌍의 수이므로 출력하면 정답.

### 풀이 설명

여러 개의 서로 다른 정수가 주어졌을 때, 주어진 정수들 중에서 서로 다른 두 정수의 합이 주어진 또 다른 정수에 가장 가까운 두 정수의 조합의 수를 찾는 것은 투 포인터 알고리즘으로 풀 수 있다.

두 수의 합이 K가 되게 만드려면 합이 K보다 작은지 큰지에 따라 어떻게 해야 할지가 다르다. 만약 K보다 작다면 수를 증가시켜 K를 만들면 되고, K보다 크다면 수를 감소시켜서 K를 만들면 된다.

하지만 여기서 될 수 있는 수가 정해져 있으므로 만약 두 수 중 하나의 수를 고정시켰을 때 다른 수를 증가시켜서 K를 만드는 쌍을 찾았다고 생각하면 찾은 수보다 더 큰 다른 수의 경우를 확인할 필요가 없기 때문에 굳이 확인하지 않아도 되는 수가 나오게 된다.

따라서 수를 오름차순으로 정렬해놓게 되면 두 수의 합이 K보다 작거나 커서 값을 증감시켜야 할 때 작은 값은 더 작은 인덱스에 저장되어 있고 큰 값은 더 큰 인덱스에 저장되어 있기 때문에 값을 인덱스를 통해 증감시킬 수 있게 된다.

두 정수의 합이 K보다 작을 때 합을 더 크게 만들기 위해서 작은 수를 늘리는 이유는 배열의 탐색 범위를 줄이기 위해서이다. 오름차순으로 정렬한 인덱스를 더 작은 값의 인덱스를 왼쪽, 더 큰 값의 인덱스를 오른쪽이라고 했을 때 탐색 범위가 작은 인덱스가 큰 인덱스를 만날때까지이므로 이를 줄이기 위해서는 두 인덱스 중 하나를 오른쪽으로 이동시켜야 하는 것이므로 작은 인덱스를 오른쪽으로 이동시키는 것이 탐색 범위를 줄이는 것이 된다.

따라서 이런 원리로 두 수의 합들을 찾으면서 최대한 합이 K와 비슷해지는 쌍들의 수를 찾으면 된다. 그러므로 합들을 확인하면서 K와의 차이를 비교하고 그 최솟값을 min에 저장한다. 만약 차이가 min보다 작게 되면 여태 찾은 쌍들보다 더 합이 K와 비슷한 쌍을 찾은 것이므로 min 값을 변경해주고 새로운 쌍을 찾은것이므로 개수가 1개라는 뜻으로 count에 1을 대입한다.

현재 쌍의 합의 차이가 min과 같게 된다면 합이 K와 비슷한 쌍을 찾은것이므로 count를 1 증가시키면 된다. 이에 따라 count에 최대한 합이 K와 비슷한 쌍의 갯수가 저장되므로 이를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer
import kotlin.math.abs

fun main(){
    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()
    repeat(t){
        System.gc()
        var st = StringTokenizer(br.readLine())
        val n = st.nextToken().toInt()
        val K = st.nextToken().toInt()
        val arr = IntArray(n)
        st = StringTokenizer(br.readLine())
        for(i in 0 until n){
            arr[i] = st.nextToken().toInt()
        }
        arr.sort()
        var left = 0
        var right = n - 1
        var count = 0
        var min = Int.MAX_VALUE
        while(left < right){
            val sum = arr[left] + arr[right]
            val diff = abs(K - sum)
            if(sum < K){
                left++
            } else if(sum > K) {
                right--
            } else {
                left++
                right--
            }
            if(diff < min){
                min = diff
                count = 1
            } else if(diff == min){
                count++
            }
        }
        println(count)
    }
}
```