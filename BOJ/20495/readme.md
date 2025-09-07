# 백준 20495번: 수열과 헌팅

> 문제: https://www.acmicpc.net/problem/20495

### 문제 풀이

이분 탐색

각 원소의 최솟값, 최댓값을 배열에 저장하고 각각 오름차순으로 정렬

최댓값 배열에서 현재 최솟값이 몇번째에 올 수 있는지 이분 탐색으로 확인해 최소 인덱스 찾기

최솟값 배열에서 현재 최댓값이 몇번째에 올 수 있는지 이분 탐색으로 확인해 최대 인덱스 찾기

찾은 최소, 최대 인덱스를 출력하면 정답

### 풀이 설명

수열의 각 원소는 값의 범위가 주어져 있고 이 수열을 정렬하면 원소의 값에 따라서 그 원소가 몇번째에 있을지가 다르다. 이 수가 있을 수 있는 인덱스의 범위를 구하려면 최소 인덱스가 될 수 있는 상황과 최대 인덱스가 될 수 있는 상황을 찾아야 한다.

먼저 현재 원소의 최소 인덱스가 되게 하려면 현재 원소가 최솟값이 되고, 나머지 원소들이 최댓값이 되면 정렬했을 때 순서가 가장 앞으로 올 수 있다. 최대 인덱스의 경우는 반대로 현재 원소가 최댓값이고 다른 원소가 최댓값일때이다.

여기서 같은 수가 여러 개 있으면 수의 순서는 상관이 없다고 했으므로 정렬한 후의 최소 인덱스는 원소의 최댓값들 중 현재 원소의 최솟값보다 작은 수의 바로 다음 인덱스가 된다. 최대 인덱스의 경우엔 원소의 최솟값들 중 현재 원소의 최댓값과 작거나 같은 수의 마지막 수의 인덱스가 된다.

이에 따라 원소의 최솟값들과 최댓값들을 오름차순으로 정렬하고 최소 인덱스를 구하기 위해 정렬된 최댓값들 중 현재 원소의 최솟값보다 작은 수의 개수를 구하기 위해 이분 탐색을 실행한다. 최대 인덱스의 경우에도 최솟값들 중 현재 원소의 최댓값보다 작거나 같은 수의 마지막 수의 인덱스를 구하기 위해 이분 탐색을 진행해서 구한다.

이렇게 구한 최소 인덱스와 최대 인덱스를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val bw = System.out.bufferedWriter()
    val minArr = IntArray(N)
    val maxArr = IntArray(N)
    val curMin = IntArray(N)
    val arrMax = IntArray(N)
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        minArr[i] = a - b
        maxArr[i] = a + b
        curMin[i] = a - b
        arrMax[i] = a + b
    }
    minArr.sort()
    maxArr.sort()
    for(i in 0 until N){
        var left = 0
        var right = N - 1
        while(left <= right){
            val mid = (left + right) / 2
            if(maxArr[mid] >= curMin[i]){
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        bw.write("${left + 1} ")
        left = 0
        right = N - 1
        while(left <= right){
            val mid = (left + right) / 2
            if(minArr[mid] > arrMax[i]){
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        bw.write("${right + 1}\n")
    }
    bw.flush()
    bw.close()
}
```