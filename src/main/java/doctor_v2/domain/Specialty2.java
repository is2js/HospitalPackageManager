package doctor_v2.domain;

import doctor_v2.discountpolicy.DiscountCondition;
import doctor_v2.discountpolicy.DiscountPolicy;
import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import doctor_v2.vo.Title;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Specialty2<T extends DiscountCondition & DiscountPolicy> {

    private final Title title;
    private final Duration duration;
    private final Money fee;
    private final LocalDate createDate;
    private final Set<T> discountPolicies = new HashSet<>();

    public Specialty2(final Title title,
                      final Duration duration,
                      final Money fee,
                      final LocalDate createDate,
                      final T... discountPolicies) {

        this.title = title;
        this.duration = duration;
        this.fee = fee;
        this.createDate = createDate;
        this.discountPolicies.addAll(Arrays.asList(discountPolicies));
    }

    public Money calculateFee(final Treatment treatment, final Count count) {
        Money calculatedFee = fee;
        for (final T discountPolicy : discountPolicies) {
            if (discountPolicy.isSatisfiedBy(treatment)) {
                calculatedFee = discountPolicy.calculateFee(calculatedFee);
            }
        }
        return calculatedFee.multi(count);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Specialty2)) {
            return false;
        }
        final Specialty2 specialty = (Specialty2) o;
        return Objects.equals(title, specialty.title) && Objects.equals(duration, specialty.duration)
            && Objects.equals(fee, specialty.fee) && Objects.equals(createDate, specialty.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, duration, fee, createDate);
    }
}
