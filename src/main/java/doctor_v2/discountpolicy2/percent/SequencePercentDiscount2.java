package doctor_v2.discountpolicy2.percent;

import doctor_v2.domain.DiscountPercent;
import doctor_v2.domain.Treatment;
import doctor_v2.vo.Sequence;

public class SequencePercentDiscount2 extends PercentDiscount2 {

    private Sequence eventMaxSequence;

    protected SequencePercentDiscount2(final DiscountPercent percent, final Sequence eventMaxSequence) {
        super(percent);
        this.eventMaxSequence = eventMaxSequence;
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return treatment.isSequenceIn(eventMaxSequence);
    }
}
