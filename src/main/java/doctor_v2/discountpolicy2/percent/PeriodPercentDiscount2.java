package doctor_v2.discountpolicy2.percent;

import doctor_v2.domain.DiscountPercent;
import doctor_v2.domain.Treatment;
import java.time.LocalDate;

public class PeriodPercentDiscount2 extends PercentDiscount2 {

    private long eventDays;
    private LocalDate purchaseDate;

    protected PeriodPercentDiscount2(final DiscountPercent percent, final long eventDays) {
        this(percent, eventDays, LocalDate.now());
    }

    protected PeriodPercentDiscount2(final DiscountPercent percent, final long eventDays, final LocalDate purchaseDate) {
        super(percent);
        this.eventDays = eventDays;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return treatment.isInEventPeriod(eventDays, purchaseDate);
    }
}
