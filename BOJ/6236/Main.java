package BOJ_6236;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int left = 0;
        int right = 0;
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
            right += arr[i];
            if(arr[i] > left){
                left = arr[i];
            }
        }
        while(left < right){
            int mid = (left + right) / 2;
            int cnt = 0;
            int bal = 0;
            for(int i=0; i<N; i++){
                if(bal < arr[i]){
                    cnt++;
                    bal = mid - arr[i];
                } else {
                    bal -= arr[i];
                }
            }
            if(cnt > M){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        bw.write(right + "\n");
        bw.flush();
        bw.close();
    }
}
