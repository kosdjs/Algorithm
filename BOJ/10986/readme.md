> 문제: https://www.acmicpc.net/problem/10986

### 문제 풀이

A의 해당 수까지의 구간 합을 sum으로 정의

sum에 저장된 값을 M으로 나눈 값의 갯수를 세서 map에 저장

map[0]은 M으로 나누어 떨어지는 구간합의 수이므로 바로 정답에 더함

map에 전체 키 동안 value 값에서 2개를 뽑는 조합을 구해 정답에 더함

### 풀이 설명

$S_i = A_1$ 부터 $A_i$ 까지의 구간 합이라고 해보자

그러면 우리가 확인해야 하는 $A_i$부터 $A_j$까지의 구간 합은 $S_j - S_{i-1}$으로 표현할 수 있다

만약 여기서 $S_j = a \times M + b$, $S_{i-1} = c \times M + d$와 같이 구간 합을 M의 몫과 나머지라고 생각해보면

$S_j - S_{i-1} = (a - c) \times M + (b - d)$와 같은 식이 나올 것이고

이 값이 M으로 나누어 떨어지려면 $(b - d) = 0$이어야 한다

즉, i부터 j까지의 구간 합을 1부터의 구간 합 $S$ 두개를 빼는 것으로 나타낼 수 있고

$S$를 $M$으로 나눈 나머지가 같다면 현재 확인하는 구간의 합이 $M$으로 나누어 떨어진다는 점을 알 수 있다

만약 $S_1$, $S_3$, $S_5$가 $M$으로 나눈 나머지가 같다고 해보자

그럼 이 $S$들로 구할 수 있는 $M$으로 나누어 떨어지는 구간은

$S_5 - S_1$, $S_5 - S_3$, $S_3 - S_1$ 이렇게 세 구간이다

이는 $1, 3, 5$ 중에서 두 수를 뽑아 내림차순으로 정렬한 것이 된다

즉,  $S$를 $M$으로 나눈 나머지들을 전부 구해서 같은 수의 갯수를 센 후

그 수들에서 2개를 뽑는 조합을 구하면 $M$으로 나누어 떨어지는 구간을 모두 구할 수 있다

### 소스 코드
```kotlin
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.HashMap
import java.util.StringTokenizer

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val A = IntArray(N)
    val sum = LongArray(N){0}
    val map = HashMap<Int, Int>()
    st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        A[i] = st.nextToken().toInt()
    }
    sum[0] = A[0].toLong()
    val tempMod = (sum[0] % M.toLong()).toInt()
    map[tempMod] = 1
    for(i in 1 until N){
        sum[i] = sum[i-1] + A[i].toLong()
        val mod = (sum[i] % M.toLong()).toInt()
        if(map.containsKey(mod)){
            map[mod] = map[mod]!! + 1
        } else {
            map[mod] = 1
        }
    }
    var answer : Long = 0
    if(map.containsKey(0)){
        answer += map[0]!!.toLong()
    }
    for(key in map.keys){
        answer += map[key]!!.toLong() * (map[key]!! - 1).toLong() / 2
    }
    println(answer)
}
```