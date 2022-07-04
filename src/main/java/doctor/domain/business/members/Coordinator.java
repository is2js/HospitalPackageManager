package doctor.domain.business.members;

import doctor.domain.business.Coupon;
import doctor.domain.business.Package;
import doctor.domain.business.Specialty;
import doctor.domain.business.Treatment;
import doctor.domain.vo.Count;
import doctor.domain.vo.Money;
import java.util.List;
import java.util.Objects;

public class Coordinator {

    private Reception reception;

    public Package sellPackage(final Patient patient,
                               final Doctor doctor,
                               final Specialty specialty,
                               final Treatment treatment,
                               final Count count) {
        Package packageItem = Package.EMPTY;

        final List<Coupon> coupons = patient.getCoupons();

        // A: 쿠폰 있는 경우
        if (!coupons.isEmpty()) {
            validateCouponCount(patient, count);
            packageItem = reception.getPackageWithCoupon(doctor, specialty, treatment, count);

            minusPatientCouponCount(patient, count, packageItem);
            return packageItem; // early return for 아래의 쿠폰 없는 경우에 else 안쓰려고
        }

        // B: 쿠폰 없는 경우
        final Money sales = reception.calculateFee(specialty, treatment, count);
        if (patient.hasAmount(sales)) {
            packageItem = reception.sellPackage(doctor, specialty, treatment, count);
            minusPatientAmount(patient, packageItem, sales);
        }

        return packageItem;
    }

    private void validateCouponCount(final Patient patient, final Count count) {
        if (!patient.hasCoupons(count)) {
            throw new RuntimeException("[ERROR] 쿠폰의 갯수가 모자랍니다.");
        }
    }

    private void minusPatientCouponCount(final Patient patient, final Count count, final Package packageItem) {
        if (packageItem != Package.EMPTY) {
            patient.minusCoupon(count);
        }
    }

    private void minusPatientAmount(final Patient patient, final Package packageItem, final Money sales) {
        if (packageItem != Package.EMPTY) {
            patient.minusAmount(sales);
        }
    }

    public void setReception(final Reception reception) {
        this.reception = reception;
    }

    public Reception getReception() {
        return reception;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Coordinator that = (Coordinator) o;
        return Objects.equals(getReception(), that.getReception());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReception());
    }
}
