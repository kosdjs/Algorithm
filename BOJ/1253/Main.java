package BOJ_1253;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int result = 0;
        for(int i=0; i<N; i++){
            int left = 0;
            int right = N-1;
            while(left < right){
                if(left == i){
                    left++;
                } else if(right == i){
                    right--;
                } else {
                    int current = arr[left] + arr[right];
                    if(current == arr[i]){
                        result++;
                        break;
                    } else if(current < arr[i]){
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
