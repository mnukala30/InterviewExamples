package javaPrograms.inheritance;

public class BullDog extends Dog {

	public BullDog() {
		super.sound();
	}
	public static void main(String[] args) {
		
		BullDog obj = new BullDog();
		obj.sound();
		
		
	}

}
