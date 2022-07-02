package doctor_v2.discountpolicy.percent;

import doctor_v2.domain.DiscountPercent;
import doctor_v2.domain.Treatment;
import java.time.LocalDate;

public class PeriodPercentDiscount extends PercentDiscount {

    private long eventDays;
    private LocalDate purchaseDate;

    protected PeriodPercentDiscount(final DiscountPercent percent, final long eventDays) {
        this(percent, eventDays, LocalDate.now());
    }

    protected PeriodPercentDiscount(final DiscountPercent percent, final long eventDays, final LocalDate purchaseDate) {
        super(percent);
        this.eventDays = eventDays;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return treatment.isInEventPeriod(eventDays, purchaseDate);
    }
}
