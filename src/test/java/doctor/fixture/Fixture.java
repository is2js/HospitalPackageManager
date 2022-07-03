package doctor.fixture;

import doctor.domain.business.discountpolicy.DiscountPolicy;
import doctor.domain.business.discountpolicy.strategy.AmountPolicyApplierFactory;
import doctor.domain.members.Doctor;
import doctor.domain.members.Patient;
import doctor.domain.members.Reception;
import doctor.domain.business.Specialty;
import doctor.domain.business.Treatment;
import doctor.domain.vo.Count;
import doctor.domain.vo.Description;
import doctor.domain.vo.Money;
import doctor.domain.vo.Sequence;
import doctor.domain.vo.Title;
import java.time.Duration;
import java.time.LocalDate;

public class Fixture {
    public static final Specialty SPECIALTY_구안와사_5000원_AMOUNT_0원_할인 = new Specialty(
        Title.of("구안와사"),
        Duration.ofDays(60),
        Money.of(5000.0),
        LocalDate.of(2022, 06, 22),
        new DiscountPolicy(new AmountPolicyApplierFactory(Money.of(0.0))));

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
    public static final Treatment TREATMENT_첫번째_시퀀스 = new Treatment(Sequence.of(1L), Title.of("123"), Description.of("12345"),
        Count.of(1L), LocalDate.now());
    public static final Doctor DOCTOR_0원 = new Doctor(Money.of(0.0));
    public static final Reception RECEPTION_1 = new Reception(Money.of(0.0));
    public static final Reception RECEPTION_2 = new Reception(Money.of(0.0));
    public static final Patient PATIENT_0원 = new Patient(Money.of(0.0));
    public static final Count COUNT_1개 = Count.of(1L);
    public static final Money MONEY_천원 = Money.of(1000.0);
    public static final Money MONEY_만원 = Money.of(10000.0);
}
