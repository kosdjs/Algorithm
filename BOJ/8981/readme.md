# 백준 8981번: 입력숫자

> 문제: https://www.acmicpc.net/problem/8981

### 문제 풀이

구현

arr[i] = 원래 입력 두 번째 줄에 i + 1번째 숫자

idx = arr 배열의 인덱스

입력으로 N을 받아 arr배열을 N개의 숫자를 받을 수 있도록 정의함

idx가 0부터 arr[idx]가 0이라면 그 칸이 빈 칸이므로 입력으로 받은 숫자를 저장함

그 이후에 idx에 num을 더하고 N으로 나눈 나머지를 저장함

다음 숫자를 저장하기 위해 빈 칸을 찾도록 arr[idx]가 0이 될때까지 idx에 1을 더하고 N으로 나눈 나머지를 대입함

이 과정에서 N과 N개의 숫자를 입력받기 전에 입력이 끝나면 비정상적인 출력이므로 -1을 출력하고 함수를 반환함

이 과정을 반복해 정상적으로 N과 N개의 숫자를 입력받고 arr 배열에 순서대로 저장했다면 N과 arr배열에 저장된 N개의 숫자를 출력하면 정답

### 풀이 설명

아래 mystery.c는 입력파일 X를 읽어서 그 안에 기록된 N개의 정수를 배열 NUM에 저장한 뒤에 이 N개의 수를 어떤 순서에 따라서 화면에 출력하는 프로그램이다. mystery.c가 X를 입력으로 받아 화면에 출력한 결과를 Y라고 하자. 
```c
#include <stdio.h>
int NUM[101] ;
FILE *fin ;
int main(){
    int i, token,N ;
    int count=0, from= 0, value ;
    fin = fopen("X","r");
    fscanf(fin,"%d",&N);
    for(i=0; i<N; i++){
        fscanf(fin,"%d",&token);
        NUM[i]= token;
    } /* end of for */
    printf("%d\n", N ) ;
    value = NUM[ from ] ;
    while( count < N ) {
        while( value == 0 ) { 
            from = (from+1)%N; 
            value = NUM[ from ] ; 
        } /* end of inner while */ 
        printf("%d ", value ) ;
        count++ ;
        NUM[ from ] = 0 ; 
        from = (value +from )% N ; 
        value = NUM[ from ] ; 
    } /* end of outer while */
    return(0);
} /* end of main() */
```
여러분은 mystery.c에서 생성된 Y를 파일로 받아서 그것의 입력에 해당하는 X를 찾아내는 프로그램을 작성해야 한다.

mystery.c는 입력으로 첫 줄에 숫자 N과 두 번째 줄에 N개의 숫자가 주어지면 N을 첫 줄에 출력하고 두 번째 줄에 주어지는 N개의 숫자를 순서에 따라 출력을 하는 코드다.

이때 출력하는 순서는 NUM 배열에 N개의 숫자를 저장하고 from을 0부터 N - 1까지의 인덱스, value를 NUM[from]값으로 사용해 value가 0이 아닐때마다 출력하고 배열에서 값을 제거하기 위해 NUM[from]에 0을 대입, from에 다음 값을 찾기 위해 (from + value) % N을 대입한 이후에 NUM[from]에 값이 존재할때까지 from에 (from + 1) % N을 대입해 다음 값을 찾는 순서로 값을 출력한다.

이렇게 mystery.c에서 출력된 값을 입력으로 사용해 다시 원래 입력값을 찾아야 하므로 입력으로 첫 줄에 N을 받으면 N개의 숫자를 저장하기 위해 arr배열을 만들고 첫 숫자는 항상 0번째 인덱스에서 시작하므로 idx를 0으로 초기화한다.

그 이후에 첫 줄에 나오는 N은 그대로 출력하므로 출력을 먼저 해주고, 두 번째 줄에 나오는 N개의 숫자는 idx를 조절하면서 arr에 저장을 해야 한다.

원래 코드의 반대로 하기 위해 arr[idx]가 0이라면 숫자가 존재하지 않는 것이므로 입력에 들어오는 숫자를 저장해야 한다. 그 이후에 입력받은 숫자가 num이라고 하면 idx에 (idx + num) % N을 대입해준다.

그 다음 숫자에 대해서는 arr[idx]가 0이어야 현재 숫자를 저장할 수 있는 것이므로 arr[idx]가 0이 될때까지 idx에 (idx + 1) % N을 대입해줘서 빈 칸을 찾아준다. 이 과정을 반복해 모든 숫자를 arr 배열에 저장하면 원래 순서대로 N개의 숫자가 arr 배열에 저장된 것이므로 순서대로 숫자를 출력해주면 정답이 된다.

단 문제에서 원래대로 돌릴 수 없는 입력이 들어오면 -1을 출력하라고 했으므로 N과 N개의 숫자를 마지막에 출력을 하고, N과 N개의 숫자가 들어오기 전에 입력이 끝난다면 -1을 출력하고 함수를 바로 반환하면 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int?{
        val token = nextToken()
        return if(token == StreamTokenizer.TT_EOF) null else nval.toInt()
    }
    val N = nextInt()
    if(N == null){
        println(-1)
        return
    } else {
        val arr = IntArray(N)
        var idx = 0
        repeat(N){
            val num = nextInt()
            if(num == null){
                println(-1)
                return@run
            } else {
                while(arr[idx] != 0){
                    idx = (idx + 1) % N
                }
                arr[idx] = num
                idx = (idx + num) % N
            }
        }
        println(N)
        for(i in 0 until N){
            print("${arr[i]} ")
        }
    }
}
```