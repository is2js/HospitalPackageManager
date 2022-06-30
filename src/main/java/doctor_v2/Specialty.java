package doctor_v2;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import doctor_v2.vo.Title;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

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

    public Money calculateFee(final Treatment treatment, final Count count) {
        return fee.multi(count);
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
