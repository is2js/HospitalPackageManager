package doctor_v2.discountpolicy.amount;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Money;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DayOfWeekAmountDiscount extends AmountDiscount {

    private Set<DayOfWeek> dayOfWeeks = new HashSet<>();

    protected DayOfWeekAmountDiscount(final Money amount, final DayOfWeek... dayOfWeek) {
        super(amount);
        this.dayOfWeeks.addAll(Arrays.asList(dayOfWeek));
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return treatment.containsSaleDateIn(dayOfWeeks);
    }
}
