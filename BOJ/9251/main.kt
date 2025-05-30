package BOJ_9251

import java.io.*;

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val s1 = br.readLine()
    val s2 = br.readLine()
    val arr = Array(1001){IntArray(1001){0}}
    for (i in 1..s1.length){
        for (j in 1..s2.length){
            if(s1[i-1] == s2[j-1]){
                arr[i][j] = arr[i-1][j-1] + 1
            } else {
                arr[i][j] = if(arr[i-1][j] > arr[i][j-1]) arr[i-1][j] else arr[i][j-1]
            }
        }
    }
    println(arr[s1.length][s2.length])
}