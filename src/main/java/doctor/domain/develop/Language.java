package doctor.domain.develop;

import java.util.Objects;

public class Language {
    private final String value;

    public Language(final String value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Language language = (Language) o;
        return Objects.equals(value, language.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Language{" +
            "value='" + value + '\'' +
            '}';
    }
}
