package BOJ_9019

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    fun D(x: Int): Int{
        return (x * 2) % 10000
    }
    fun S(x: Int): Int{
        return if(x == 0) 9999 else x - 1
    }
    fun L(x: Int): Int{
        return (x % 1000) * 10 + (x / 1000)
    }
    fun R(x: Int): Int{
        return x / 10 + (x % 10) * 1000
    }
    val sb = StringBuilder()
    repeat(nextInt()){
        val A = nextInt()
        val B = nextInt()
        val q = ArrayDeque<Int>()
        val visit = BooleanArray(10000)
        val prev = IntArray(10000)
        val ops = CharArray(10000)
        visit[A] = true
        q.add(A)
        while (q.isNotEmpty()){
            val cur = q.removeFirst()
            if(cur == B){
                break
            }
            val nextList = listOf(D(cur), S(cur), L(cur), R(cur))
            for(i in nextList.indices){
                val next = nextList[i]
                if(visit[next]) continue
                visit[next] = true
                prev[next] = cur
                ops[next] = when(i){
                    0 -> 'D'
                    1 -> 'S'
                    2 -> 'L'
                    3 -> 'R'
                    else -> ' '
                }
                q.add(next)
            }
        }
        val path = mutableListOf<Char>()
        var num = B
        while(num != A){
            path.add(ops[num])
            num = prev[num]
        }
        path.reverse()
        for(c in path){
            sb.append(c)
        }
        sb.append("\n")
    }
    print(sb)
}