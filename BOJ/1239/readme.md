# 백준 1239번: 차트

> 문제: https://www.acmicpc.net/problem/1239

### 문제 풀이

브루트포스

arr = 입력된 데이터를 저장하는 배열

pickNum(count) = 현재 데이터를 count개 선택해 selectedArr에 순서대로 정렬하고 N개 뽑혔을 때 원의 중심을 지나는 선이 몇 개 생기는지 세는 재귀 함수

visit[i] = i번 데이터를 뽑았는지 여부

answer = 원의 중심을 지나는 선의 갯수의 최댓값

입력받은 N개의 데이터를 활용해 원형 차트를 만들 때 데이터를 나열하는 순서에 따라 원의 중심을 지나는 선의 갯수가 달라지므로 모든 순서를 순열로 뽑아서 확인해야 함

이에 따라 pickNum(count) 함수를 현재 count가 N이 될때까지 데이터를 한개 뽑아 selectedArr배열에 순열을 저장하도록 만들고 count가 N이 된다면 순열을 정상적으로 만든 것이므로 이 때 원의 중심을 지나는 선의 갯수를 세어 최댓값을 answer에 반영하도록 만듬

이때 원의 중심을 지나는 선의 갯수를 세는 방법은 이 선이 원을 반으로 나누는 것이고 데이터의 총 합이 항상 100이라고 했으므로 모든 데이터에 대해 그 데이터를 시작으로 이어지는 데이터의 총 합이 50이 될 때 원의 중심을 지나는 선이 생기는 것으로 간주해 이 선의 갯수를 result에 저장하면 됨

이 방식으로 원의 중심을 지나는 선의 갯수를 세면 처음으로 발견하는 선과 마지막으로 발견하는 선이 같은 선이 되므로 answer와 result - 1을 비교해야 함

만약 선이 없는 경우에 result - 1이 -1이 되지만 answer의 초깃값이 0이므로 최댓값을 챙기는 maxOf(answer, result - 1) 함수에 의해 값이 사라지므로 괜찮음

이에 따라 모든 데이터를 순서대로 배열했을 때 원의 중심을 지나는 선의 갯수의 최댓값이 answer에 저장되므로 출력하면 정답

### 풀이 설명

조사한 데이터로 원형 차트를 만들 때 데이터를 나열하는 순서를 바꿔서 원의 중심을 지나는 선의 개수가 최대로 되게 만들었을 때 원의 중심을 지나는 선의 갯수의 최댓값을 구하는 문제이다.

원의 중심을 지나는 선은 정확히 원을 절반으로 나누는 선이므로 데이터의 합이 50%가 되면 생기게 된다. 데이터의 총합이 항상 100으로 주어진다고 했으므로 다음 그림과 같이 10, 40, 10, 40의 순서로 원형 차트를 만든다고 했을 때 10, 40의 합이 50이 되고 이는 50%에 해당하는 값이 되므로 이 두 데이터의 합이 50이 되어서 원의 중심을 지나는 선을 만들게 된다.

![](https://velog.velcdn.com/images/kosdjs/post/ed7dd35c-c474-4a7f-88ab-2a8448cff951/image.png)

따라서 특정 데이터를 시작으로 생각했을 때 그 뒤에 이어지는 데이터를 더하면서 합이 50이 되는 경우의 수를 세면 된다.

데이터의 갯수가 총 8개가 주어진다고 했으므로 이 8개의 데이터 중 8개를 뽑아 순서대로 정렬하는 경우의 수는 8!이 되고 이는 40,320개가 된다. 그리고 정렬된 갯수마다 각 데이터부터 시작해 총 합이 50이 되는지 확인해야 하므로 총 8번 확인해야 하므로 이를 순열의 갯수와 곱하면 322,560이 되므로 실행 횟수가 크지 않으므로 모든 경우를 확인해도 시간 내에 확인할 수 있다.

그리고 이 방식대로 각 데이터부터 시작하는 총 합이 50이 될때 생기는 원의 중심을 지나는 선의 갯수를 센다면 위의 예시에서 첫 두 데이터인 10, 40의 합인 50이 만드는 선과, 마지막 두 데이터인 10, 40의 합인 50이 만드는 선이 같은 선이 된다. 이처럼 처음 생기는 원의 중심을 지나는 선과 마지막에 세는 원의 중심을 지나는 선은 같은 선이므로 선의 갯수에서 한 개를 빼주어야 한다.

단, 이럴 경우에 원의 중심을 지나는 선이 존재하지 않다면 -1이라는 잘못된 값이 나올 수 있으므로 음수의 경우에 처리가 되지 않도록 초기값을 0으로 만들어두고 최댓값을 저장하도록 만들면 된다.

따라서 원의 중심을 지나는 선의 최댓값을 answer에 저장하도록 변수를 정의하고 0으로 초기화 한다. 그 이후에 입력받은 데이터를 arr 배열에 저장하고 여기서 순열을 뽑아 저장할 배열을 selectedArr로 정의하고, 어떤 데이터가 뽑혔는지 확인하기 위한 배열을 visit으로 정의한다.

그 이후에 순열을 뽑기 위해 pickNum(count) 재귀함수를 정의해 N개가 뽑힐 때까지 순열을 뽑아 selectedArr 배열에 저장하고 N개를 뽑은 이후에는 각 데이터에서부터 이어지는 데이터의 총합이 50이 되는지 확인해 원의 중심을 지나는 선의 갯수를 result에 저장하고 이 값에서 마지막 선을 제외한 result - 1과 answer를 비교해 최댓값을 다시 answer에 저장하도록 한다.

그렇게 정의한 pickNum(count)함수를 이용해 pickNum(0)을 실행하면 N개의 데이터를 순열로 나타내는 모든 경우에 대해 원의 중심을 지나는 선의 갯수의 최댓값이 answer에 저장되므로 answer를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val arr = IntArray(N){nextInt()}
    var selectedArr = IntArray(N)
    val visit = BooleanArray(N)
    var answer = 0
    fun pickNum(count: Int){
        if(count == N){
            var result = 0
            for(i in 0 until N){
                var sum = selectedArr[i]
                var idx = i + 1
                while(sum < 50 && idx < N){
                    sum += selectedArr[idx]
                    idx++
                }
                if(sum == 50) result++
            }
            answer = maxOf(answer, result - 1)
        } else {
            for(i in 0 until N){
                if(!visit[i]){
                    visit[i] = true
                    selectedArr[count] = arr[i]
                    pickNum(count + 1)
                    visit[i] = false
                    selectedArr[count] = 0
                }
            }
        }
    }
    pickNum(0)
    println(answer)
}
```