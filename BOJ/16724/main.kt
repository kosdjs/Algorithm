package BOJ_16724

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val map = Array(N){CharArray(M)}
    val visit = Array(N){BooleanArray(M){false} }
    val isRoot = Array(N){BooleanArray(M){false} }
    val root = Array(N){Array(M){IntArray(2) } }
    var answer = 0
    for(i in 0 until N){
        val s = br.readLine()
        for(j in 0 until M){
            map[i][j] = s[j]
            root[i][j][0] = i
            root[i][j][1] = j
        }
    }
    for(i in 0 until N){
        for(j in 0 until M){
            if(visit[i][j]){
                continue
            }
            var prevX = i
            var prevY = j
            var x = if(map[i][j] == 'D') i + 1 else if(map[i][j] == 'U') i - 1 else i
            var y = if(map[i][j] == 'L') j - 1 else if(map[i][j] == 'R') j + 1 else j
            while(true){
                union(root, prevX, prevY, x, y)
                if(visit[x][y]){
                    break
                }
                visit[x][y] = true
                prevX = x
                prevY = y
                x = if(map[prevX][prevY] == 'D') prevX + 1 else if(map[prevX][prevY] == 'U') prevX - 1 else prevX
                y = if(map[prevX][prevY] == 'L') prevY - 1 else if(map[prevX][prevY] == 'R') prevY + 1 else prevY
            }
        }
    }
    for(i in 0 until N){
        for(j in 0 until M){
            val temp = find(root, i , j)
            val x = temp[0]
            val y = temp[1]
            if(!isRoot[x][y]){
                isRoot[x][y] = true
                answer++
            }
        }
    }
    println(answer)
}

fun find(root: Array<Array<IntArray>>, x: Int, y: Int): IntArray{
    if(root[x][y][0] == x && root[x][y][1] == y){
        return intArrayOf(root[x][y][0], root[x][y][1])
    } else {
        val temp = find(root, root[x][y][0], root[x][y][1])
        root[x][y][0] = temp[0]
        root[x][y][1] = temp[1]
        return intArrayOf(root[x][y][0], root[x][y][1])
    }
}

fun union(root: Array<Array<IntArray>>, ax: Int, ay: Int, bx: Int, by: Int){
    val aFind = find(root, ax, ay)
    val bFind = find(root, bx, by)
    val aFindX = aFind[0]
    val aFindY = aFind[1]
    val bFindX = bFind[0]
    val bFindY = bFind[1]
    if(aFindX < bFindX){
        root[bFindX][bFindY][0] = aFindX
        root[bFindX][bFindY][1] = aFindY
    } else if(aFindX > bFindX){
        root[aFindX][aFindY][0] = bFindX
        root[aFindX][aFindY][1] = bFindY
    } else {
        if(aFindY < bFindY){
            root[bFindX][bFindY][0] = aFindX
            root[bFindX][bFindY][1] = aFindY
        } else if(aFindY > bFindY){
            root[aFindX][aFindY][0] = bFindX
            root[aFindX][aFindY][1] = bFindY
        }
    }
}