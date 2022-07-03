package doctor.domain.business;

import doctor.domain.business.discountpolicy.DiscountPolicy;
import doctor.domain.vo.Count;
import doctor.domain.vo.Money;
import doctor.domain.vo.Title;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

public class Specialty {

    private final Title title;
    private final Duration duration;
    private final Money fee;
    private final LocalDate createDate;
    private final DiscountPolicy discountPolicy;

    public Specialty(final Title title,
                     final Duration duration,
                     final Money fee,
                     final LocalDate createDate,
                     final DiscountPolicy discountPolicy) {

        this.title = title;
        this.duration = duration;
        this.fee = fee;
        this.createDate = createDate;
        this.discountPolicy = discountPolicy;
    }

    public Money calculateFee(final Treatment treatment, final Count count) {
        return discountPolicy.calculateFee(treatment, count, fee)
            .multi(count);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
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
