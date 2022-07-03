package doctor.discountpolicy.condition;

import doctor.domain.Treatment;
import doctor.domain.vo.Count;
import java.time.LocalDate;

public class PeriodCondition implements DiscountCondition {

    private long eventDays;
    private LocalDate purchaseDate;

    public PeriodCondition(final long eventDays) {
        this(eventDays, LocalDate.now());
    }

    public PeriodCondition(final long eventDays, final LocalDate purchaseDate) {
        this.eventDays = eventDays;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment, final Count count) {
        return treatment.isInEventPeriod(eventDays, purchaseDate);
    }
}
