# 백준 1722번: 순열의 순서

> 문제: https://www.acmicpc.net/problem/1722

### 문제 풀이

구현

count = 현재 순열의 앞에서부터 확정된 숫자를 제외하면 가능한 가짓수

visit = 순열에 사용된 숫자인지 여부

i = 현재 사용 가능한 숫자의 수

count를 i로 나누면 현재 확인하는 자리의 수까지 고정되면 나머지 남은 수가 나올 수 있는 가짓수가 되므로 나눈 값을 저장해 정의에 맞게 들어가도록 계산함

순서가 주어지면 이 순서를 count로 나눈 몫이 현재 자리 숫자가 사용되지 않은 수 중에서 몇번째인지를 나타냄 따라서 이를 모든 자리에 따라 구하면 순열을 구할 수 있음

역으로 순열이 주어졌다면 앞자리 부터 사용되지 않은 숫자의 몇 번째 숫자가 사용되었는지 확인하면 순서를 구할 수 있음

따라서 순열 또는 순서를 출력하면 정답

### 풀이 설명

1부터 N까지의 수를 임의로 배열한 순열은 총 N! = N×(N-1)×…×2×1 가지가 있다.

임의의 순열은 정렬을 할 수 있다. 예를 들어  N=3인 경우 {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}의 순서로 생각할 수 있다. 첫 번째 수가 작은 것이 순서상에서 앞서며, 첫 번째 수가 같으면 두 번째 수가 작은 것이, 두 번째 수도 같으면 세 번째 수가 작은 것이….

N이 주어지면, 아래의 두 소문제 중에 하나를 풀어야 한다. k가 주어지면 k번째 순열을 구하고, 임의의 순열이 주어지면 이 순열이 몇 번째 순열인지를 출력하는 프로그램을 작성하시오.

순열의 정의에 따라 맨 앞자리부터 생각해보면 1부터 N까지 N가지 경우의 수가 가능하고 그 뒤에 두 번째 자리의 수는 1부터 N까지의 수 중에서 맨 앞자리에서 사용한 숫자를 제외한 N - 1가지 수가 가능하다.

여기서 순열의 순서가 오름차순이라고 했으므로 맨 앞자리가 1인 순열이 (N - 1)!개, 2인 순열이 (N - 1)!개부터 N인 순열까지 쭉 (N - 1)!개 있는 것이므로 맨 앞자리 숫자가 i라고 하면 순서가 (i - 1) * (N - 1)!부터 시작한다는 뜻이다.

두 번째 자리의 숫자는 첫 번째 자리에서 사용된 숫자를 제외한 N - 1개가 가능하고 또 순서대로 (N - 2)!개의 순열이 N - 1개 있는 것이므로 사용되지 않은 숫자의 몇 번째인지에 따라 순서를 알 수 있다.

따라서 count를 현재 순열의 앞에서부터 확정된 숫자를 제외하면 가능한 가짓수, visit을 순열에 사용된 숫자인지 여부로 정의하고 첫 번째 자리의 숫자부터 끝 자리의 숫자까지 계산하면 정확히 몇 번째 순열인지 알 수 있다.

그러므로 순열의 순서가 있다면 그 순서에 따라 맨 앞자리부터 숫자를 찾아 순서에 맞는 순열을 출력하고, 순열이 주어졌다면 맨 앞자리의 숫자부터 확인해 순열의 순서를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val visit = BooleanArray(21){false}
    var count = 1L
    for(i in 2..N){
        count *= i
    }
    if(st.nextToken().toInt() == 1){
        //번호로 순열 출력
        var num = st.nextToken().toLong() - 1
        for(i in N downTo 1){
            count /= i
            var unusedCount = 0
            var unusedNum = 0
            while(unusedCount <= num / count){
                unusedNum++
                if(!visit[unusedNum]){
                    unusedCount++
                }
            }
            print("$unusedNum ")
            visit[unusedNum] = true
            num %= count
        }
    } else {
        //순열로 번호 출력
        var answer = 0L
        for(i in N downTo 1){
            count /= i
            val num = st.nextToken().toInt()
            var unusedCount = 0
            var unusedNum = 0
            while(unusedNum < num){
                unusedNum++
                if(!visit[unusedNum]){
                    unusedCount++
                }
            }
            answer += count * (unusedCount - 1)
            visit[num] = true
        }
        println(answer + 1)
    }
}
```