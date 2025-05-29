>문제: https://www.acmicpc.net/problem/1339

알파벳에 숫자를 대입해 최댓값을 얻어야 하기 때문에 높은 자리수에 있을 수록 값을 크게 지정해야 함

따라서 대문자 알파벳과 그 알파벳이 들어가는 자릿수를 대응해서 저장해야 함

문자열이 GCF라면 G에는 100을 저장하는 식임

이를 HashMap을 사용해 저장했고 알파벳들을 자릿수로 내림차순 정렬 후 9부터 순서대로 대입해 값을 구하면 되는 문제

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0; i<N; i++){
            String s = br.readLine();
            int digit = 1;
            for (int j=s.length() - 1; j>=0; j--){
                char c = s.charAt(j);
                if(map.containsKey(c)){
                    int temp = map.get(c);
                    temp += digit;
                    map.replace(c, temp);
                } else {
                    map.put(c, digit);
                }
                digit *= 10;
            }
            //받은 문자열 자릿수 가져와서 저장하기
        }
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return map.get(o2) - map.get(o1);
            }
        });
        //comparator 우선순위 자릿수 내림차순
        int answer = 0;
        int number = 9;
        for(int i=0; i<list.size(); i++){
            answer += map.get(list.get(i)) * number;
            number--;
        }
        System.out.println(answer);
    }
}
```