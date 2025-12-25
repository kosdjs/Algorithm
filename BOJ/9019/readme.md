# 백준 9019번: DSLR

> 문제: https://www.acmicpc.net/problem/9019

### 문제 풀이

BFS

visit[i] = i를 방문했는지 여부

prev[i] = i를 만들기 전의 숫자

ops[i] = i를 만들기 위해 사용한 명령어

path = A에서 B를 만들기 위해 사용한 명령어들

큐에 A를 넣고 visit[A]에 true를 대입해 방문 처리

큐에서 B를 꺼내기 전까지 현재 값 cur를 꺼냄

명령어를 통해 만들어낸 수 next에 대해 visit[next]를 확인해 방문 여부를 확인함

방문하지 않은 수라면 prev[next]에 cur, ops[next]에 현재 사용한 명령어를 저장하고 next를 큐에 넣음

큐에서 꺼낸 수가 B라면 A에서 B를 만드는 경로를 찾은 것이므로 반복문 종료

B에서부터 prev 배열을 확인해 A를 찾는 경로를 역추적해 path 리스트에 ops 배열에 있는 명령어들을 저장함

역추적한 경로가 저장되어있으므로 reverse()함수를 이용해 path 리스트를 뒤집고 출력하면 정답

### 풀이 설명

네 개의 명령어 D, S, L, R 을 이용하는 간단한 계산기가 있다. 이 계산기에는 레지스터가 하나 있는데, 이 레지스터에는 0 이상 10,000 미만의 십진수를 저장할 수 있다. 각 명령어는 이 레지스터에 저장된 n을 다음과 같이 변환한다. n의 네 자릿수를 d1, d2, d3, d4라고 하자(즉 n = ((d1 × 10 + d2) × 10 + d3) × 10 + d4라고 하자)

1. D: D 는 n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다. 그 결과 값(2n mod 10000)을 레지스터에 저장한다.
2. S: S 는 n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
3. L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
4. R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.

위에서 언급한 것처럼, L 과 R 명령어는 십진 자릿수를 가정하고 연산을 수행한다. 예를 들어서 n = 1234 라면 여기에 L 을 적용하면 2341 이 되고 R 을 적용하면 4123 이 된다.

여러분이 작성할 프로그램은 주어진 서로 다른 두 정수 A와 B(A ≠ B)에 대하여 A를 B로 바꾸는 최소한의 명령어를 생성하는 프로그램이다. 예를 들어서 A = 1234, B = 3412 라면 다음과 같이 두 개의 명령어를 적용하면 A를 B로 변환할 수 있다.

1234 →L 2341 →L 3412
1234 →R 4123 →R 3412

따라서 여러분의 프로그램은 이 경우에 LL 이나 RR 을 출력해야 한다.

n의 자릿수로 0 이 포함된 경우에 주의해야 한다. 예를 들어서 1000 에 L 을 적용하면 0001 이 되므로 결과는 1 이 된다. 그러나 R 을 적용하면 0100 이 되므로 결과는 100 이 된다.

최소한의 명령어로 A를 B로 만들어야 하므로 A에서부터 BFS를 이용해 B로 만드는 경로를 찾으면 된다. 그리고 명령어의 수가 아니라 직접 어떤 명령어를 사용했는지 경로 자체를 찾아야 하는 문제이므로 경로를 저장해야 한다.

이를 저장하기 위해 현재 수가 어떤 명령어를 통해 만들어졌는지 저장하는 배열 ops, 어떤 수에서 명령어를 통해 만들었는지 저장하는 배열 prev를 정의한다.

BFS 특성상 같은 수가 다시 나오면 그 수는 이미 더 빠르게 만들어지는 방법이 이미 나온 상황이므로 다시 탐색할 필요가 없다. 따라서 방문 여부를 처리하는 visit 배열을 정의한다.

그 이후에 A를 큐에 넣고 방문 처리를 위해 visit[A]에 true를 대입한다. 큐에서부터 현재 값 cur를 하나씩 꺼내면서 명령어를 통해 만들어진 수 next에 대해 방문 여부를 확인해 방문한 적이 없을 경우에만 prev[next], ops[next]에 현재 수 cur와 사용한 명령어를 저장하고 큐에 next를 넣는다.

B를 만들때까지만 탐색하면 되므로 cur가 B와 같을 때 반복문을 탈출한다. 그 이후에 경로를 찾아야 하므로 경로를 저장할 리스트 path를 정의하고 B에서부터 prev배열을 따라서 A가 나올때까지 ops 배열에 있는 값을 저장한다.

그렇게 path 리스트에 저장된 경로는 역추적을 했으므로 순서가 뒤집혀 있으므로 reverse() 함수를 이용해 경로를 뒤집어야 한다. 이렇게 뒤집은 경로를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    fun D(x: Int): Int{
        return (x * 2) % 10000
    }
    fun S(x: Int): Int{
        return if(x == 0) 9999 else x - 1
    }
    fun L(x: Int): Int{
        return (x % 1000) * 10 + (x / 1000)
    }
    fun R(x: Int): Int{
        return x / 10 + (x % 10) * 1000
    }
    val sb = StringBuilder()
    repeat(nextInt()){
        val A = nextInt()
        val B = nextInt()
        val q = ArrayDeque<Int>()
        val visit = BooleanArray(10000)
        val prev = IntArray(10000)
        val ops = CharArray(10000)
        visit[A] = true
        q.add(A)
        while (q.isNotEmpty()){
            val cur = q.removeFirst()
            if(cur == B){
                break
            }
            val nextList = listOf(D(cur), S(cur), L(cur), R(cur))
            for(i in nextList.indices){
                val next = nextList[i]
                if(visit[next]) continue
                visit[next] = true
                prev[next] = cur
                ops[next] = when(i){
                    0 -> 'D'
                    1 -> 'S'
                    2 -> 'L'
                    3 -> 'R'
                    else -> ' '
                }
                q.add(next)
            }
        }
        val path = mutableListOf<Char>()
        var num = B
        while(num != A){
            path.add(ops[num])
            num = prev[num]
        }
        path.reverse()
        for(c in path){
            sb.append(c)
        }
        sb.append("\n")
    }
    print(sb)
}
```