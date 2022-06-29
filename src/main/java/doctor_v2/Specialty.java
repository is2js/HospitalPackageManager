package doctor_v2;

import doctor_v2.vo.Money;
import doctor_v2.vo.Title;
import java.time.Duration;
import java.time.LocalDate;

public class Specialty {

    private final Title title;
    private final Duration duration;
    private final Money fee;
    private final LocalDate createDate;

    public Specialty(final Title title,
                     final Duration duration,
                     final Money fee,
                     final LocalDate createDate) {

        this.title = title;
        this.duration = duration;
        this.fee = fee;
        this.createDate = createDate;
    }
}
