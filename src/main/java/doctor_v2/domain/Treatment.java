package doctor_v2.domain;

import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Sequence;
import doctor_v2.vo.Title;
import java.util.Objects;

public class Treatment {

    private final Sequence sequence;
    private final Title title;
    private final Description description;
    private Count count;

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

    public void minusCount(final Count count) {
        if (!hasCount(count)) {
            throw new RuntimeException("[ERROR] no available count");
        }
        this.count = this.count.minus(count);
    }

    public Count getCount() {
        return count;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Treatment)) {
            return false;
        }
        final Treatment treatment = (Treatment) o;
        return Objects.equals(sequence, treatment.sequence) && Objects.equals(title, treatment.title)
            && Objects.equals(description, treatment.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequence, title, description);
    }
}
