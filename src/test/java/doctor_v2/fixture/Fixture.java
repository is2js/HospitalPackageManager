package doctor_v2.fixture;

import doctor_v2.discountpolicy.amount.SequenceAmountDiscount;
import doctor_v2.domain.Doctor;
import doctor_v2.domain.Patient;
import doctor_v2.domain.Reception;
import doctor_v2.domain.Specialty;
import doctor_v2.domain.Treatment;
import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Money;
import doctor_v2.vo.Sequence;
import doctor_v2.vo.Title;
import java.time.Duration;
import java.time.LocalDate;

public class Fixture {
    public static final Specialty SPECIALTY_구안와사_5000원 = new Specialty(
        Title.of("구안와사"),
        Duration.ofDays(60),
        Money.of(5000.0),
        LocalDate.of(2022, 06, 22),
        new SequenceAmountDiscount(Money.of(0.0), Sequence.of(0L)));
    public static final Specialty SPECIALTY_구안와사_5000원_2 = new Specialty(
        Title.of("구안와사"),
        Duration.ofDays(60),
        Money.of(5000.0),
        LocalDate.of(2022, 06, 22),
        new SequenceAmountDiscount(Money.of(0.0), Sequence.of(0L)));

    public static final Specialty SPECIALTY_매선 = new Specialty(
        Title.of("매선"),
        Duration.ofDays(60),
        Money.of(5000.0),
        LocalDate.of(2022, 06, 22),
        new SequenceAmountDiscount(Money.of(0.0), Sequence.of(0L)));

    public static final Treatment TREATMENT_첫번째_10개 = new Treatment(
        Sequence.of(1L),
        Title.of(String.format("%dth 제목", 1L)),
        Description.of(String.format("%d번째 패키지", 1L)),
        Count.of(10L), LocalDate.now());
    public static final Treatment TREATMENT_두번째_10개 = new Treatment(
        Sequence.of(2L),
        Title.of(String.format("%dth 제목", 2L)),
        Description.of(String.format("%d번째 패키지", 2L)),
        Count.of(10L), LocalDate.now());
    public static final Treatment TREATMENT_두번째_9개 = new Treatment(
        Sequence.of(2L),
        Title.of(String.format("%dth 제목", 2L)),
        Description.of(String.format("%d번째 패키지", 2L)),
        Count.of(9L), LocalDate.now());
    public static final Doctor DOCTOR_0원 = new Doctor(Money.of(0.0));
    public static final Reception RECEPTION_1 = new Reception(Money.of(0.0));
    public static final Reception RECEPTION_2 = new Reception(Money.of(0.0));
    public static final Patient PATIENT_0원 = new Patient(Money.of(0.0));
}
