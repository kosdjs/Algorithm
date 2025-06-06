# 28250번: 이브, 프시케 그리고 푸른 MEX의 아내

문제: [https://www.acmicpc.net/problem/28250](https://www.acmicpc.net/problem/28250)

앞에서부터 2개의 수를 뽑아 음이 아닌 정수 중 뽑은 두 수를 제외한 수에서 제일 작은 수를 계속 더해서 구하는 문제

경우의 수로 값을 구할 수 있다. 뽑은 두 수가 둘다 0일 때 제일 작은 수가 1이 되기 때문에 결과에 1을 더해주게 된다.

뽑은 수가 0과 1일 때 제일 작은 수가 2가 된다.

뽑은 수가 0과 2 이상의 수일때 제일 작은 수가 1이 된다.

뽑은 수가 둘다 2 이상일 때 제일 작은 수가 0이 되기 때문에 값이 더해지지 않는다.

그렇기 때문에 처음에 0의 개수, 1의 개수, 나머지의 개수를 구한다.

0의 개수를 zero, 1의 개수를 one, 나머지의 개수를 other로 정의한다.

앞에서 부터 수를 하나 뽑고 개수를 줄인다.

뽑은 수가 0이라면 결과 값에 더해야 하는 값은 앞서 확인한 경우의 수에 따라 zero + (one * 2) + other가 된다.

뽑은 수가 1이라면 결과 값에 더해야 하는 값은 zero * 2가 된다.

뽑은 수가 2 이상이라면 결과 값에 더해야 하는 값은 zero가 된다.

여기서 결과 값을 저장할 때 int로 하면 최악의 경우에 범위를 벗어나기 때문에 long으로 만들어야 한다.