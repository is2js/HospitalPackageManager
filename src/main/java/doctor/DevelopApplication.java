package doctor;

import doctor.domain.develop.paper.TxPackagePaper;
import doctor.domain.develop.members.Director;
import doctor.domain.develop.paper.TxRoomPaper;

public class DevelopApplication {
    public static void main(String[] args) {
        final Director director = new Director();

        director.addProjectPaper("구완와사", new TxPackagePaper("AWS", "java", "js"));
        director.addProjectPaper("백구한의원", new TxRoomPaper("VueJS", "js"));

        director.runProjectPaper("구완와사");
    }
}
