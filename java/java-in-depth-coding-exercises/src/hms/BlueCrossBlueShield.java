package hms;

public class BlueCrossBlueShield implements InsuranceBrand {
	private long id;
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
		double total = 0;
		if (age > 55) {
			if (insurancePlan instanceof PlatinumPlan) {
				total += 200;
			} else if (insurancePlan instanceof GoldPlan) {
				total += 150;
			} else if (insurancePlan instanceof SilverPlan) {
				total += 100;
			} else if (insurancePlan instanceof BronzePlan) {
				total += 50;
			}
		}
		
		if (smoking) {
			if (insurancePlan instanceof PlatinumPlan) {
				total += 100;
			} else if (insurancePlan instanceof GoldPlan) {
				total += 90;
			} else if (insurancePlan instanceof SilverPlan) {
				total += 80;
			} else if (insurancePlan instanceof BronzePlan) {
				total += 70;
			}
		}
		return total;
	}
}
