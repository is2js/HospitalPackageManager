package doctor_v2.domain;

import doctor_v2.discountpolicy.DiscountCondition;
import doctor_v2.discountpolicy.DiscountPolicy;
import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import doctor_v2.vo.Title;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

public class Specialty<T extends DiscountCondition & DiscountPolicy> {

    private final Title title;
    private final Duration duration;
    private final Money fee;
    private final LocalDate createDate;
    private final T discountPolicy;

    public Specialty(final Title title,
                     final Duration duration,
                     final Money fee,
                     final LocalDate createDate,
                     final T discountPolicy) {

        this.title = title;
        this.duration = duration;
        this.fee = fee;
        this.createDate = createDate;
        this.discountPolicy = discountPolicy;
    }

    public Money calculateFee(final Treatment treatment, final Count count) {
        if (discountPolicy.isSatisfiedBy(treatment)){
            return discountPolicy.calculateFee(fee)
                .multi(count);
        }
        return fee.multi(count);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Specialty)) {
            return false;
        }
        final Specialty specialty = (Specialty) o;
        return Objects.equals(title, specialty.title) && Objects.equals(duration, specialty.duration)
            && Objects.equals(fee, specialty.fee) && Objects.equals(createDate, specialty.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, duration, fee, createDate);
    }
}
