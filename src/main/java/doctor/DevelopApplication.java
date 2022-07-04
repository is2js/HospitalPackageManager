package doctor;

public class DevelopApplication {
    public static void main(String[] args) {

        //director를 생성한다.
        final Director director = new Director();

        // 외부(client)에서 주는 프로젝트Paper를 [여러개 받기기능 add]으로 받아온다.
        // -> Paper는 인터페이스이며, 구상체는 TxPackagePaper, TxRoomPaper 2가지 종류가 있다.
        director.addProjectPaper(new TxPackagePapaer("구완와사"));
        director.addProjectPapaer(new TxRoomPaper("백구한의원"));

        // director는 내부에 저장한 Paper들 중 1개를 name로 찾아, programmer들에게 만들라고 시킨다.
        // -> 프로그래머들은 director의 하위도메인이라, 내부에서 생성하여 일을 시킨다.
        director.runProjectPaper("구완와사");
    }
}
