package doctor_v1.domain;

import java.util.ArrayList;
import java.util.List;

public class Reception {
    private final Long fee;

    public List<Package> getPackages() {
        return packages;
    }

    private List<Package> packages = new ArrayList<>();

    public Reception(final Long fee) {
        this.fee = fee;
    }

    public void addPackage(final Package packageItem) {
        this.packages.add(packageItem);
    }
}
