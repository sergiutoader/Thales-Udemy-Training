package hms;

public class PlatinumPlan extends HealthInsurancePlan {
	
	public PlatinumPlan() {
		this.coverage = 0.9;
		this.discount = 50;
	}

	@Override
	public double computeMonthlyPremium(double salary, int age, boolean smoking) {
		return 0.08 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
	}

}
