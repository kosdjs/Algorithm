# 백준 2343번: 기타 레슨

> 문제: https://www.acmicpc.net/problem/2343

### 문제 풀이

이분 탐색

arr[i] = 강의의 길이

블루레이의 크기를 이분 탐색으로 탐색하면서 강의를 현재 크기로 담았을 때 몇개의 블루레이가 나오는지 세어서 M개 이하가 나오는 최소 크기를 구해 출력하면 정답

### 풀이 설명

블루레이에는 총 N개의 강의가 들어가는데, 블루레이를 녹화할 때, 강의의 순서가 바뀌면 안 된다. 순서가 뒤바뀌는 경우에는 강의의 흐름이 끊겨, 학생들이 대혼란에 빠질 수 있기 때문이다. 즉, i번 강의와 j번 강의를 같은 블루레이에 녹화하려면 i와 j 사이의 모든 강의도 같은 블루레이에 녹화해야 한다.

M개의 블루레이에 모든 기타 강의 동영상을 녹화하기로 했다. 이때, 블루레이의 크기(녹화 가능한 길이)를 최소로 하려고 한다. 단, M개의 블루레이는 모두 같은 크기이어야 한다. 강의의 길이가 분 단위(자연수)로 주어진다. 이때, 가능한 블루레이의 크기 중 최소를 구하는 프로그램을 작성하시오.

강의를 순서대로 담아야 하기 때문에 블루레이의 크기를 정하면 앞에서부터 순서대로 강의의 길이의 합이 크기보다 작거나 같도록 묶어 블루레이가 몇 개 나오는지 구할 수 있다.

N (1 ≤ N ≤ 100,000)과 M (1 ≤ M ≤ N)의 범위에 따라 블루레이의 크기가 1부터 10억까지 가능하므로 완전 탐색은 범위가 너무 넓다. 따라서 이분 탐색으로 크기를 구하면 된다.

따라서 left를 1, right를 10억으로 설정해놓고 mid를 구해서 탐색한다. 이 때 블루레이의 크기가 mid라면 강의 하나의 길이가 mid보다 크게 된다면 그 강의를 블루레이에 담을 수 없기 때문에 불가능한 경우로 따지고 left에 mid + 1을 대입한다.

그런 경우가 아니라면 현재 블루레이의 들어가는 강의의 길이의 합을 sum으로 정의하고 0으로 초기화 한 후에 강의의 앞에서부터 길이를 sum에 더한다. count는 현재 강의를 담는 블루레이의 번호이며 개수가 된다.

현재 강의의 길이를 sum에 더했을 때 sum이 mid보다 커진다면 현재 강의부터는 다음 블루레이에 담아야 하기 때문에 count를 증가시키고 sum에 현재 강의의 길이인 arr[i]를 대입하고 다음 강의를 확인한다.

이렇게 하면 count에 모든 강의를 담기 위한 블루레이의 개수가 나오고 이 개수가 M보다 크다면 블루레이의 크기가 모자라 블루레이가 너무 많이 나오는 것이므로 left에 mid + 1을 대입한다.

M개 이하가 나오게 된다면 우리가 구하는 경우지만 최솟값을 구해야 하므로 right에 mid - 1을 대입한다. 이 과정을 left가 right보다 커질때까지 반복하면 left에 블루레이의 개수가 M개 이하가 되는 최소 크기가 저장되므로 이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val arr = IntArray(N)
    st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        arr[i] = st.nextToken().toInt()
    }
    var left = 1
    var right = 1000000000
    while(left <= right){
        val mid = (left + right) / 2
        var sum = 0
        var count = 1
        var possible = true
        for(i in 0 until N){
            if(arr[i] > mid){
                left = mid + 1
                possible = false
                break
            }
            sum += arr[i]
            if(sum > mid){
                sum = arr[i]
                count++
            }
        }
        if(!possible) continue
        if(count > M){
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    println(left)
}
```