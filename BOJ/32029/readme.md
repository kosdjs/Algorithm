# 백준 32029번: 지금 자면 꿈을 꾸지만

> 문제: https://www.acmicpc.net/problem/32029

### 문제 풀이

그리디, 브루트 포스

deadline = 과제의 마감 기한

timeBeforeSleep = 잠들기 전까지 과제를 했을 때의 시간

countBeforeSleep = 잠들기 전까지 완료한 과제의 수

time = 잠을 잔 이후에 시간

count = 잠을 잔 이후 완료한 과제의 수

answer = 완료할 수 있는 과제의 최대 개수

잠을 한번 BX만큼 자서 과제 완료시간 A를 X만큼 줄일 수 있음

과제를 마감 기한 내에 마무리 해야 하므로 마감 기한을 오름차순으로 정렬함

잠을 자서 과제 완료 시간을 줄이는 것이므로 잠을 자는 건 문제 마감 기한 전에 자야함

모든 X와 모든 문제 번호 i에 대해 i번 과제를 하기 전에 잔다고 하면 최대 10,000번이므로 모든 경우 확인 가능함

근데 i번 과제를 하기 전에 잔다고 했으므로 i가 바뀔때마다 자기 전에 얼마나 문제를 풀어서 시간을 썼는지 확인해야 함

i를 오름차순으로 확인하면 이전 정보를 저장했다가 현재 문제를 시간 내에 풀 수 있으면 푼 정보로 바꾸면 됨

이에 따라 모든 경우를 확인해 문제를 푸는 개수를 count에 저장하고 answer와 비교해 최댓값을 answer에 저장하면 해결 가능한 과제의 최대 개수가 저장되므로 출력하면 정답

### 풀이 설명

N개의 과제가 있고 각각의 문제는 마감 기한 T를 가지고 있다. 모든 과제는 완료하는 데 A만큼의 시간이 걸리고 딱 한번 BX 만큼의 시간을 자서 과제를 완료하는 시간을 X만큼 줄일 수 있다. 이럴 때 최대 몇 개의 과제를 완료할 수 있는 지 구하는 문제이다.

먼저 마감이 있는 과제를 최대한 많이 완료해야 하는 문제이므로 마감 기한이 더 가까운 과제를 먼저 완료해야 한다는 그리디 알고리즘으로 접근할 수 있다. 그러므로 마감 기한을 오름차순으로 정렬해 deadline에 배열로 저장한다.

그러나 일반적인 마감이 있는 과제 문제와 다르게 이 문제는 딱 한 번 잠을 자서 과제를 완료하는 시간을 줄일 수 있기 때문에 얼마나 잠을 잘 것인지와 언제 잠을 잘 것인지가 답을 구하는 데 영향을 끼친다.

얼마나 잠을 잘 것인지는 잠을 잘 때 BX 만큼의 시간을 자면 과제를 완료하는 시간을 X만큼 줄일 수 있는 것이고 X의 범위가 0 이상 A 미만이므로 문제 범위로 따지면 최대 100이 된다.

다음으로 언제 잠을 잘 것인지는 잠을 잔다는 것이 과제 완료 시간을 단축시킨다는 것이므로 과제를 더 빨리 마무리하기 위해 하는 것이므로 과제마다 마감 시간 전에 잠을 자야 의미가 있으므로 과제의 개수 N개 만큼의 선택지가 생기게 되고 N의 범위도 최대 100이 된다.

따라서 영향을 끼치는 두 변수의 곱이 10,000이고 이는 제한 시간내에 확인하기에 충분한 경우의 수이므로 브루트 포스로 문제를 해결할 수 있다는 것이 된다.

그러므로 이를 해결하기 위해 X를 0부터 A미만까지, i를 해당 문제를 풀기 전에 자는 문제의 번호라고 했을 때를 이중 반복문으로 확인하면 된다.

이때 자기 전에 문제를 얼마나 해결했는지, 그 동안 소요한 시간이 얼마나 걸렸는지를 확인하기 위해 각각 countBeforeSleep, timeBeforeSleep으로 저장하고, i번 문제를 풀기 전에 자는 것으로 처리한 이후에 다음 i번 문제를 자기 전에 푸는 것으로 처리하기 위해 반복문 마지막에 풀 수 있는 문제라면 값들을 갱신하도록 했다.

다시 반복문 앞으로 돌아가서 i번과 이후의 문제는 자고 일어나서 확인해야 하기 때문에 timeBeforeSleep에 저장된 시간에서 BX 시간 이후가 되므로 이를 time에, 문제 개수는 아직 i번 문제를 확인하지 않았으므로 count에 그대로 countBeforeSleep의 값을 저장한다.

이후에 i번 문제부터 끝까지 확인하기 위해 k를 해당 문제의 번호로 반복을 하면서 해당 문제가 자고 일어난 이후에 과제 완료 시간 reducedDuration으로 해결가능한지 확인해 가능할때 time에 reducedDuration을 더하고 count를 1 증가시켜 과제를 완료했다는 것을 나타낸다.

그렇게 모든 문제를 확인하면 해당 상황에서 문제를 풀 수 있는 개수가 count에 저장되므로 이를 answer와 비교해 최댓값을 answer에 저장한다.

이에 따라 모든 상황을 비교하고 나면 answer에 구하는 완료 가능한 과제의 최대 개수가 저장되므로 이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val A = nextInt()
    val B = nextInt()
    val deadline = IntArray(N)
    for(i in 0 until N){
        deadline[i] = nextInt()
    }
    deadline.sort()
    var answer = 0
    for(X in 0 until A){
        var timeBeforeSleep = 0
        var countBeforeSleep = 0
        val reducedDuration = A - X
        for(i in 0 until N){
            var time = timeBeforeSleep + B * X
            var count = countBeforeSleep
            for(k in i until N){
                if(time + reducedDuration <= deadline[k]){
                    time += reducedDuration
                    count++
                }
            }
            answer = maxOf(answer, count)
            if(timeBeforeSleep + A <= deadline[i]){
                timeBeforeSleep += A
                countBeforeSleep++
            }
        }
    }
    println(answer)
}
```