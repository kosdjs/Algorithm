package BOJ_17425

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