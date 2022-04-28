package hms;

public class GoldPlan extends HealthInsurancePlan {
	public GoldPlan() {
		this.coverage = 0.8;
		this.discount = 40;
	}

	@Override
	public double computeMonthlyPremium(double salary, int age, boolean smoking) {
		return 0.07 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
	}
}
