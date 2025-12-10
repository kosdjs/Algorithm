package BOJ_11444

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