# 백준 16498번: 작은 벌점

> 문제: https://www.acmicpc.net/problem/16498

### 문제 풀이

이분 탐색

max = 현재 뽑은 카드의 숫자 중 최댓값

min = 현재 뽑은 카드의 숫자 중 최솟값

answer = |max - min| 중 최솟값

모든 a에 대해 숫자 하나를 뽑고 그 중 b에서 a에서 뽑은 숫자와 가장 차이가 적은 숫자를 이분 탐색으로 뽑아 max, min 값 변경

그 후 max와 min 사이에 있는 c 값을 뽑기 위해 이분 탐색하고, max와 min 사이의 값을 뽑을 수 있으면 바로 |max - min| 계산 후 answer와 비교해 최솟값 찾음, 사이에 없다면 그 구간보다 큰 값 하나, 작은 값 하나를 가져와 각각의 상황의 |max - min| 계산 후 더 작은 값을 answer와 비교하고 최솟값 저장

이를 모든 a에 대해 반복하면 answer에 저장된 값이 정답

### 풀이 설명

세 플레이어가 각각 A 개의 카드 중 한 개, B 개의 카드 중 한 개, C 개의 카드 중 한 개를 내는 상황에서 a, b, c의 카드가 나왔을 때 | max(a,b,c) – min(a,b,c) | 의 최솟값을 구하려면 모든 카드의 조합을 보기엔 A, B, C가 최악의 상황일 때 각각 1,000 개가 될 수 있으므로 모든 조합을 구할수는 없다.

여기서 세 카드의 최댓값과 최솟값의 차이가 적게 나와야 하기 때문에 만약 a를 먼저 뽑은 후에 b를 뽑는다고 생각하면 a와 가장 차이가 적게 나는 카드를 뽑으면 된다. 그렇게 뽑은 a와 b가 있을 때 또 c를 뽑는다고 생각하면 a, b 와 차이가 가장 적게 나는 카드를 뽑으면 된다.

따라서 모든 a에 대해 가장 차이가 적게 나도록 b와 c를 뽑으면 되는 문제이다. 그러므로 이를 구하기 위해 A, B, C 개의 카드를 각각 오름차순으로 정렬한 후 모든 a에 대해서 현재 a에 대해 차이가 가장 적게 나는 b를 먼저 뽑고, 이후에 뽑힌 a와 b에 대해 차이가 가장 적게 나는 c를 뽑으면 된다.

b를 뽑을 때는 단순히 a와 비교해 a보다 작은 값중 가장 큰 값과 크거나 같은 값중 가장 작은 값을 비교해 a와 차이가 가장 적은 값을 비교하면 되지만, c를 뽑을 때는 a와 b 두 수에 대해 비교를 해야 하기 때문에 여태까지 뽑은 카드의 최댓값, 최솟값을 각각 max, min에 저장을 한다.

그리고 c를 뽑을 때 c가 min보다 작다면 뽑은 c가 새로운 최솟값이 되므로 min에 c를 저장을 하고 차이를 계산하면 되고, c가 min보다 크거나 같고 max보다 작거나 같으면 최댓값, 최솟값이 변경되지 않으므로 이 값의 차이를 계산하면 된다. 그리고 c가 max보다 크다면 c가 최댓값이 되는 것이므로 max에 c를 저장하고 차이를 계산하면 된다.

이렇게 모든 a에 대해 가장 차이가 적은 b, c를 구한 후 최댓값과 최솟값의 차이를 계산하고 이 값중 최솟값을 저장하고 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer
import kotlin.math.abs

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val A = st.nextToken().toInt()
    val B = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val a = IntArray(A)
    val b = IntArray(B)
    val c = IntArray(C)
    st = StringTokenizer(br.readLine())
    for(i in 0 until A){
        a[i] = st.nextToken().toInt()
    }
    st = StringTokenizer(br.readLine())
    for(i in 0 until B){
        b[i] = st.nextToken().toInt()
    }
    st = StringTokenizer(br.readLine())
    for(i in 0 until C){
        c[i] = st.nextToken().toInt()
    }
    a.sort()
    b.sort()
    c.sort()
    var answer = Int.MAX_VALUE
    for(i in 0 until A){
        var max = a[i]
        var min = a[i]
        var left = 0
        var right = B - 1
        while(left <= right){
            val mid = (left + right) / 2
            if(b[mid] < min){
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        if(left == B){
            min = b[right]
        } else if(right == -1){
            max = b[left]
        } else {
            if(abs(b[left] - min) > abs(max - b[right])){
                min = b[right]
            } else {
                max = b[left]
            }
        }
        left = 0
        right = C - 1
        while(left <= right){
            val mid = (left + right) / 2
            if(c[mid] < min){
                left = mid + 1
            } else if(c[mid] > max){
                right = mid - 1
            } else {
                break
            }
        }
        if(left <= right){
            answer = minOf(answer, abs(max - min))
        } else {
            if(left == C){
                min = c[right]
            } else if(right == -1){
                max = c[left]
            } else {
                if(abs(c[left] - min) > abs(max - c[right])){
                    min = c[right]
                } else {
                    max = c[left]
                }
            }
            answer = minOf(answer, abs(max - min))
        }
    }
    println(answer)
}
```