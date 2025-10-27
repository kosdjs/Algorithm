# 백준 2166번: 다각형의 면적

> 문제: https://www.acmicpc.net/problem/2166

### 문제 풀이

기하학

[신발끈 공식](https://ko.wikipedia.org/wiki/%EC%8B%A0%EB%B0%9C%EB%81%88_%EA%B3%B5%EC%8B%9D)을 이용해 넓이를 구함

firstX, firstY = 첫 점의 좌표

curX, curY = 현재 점의 좌표

prevX, prevY = 이전 점의 좌표

공식에 따라 계산하고 출력하면 정답

### 풀이 설명

2차원 평면상에 N(3 ≤ N ≤ 10,000)개의 점으로 이루어진 다각형이 있다. 이 다각형의 면적을 구하는 프로그램을 작성하시오. 첫째 줄에 N이 주어진다. 다음 N개의 줄에는 다각형을 이루는 순서대로 N개의 점의 x, y좌표가 주어진다. 좌표값은 절댓값이 100,000을 넘지 않는 정수이다.

아래 그림처럼 A, B, C로 이루어진 삼각형이 있다고 했을 때 신발끈 공식에 따라 벡터 외적을 계산하면 $\frac12|OA \times OB + OB \times OC + OC \times OB|$이 된다.

![](https://velog.velcdn.com/images/kosdjs/post/d626ee39-ad34-43bf-9f07-43fa85c82d83/image.png)

$\frac12OA \times OB$가 빨간색, $\frac12OB \times OC$가 파란색, $\frac12OC \times OA$가 초록색 영역이고 벡터 외적의 정의에 의해서 빨간색, 파란색 영역이 양수, 초록색 영역이 음수가 되기 때문에 최종적으로 남는 값이 삼각형 ABC의 넓이가 된다.

식을 계산하고 나서 결과값에 절대값을 하는 이유도 이와 같은데 입력에서 주어지는 점들이 반시계 방향인지, 시계 방향인지에 따라 계산 결과가 양수 또는 음수가 나올 수 있다. 우리가 구하는 값은 넓이이기 때문에 절댓값을 씌워서 출력하면 정답이 된다.

### 소스 코드
```kotlin
fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())
    var area = 0.0
    var curX = st.nextToken().toDouble()
    var curY = st.nextToken().toDouble()
    var prevX = 0.0
    var prevY = 0.0
    val firstX = curX
    val firstY = curY
    for(i in 1 until N){
        prevX = curX
        prevY = curY
        st = StringTokenizer(br.readLine())
        curX = st.nextToken().toDouble()
        curY = st.nextToken().toDouble()
        area += (prevX * curY) - (prevY * curX)
    }
    area += (curX * firstY) - (curY * firstX)
    println(String.format("%.1f", kotlin.math.abs(area) / 2))
}
```