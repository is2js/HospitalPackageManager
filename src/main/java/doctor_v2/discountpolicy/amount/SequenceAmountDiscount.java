package doctor_v2.discountpolicy.amount;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Money;
import doctor_v2.vo.Sequence;

public class SequenceAmountDiscount extends AmountDiscount{

    private Sequence sequence;

    public SequenceAmountDiscount(final Money amount, final Sequence sequence) {
        super(amount);
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return treatment.isSequenceIn(sequence);
    }
}
