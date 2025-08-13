# 백준 1092번: 배

> 문제: https://www.acmicpc.net/problem/1092

### 문제 풀이

그리디

$using[i] = i$번 크레인이 옮길 박스의 수

$capable =$ 현재 박스를 옮길 수 있는 크레인 중 무게 제한이 제일 적은 크레인 $($무게 제한 기준으로 오름차순으로 정렬한 후의 인덱스 $0$부터 $N - 1$까지이고, 이렇게 하면 $N$에서 이 값을 빼면 현재 박스를 옮길 수 있는 크레인의 수가 됨$)$

$time =$ 박스의 수가 가장 많은 크레인의 박스의 수 $=$ 최소 시간

$count =$ 지금까지 분배한 박스의 수

크레인을 오름차순, 박스를 내림차순 정렬 후 박스 하나마다 다음 순서를 반복

1. 크레인의 무게 제한을 비교해 현재 박스를 옮길 수 있는 무게 제한이 제일 적은 크레인을 찾음

2. $count$ 가 $(N - capable) \times time$과 같아지면 $time$을 늘림

3. 사용 가능한 크레인 중 $time$보다 $using$값이 더 작은 크레인에 박스를 분배

이를 모든 박스에 대해 반복하고 $time$을 출력하면 정답

### 풀이 설명

크레인마다 최대 하중이 있고 크레인이 한 박스를 옮기는 데 1분이 걸리며 모든 크레인이 동시에 움직인다고 했으므로 크레인이 옮기는 박스의 수 중 최댓값이 박스를 옮기는 데 걸리는 시간이 된다.

그러므로 문제에서 구하는 모든 박스를 옮기는 최소 시간은 무게 제한에 맞게 박스를 옮겼을 때 옮긴 박스의 수가 가장 많은 크레인의 박스의 수의 최솟값이 된다. 그러므로 한 크레인이 너무 많은 박스를 옮기지 않도록 박스를 최대한 균등하게 분배해서 옮겨야 한다.

따라서 박스를 옮길 수 있는 크레인에 최대한 균등하게 분배하기 위해 박스를 무게 기준 내림차순으로 정렬하고, 현재 박스를 옮길 수 있는 크레인의 수를 편하게 보기 위해 무게 제한을 기준으로 오름차순으로 정렬한다.

그 뒤에 $using[i]$를 $i$번 크레인이 옮길 박스의 수, $capable$을 현재 박스를 옮길 수 있는 크레인 중 무게 제한이 제일 적은 크레인, $time$을 박스의 수가 가장 많은 크레인의 박스의 수, $count$를 지금까지 분배한 박스의 수라고 정의하고 박스마다 옮길 수 있는 크레인을 확인해 최대한 균등하게 분배를 하면 된다.

여기서 박스를 옮길 수 있는 크레인이 없는 경우는 모든 박스를 옮길 수 없는 경우이므로 더 이상 분배할 필요가 없이 -1을 출력하면 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val cranes = IntArray(N)
    var st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        cranes[i] = st.nextToken().toInt()
    }
    val M = br.readLine().toInt()
    st = StringTokenizer(br.readLine())
    val boxes = IntArray(M)
    for(j in 0 until M){
        boxes[j] = st.nextToken().toInt()
    }
    cranes.sort()
    boxes.sortDescending()
    val using = IntArray(N)
    var capable = N
    var time = 0
    var count = 0
    for(box in boxes){
        for(i in 0 until N){
            if(box <= cranes[i]){
                capable = i
                break
            }
        }
        if(capable == N){
            time = -1
            break
        }
        if(count >= (N - capable) * time){
            time++
        }
        for(j in capable until N){
            if(using[j] < time){
                using[j]++
                count++
                break
            }
        }
    }
    println(time)
}
```