## 병원 패키지 관리 시스템 설계해보기

### Diagram
- v2
    ![20220701120207](https://raw.githubusercontent.com/is3js/screenshots/main/20220701120207.png)
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
