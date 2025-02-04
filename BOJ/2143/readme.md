# 2143번: 두 배열의 합

문제: [https://www.acmicpc.net/problem/2143](https://www.acmicpc.net/problem/2143)

각 배열의 부분합을 구해놓고 조건에 맞는 쌍의 개수를 찾는 문제

배열의 크기가 n인 부 배열의 총 개수는 n(n+1)/2

부 배열의 합을 배열에 저장 후 정렬하고 투포인터 알고리즘을 써서 쌍을 찾는다.

left를 0, right를 bSum.length - 1로 해서 aSum[left] + bSum[right]의 값을 T 값과 비교해 작으면 left 증가, 크면 right 감소, 같으면 조건에 맞으니 중복값을 확인해 개수를 센다.

문제 조건에서 보면 모든 값이 int에 들어오지만 결과 값인 쌍의 개수는 int의 범위를 넘어설 수 있다. int와 int를 곱하면 값이 int로 나와서 오버플로우가 나기 때문에 조심해야함.