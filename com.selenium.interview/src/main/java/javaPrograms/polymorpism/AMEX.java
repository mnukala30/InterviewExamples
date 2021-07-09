package javaPrograms.polymorpism;

public class AMEX extends RBI {

	public double getHomeLoanROI() {

		return 8.5;

	}

	public double getCarLoanROI() {

		return 9.5;
	}

	public static void main(String args[])throws Exception{
		RBI R= new AMEX();
		System.out.println(R.getCarLoanROI());
		
	}
}
