## :one: Overview

* 노드(Node)를 나무(tree) 형태로 연결한 자료구조

* 구성요소
  * ***Root Node*** : tree 의 시작 노드로 이 노드를 통해 트리를 탐색한다.
  * ***Leaf Node*** : 자식 노드를 가지지 않는 노드
  * ***Edge*** : 두 노드를 연결하는 선으로 Root 로부터의 간선 수에 따라 Level 을 결정함
  
* 트리를 잘 나타내는 예시 그림
  ![tree](https://user-images.githubusercontent.com/87659486/147827756-a193b813-f08c-4a42-8c6c-6d6cf70bcc5a.png)
  
  > 컴퓨터 공학에서의 트리는 보통 자식 노드가 최대 2개인 이진 트리*(binary tree)*를 생각한다.



* 트리 자료 저장 규칙

  * Parent 노드 기준, 더 **작은** 데이터가 **왼쪽 Child** 노드에 들어간다.

  * Parent 노드 기준, 더 **큰** 데이터가 **오른쪽 Child** 노드에 들어간다.

  * 이러한 저장 규칙을 통해, 자료 구조에서 특정 데이터를 찾는데 걸리는 시간복잡도는 `O(logN)` 이다.

  

* 완전 트리 (Complete Tree)

  * Leaf 노드가 아닌 노드들은 2개의 자식 노드를 가진다.

    * 마지막 행의 노드에 대해 자식 노드를 갖지 않거나 왼쪽에만 자식노드를 가진 경우는 제외한다.

  * 그림

    ![completetree](https://user-images.githubusercontent.com/87659486/147843754-0c8bdd42-ff4b-411a-9ef4-45eb18e7ea32.png)

* 정 트리 (Full Tree)

  * Leaf 노드가 아닌 노드들은 2개의 자식 노드를 가져야한다.

  * 모든 Leaf 노드들은 같은 level 에 있어야한다.

  * 그림

    ![fulltree](https://user-images.githubusercontent.com/87659486/147843745-2348fbf1-7789-4eae-ac05-09a545bce42e.png)

* 트리 순회

  * Pre-order

    * **Root** - left child - right child 

      ![preorder](https://user-images.githubusercontent.com/87659486/147844516-c612b086-6878-4726-8b93-b079fb65e515.png)

  * In-order

    * left child - **Root** - right child 

      ![inorder](https://user-images.githubusercontent.com/87659486/147844545-cf0a5d6a-4c22-49eb-8c6c-4f3e6d423978.png)

  * Post-order

    * left child - right child - **Root** 

      ![postorder](https://user-images.githubusercontent.com/87659486/147844570-efbc613a-2a71-446e-a962-38e89c86801d.png)



* 트리 자료구조의 단점 및 해소 방법

  * 만약, 데이터 입력이 크기 순으로 들어온다면?

    ![treebadcase](https://user-images.githubusercontent.com/87659486/147844646-9420e49b-8a6e-4aa2-afff-f3eda7510c67.png)

  * 위 경우, 데이터 탐색, 제거 등에서 트리 구조의 장점을 이용할 수 없음.

  * 위의 구조를 사용하기 균형 잡힌 좋은 트리로 만드는 과정이 필요해짐

    * 트리 회전: 트리의 노드 위치를 바꾸는 과정

      ![leftrotation](https://user-images.githubusercontent.com/87659486/147844835-98d0b131-651c-48f3-9dc1-af92c8c1c13e.png)

      > <u>좌측 회전</u> : 오른쪽 서브 트리에서 불균형이 나타나는 경우

      

      ![rightrotation](https://user-images.githubusercontent.com/87659486/147844861-6d3f9b2e-8631-4eb0-a2b4-d185819575a0.png)

      > <u>우측 회전</u> : 왼쪽 서브 트리에서 불균형이 나타나는 경우

  

  * 경우에 따라, 2가지 회전을 모두 사용하여 불균형을 해소하기도 한다.

    ![image](https://user-images.githubusercontent.com/87659486/147844936-e5494e33-8e0d-49a3-a670-fb403bbb46d5.png)

    > 오른쪽 자식의 왼쪽 서브트리에서 불균형이 나타나는 경우.

    

## :two: Implementation

* [java](./java) 
