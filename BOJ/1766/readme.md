# 1766번: 문제집

문제: [https://www.acmicpc.net/problem/1766](https://www.acmicpc.net/problem/1766)

먼저 풀어야 좋은 문제를 선행 문제라고 하면, 선행 문제들을 저장하는 리스트를 countList, 선행 문제를 풀고 나서 풀어야 하는 문제들을 problemList에 저장했다. 그 후에 문제를 우선순위 큐에 저장해야 하는데 countList를 확인해 선행 문제가 없는 문제들만 우선순위 큐에 넣는다. 이제 큐에서 하나 씩 꺼내서 문제를 풀고 problemList를 확인해서 리스트 안에 문제에 대해 countList에서 풀었던 문제를 제거하고 그 리스트가 비어 있다면 이제 그 문제를 풀어도 되기 때문에 우선순위 큐에 집어넣는다. 이렇게 우선순위 큐가 비어 있을 때 까지 꺼내면 되는 문제

참고: [https://velog.io/@gillog/Java-Priority-Queue우선-순위-큐](https://velog.io/@gillog/Java-Priority-Queue%EC%9A%B0%EC%84%A0-%EC%88%9C%EC%9C%84-%ED%81%90)