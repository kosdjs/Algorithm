package BOJ_16562

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val k = nextInt()
    val root = IntArray(N + 1){it}
    val A = IntArray(N + 1)
    for(i in 1..N){
        A[i] = nextInt()
    }
    fun find(i: Int): Int{
        if(root[i] == i){
            return i
        } else {
            root[i] = find(root[i])
            return root[i]
        }
    }
    fun union(a: Int, b: Int){
        val aFind = find(a)
        val bFind = find(b)
        if(aFind > bFind){
            root[aFind] = bFind
        } else {
            root[bFind] = aFind
        }
    }
    repeat(M){
        val v = nextInt()
        val w = nextInt()
        union(v, w)
    }
    val minMoney = HashMap<Int, Int>()
    for(i in 1..N){
        val curRoot = find(i)
        if(minMoney.containsKey(curRoot)){
            minMoney[curRoot] = minOf(minMoney[curRoot]!!, A[i])
        } else {
            minMoney[curRoot] = A[i]
        }
    }
    var moneySum = 0
    for(money in minMoney.values){
        moneySum += money
    }
    println(if(k >= moneySum) moneySum else "Oh no")
}