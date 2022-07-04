package doctor.domain.develop;

import java.util.Objects;

public class Library {
    private final String value;

    public Library(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Library{" +
            "value='" + value + '\'' +
            '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Library library = (Library) o;
        return Objects.equals(value, library.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
