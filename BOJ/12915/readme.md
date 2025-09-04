# 백준 12915번: 대회 개최

> 문제: https://www.acmicpc.net/problem/12915

### 문제 풀이

그리디, 이분 탐색

E, H가 각각 대회 횟수만큼 되도록 EM, MH에서 가져오고 남은 문제를 전부 M으로 옮겨서 E, M, H에 대회 횟수만큼의 문제가 있게 되면 횟수만큼 대회 개최 가능

이 기준에 따라 이분 탐색으로 개최 가능한 최대 횟수를 구해 출력하면 정답

### 풀이 설명

- E개의 문제는 쉬운 문제로 사용할 수 있다.
- EM개의 문제는 쉬운 문제나 중간 문제로 사용할 수 있다.
- M개의 문제는 중간 문제로 사용할 수 있다.
- MH개의 문제는 중간 문제나 어려운 문제로 사용할 수 있다.
- H개의 문제는 어려운 문제로 사용할 수 있다.

문제가 이렇게 분류되어 있고, 대회를 한 번 개최할 때 쉬운 문제, 중간 문제, 어려운 문제가 모두 있어야 하고, 문제는 한 대회의 한 난이도로만 사용이 가능할 때 개최 가능한 최대 횟수를 구하려면 EH, MH개의 문제가 난이도가 변할 수 있기 때문에 난이도 별로 가능한 문제 갯수를 직접 구하는 것보다 특정 횟수로 맞출 수 있는지 확인하는 게 더 쉽다.

그러므로 개최 횟수를 이분 탐색하면서 개최 가능한 횟수의 최댓값을 구하면 된다. 개최 하려는 횟수를 N이라고 할 때 개최 가능한 횟수인지 판단하려면 쉬운 문제, 중간 문제, 어려운 문제가 각각 N개가 될 수 있는지 판단하면 된다.

쉬운 문제와 어려운 문제는 부족하다면 각각 EM, MH개의 문제에서만 가져올 수 있지만, 중간 문제는 부족할 때 EM, MH개의 문제 두 군데에서 모두 가져올 수 있기 때문에 최대한 문제를 가능하도록 맞추려면 EM, MH개의 문제에서 최대한 쉬운, 어려운 문제의 갯수를 맞추도록 하고 남은 문제를 중간 문제의 갯수에 맞추도록 사용해야 한다.

따라서 먼저 E + EM개와 H + MH개의 문제가 각각 N보다 작다면 쉬운 문제와 어려운 문제의 갯수가 N개가 될 수 없다는 뜻이므로 개최가 불가능 하다고 판단하면 된다. 또한 EM, MH개의 문제에서 쉬운 문제와 어려운 문제의 갯수를 N개로 맞추도록 사용하고 남은 개수의 문제와 M개의 문제를 더했을 때 N개가 될 수 없다면 이 때도 개최가 불가능 한 것으로 판단하면 된다. 이런 조건에 따라 판단한 개최 가능한 갯수 중 최댓값을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val e = st.nextToken().toInt()
    val em = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val mh = st.nextToken().toInt()
    val h = st.nextToken().toInt()
    var left = 0
    var right = 200000
    while(left <= right){
        val mid = (left + right) / 2
        if(e + em < mid || mh + h < mid){
            right = mid - 1
        } else {
            var curM = m
            if(e < mid){
                curM += em - (mid - e)
            } else {
                curM += em
            }
            if(h < mid){
                curM += mh - (mid - h)
            } else {
                curM += mh
            }
            if(curM < mid){
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
    }
    println(right)
}
```