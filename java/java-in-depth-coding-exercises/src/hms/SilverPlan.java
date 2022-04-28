package hms;

public class SilverPlan extends HealthInsurancePlan {

	public SilverPlan() {
		this.coverage = 0.7;
		this.discount = 30;
	}

	@Override
	public double computeMonthlyPremium(double salary, int age, boolean smoking) {
		return 0.06 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
	}
}
