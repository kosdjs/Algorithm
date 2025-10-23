package BOJ_2447

lateinit var arr: Array<CharArray>

fun main(){
    val N = System.`in`.bufferedReader().readLine().toInt()
    arr = Array(N){ CharArray(N){' '} }
    star(N, 0, 0)
    val bw = System.out.bufferedWriter()
    for(i in 0 until N){
        bw.write(arr[i])
        bw.newLine()
    }
    bw.flush()
    bw.close()
}

fun star(level: Int, y: Int, x: Int){
    if(level == 1){
        arr[y][x] = '*'
    } else {
        for (ny in y until y + level step level / 3){
            for(nx in x until x + level step level / 3){
                if(nx == x + level / 3 && ny == y + level / 3){
                    continue
                } else {
                    star(level / 3, ny, nx)
                }
            }
        }
    }
}