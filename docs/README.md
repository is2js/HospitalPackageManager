## 병원 패키지 관리 시스템 설계해보기

### Diagram

### Steps
- main
    - docs > README.md 작성
    - docs > doctor.xlsx(요구사항 및 상호의존표 설계내역) 작성
- step1
    - doctor.xlsx v1 요구사항 설계
- step2
    - doctor.xlsx v2 요구사항 설계
    - 변경내역
        1. VO(값 객체)를 활용한다.
        2. doctor가 package 1개 가격이 아닌, 자본금을 상태값으로 가진다.
        3. reception은 커미션을 계산해 수수료만 가지고, 나머지는 doctor에게 준다.
        4. 전략패턴을 활용하여 할인정책을 반영한다.
