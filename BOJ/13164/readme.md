# 백준 13164번: 행복 유치원

> 문제: https://www.acmicpc.net/problem/13164

### 문제 풀이

그리디

diff[i] = i번째 원생과 i + 1번째 원생의 키 차이

diff 배열을 내림차순으로 정렬한 후 모든 값의 합에서 앞에서부터 K - 1개의 값을 빼면 정답

### 풀이 설명

가장 키가 큰 원생과 가장 키가 작은 원생의 키 차이는 사실상 인접한 원생의 키 차이를 모두 더한것과 같다

![](https://velog.velcdn.com/images/kosdjs/post/236ae461-52e3-48b5-b093-d4ca2493a593/image.png)


그림과 같이 한 조에 키가 3, 4, 5, 6인 원생들이 키 순서대로 있다고 생각해보면 이때의 비용은 $6 - 3 = 3$이 된다. 이는 다시 생각해보면 그림에서 빨간색으로 칠한 부분처럼 키 순서대로 정렬했을 때 인접한 원생들의 키 차이를 모두 더한값이 된다는 것을 알 수 있다.

따라서 N명의 원생들을 키 순서대로 일렬로 줄 세우고, 총 K개의 조로 나누었을 때 티셔츠 만드는 비용의 합을 최소로 하는 방법은 인접한 원생들의 키 차이의 합이 최소가 되도록 K개의 조로 나누는 방법이 된다.

이를 인접한 원생들의 키 차이의 합으로 생각해보면 같은 조에 속한 원생들은 서로 인접해 있어야 하기 때문에 인접한 두 조의 경계에 있는 두 원생의 키 차이는 비용에 들어가지 않는다. 따라서 지용은 전체 원생들의 키 차이의 합에서 인접한 조의 경계에 있는 원생들의 키 차이를 뺀 값이 된다. 

이를 최소로 하기 위해서는 전체 원생들의 키 차이의 합은 고정되어 있기 때문에 키 차이가 많이 나는 원생들을 다른 조로 나눠서 빼는 값을 최대로 만들어야 한다. K개의 조로 나눈다고 했으므로 빼야하는 인접한 조의 경계에 있는 원생들의 키 차이는 K - 1개이므로 모든 인접한 원생들의 키 차이에서 큰 순서대로 K - 1개의 값을 빼면 최솟값이 될 수 있다.

그러므로 이를 코드로 작성할 때는 이미 입력이 키 순서대로 들어온다고 했으므로 모든 원생들의 키 차이의 합에서 인접한 원생들의 키 차이를 배열에 넣고 내림차순으로 정렬한 후 큰 값부터 K - 1개 뺀 값을 구해 정답을 구할 수 있다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    var prev = st.nextToken().toInt()
    val diff = IntArray(N - 1)
    var answer = 0
    for(i in 0 until N - 1){
        var curr = st.nextToken().toInt()
        diff[i] = curr - prev
        answer += diff[i]
        prev = curr
    }
    diff.sortDescending()
    for(i in 0 until K - 1){
        answer -= diff[i]
    }
    println(answer)
}
```