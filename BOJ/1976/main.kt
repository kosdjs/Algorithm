package BOJ_1976

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val M = br.readLine().toInt()
    val graph = Array(N + 1){IntArray(N + 1){0} }
    val root = IntArray(N + 1){it}
    for(i in 1..N){
        val st = StringTokenizer(br.readLine())
        for(j in 1..N){
            graph[i][j] = st.nextToken().toInt()
            if(graph[i][j] == 1){
                union(root, i, j)
            }
        }
    }
    var condition = true
    val st = StringTokenizer(br.readLine())
    var before = 0
    for(i in 1..M){
        val city = st.nextToken().toInt()
        if(before == 0){
            before = root[city]
        } else if(before != root[city]){
            condition = false
            break
        }
    }
    println(if (condition) "YES" else "NO")
}

fun union(root: IntArray, x: Int, y: Int){
    val xFind = find(root, x)
    val yFind = find(root, y)
    if(xFind < yFind){
        root[yFind] = xFind
    } else if(xFind > yFind){
        root[xFind] = yFind
    }
}

fun find(root: IntArray, x: Int): Int{
    if(root[x] == x){
        return x
    } else {
        root[x] = find(root, root[x])
        return root[x]
    }
}