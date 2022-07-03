package doctor.discountpolicy.condition;

import doctor.domain.Treatment;
import doctor.domain.vo.Count;
import doctor.domain.vo.Sequence;

public class SequenceCondition implements DiscountCondition {

    private Sequence eventMaxSequence;

    public SequenceCondition(final Sequence eventMaxSequence) {
        this.eventMaxSequence = eventMaxSequence;
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment, final Count count) {
        return treatment.isSequenceIn(eventMaxSequence);
    }
}
