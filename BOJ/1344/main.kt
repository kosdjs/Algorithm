package BOJ_1344

import kotlin.math.pow

fun main(){
    val br = System.`in`.bufferedReader()
    val A = br.readLine().toInt() / 100.0
    val B = br.readLine().toInt() / 100.0
    val nonPrime = intArrayOf(0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18)
    var nonPrimeA = 0.0
    var nonPrimeB = 0.0
    for(i in nonPrime){
        nonPrimeA += comb(18, i) * A.pow(i) * (1 - A).pow(18 - i)
        nonPrimeB += comb(18, i) * B.pow(i) * (1 - B).pow(18 - i)
    }
    println((1 - (nonPrimeA * nonPrimeB)))
}

fun comb(n: Int, r: Int): Double{
    var result = 1.0
    var count = r
    if(r > n - r){
        count = n - r
    }
    for(i in 0 until count){
        result *= n - i
        result /= count - i
    }
    return result
}