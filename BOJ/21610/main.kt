package BOJ_21610

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val A = Array(N){IntArray(N)}
    for(r in 0 until N){
        st = StringTokenizer(br.readLine())
        for(c in 0 until N){
            A[r][c] = st.nextToken().toInt()
        }
    }
    val cloud = Array(N){IntArray(N)}
    for(r in N - 2..N - 1){
        for(c in 0..1){
            cloud[r][c] = 1
        }
    }
    repeat(M){
        st = StringTokenizer(br.readLine())
        val d = st.nextToken().toInt()
        val s = st.nextToken().toInt()
        moveCloud(cloud, d, s)
        for(r in 0 until N){
            for(c in 0 until N){
                if(cloud[r][c] == 1){
                    A[r][c]++
                }
            }
        }
        //물 복사
        for(r in 0 until N){
            for(c in 0 until N){
                if(cloud[r][c] == 1){
                    var y = r - 1
                    var x = c - 1
                    if(y >= 0){
                        if(x >= 0 && A[y][x] > 0){
                            A[r][c]++
                        }
                        x = c + 1
                        if(x < N && A[y][x] > 0){
                            A[r][c]++
                        }
                    }
                    y = r + 1
                    x = c - 1
                    if(y < N){
                        if(x >= 0 && A[y][x] > 0){
                            A[r][c]++
                        }
                        x = c + 1
                        if(x < N && A[y][x] > 0){
                            A[r][c]++
                        }
                    }
                }
            }
        }
        //구름 있던 칸 제외 2이상인 칸만 구름
        for(r in 0 until N){
            for(c in 0 until N){
                if(cloud[r][c] == 1){
                    cloud[r][c] = 0
                } else if(A[r][c] >= 2) {
                    A[r][c] -= 2
                    cloud[r][c] = 1
                }
            }
        }
    }
    //전체 합 계산 후 출력
    var sum = 0
    for(r in 0 until N){
        for(c in 0 until N){
            sum += A[r][c]
        }
    }
    println(sum)
}

fun moveCloud(cloud: Array<IntArray>, d: Int, s: Int){
    if(d == 1 || d == 2 || d == 8){
        moveLeft(cloud, s)
    }
    if(d == 2 || d == 3 || d == 4){
        moveUp(cloud, s)
    }
    if(d == 4 || d == 5 || d == 6){
        moveRight(cloud, s)
    }
    if(d == 6 || d == 7 || d == 8){
        moveDown(cloud, s)
    }
}

fun moveLeft(cloud: Array<IntArray>, s: Int){
    val count = s % cloud.size
    if(count == 0){
        return
    }
    for(i in 0 until cloud.size){
        cloud[i] = (cloud[i].drop(count) + cloud[i].take(count)).toIntArray()
    }
}

fun moveUp(cloud: Array<IntArray>, s: Int){
    val count = s % cloud.size
    if(count == 0){
        return
    }
    val temp = (cloud.drop(count) + cloud.take(count)).toTypedArray()
    for(i in 0 until cloud.size){
        cloud[i] = temp[i]
    }
}

fun moveRight(cloud: Array<IntArray>, s: Int){
    val count = s % cloud.size
    if(count == 0){
        return
    }
    for(i in 0 until cloud.size){
        cloud[i] = (cloud[i].takeLast(count) + cloud[i].dropLast(count)).toIntArray()
    }
}

fun moveDown(cloud: Array<IntArray>, s: Int){
    val count = s % cloud.size
    if(count == 0){
        return
    }
    val temp = (cloud.takeLast(count) + cloud.dropLast(count)).toTypedArray()
    for(i in 0 until cloud.size){
        cloud[i] = temp[i]
    }
}