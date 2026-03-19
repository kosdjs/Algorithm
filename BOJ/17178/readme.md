# 백준 17178번: 줄서기

> 문제: https://www.acmicpc.net/problem/17178

### 문제 풀이

구현

Ticket(alp, num) = 알파벳 alp, 뒤의 숫자가 num인 입장권

arr = 5명씩 N줄로 서있는 사람들의 입장권 순서

pq = 입장권을 입장 가능한 순서대로 확인할 수 있는 우선순위 큐

stack = 대기공간에 입장한 사람들의 입장권

top = 현재 대기공간에 마지막으로 입장한 사람의 인덱스

입장권이 문자열로 주어지므로 이 상태로는 원하는 순서대로 비교하기 힘드므로 "-" 문자를 기준으로 알파벳 alp, 숫자 num으로 나누어 클래스 Ticket(alp, num)으로 변환함

현재 입장 가능한 입장권을 순서대로 확인해야 하므로 우선순위 큐 pq에 모든 입장권을 넣고 가장 빠른 입장권을 하나씩 확인함

대기 공간은 마지막에 들어온 사람부터 나가는 스택의 구조와 동일하므로 이를 배열로 구현하기 위해 stack 배열과 top 인덱스로 구현함

arr 배열에 사람들의 입장권을 순서대로 넣고 pq에 있는 입장 가능한 입장권과 비교해 가능하면 pq에서 입장권을 빼 입장시키고 아니면 현재 사람의 입장권을 스택에 넣음

한 사람이 입장할 때마다 입장 가능한 입장권이 바뀌므로 사람이 입장할때마다 대기 공간에 있는 입장권을 확인해야 함

또한 대기 공간에서 사람이 입장하면 이때도 입장 가능한 입장권이 바뀌므로 반복해서 대기 공간에서 나갈 수 있는 입장권을 확인함

이를 arr에 저장된 모든 입장권에 대해 반복하면 모든 사람이 입장하면 pq에 남아있는 입장권이 없으므로 이때 GOOD을 출력하고, pq에 입장권이 남아있다면 BAD를 출력하면 정답

### 풀이 설명

알파벳과 숫자로 이루어진 입장권을 가진 사람들이 5명씩 N줄 서있을 때 맨 앞 사람만 움직일 수 있다고 한다. 이때 대기 공간으로 이동하거나 입장권의 순서가 맞다면 입장을 할 수 있다고 한다.

단, 대기 공간은 마지막에 들어온 사람만 움직일 수 있고 이는 입장권의 순서가 맞을때 입장만 가능하다고 한다. 이에 따라 5명씩 N줄로 서있는 사람의 입장권이 주어져있을 때 순서대로 모든 사람이 입장 가능한지 출력하는 문제이다.

먼저 모든 입장권을 순서대로 정렬해 현재 입장 가능한 입장권인지 확인해야 하므로 입장권끼리 어느 입장권이 빠른지 비교가 가능해야 한다. 입장권의 순서를 비교하는 방법은 우선 앞의 알파벳이 빠른지 확인하고 같은 알파벳이라면 뒤의 숫자를 비교하는 순서다.

이를 구현하기 위해 알파벳과 숫자를 저장하는 클래스 Ticket(alp, num)을 만들고 비교를 구현하기 위해 Comparable 인터페이스를 구현해 알파벳과 숫자를 순서대로 비교하도록 만든다.

그 이후 모든 입장권을 정렬해 어떤 입장권이 입장이 가능한지 순서대로 확인해야 하는데 이를 배열에 담아서 직접 정렬해 순서대로 확인하는 것 보다는 우선순위 큐에 모두 저장해서 하나씩 순서대로 빼는 것이 더 빠르다. 따라서 입장권 Ticket 클래스를 받는 우선순위 큐를 pq로 만들어서 모든 입장권을 입력받을때 넣는다.

또한 입력받을 때 어떤 사람이 순서대로 움직일 수 있는지도 저장해야 하므로 이차원 배열 arr을 만들어 순서대로 사람들이 어떤 입장권을 가지고 있는지 저장한다.

그 이후에 대기 공간은 사람이 순서대로 들어가고 마지막 사람들부터 나오기 때문에 스택 구조를 사용할 수 있다. 스택은 라이브러리를 사용해도 되지만 배열을 직접 만들어서 사용하는 것이 더 빠르기 때문에 배열 stack과 마지막에 들어간 사람의 인덱스 top을 만들어서 구현한다.

그럼 이제 arr에 저장된 입장권을 하나씩 확인하며 pq에 있는 입장권과 비교해 입장이 가능하면 바로 입장시켜 pq에서 해당 입장권을 빼고, 불가능하다면 대기 공간인 stack에 입장권을 넣으면 된다.

여기서 문제는 대기 공간에 있는 입장권을 언제 확인할 지인데 문제에 따로 명시되어 있지 않지만 현재 입장 가능한 입장권은 맨 앞줄 사람이 입장할 때 바뀌기 때문에 이때 대기 공간에 마지막으로 들어온 사람의 입장권을 확인해 입장 가능하면 입장시키면 된다. 또한 이 상황도 입장 가능한 입장권이 바뀌므로 대기 공간에서 다음으로 나갈 수 있는 사람의 입장권도 연속해서 확인해야 한다.

모든 입장권을 우선순위 큐 pq에 저장해놓고 순서대로 입장한 입장권을 우선순위 큐에서 빼기 때문에 이렇게 모든 입장권을 순서대로 확인했을 때 pq에 입장권이 남아있다면 입장을 못한 사람이 있는 것이므로 BAD, 비었다면 모든 사람이 입장한 것이므로 GOOD을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() = System.`in`.bufferedReader().run {
    val N = readLine().toInt()
    data class Ticket(val alp: Char, val num: Int): Comparable<Ticket>{
        override fun compareTo(other: Ticket): Int {
            return if(this.alp != other.alp) this.alp.compareTo(other.alp) else this.num.compareTo(other.num)
        }
    }
    val arr = Array(N){Array(5){Ticket('A', 0)} }
    val pq = PriorityQueue<Ticket>()
    for(i in 0 until N){
        val st = StringTokenizer(readLine())
        for(j in 0 until 5){
            val s = st.nextToken()
            val st2 = StringTokenizer(s, "-")
            arr[i][j] = Ticket(st2.nextToken()[0], st2.nextToken().toInt())
            pq.add(arr[i][j])
        }
    }
    val stack = Array(5 * N){Ticket('A', 0)}
    var top = -1
    for(i in 0 until N){
        for(j in 0 until 5){
            stack[++top] = arr[i][j]
            while(top >= 0){
                if(stack[top] == pq.peek()){
                    top--
                    pq.poll()
                } else break
            }
        }
        while(top >= 0){
            if(stack[top] == pq.peek()){
                top--
                pq.poll()
            } else break
        }
    }
    println(if(pq.isEmpty()) "GOOD" else "BAD")
}
```