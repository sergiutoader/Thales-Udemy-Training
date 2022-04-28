package hms;

public class Billing {
	   
    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];
        
        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        // your logic 
        if (patientInsurancePlan == null) {
        	
        	return new double[] {20.0, amount - 20.0};
        }
        
        double coverage = patientInsurancePlan.getCoverage();
        double discount = patientInsurancePlan.getDiscount();
        
        payments[0] = coverage * amount;
        payments[1] = Math.round((1 - coverage) * amount - discount);

        return payments;
    }
    
    public static void main(String[] args) {
		HealthInsurancePlan insurancePlan = new PlatinumPlan();
		Patient patient = new Patient();
		patient.setInsurancePlan(insurancePlan);
		double[] payments = Billing.computePaymentAmount(patient, 950.0);
		System.out.println(payments[0] + " " + payments[1]);
	}

}
