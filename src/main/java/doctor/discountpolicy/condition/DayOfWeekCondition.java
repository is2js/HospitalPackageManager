package doctor.discountpolicy.condition;

import doctor.domain.Treatment;
import doctor.domain.vo.Count;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DayOfWeekCondition implements DiscountCondition {

    private Set<DayOfWeek> dayOfWeeks = new HashSet<>();

    public DayOfWeekCondition(final DayOfWeek... dayOfWeek) {
        this.dayOfWeeks.addAll(Arrays.asList(dayOfWeek));
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment, final Count count) {
        return treatment.containsReleaseDateIn(dayOfWeeks);
    }
}
