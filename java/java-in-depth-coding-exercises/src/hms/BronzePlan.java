package hms;

public class BronzePlan extends HealthInsurancePlan {

	public BronzePlan() {
		this.coverage = 0.6;
		this.discount = 25;
	}

	@Override
	public double computeMonthlyPremium(double salary, int age, boolean smoking) {
		return 0.05 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
	}
}
