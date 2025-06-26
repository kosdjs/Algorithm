package BOJ_20040

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val root = IntArray(n){it}
    var answer = 0
    for(i in 1..m){
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        if(union(root, a, b)){
            answer = i
            break
        }
    }
    println(answer)
}

fun find(root: IntArray, idx: Int): Int{
    if(root[idx] == idx){
        return idx
    } else {
        root[idx] = find(root, root[idx])
        return root[idx]
    }
}

fun union(root: IntArray, a: Int, b: Int): Boolean{
    val aFind = find(root, a)
    val bFind = find(root, b)
    if(aFind == bFind){
        return true
    } else {
        if(aFind > bFind){
            root[aFind] = bFind
        } else {
            root[bFind] = aFind
        }
        return false
    }
}