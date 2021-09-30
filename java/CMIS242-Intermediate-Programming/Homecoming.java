
public class Homecoming {

	public static void main(String[] args) {
		System.out.println(
				"Ever wondered what it's like coming home to 4 cats? You're in luck! Read on to see a little of each's personality during meal time!");
		// Creates different objects of cats
		Cats Trinity = new Trinity();
		Cats Tux = new Tux();
		Cats Steve = new Steve();
		Cats Bucky = new Bucky();
		Cats David = new David();

		// All the different actions which are defined further below in the separate cat
		// classes
		System.out.println("\nLet's first introduce the heathens:");
		Trinity.actionIntro();
		Tux.actionIntro();
		Steve.actionIntro();
		Bucky.actionIntro();
		David.actionIntro();

		System.out.println("\nWhat happens when you walk through the door?");
		Trinity.actionGreet();
		Tux.actionGreet();
		Steve.actionGreet();
		Bucky.actionGreet();
		David.actionGreet();

		System.out.println("\nWhat food do they like?");
		Trinity.mealTime();
		Tux.mealTime();
		Steve.mealTime();
		Bucky.mealTime();
		David.mealTime();

		System.out.println("\nHow they actually eat:");
		Trinity.mealActions();
		Tux.mealActions();
		Steve.mealActions();
		Bucky.mealActions();
		David.mealActions();
	

		System.out.println("\nAnd now you know what it's like to live in a zoo!");

	}
}

//This class gives Trinity what happens for each of the actions
class Trinity implements Cats {

	@Override
	public void actionIntro() {
		System.out.println("Trinity is a 14yr old torti who is the ruler of the house:");
	}

	@Override
	public void actionGreet() {
		System.out.println("Trinity: Sits patiently on counter, stares");
	}

	@Override
	public void mealTime() {
		//
		System.out.println("Trinity: Salmon and Chicken Pate with probiotics");
	}

	@Override
	public void mealActions() {
		System.out.println(
				"Trinity: She will eat a little then jump down and leave it up to you to defend it from America's Ass");
	}

}

//This class gives Tux what happens for each of the actions
class Tux implements Cats {
	@Override
	public void actionIntro() {
		System.out.println("Tux is the 2 year old leader of the trio of tuxedo aliens.");
	}

	@Override
	public void actionGreet() {
		System.out.println("Tux: Lets kittens fly down stairs before him, then comes down and flops on gym bag");
	}

	@Override
	public void mealTime() {
		// TODO Auto-generated method stub
		System.out.println("Tux: Rabbit Pate with pumpkin powder");
	}

	@Override
	public void mealActions() {
		System.out.println(
				"Tux: He will eat some then come and stare at you while you wash dishes expecting a treat for basically exisiting (he deserves it).");
	}

}

//This class gives Steve what happens for each of the actions
class Steve implements Cats {
	@Override
	public void actionIntro() {
		System.out.println("Steve Rogers is a 7mo old tuxedo alien who defends the world one treat at a time.");
	}

	@Override
	public void actionGreet() {
		System.out.println("Steve: Flies down stairs and does a belly flop right inbetween your feet for belly rubs");
	}

	@Override
	public void mealTime() {
		// TODO Auto-generated method stub
		System.out.println("Steve: Salmon pate with probiotics.");
	}

	@Override
	public void mealActions() {
		System.out.println(
				"Steve: He will eat until his brother interrupts, then he will go finish Tux' food then try to get to Trinity's that I have been left to defend. He is a portly soul.");
	}

}

//This class gives Bucky what happens for each of the actions
class Bucky implements Cats {
	@Override
	public void actionIntro() {
		System.out
				.println("Bucky Barnes is Steve's brother, also 7mo old, he enjoys tripping people on stairs.");
	}

	@Override
	public void actionGreet() {
		System.out.println("Bucky: Flies down the stairs and twirls around Tux, meowing");
	}

	@Override
	public void mealTime() {
		// TODO Auto-generated method stub
		System.out.println("Bucky: Salmon pate with probiotics");
	}

	@Override
	public void mealActions() {
		System.out.println(
				"Bucky: He will eat some of his food and some of Steve's food but leave enough to come back for a snack later.");

	}
}
	class David implements Cats {
		@Override
		public void actionIntro() {
			System.out
					.println("Hello, I'm David and i'm not a cat!");
		}

		@Override
		public void actionGreet() {
			System.out.println("David: I'm pretending to be a cat for Week 5 Discussion");
		}

		@Override
		public void mealTime() {
			// TODO Auto-generated method stub
			System.out.println("David: I do love salmon and i'm starving");
		}

		@Override
		public void mealActions() {
			System.out.println(
					"David: I'm eating salmon with all my cat friends! :)");

		}
	}

