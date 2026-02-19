# 백준 9011번: 순서

> 문제: https://www.acmicpc.net/problem/9011

### 문제 풀이

구현

isPossible = R에서 S를 복원할 수 있는지 여부

R = S의 각 원소마다 앞에 본인보다 작은 수가 몇개 있는지

S = R에서 복원한 결과

num = 1부터 n까지의 수 중 아직 뽑히지 않은 수

R이 S에서 본인 앞에 본인보다 작은 수가 몇 개 있는지고, S가 1부터 n까지의 서로 다른 n개의 숫자라고 했으므로 R의 뒷 부분부터 수를 뽑으면 S를 복원할 수 있음

복원하다가 R의 값이 현재 남은 수의 갯수 이상이 되면 수를 뽑을 수 없는 것이므로 불가능한 경우이므로 isPossible에 false를 저장하고 반복문 탈출함

반복문이 끝났을 때 isPossible이 true라면 S가 정상적으로 복원된 것이므로 S에 저장된 값들 출력, false라면 S를 복원할 수 없는 것이므로 IMPOSSIBLE을 출력하면 정답

### 풀이 설명

1부터 n까지의 서로 다른 n개의 정수로 이루어진 순서 S가 있을 때 순서 R이 순서 S에서 해당 원소의 앞에 해당 원소보다 작은 수의 갯수라고 한다. 순서 R이 주어졌을 때 순서 S를 찾는 문제이다. 이 때 R에 대응되는 S가 없을 수 있다.

R이 해당 원소보다 앞에 있는 수 중 해당 원소보다 작은 수의 갯수이기 때문에 뒤에서부터 조건에 맞도록 숫자를 하나씩 뽑아서 배치하면 순서 S를 복원할 수 있다.

예시로 n이 10일 때 R의 맨 마지막 수가 9라면 1부터 10까지의 수 중에서 본인 수보다 작은 수가 9개라면 10번째로 큰 숫자인 것이므로 10을 뽑으면 된다. 그 다음 R의 값이 6이라면 남은 1부터 9까지의 숫자 중 7번째로 큰 숫자인 7이 되므로 7을 뽑아서 배치하면 된다.

이런 식으로 R의 뒷 부분부터 조건에 맞도록 숫자를 뽑으면 된다. 여기서 불가능한 경우가 생기는데 R의 값이 남은 숫자의 갯수 이상이라면 해당 수보다 작은 갯수가 이미 전체 갯수를 넘는 것이므로 이 경우엔 불가능하다.

따라서 문제를 풀기 위해 1부터 n까지의 수를 num이라는 ArrayList에 저장하고 복원이 가능한지 여부를 isPossible 변수에 저장한다. 그 이후에 R의 뒷 부분부터 num.size를 확인해 현재 남아있는 수가 현재 수보다 작은 수의 갯수를 넘는지 확인하면서 하나씩 뽑는다.

만약 남아 있는 수가 부족하다면 isPossible에 false를 저장하고 반복문을 끝내 복원이 불가능하다는 것을 나타내면 된다. 이렇게 수를 하나씩 뽑아서 S에 저장하고 나면 isPossible에 저장된 값에 따라 복원이 가능한지가 결정된다.

true가 저장되어 있다면 S가 정상적으로 복원된 것이므로 저장된 값들을 출력하면 되고, false라면 불가능한 것이므로 IMPOSSIBLE을 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val sb = StringBuilder()
    repeat(T){
        val n = nextInt()
        var isPossible = true
        val R = IntArray(n)
        val S = IntArray(n)
        val num = ArrayList<Int>()
        for(i in 0 until n){
            R[i] = nextInt()
            num.add(i + 1)
        }
        for(i in n - 1 downTo 0){
            if(R[i] + 1 > num.size){
                isPossible = false
                break
            }
            S[i] = num[R[i]]
            num.removeAt(R[i])
        }
        if(isPossible){
            for(i in 0 until n){
                sb.append(S[i]).append(" ")
            }
        } else {
            sb.append("IMPOSSIBLE")
        }
        sb.append("\n")
    }
    print(sb)
}
```