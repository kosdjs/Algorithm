package BOJ_1717

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val arr = IntArray(N + 1){it}
    for(i in 1..M){
        st = StringTokenizer(br.readLine())
        val op = st.nextToken().toInt()
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        if(op == 0){
            union(arr, a, b)
        } else {
            if(find(arr, a) == find(arr, b)){
                println("YES")
            } else {
                println("NO")
            }
        }
    }
}

fun find(arr: IntArray, idx: Int): Int{
    return if(arr[idx] == idx){
        idx
    } else {
        arr[idx] = find(arr, arr[idx])
        arr[idx]
    }
}

fun union(arr: IntArray, a: Int, b: Int){
    val aFind = find(arr, a)
    val bFind = find(arr, b)
    if(aFind < bFind){
        arr[bFind] = aFind
    } else if(aFind > bFind){
        arr[aFind] = bFind
    }
}