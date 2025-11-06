# 백준 11758번: CCW

> 문제: https://www.acmicpc.net/problem/11758

### 문제 풀이

기하학

벡터 외적 값의 부호에 따라 반시계인지, 시계인지, 직선인지 출력하면 정답

### 풀이 설명

2차원 좌표 평면 위에 있는 점 3개 P1, P2, P3가 주어진다. P1, P2, P3를 순서대로 이은 선분이 어떤 방향을 이루고 있는지 구하는 프로그램을 작성하시오.

P1, P2, P3를 순서대로 이은 선분이 반시계 방향이라면 직선 P1 P2와 직선 P1 P3가 반시계 방향으로 있다는 뜻임, 즉 벡터 P1P2, P1P3의 외적을 구하면 외적의 부호로 반시계인지, 직선인지, 시계 방향인지 알 수 있다.

따라서 부호가 양수라면 반시계 방향이므로 1, 음수라면 시계 방향이므로 -1, 0이라면 직선이므로 0을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val x = IntArray(3)
    val y = IntArray(3)
    for(i in 0 until 3){
        val st = StringTokenizer(br.readLine())
        x[i] = st.nextToken().toInt()
        y[i] = st.nextToken().toInt()
    }
    val ccw = (x[1] - x[0]) * (y[2] - y[0]) - (y[1] - y[0]) * (x[2] - x[0])
    println(if(ccw > 0) 1 else if(ccw < 0) -1 else 0)
}
```