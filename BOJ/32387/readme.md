# 32387번 : 충전하기

문제: [https://www.acmicpc.net/problem/32387](https://www.acmicpc.net/problem/32387)

포트 번호가 출력이기 때문에 1번 행동에서 포트가 꽂혀있을 때 찾아야 하는 수는 번호가 현재 필요한 포트 번호보다 큰 포트 중 가장 번호가 작은 포트이다. 처음엔 단순하게 순서대로 찾으면 되는 게 아닐까 했지만 시간 초과.

TreeSet을 사용하면 어떤 포트를 사용할 수 있을지 편하게 찾을 수 있다. 구현도 레드블랙트리로 되어있어서 탐색할 때 빠른 속도로 찾을 수 있다고 한다. 이 문제에서 사용해야 하는 메소드는 ceiling으로 앞서 설명한 조건처럼 현재 들어가 있는 요소 중 크거나 같은 요소 중 가장 작은 요소를 반환해준다.

이 메소드를 사용하기 위해 현재 사용 가능한 포트들을 TreeSet에 넣어 놓고 포트를 사용할 때 해당 포트 번호를 제거하면서 조건을 확인하면 현재 어떤 포트를 사용할 수 있는지 확인할 수 있다.

또한 그 포트를 몇 번째 명령어일 때 사용하는 지에 대한 정보를 저장하기 위해 포트 길이 만큼의 배열을 만들어 반복문 횟수를 저장했다.

이 문제는 명령어마다 값을 한 줄 씩  출력해야 하기 때문에 System.out.println을 사용해서 출력하면 출력하는데 시간이 오래 걸린다고 한다. 따라서 BufferedWriter를 사용하면 더 빠르게 출력할 수 있다.

```java
BufferedWriter bw = New BufferedWriter(New OutputStreamReader(System.Out));

bw.write(-1 + "\n");

// 버퍼를 사용하기 때문에 버퍼가 가득차면 자동으로 출력하지만 그 전에는 출력을 해 주어야 한다
bw.flush();
// 사용이 끝나면 닫아주어야 한다.
bw.close();
```

참고: [https://innovation123.tistory.com/121](https://innovation123.tistory.com/121), [https://www.acmicpc.net/blog/view/57](https://www.acmicpc.net/blog/view/57)