# 백준 32358번: 근성아 일하자

> 문제: https://www.acmicpc.net/problem/32358

### 문제 풀이

이분 탐색

trash = 쓰레기가 있는 나무 좌표의 리스트

쿼리가 1일 때 trash에 좌표 저장

2일 때 trash에 저장된 좌표가 있는지 확인하고 있다면 오름차순으로 정렬한 후 이분 탐색으로 현재 좌표와 가까운 나무 중 좌표가 작은 나무와 큰 나무를 찾음

찾은 나무의 거리를 비교해 가까운 나무의 쓰레기를 줍도록 이동 처리함

이를 모든 쿼리에 대해 실행하면 정답

### 풀이 설명

현재 위치에서 시작하여 쓰레기가 있는 나무 중 가장 가까운 나무로 이동하여 쓰레기를 수거하고, 모든 쓰레기를 수거할 때까지 이 행동을 반복하기 때문에 2번 쿼리가 들어올 때만 쓰레기의 위치를 정렬하면 된다.

따라서 1번 쿼리가 들어올 때 리스트에 나무 좌표를 저장하고 2번 쿼리가 들어올 때 리스트를 오름차순으로 정렬한다.

그 이후에 현재 위치와 가장 가까운 두 나무의 좌표를 구하기 위해 이분 탐색을 실행한다. 이분 탐색을 실행하고 나면 right에 현재 좌표보다 좌표가 작은 나무 중 가장 가까운 나무, left에 현재 좌표보다 좌표가 크거나 같은 나무 중 가장 가까운 나무의 인덱스가 들어오게 된다.

만약 left가 trash의 크기와 같게 된다면 trash에 든 모든 나무의 좌표가 현재 좌표보다 작은 것이므로 일직선으로 쭉 이동하면서 쓰레기를 수거하면 된다. 따라서 trash[0]의 좌표까지 이동한 거리를 추가하고 리스트를 비워준다.

right가 0보다 작다면 마찬가지로 trash에 든 모든 나무의 좌표가 현재 좌표보다 크거나 같은 것이므로 일직선으로 쭉 이동해 쓰레기를 수거하면 된다. 가장 좌표가 큰 나무까지 이동해 쓰레기를 수거하면 된다.

둘의 경우가 모두 아니라면 right에 저장된 나무와 left에 저장된 나무의 좌표를 비교해 더 가까운 나무로 가서 쓰레기를 수거하는데 이를 현재 좌표보다 크거나 같은 나무가 없거나 현재 좌표가 작은 나무가 없을때 까지 반복한다.

그러면 현재 좌표보다 작은 나무만 남거나 현재 좌표보다 크거나 같은 나무만 남기 때문에 아까 했던 것처럼 일직선으로 쭉 이동해 쓰레기를 수거하면 된다. 따라서 끝까지 이동하고 리스트를 비워주면 된다.

이를 모든 쿼리에 대해 반복하면 총 이동 거리를 구할 수 있다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val trash = ArrayList<Int>()
    var current = 0
    var answer = 0L
    repeat(N){
        val st = StringTokenizer(br.readLine())
        val op = st.nextToken().toInt()
        if(op == 1){
            trash.add(st.nextToken().toInt())
        } else {
            if(trash.isNotEmpty()){
                trash.sort()
                var left = 0
                var right = trash.size - 1
                while(left <= right){
                    val mid = (left + right) / 2
                    if(trash[mid] < current){
                        left = mid + 1
                    } else {
                        right = mid - 1
                    }
                }
                if(left >= trash.size){
                    answer += current - trash[0]
                    current = trash[0]
                    trash.clear()
                } else if(right < 0){
                    answer += trash.last() - current
                    current = trash.last()
                    trash.clear()
                } else {
                    while(right >= 0 && left < trash.size){
                        if(current - trash[right] > trash[left] - current){
                            answer += trash[left] - current
                            current = trash[left]
                            left++
                        } else {
                            answer += current - trash[right]
                            current = trash[right]
                            right--
                        }
                    }
                    if(left >= trash.size){
                        answer += current - trash[0]
                        current = trash[0]
                        trash.clear()
                    } else {
                        answer += trash.last() - current
                        current = trash.last()
                        trash.clear()
                    }
                }
            }
        }
    }
    println(answer)
}
```