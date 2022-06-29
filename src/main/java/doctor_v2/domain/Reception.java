package doctor_v2.domain;

import doctor_v2.vo.CommissionRate;
import doctor_v2.vo.Money;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reception {
    private final Money amount;

    private List<Package> packages = new ArrayList<>();
    private Map<Doctor, CommissionRate> doctors = new HashMap<>();

    public Reception(final Money amount) {
        this.amount = amount;
    }

    public void addPackage(final Package packageItem) {
        this.packages.add(packageItem);
    }

    public List<Package> getPackages() {
        return packages;
    }

    public Package getPackageWithNoFee() {
        // 뭔가 저장된 것을 가져올 땐, 존재검사를 해야한다.
        // READ - 존재 검증
        if (packages.isEmpty()) {
            return Package.EMPTY;
        }

        return packages.remove(0);
    }

    public Long calculatePackageFee() {
        // 나갈놈의 가격을 미리 계산한다. 저장된 것을 꺼내야하므로 계산이지만, 존재 검증부터 하고
        // -> 없으면 0L원 반환한다.
        if (packages.isEmpty()) {
            return 0L;
        }

//        return packages.get(0).getFee();
        return null;
    }

    public Package getPackageWithFee() {
        if (packages.isEmpty()) {
            return Package.EMPTY;
        }

        final Package packageItem = packages.remove(0);
//        fee += packageItem.getFee();

        return packageItem;
    }

    public boolean contract(final Doctor doctor, final CommissionRate commissionRate) {
        if (doctors.containsKey(doctor)){
            return false;
        }
        doctors.put(doctor, commissionRate);
        return true;
    }

    public boolean cancelContract(final Doctor doctor) {
        if (!doctors.containsKey(doctor)) {
            return false;
        }

        doctors.remove(doctor);
        return true;
    }

    public Map<Doctor, CommissionRate> getDoctors() {
        return doctors;
    }
}
