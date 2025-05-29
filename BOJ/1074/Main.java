package BOJ_1074;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.println(Z(N, r, c));
    }

    static int Z(int N, int r, int c){
        if(N == 0){ // 마지막 한칸
            return 0;
        } else {
            int mid = (int) Math.pow(2, N-1); // 현재 칸이 배열의 어느 위치에 있는지 구분하기 위한 배열의 중간값
            int quarter = mid * mid; // 4등분한 배열의 크기
            if(r < mid){ // r이 mid보다 작다면 배열의 위쪽
                if(c < mid){ // c가 mid보다 작다면 배열의 왼쪽
                    return Z(N-1, r, c); //왼쪽 위
                } else {
                    return quarter + Z(N-1, r, c - mid); // 오른쪽 위
                }
            } else {
                if(c < mid){
                    return quarter * 2 + Z(N-1, r - mid, c); //왼쪽 아래
                } else {
                    return quarter * 3 + Z(N-1, r - mid, c - mid); // 오른쪽 아래
                }
            }
        }
    }
}
