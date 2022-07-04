package doctor.domain.develop;

import java.util.Objects;

public class Server {
    private final String value;

    public Server(final String value) {
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
        final Server server = (Server) o;
        return Objects.equals(value, server.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Server{" +
            "value='" + value + '\'' +
            '}';
    }
}
