## :zero: Introduction

* 연결 리스트의 단점
  * 자료구조에서 특정 데이터를 찾으려면 모든 요소를 순서대로 살펴봐야함.
  * 데이터를 빠르게 제거하는 것이 불가능함
  * 데이터를 원하는 위치에 추가하는 것도 시간이 오래걸림

## :one: Overview

* 효율적으로 데이터를 관리하는 자료구조

* 해시 (Hash)

  * 구성요소<u>1</u> : ***Key*** 
  * 구성요소<u>2</u> : 해당 ***Key*** 에 해당하는 ***Value***

* 해시 함수 (Hash Function) 작성시 유의사항

  * `java` 에서는 `hashCode` 메서드를 overriding 하는 행위.
  * 데이터의 속성을 고려해야한다.
    * CSSC 아이디가 있는 경우 CSSC 부분을 제거해야한다.
  * 연산이 빨라야한다.
  * 같은 두 요소에 대해 같은 값을 반환해야한다.
  * 같은 실행 환경에서 같은 객체에 대해 같은 값을 반환해야한다.
  * 코드 재실행시, 동일 객체여도 다른 값이 나올 수 있다.
  * 코드에서 최대한 충돌이 일어나지 않아야한다.

* 해시 충돌

  * 서로 다른 2개의 객체에 대해 해시 함수가 같은 값을 반환하는 것

  * **예.** 전화번호 해싱

    * 해시 함수: 전화번호의 각 부분의 합으로 정의

      * "332-123-0011" : 332 + 123 + 0011 = 466

    * "619-550-1217" 과 "619-555-1212" 는 해시함수가 같은 값을 반환

    * 즉, 2개의 서로 다른 객체가 같은 ***Key*** 값을 가지게 되어 충돌 발생

      ![collision](https://user-images.githubusercontent.com/87659486/147823757-81c7156a-097c-4446-b348-84642d5eb8ce.png)

* 해시 충돌 해소

  * **예.** 문자열 해시 함수

    * 충돌이 나는 함수

      ```java
      public int hashCode(String s) {
          int hash = 0;
          
          // A character can be replaced as a number, a.k.a. unicode.
          for (int i = 0; i < s.length; i++) {
              hash = hash + s.charAt(i);
          }
          
          return hash
      }
      ```

      > :warning: "hits" 와 "this" 가 같은 해시값을 가지게 되어 충돌이 발생한다.

    * 해시 충돌 해소가 이루어진 함수

      ```java
      public int hashCode(String s) {
          int g = 31;
          int hash = 0;
          
          // A character can be replaced as a number, a.k.a. unicode.
          for (int i = 0; i < s.length; i++) {
              hash = g * hash + s.charAt(i);
          }
          
          return hash
      }
      ```

      

## :two: Implementation

* [java](./java)
