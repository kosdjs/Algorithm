> 문제: https://www.acmicpc.net/problem/17425

### 문제 풀이

수학 문제

f(N)을 전부 구해놓고 배열에 저장, 그 후 이 값들을 이용해 g(N)의 값들도 구해서 배열에 저장

### 풀이 설명

문제에서 f(N)이 N의 약수의 합이라고 했으므로, 약수의 합을 구하는 것을 생각해 보면 그 수를 나눠서 구해야할 것 같지만 반대로 N은 약수의 배수이므로, 배열을 만들어서 본인의 배수가 될 수 있는 수에 본인의 값을 더해놓으면 쉽게 구할 수 있다.

g(N)은 문제의 조건에 따라 f(N)의 누적합이기 때문에 g(N-1)에 방금 구해놓았던 f(N)의 값을 더해주면 된다.

### 소스 코드
```kotlin
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(args:Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val T = br.readLine().toInt()
    val f = LongArray(1000001){1}
    for(i in 2..1000000){
        var idx = i
        while(idx < f.size){
            f[idx] += i.toLong()
            idx += i
        }
    }
    val g = LongArray(1000001){0}
    for(i in 1..1000000){
        g[i] = g[i-1] + f[i]
    }
    for(i in 1..T){
        val t = br.readLine().toInt()
        bw.write("${g[t]}\n")
    }
    bw.flush()
    bw.close()
}
```