# 백준 11444번: 피보나치 수 6

> 문제: https://www.acmicpc.net/problem/11444

### 문제 풀이

분할 정복

Matrix(a, b, c, d) = 아래와 같은 2x2 행렬 피보나치 행렬

$$
\begin{pmatrix}
a & b \\
c & d
\end{pmatrix}
$$

pow(base, n) = 행렬 base의 n승을 구하는 함수

거듭제곱을 분할 정복으로 구하는 방법은 재귀 함수를 사용하는 방법도 있지만 거듭제곱을 나눌 때 지수의 합으로 나눌 수 있으므로 지수를 2진수로 나타냈을 때 1이 되는 지수의 값을 곱해주면 됨

예를 들어 13승을 구한다고 하면 13을 2진수로 나타내면 1101이 되고 여기서 1이 나타내는 값이 8, 4, 1 이므로 8승, 4승, 1승을 곱해주면 됨

이는 구해야 하는 지수를 계속 2로 나누면서 곱해야 하는 값을 계속 제곱을 하고 지수를 2로 나눌때 나머지가 1일때만 결과값에 곱하면 구할 수 있음

이렇게 구한 행열의 1행 1열을 출력하면 정답

### 풀이 설명

n이 주어졌을 때, n번째 피보나치 수를 구하는 프로그램을 작성하시오.

n번째 피보나치 수를 $F_n$으로 나타내면 다음과 같은 점화식이 성립한다.

$$
\begin{pmatrix}
F_n \\
F_{n - 1}
\end{pmatrix}
=
\begin{pmatrix}
1 & 1 \\
1 & 0
\end{pmatrix}
\begin{pmatrix}
F_{n - 1} \\
F_{n - 2}
\end{pmatrix}
$$

이 점화식을 이용해 $\begin{pmatrix}
F_{n - 1} \\
F_{n - 2}
\end{pmatrix}$을 반복 전개하면 아래와 같이 식이 바뀐다.

$$
\begin{pmatrix}
F_n \\
F_{n - 1}
\end{pmatrix}
=
\begin{pmatrix}
1 & 1 \\
1 & 0
\end{pmatrix}^{n - 1}
\begin{pmatrix}
F_{1} = 1 \\
F_{0} = 0
\end{pmatrix}
$$

이 식에서 $\begin{pmatrix}
1 & 1 \\
1 & 0
\end{pmatrix}^{n - 1}$를 $\begin{pmatrix}
a & b \\
c & d
\end{pmatrix}$로 나타내면 위의 식이 다음과 같이 바뀐다.

$$
\begin{pmatrix}
F_n \\
F_{n - 1}
\end{pmatrix}
=
\begin{pmatrix}
a & b \\
c & d
\end{pmatrix}
\begin{pmatrix}
1 \\
0
\end{pmatrix}
=
\begin{pmatrix}
a \\
c
\end{pmatrix}
$$

단, 이 식이 성립하려면 n이 1 이상이어야 하는데 문제에서 n이 0일 때 피보나치 수를 0이라고 해놓았으므로 예외처리를 해야 한다. 또한 n이 1일 때는 정사각행렬의 0승이 되고 곱셈의 성질에 따라 0승은 항등원이 되어야 하므로 이는 단위 행렬이 된다.

$\begin{pmatrix}
1 & 1 \\
1 & 0
\end{pmatrix}^{n - 1}$을 구하면 되는데 거듭제곱을 분할 정복을 이용해서 구하면 된다. 단, 여기서 n이 $10^{18}$으로 매우 크기 때문에 계산을 할때마다 $1,000,000,007$으로 나누어 주면 된다. 따라서 구한 행렬의 1행 1열의 값을 출력하면 정답이 된다.

### 소스 코드
```kotlin
const val MOD = 1000000007L

data class Matrix(val a: Long, val b: Long, val c: Long, val d: Long) {
    operator fun times(other: Matrix): Matrix {
        return Matrix(
            (a * other.a + b * other.c) % MOD,
            (a * other.b + b * other.d) % MOD,
            (c * other.a + d * other.c) % MOD,
            (c * other.b + d * other.d) % MOD
        )
    }
}

fun pow(base: Matrix, n: Long): Matrix {
    var result = Matrix(1, 0, 0, 1)  // 단위 행렬
    var mat = base
    var exp = n
    while (exp > 0) {
        if (exp % 2 == 1L) result = result * mat
        mat = mat * mat
        exp /= 2
    }
    return result
}

fun main() {
    val n = readLine()!!.toLong()
    if (n == 0L) {
        println(0)
        return
    }
    val base = Matrix(1, 1, 1, 0)
    val res = pow(base, n - 1)
    println(res.a)  // F_n
}
```