package hms;


abstract public class HealthInsurancePlan {
    // Code for 'coverage' field goes here'
	protected double coverage;
	protected double discount;
    
    public double getDiscount() {
		return discount;
	}

	public double getCoverage() {
		return coverage;
	}

	// Don't worry about the below code and also the InsuranceBrand class
	private InsuranceBrand offeredBy;

	public InsuranceBrand getOfferedBy() {
		return offeredBy;
	}

	public void setOfferedBy(InsuranceBrand offeredBy) {
		this.offeredBy = offeredBy;
	}
	
	public abstract double computeMonthlyPremium(double salary, int age, boolean smoking);
}