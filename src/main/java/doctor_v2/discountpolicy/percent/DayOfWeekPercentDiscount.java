package doctor_v2.discountpolicy.percent;

import doctor_v2.domain.DiscountPercent;
import doctor_v2.domain.Treatment;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DayOfWeekPercentDiscount extends PercentDiscount {
    private Set<DayOfWeek> dayOfWeeks = new HashSet<>();

    protected DayOfWeekPercentDiscount(final DiscountPercent percent, final DayOfWeek... eventDayOfWeeks) {
        super(percent);
        dayOfWeeks.addAll(Arrays.asList(eventDayOfWeeks));
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return treatment.containsReleaseDateIn(dayOfWeeks);
    }
}
