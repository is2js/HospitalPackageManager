package doctor_v2.fixture;

import doctor_v2.Specialty;
import doctor_v2.domain.Treatment;
import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Money;
import doctor_v2.vo.Sequence;
import doctor_v2.vo.Title;
import java.time.Duration;
import java.time.LocalDate;

public class Fixture {
    public static final Specialty SPECIALTY_구안와사 = new Specialty(
        Title.of("구안와사"),
        Duration.ofDays(60),
        Money.of(5000.0),
        LocalDate.of(2022, 06, 22)
    );

    public static final Specialty SPECIALTY_매선 = new Specialty(
        Title.of("매선"),
        Duration.ofDays(60),
        Money.of(5000.0),
        LocalDate.of(2022, 06, 22)
    );

    public static final Treatment TREATMENT_첫번째 = new Treatment(
        Sequence.of(1L),
        Title.of(String.format("%dth 제목", 1L)),
        Description.of(String.format("%d번째 패키지", 1L)),
        Count.of(10L));
    public static final Treatment TREATMENT_두번째 = new Treatment(
        Sequence.of(2L),
        Title.of(String.format("%dth 제목", 2L)),
        Description.of(String.format("%d번째 패키지", 2L)),
        Count.of(10L));
}
