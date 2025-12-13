# 백준 10830번: 행렬 제곱

> 문제: https://www.acmicpc.net/problem/10830

### 문제 풀이

분할 정복

times(A, B) = N x N 행렬 A, B의 곱을 반환하는 함수

pow(base, B) = N x N 행렬 base의 B승을 반환하는 함수

N x N 행렬 A, B의 곱은 결과로 N x N 행렬이 나오고 각 원소는 A의 행, B의 열의 원소를 하나하나 곱해서 더하는 값이 되므로 이를 구하는 함수 times(A, B)를 구현

구현한 times(A, B)를 이용해 이진 거듭제곱 방식으로 거듭제곱을 구하는 pow(base, B) 함수를 구현하고 이를 이용해 $A^B$를 구해 모든 원소를 1,000으로 나눈 나머지를 출력하면 정답

### 풀이 설명

크기가 N*N인 행렬 A가 주어진다. 이때, A의 B제곱을 구하는 프로그램을 작성하시오. 수가 매우 커질 수 있으니, $A^B$의 각 원소를 1,000으로 나눈 나머지를 출력한다.

거듭 제곱을 구하는 방법은 B승을 절반씩 분할해 구하면 된다. 그러나 이 경우에는 행렬의 곱이기 때문에 행렬의 곱을 우선적으로 구현할 필요가 있다.

행렬의 곱은 기본적으로 앞 행렬의 행과 뒷 행렬의 열에서 원소 하나씩 대응해 곱한 값들을 모두 더해서 구하는 것이므로 행렬 A, B의 곱을 구하는 함수 times(A, B)를 구현한다. 이 때 문제에서 나왔듯이 수가 매우 커질 수 있으니 모든 원소를 1,000으로 나눈 나머지를 저장하도록 해야 한다.

그 이후에는 행렬의 거듭 제곱을 구해야 하므로 이진 거듭제곱 방식을 이용해 행렬의 거듭제곱을 구하는 함수를 만든다. 이진 거듭제곱 방식은 거듭제곱을 지수의 합으로 나눌 수 있다는 점을 이용해 지수를 이진수로 나타냈을 때 1이 되는 값으로 나눠서 구하는 방법이다. 예를 들어 13을 이진수로 나타내면 1101가 되고 여기서 1이 되는 값을 보면 8, 4, 1이므로 8승, 4승, 1승으로 나누어서 구하는 방법이다.

이에 따라 구현한 거듭제곱을 구하는 함수를 사용해 $A^B$승의 모든 원소를 1,000으로 나눈 나머지를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    fun nextLong(): Long{
        nextToken()
        return nval.toLong()
    }
    val N = nextInt()
    val B = nextLong()
    val A = Array(N){IntArray(N)}
    for(i in 0 until N){
        for(j in 0 until N){
            A[i][j] = nextInt()
        }
    }
    fun times(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray>{
        val result = Array(N){ IntArray(N) }
        for(i in 0 until N){
            for(j in 0 until N){
                for(k in 0 until N){
                    result[i][j] += A[i][k] * B[k][j]
                }
                result[i][j] %= 1000
            }
        }
        return result
    }
    //반복문으로 B승 구하기
    fun pow(base: Array<IntArray>, B: Long): Array<IntArray> {
        var result = Array(N){IntArray(N)}
        for(i in 0 until N){
            result[i][i] = 1
        }
        var mat = base
        var exp = B
        while (exp > 0) {
            if (exp % 2 == 1L) result = times(result, mat)
            mat = times(mat, mat)
            exp /= 2
        }
        return result
    }
    val result = pow(A, B)
    val sb = StringBuilder()
    for(i in 0 until N){
        for(j in 0 until N){
            sb.append(result[i][j]).append(" ")
        }
        sb.append("\n")
    }
    print(sb)
}
```