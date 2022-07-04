package doctor;

import doctor.domain.develop.paper.TxPackagePaper;
import doctor.domain.develop.members.Director;
import doctor.domain.develop.paper.TxRoomPaper;

public class DevelopApplication {
    public static void main(String[] args) {

        //director를 생성한다.
        final Director director = new Director();

        // 외부(client)에서 주는 프로젝트Paper를 [여러개 받기기능 add]으로 받아온다.
        // -> Paper는 인터페이스이며, 구상체는 TxPackagePaper, TxRoomPaper 2가지 종류가 있다. 구상체로 받아와 저장한다.
        // --> 받아서 저장할 때, map에 이름과 함께 저장한다.
        // --> MemoryRepository는 static map을 사용하여, Id를 사용하고, 초기데이터도 미리 만들어 둘 순 있지만,
        //     Director는 db의 저장이 아닌 비지니스로직에 사용하는 데이터를, 현재 인스턴스만 알고서 사용하기 때문에, static이 아닌 것으로 저장한다.
        //    객체 저장시, CRUD를 하기 위해, map에 key값으로 String을 같이 저장한다.
        director.addProjectPaper("구완와사", new TxPackagePaper());
        director.addProjectPaper("백구한의원", new TxRoomPaper());

        // director는 내부에 저장한 Paper들 중 1개를 name로 찾아, programmer들에게 만들라고 시킨다.
        // -> 프로그래머들은 director의 하위도메인이라, 내부에서 생성하여 일을 시킨다.
        director.runProjectPaper("구완와사");
    }
}
