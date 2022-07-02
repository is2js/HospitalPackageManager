package doctor_v2.discountpolicy;

public interface DiscountPolicy {

    interface AMOUNT extends DiscountPolicy{}
    interface PERCENT extends DiscountPolicy{}
    interface OVERLAPPED extends DiscountPolicy{}
    interface NONE extends DiscountPolicy{}
}
