## :zero: Introduction

여러 정렬 알고리즘의 기초 개념과 시간/공간 복잡도에 대해 알아보고 구현해보자.

## :one: 정렬 알고리즘의 분류

1. 메모리
   * Out of place: 모든 데이터를 자료 구조의 복사본에 옮긴 후, 순서대로 배열하여 정렬한다.
   * In place: 자료구조를 그대로 두고 그 안에서 정렬 수행.
2. 안정성
   * Stable: 같은 값을 가지는 객체들의 순서가 정렬 전후로 동일한 알고리즘
   * Unstable: 같은 값을 가지는 객체들의 순서가 정렬 이후로 뒤바뀌는 알고리즘

## :two: 정렬 알고리즘

### 2.1. 선택 정렬 (Selection Sort)

* 리스트의 가장 작은 객체를 앞으로 가져와서 정렬을 수행

  ![selectionsort](https://user-images.githubusercontent.com/87659486/148340401-4130cd4f-86ef-4540-9cbb-80828a179bc0.png)

* In-place: 리스트 내부에서 순서만 바꾼다.

* Stable: 정렬 전후로 같은 값을 가지는 인스턴스들의 순서가 바뀌지 않는다.

* Pseudo code

  ```python
  for i in range(len(arr)):
    min_idx = i
  	for j in range(i+1, len(arr)):
  			if arr[j].element() < arr[min_idx].element():
          min_idx = j
    swap(arr[i], arr[min_idx])
  ```

  > Time Complexity(Best, Avg, Worst): `O(n^2)`, `O(n^2)`, `O(n^2)`

### 2.2 삽입 정렬 (Insertion Sort)

* 리스트의 요소를 하나씩 꺼내서 해당 요소 앞의 모든 요소와 비교하여 위치를 정해주는 정렬

  ![insertionsort](https://user-images.githubusercontent.com/87659486/148340753-40d67cd9-d6b8-426f-8fc9-604dcda8f31a.png)

* In-place: 리스트 내부에서 순서만 바꾼다.

* Stable: 정렬 전후로 같은 값을 가지는 인스턴스들의 순서가 바뀌지 않는다.

* Pseudo code

  ```python
  for i in range(1, len(arr)):
    current_node = arr[i]
    for j in range(i)[::-1]:
    	if  arr[j].element() <= current_node:
        break
      arr[j+1].setElement(arr[j].element())
    arr[j].setElement(current_node.element())
  ```

  > Time Complexity(Best, Avg, Worst): `O(n^2)`, `O(n^2)`, `O(n)`

* 장점

  * 이미 잘 정렬된 리스트가 주어지고 새로운 인스턴스가 추가되어 다시 정렬해야하는 경우 유리하다.
  * 해당 인스턴스의 적절한 위치만 찾아 바꿔주고 나머지 부분은 건들지 않아도 되기 때문이다. 

### 2.3. 셀 정렬 (Shell Sort)

* 삽입 정렬이 어느정도 정렬이 이루어진 리스트에 대해서는 속도가 매우 빠르다는 점에서 고안됨

* 리스트 내의 Gap 을 정하여 대략적으로 정렬을 수행한다.

  ![shellsort](https://user-images.githubusercontent.com/87659486/148350489-cbf5b12b-9a1f-48bb-bf81-b897ecbea7fa.png)

* Gap 은 홀수로 정하는 것이 좋고 매 스텝마다 반씩 줄여나가는 것이 좋다. (반으로 줄일때는 홀수로 줄인다.)

* Gap 이 1이 되면 삽입 정렬을 수행한다.

* Pseudo code

  ```python
  def make_odd(number:int):
    return number if number % 2 == 1 else number + 1
  ```

  ```python
  # gap should be odd.
  gap = make_odd(len(arr) // 2)
  
  while gap != 1:
    # gap sort
    for start_of_sublist in range(len(arr), 2*gap):
      end_of_sublist = start_of_sublist + 1
      
  		for _ in range(gap):
        if arr[start_of_sublist] > arr[end_of_sublist]:
          swap(arr[start_of_sublist], arr[end_of_sublist])
        
        start_of_sublist += 1
        end_of_sublist += 1
        
    gap = make_odd(gap // 2)
  
  insertion_sort(arr)
  ```

  > Time Complexitiy(Best, Avg, Worst): `O(n)`, `O(n^(1.5))`, `O(n^2)`

* In-place: 리스트 내부에서 순서만 바꾼다.

* Unstable: 같은 값을 가지는 요소들의 순서가 정렬 전후로 뒤바뀔수 있다.

  **예.** `10(1st), 3, 12, 2, 4, 10(2nd)`
        `2, 3, 10(2nd), 10(1st), 4, 12`

  ​      `2, 3, 4, 10(2nd), 10(1st), 12`

* 삽입 정렬과의 차이점
  * Gap 을 이용하여 대략적인 정렬을 수행한 후, 빠르게 삽입 정렬을 수행하는 정렬 알고리즘이다.

### 2.4. 버블 정렬 (Bubble Sort)

* 서로 인접한 두 요소를 비교하는 면에서 선택 정렬과 유사한 정렬 알고리즘임.

* 매 스텝마다 큰 값이 오른쪽으로 밀려나오는 모습이 깊은 물에서 거품이 올라오는 모습과 유사

  ![image](https://user-images.githubusercontent.com/87659486/148401731-4d012684-13d8-4509-a564-8b5bc52e57d2.png)

  > 참고: [권희정님의 버블 정렬 블로그글](https://gmlwjd9405.github.io/2018/05/06/algorithm-bubble-sort.html) 

* Pseudo code

  ```python
  for target_idx in range(len(arr))[::-1]:
    for j in range(target_idx-1):
      if arr[j].element() > arr[j+1].element():
        swap(arr[j].element(), arr[j+1].element())
  ```

  > Time complexity(Best, Avg, Worst): `O(n^2)`, `O(n^2)`, `O(n^2)`

* In-place: 리스트 내부에서 순서만 바꾼다.

* Stable: 정렬 전후로 같은 값을 가지는 인스턴스들의 순서가 바뀌지 않는다.

### 2.5. 합병 정렬 (Merge Sort)

* 요소가 하나만 남을 때까지 리스트를 나눠준 후, 나눴던 리스트를 대소 관계에 맞게 다시 합치는 정렬 알고리즘
	![mergesort](https://user-images.githubusercontent.com/87659486/148373814-64b84bbc-71d6-49d2-8e7a-236a936adb41.png)

	![image](https://user-images.githubusercontent.com/87659486/148401305-17e21530-72d3-4e3b-a0ef-52b9a68321fa.png)

> 참고: [권희정님 합병 정렬 블로그글](https://gmlwjd9405.github.io/2018/05/10/algorithm-quick-sort.html) 

* Pseudo code

  ```python
  def merge_sort(arr):
    if len(arr) < 2:
      return arr
    
    mid = len(arr) // 2
    low_arr = merge_sort(arr[:mid])
    high_arr = merge_sort(arr[mid:])
    
    merged_arr = []
    l = h = 0
    while l < len(low_arr) and h < len(high_arr):
      if low_arr[l] < high_arr[h]:
        merged_arr.append(low_arr[l])
        l += 1
      else:
        merged_arr.append(high_arr[h])
        h += 1
    
    merged_arr += low_arr[l:]
    merged_arr += high_arr[h:]
    return merged_arr
  ```

  > 참고: [DalySeo 님의 합병 정렬 블로그글](https://www.daleseo.com/sort-merge/) 
  >
  > `arr[:mid]` 및 `arr[mid:]` 부분에서 배열 복제 발생으로 메모리 부족 현상이 발생할 수 있다.

  

  ```python
  def merge_sort(arr):
    def merge(low, mid, high):
      temp = []
      l, h = low, mid
      
      while l < mid and h < high:
        if arr[l] < arr[h]:
          temp.append(arr[l])
          l += 1
        else:
          temp.append(arr[h])
          h += 1
  
        while l < mid:
          temp.append(arr[l])
          l += 1
        while h < high:
          temp.append(arr[h])
          h += 1
  
        for i in range(low, high):
          arr[i] = temp[i - low]
    
    def sort(low, high):
      if high - low < 2:
        return
      mid = (low + high) // 2
      sort(low, mid)
      sort(mid, high)
      merge(low, mid, high)
    
    return sort(0, len(arr))
  ```

  > 참고: [DalySeo 님의 합병 정렬 블로그글](https://www.daleseo.com/sort-merge/) 
  >
  > Time complexity(Best, Avg, Worst): `O(NlogN)`, `O(NlogN)`, `O(NlogN)`

* Out-of-place: 리스트를 나누기 위해 데이터를 다른 공간에 저장해야한다.

* Stable: 정렬 전후로 같은 값을 가지는 인스턴스들의 순서가 바뀌지 않는다.

### 2.6. 퀵 정렬 (Quick Sort)

* Pivot Point 를 이용하여 divide-and-conquer 방식으로 정렬하는 알고리즘

  ![image](https://user-images.githubusercontent.com/87659486/148401032-e1c5bb1e-dcf6-4be1-881b-16cc07a78ce1.png)

  > 참고: [권희정님 퀵 정렬 블로그글](https://gmlwjd9405.github.io/2018/05/10/algorithm-quick-sort.html) 

* Pivot Point

  * Pivot Point 기준, 작은 원소는 pivot 왼쪽으로 큰 원소는 pivot 오른쪽으로 옮긴다.

  ![quichsort](https://user-images.githubusercontent.com/87659486/148385195-00ca2d3e-c920-478a-bb26-8bfc09c599c2.png)

* Pseudo code

  ```python
  def quick_sort(arr, low, high):
    if high - low < 1:
      return
    
    # init pivot index is mid-index.
    pivot = (low + high) // 2
    swap(arr, pivot, high)
    pivot = high
    
    _from, _to = low, low
    while _from <= pivot - 1 and _to <= pivot - 1:
      if arr[_to] > arr[pivot] or _from > _to:
        _to += 1
      else:
        swap(arr, _from, _to)
        _from += 1
      
      swap(arr, _from, pivot)
      pivot = _from
      
      quick_sort(arr, low, pivot-1)
      quick_sort(arr, pivot+1, high)
  ```

  > Time complexity(Best, Avg, Worst): `O(NlogN)`, `O(NlogN)`, `O(N^2)`

### 2.7. 기수 정렬 (Radix Sort)

### 2.8. 힙 정렬 (Heap Sort)

