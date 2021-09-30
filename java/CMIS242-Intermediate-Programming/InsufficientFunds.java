
/**
 * @author David Mejia
 * Date: 11/15/2019
 * Course: CMIS242
 * Professor:Andrew Seely
 * Purpose: This exception will be thrown whenever the user 
 * wants to withdraw more money than what is available
 * in their account.
 */

public class InsufficientFunds extends Exception {

	public InsufficientFunds() {
		super("Not enough money in your balance");
	}
}
