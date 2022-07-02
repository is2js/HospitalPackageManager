package doctor_v2.discountpolicy2.amount;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Money;
import java.time.LocalDate;

public class PeriodAmountDiscount2 extends AmountDiscount2 {

    private long eventDays;
    private LocalDate purchaseDate;

    public PeriodAmountDiscount2(final Money amount, final long eventDays) {
        this(amount, eventDays, LocalDate.now());
    }

    protected PeriodAmountDiscount2(final Money amount, final long eventDays, final LocalDate purchaseDate) {
        super(amount);
        this.eventDays = eventDays;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return treatment.isInEventPeriod(eventDays, purchaseDate);
    }
}
