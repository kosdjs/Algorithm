# 백준 28140번: 빨강~ 빨강~ 파랑! 파랑! 달콤한 솜사탕!

> 문제: https://www.acmicpc.net/problem/28140

### 문제 풀이

누적합

red[i] = 문자열에서 i 이상의 인덱스 중 가장 먼저 문자 R이 등장하는 인덱스

blue[i] = 문자열에서 i 이상의 인덱스 중 가장 먼저 문자 B이 등장하는 인덱스

l 이상의 인덱스 중 가장 먼저 문자 R이 등장하는 인덱스는 red[l] 즉 a = red[l]

b는 a 보다 큰 인덱스에서 R이 등장하는 인덱스여야 하므로 b = red[a + 1]

c는 b 보다 큰 인덱스에서 B가 등장하는 인덱스여야 하므로 c = blue[b + 1]

d는 c 보다 큰 인덱스에서 B가 등장하는 인덱스여야 하므로 d = blue[c + 1]

이에 따라 배열을 통해 구한 a, b, c, d가 r보다 전부 작거나 같으면 구간 내에 존재하는 것이므로 출력하고 아니라면 -1을 출력하면 정답.

### 풀이 설명

알파벳 대문자로 이루어진 길이 $N$의 문자열 $S=S_0S_1S_2\cdots S_{N-1}$가 주어질 때 구간 $[l, r]$이 주어질 때, 아래의 규칙을 만족하는 정수 $a,\ b,\ c,\ d$를 찾아야 한다.

- $S_a=S_b=$ R
 
- $S_c=S_d=$ B
 
- $l \leq a \lt b \lt c \lt d \leq r$ 

즉, 구간 $[l, r]$에서 순차적으로 R이 나오는 인덱스 2개와 B가 나오는 인덱스 2개를 찾을 수 있는지를 물어보는 문제이다. 이를 쉽게 구하기 위해서 R이 나오는 다음 인덱스를 저장하는 red 배열과 B가 나오는 다음 인덱스를 저장하는 blue 배열을 만든다. 문자열의 인덱스가 N - 1까지 이므로 현재 인덱스 이후로 R이나 B가 나오지 않을 경우도 있기 때문에 배열을 N으로 초기화해준다.

문자열을 뒤에서부터 확인하면서 R이나 B가 나오면 그때의 인덱스를 red, blue 배열에 저장하고 나오지 않았다면 배열의 뒤의 값을 저장해 현재 인덱스 이후 가장 빨리 나오는 인덱스를 저장한다.

이렇게 저장된 배열을 가지고 구간 $[l, r]$이 주어지면 순차적으로 R, R, B, B가 나오는 인덱스를 구하면 $a\ =\ red[l],\ b\ =\ red[a + 1],\ c\ =\ blue[b + 1],\ d\ =\ blue[c + 1]$가 된다. 이렇게 나온 정수 $a,\ b,\ c,\ d$가 $r$보다 작거나 같으면 조건을 만족하는 값을 찾은것이므로 출력해주면 되고, $r$보다 크다면 조건을 만족하는 값이 없는 것이므로 -1을 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val Q = st.nextToken().toInt()
    val s = br.readLine()
    val red = IntArray(N + 4){N}
    val blue = IntArray(N + 4){N}
    for(i in N - 1 downTo 0){
        val c = s[i]
        red[i] = if(c == 'R') i else red[i + 1]
        blue[i] = if(c == 'B') i else blue[i + 1]
    }
    for(i in 0 until Q){
        st = StringTokenizer(br.readLine())
        val l = st.nextToken().toInt()
        val r = st.nextToken().toInt()
        val a = red[l]
        val b = red[a + 1]
        val c = blue[b + 1]
        val d = blue[c + 1]
        if(a > r || b > r || c > r || d > r){
            bw.write("-1\n")
        } else {
            bw.write("$a $b $c $d\n")
        }
    }
    bw.flush()
    bw.close()
}
```