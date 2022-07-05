## 병원 패키지 관리 시스템 설계해보기

### Diagram
- doctor 패키지 관련
    ![image-20220703213949395](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220703213949395.png)
- delevelop 부서 관련
    ![image-20220705184019320](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220705184019320.png)
    ![image-20220705184728692](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220705184728692.png)
### Steps
- main
    - docs > README.md(updating)
    - docs > doctor.xlsx(요구사항 및 상호의존표 설계내역) 작성
- step1
    - doctor.xlsx v1 요구사항 설계
- step2
    - doctor.xlsx v2 요구사항 설계 및 중요 책임들의 테스트 구현
    - 변경내역
        1. VO(값 객체)를 활용한다.
        2. Coordinator는 판매중개 책임만 위임받고
        3. Reception과 Doctor가 판매금액을 나누서 가진다
- step3
    - trigger + action을 모두 가진 인터페이스 + 마커인터페이스로 `조합된 최종구상체`로서 할인정책을 반영한다.
        ![image-20220702164431579](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220702164431579.png)
    - specialty가 2개의 인터페이스를 모두 알아야하는 문제점을 가진다.
        ![image-20220702163401201](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220702163401201.png) 
- step4
    - 2개 인터페이스 조합을 깨고, 그 지식을 분산시킨다.
        ![image-20220702164553323](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220702164553323.png)  
    - specialty 내부에 있던 `정책 최종구상체들 trigger 묻기 -> action 하기`의 과정을 **템플릿 메소드 패턴**로 보고
        ![image-20220702171035277](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220702171035277.png)
        1. trigger의 묻는 것은 `정책조건 전략객체들을 주입`받아서 `1개의 전략메서드로서 그들이 알아서 boolean반환`하게 함.
        2. action하는 것은 `훅메서드로서 상속 자식들서 개별구현`
           ![image-20220702235625410](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220702235625410.png)
    - specialty는 `정책적용 대상객체(fee)` + `정책조건 판단 정보(treatement, count)`를 모두 인자로 받는 
        - **trigger를 머금은 action 추상클래스**인 `policy`를 안다. 
    - policy는 **템플릿 메소드 내부에서 공통로직으로 사용될 trigger메서드**를 위한 `정책조건 전략객체`를 생성자 주입받아서 안다.
- step5
    - 전략condition은 그대로 두고, 템플렛policy -> 일반class + 전략policy
      ![image-20220703010442315](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220703010442315.png)
      ![image-20220703121338772](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220703121338772.png)

- step6
    - 현 정책 구성: Condition전략객체 + Policy전략객체를 사용하는 DiscountPolicy 일반클래스 
    - Policy전략객체별로 Policy전략Factory를 추가한 뒤, factory를 policy전략객체 대신 주입한다.
      ![image-20220703213448253](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220703213448253.png)
      ![image-20220703225131663](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220703225131663.png)

- develop
1. 아래와 같이 프로젝트를 착수하도록 설계한다.
    ![image-20220705184728692](https://raw.githubusercontent.com/is3js/screenshots/main/image-20220705184728692.png)
