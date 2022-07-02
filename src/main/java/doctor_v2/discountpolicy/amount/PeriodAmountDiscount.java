package doctor_v2.discountpolicy.amount;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Money;
import java.time.LocalDate;

public class PeriodAmountDiscount extends AmountDiscount {

    private long eventDays;
    private LocalDate now;

    protected PeriodAmountDiscount(final Money amount, final long eventDays, final LocalDate now) {
        super(amount);
        this.eventDays = eventDays;
        this.now = now;
    }

    public PeriodAmountDiscount(final Money amount, final long eventDays) {
        super(amount);
        this.eventDays = eventDays;
        this.now = LocalDate.now();
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return treatment.isEventPeriod(eventDays, now);
    }
}
