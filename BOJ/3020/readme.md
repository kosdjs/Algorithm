# 백준 3020번: 개똥벌레

> 문제: https://www.acmicpc.net/problem/3020

### 문제 풀이

누적합

bottom[i] = i + 1의 높이를 가지는 석순의 개수

top[H - i] = i의 높이를 가지는 종유석의 개수

석순과 종유석의 높이가 높은 순에서 낮은 순으로 가는 방향으로 누적합을 계산해 해당 높이의 구간에서 파괴해야 하는 장애물의 수를 구함

그 후 모든 높이에서 파괴되는 석순과 종유석의 개수를 더하고 이때 최솟값의 개수를 세주면 정답

### 풀이 설명

개똥벌레는 장애물을 피하지 않는다. 자신이 지나갈 구간을 정한 다음 일직선으로 지나가면서 만나는 모든 장애물을 파괴한다. 이때, 개똥벌레가 파괴해야하는 장애물의 최솟값과 그러한 구간이 총 몇 개 있는지 구하려면 각 구간에 장애물이 몇개가 있는지 알아야 한다.

이 문제에서는 석순과 종유석이 모두 장애물이 될 수 있고 시작 위치가 반대이기 때문에 같이 구하기 힘들다. 따라서 높이에 따라 장애물이 되는 석순의 개수와 종유석의 개수를 따로 구하면 된다.

먼저 석순의 경우 현재 높이 구간의 장애물이 되려면 현재 높이와 크거나 같은 높이의 석순이 장애물이 될 수 있다. 따라서 높이에 따른 석순의 개수를 구해놓고 높이가 높은 석순부터 현재 높이와 같은 석순까지의 개수의 누적합이 현재 높이 구간의 장애물이 되는 석순의 개수가 된다.

종유석의 경우 반대로 맨 위에서 아래로 뻗는 형태이기 때문에 종유석의 높이가 높을수록 낮은 높이에서 장애물이 된다. 이를 계산하기 쉽도록 개수를 구할 때 종유석 자체의 높이가 아니라 종유석의 맨 아래 부분의 높이를 기준으로 구하면 현재 높이 구간의 장애물이 되는 종유석의 개수는 맨 아래 부분의 높이가 현재 높이보다 작거나 같은 종유석의 개수가 된다. 따라서 이 개수도 누적합으로 구하면 된다.

이렇게 누적합으로 구한 높이에 따른 장애물이 되는 석순, 종유석의 개수를 더한 값이 그 높이 구간에서 개똥벌레가 파괴해야 하는 장애물의 개수가 되므로 이 값의 최솟값과 이 최솟값의 갯수를 구해서 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val H = st.nextToken().toInt()
    val bottom = IntArray(H){0}
    val top = IntArray(H){0}
    for(i in 0 until N){
        if(i % 2 == 0){
            bottom[readLine()!!.toInt() - 1]++
        } else {
            top[H - readLine()!!.toInt()]++
        }
    }
    for(i in H - 2 downTo 0){
        bottom[i] += bottom[i + 1]
    }
    for(i in 1 until H){
        top[i] += top[i - 1]
    }
    var min = Int.MAX_VALUE
    var count = 1
    for(i in 0 until H){
        if(min > bottom[i] + top[i]){
            min = bottom[i] + top[i]
            count = 1
        } else if(min == bottom[i] + top[i]){
            count++
        }
    }
    println("$min $count")
}
```