# 백준 32069번: 가로등

> 문제: https://www.acmicpc.net/problem/32069

### 문제 풀이

BFS

어두운 정도가 가장 가까운 가로등까지의 거리이고 어두운 정도가 낮은 것부터 출력해야 하므로 가로등부터 BFS로 좌표를 하나씩 출력하면 됨

L이 매우 크기 때문에 방문 처리를 일반적 배열로 하지 않고 HashSet을 이용해 포함 관계를 계산해 방문처리를 함

따라서 큐에 가로등의 좌표부터 넣고 BFS로 K번 탐색하면 어두운 정도가 가장 작은 K개를 탐색할 수 있음 따라서 K번 탐색할 때 어두운 정도를 출력하면 정답

### 풀이 설명

수직선 도로 위에 $N$ 개의 가로등이 켜져 있다. 각 가로등의 위치는 왼쪽부터 차례대로 $A_1 < \cdots < A_N$로 나타낼 수 있다.

위치 $x$의 어두운 정도를, 그 위치로부터 가장 가까운 가로등까지의 거리로 정의하자. 이는 $N$ 개의 수 $| A_1 - x |, \cdots, | A_N - x |$ 중에서 가장 작은 값과 같다. 여기서, $| \cdot |$는 절댓값 기호로, $y \ge 0$이면 $|y| := y$, $y < 0$이면 $|y| := -y$이다.

$x = 0$부터 $x = L$까지 $L+1$ 개의 정수 위치의 어두운 정도를 모두 계산했을 때, 가장 작은 값부터 $K$ 번째로 작은 값까지 차례대로 출력하는 프로그램을 작성하라.

도로가 수직선이라고 했고 어두운 정도가 그 위치로부터 가장 가까운 가로등까지의 거리라고 했으므로, 어두운 정도를 가장 작은 값부터 출력한다는 것은 가로등을 포함한 위치들 중 가로등과 가장 가까운 K개 위치를 찾으면 된다.

따라서 BFS를 이용해 가로등과 가장 가까운 K개 위치를 찾으면 된다. 그러므로 큐에 위치의 좌표와 가로등까지의 거리를 저장하고 방문처리는 L이 매우 크기 때문에 HashSet에 원소가 포함되어있는지로 확인한다.

처음 입력받은 가로등의 위치 a와 거리 0을 큐에 집어넣고 가로등 위치를 방문처리하기 위해 set에 위치 a를 넣는다. 그 후 K번 탐색할 동안 큐에서 위치 x와 거리 dist를 꺼내서 dist를 출력해준다. 그 후 x - 1, x + 1의 위치가 탐색되었는지 확인하고 탐색이 되지 않았다면 큐에 위치와 거리 dist + 1을 넣고 방문 처리를 하기 위해 set에 위치를 넣는다.

이러면 BFS에 따라 가로등에서 가까운 K개의 위치를 확인해 거리를 출력한 것이므로 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val L = st.nextToken().toLong()
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val queue = ArrayDeque<LongArray>()
    st = StringTokenizer(br.readLine())
    val set = HashSet<Long>()
    for(i in 0 until N){
        val a = st.nextToken().toLong()
        queue.add(longArrayOf(a, 0L))
        set.add(a)
    }
    var count = 0
    val sb = StringBuilder()
    while(count < K){
        val (x, dist) = queue.removeFirst()
        sb.append("$dist\n")
        count++
        if(x > 0 && !set.contains(x - 1)){
            queue.add(longArrayOf(x - 1, dist + 1))
            set.add(x - 1)
        }
        if(x < L && !set.contains(x + 1)){
            queue.add(longArrayOf(x + 1, dist + 1))
            set.add(x + 1)
        }
    }
    print(sb)
}
```