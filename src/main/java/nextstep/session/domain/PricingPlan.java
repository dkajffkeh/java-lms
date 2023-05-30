package nextstep.session.domain;

public enum PricingPlan {
    FREE("무료"),
    PAID("유료");

    private final String name;

    PricingPlan(String name) {
        this.name = name;
    }

}
