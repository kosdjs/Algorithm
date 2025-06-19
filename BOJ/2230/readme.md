> 문제: https://www.acmicpc.net/problem/2230

### 문제 풀이

투 포인터

배열에 값을 저장해 오름차순으로 정렬

앞에서부터 left, right로 두 수를 뽑아 차이를 비교함

이때 차이가 M보다 크면 정답을 저장하는 answer와 비교해 차이가 더 작다면 값 저장 후 left 증가

차이가 정확히 M이면 더 탐색할 필요 없이 정답이 M이기 때문에 반복문 탈출

차이가 M보다 작다면 right 증가

right이 배열의 범위를 넘어가지 않을 동안 반복하면 정답을 찾을 수 있음

### 풀이 설명

수열의 두 수를 뽑아 차이를 구했을 때 M 이상인 값 중 최솟값을 구하는 문제

두 수를 뽑아 차이를 구하는 것은 두 수 중 큰 값에서 작은 값을 빼서 구하는 것이기 때문에 수열을 정렬해놓으면 두 수의 차이에 따라 빼야하는 작은 값을 늘려야 하는지, 큰 값을 늘려야 하는지 판단하기 편하다

차이가 M보다 크다면 빼는 수를 더 큰 값으로 바꿔서 그래도 차이가 만족하는지 확인하고, 작다면 빼기 전의 큰 값을 더 크게 만들어 차이를 더 크게 할 수 있다.

그리고 차이가 M인 값을 찾으면 문제 조건에 따라 이상인 최솟값이기 때문에 결과를 반환하면 된다

이러면 모든 조합을 살펴보지 않아도 두 수의 차이를 효율적으로 구할 수 있다

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    var answer = Int.MAX_VALUE
    var left = 0
    var right = 0
    val arr = IntArray(N)
    for(i in 0 until N){
        arr[i] = br.readLine().toInt()
    }
    arr.sort()
    while(right < N){
        val diff = arr[right] - arr[left]
        if(diff > M){
            if(diff < answer){
                answer = diff
            }
            left++
        } else if(diff < M){
            right++
        } else {
            answer = diff
            break
        }
    }
    println(answer)
}
```