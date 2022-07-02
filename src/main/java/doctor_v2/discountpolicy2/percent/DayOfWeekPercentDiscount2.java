package doctor_v2.discountpolicy2.percent;

import doctor_v2.domain.DiscountPercent;
import doctor_v2.domain.Treatment;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DayOfWeekPercentDiscount2 extends PercentDiscount2 {
    private Set<DayOfWeek> dayOfWeeks = new HashSet<>();

    protected DayOfWeekPercentDiscount2(final DiscountPercent percent, final DayOfWeek... eventDayOfWeeks) {
        super(percent);
        dayOfWeeks.addAll(Arrays.asList(eventDayOfWeeks));
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return treatment.containsReleaseDateIn(dayOfWeeks);
    }
}
