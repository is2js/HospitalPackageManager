package doctor.domain;

import doctor.domain.vo.Count;
import doctor.domain.vo.Description;
import doctor.domain.vo.Sequence;
import doctor.domain.vo.Title;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Treatment {

    private final Sequence sequence;
    private final Title title;
    private final Description description;
    private Count count;
    private final LocalDate purchaseDate;

    public Treatment(final Sequence sequence,
                     final Title title,
                     final Description description,
                     final Count availableCount,
                     final LocalDate purchaseDate) {
        this.sequence = sequence;
        this.title = title;
        this.description = description;
        this.count = availableCount;
        this.purchaseDate = purchaseDate;
    }

    public boolean hasCount(final Count count) {
        return this.count.isGreaterThanOrEqualTo(count);
    }

    public void minusCount(final Count count) {
        if (!hasCount(count)) {
            throw new RuntimeException("[ERROR] no available count");
        }
        this.count = this.count.minus(count);
    }

    public boolean isSequenceIn(final Sequence sequence) {
        return this.sequence.isIn(sequence);
    }

    public boolean containsReleaseDateIn(final Set<DayOfWeek> dayOfWeeks) {
        return dayOfWeeks.contains(purchaseDate.getDayOfWeek());
    }

    public boolean isInEventPeriod(final long eventDays, final LocalDate purchaseDate) {
        final LocalDate eventEndDate = this.purchaseDate.minusDays(1).plusDays(eventDays);
        return purchaseDate.isAfter(this.purchaseDate.minusDays(1)) && purchaseDate.isBefore(eventEndDate.plusDays(1));
    }

    public Sequence getSequence() {
        return sequence;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Count getCount() {
        return count;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    // 상태가 변하는 count를 제외하고 eq/hC를 비교한다.
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Treatment treatment = (Treatment) o;
        return Objects.equals(getSequence(), treatment.getSequence()) && Objects.equals(getTitle(),
            treatment.getTitle()) && Objects.equals(getDescription(), treatment.getDescription())
            && Objects.equals(getPurchaseDate(), treatment.getPurchaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSequence(), getTitle(), getDescription(), getPurchaseDate());
    }
}
