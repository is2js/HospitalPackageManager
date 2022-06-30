package doctor_v2.domain;

import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Sequence;
import doctor_v2.vo.Title;

public class Treatment {

    private final Sequence sequence;
    private final Title title;
    private final Description description;
    private final Count count;

    public Treatment(final Sequence sequence,
                     final Title title,
                     final Description description,
                     final Count count) {
        this.sequence = sequence;
        this.title = title;
        this.description = description;
        this.count = count;
    }

    public boolean hasCount(final Count count) {
        return this.count.isGreaterThanOrEqualTo(count);
    }
}
