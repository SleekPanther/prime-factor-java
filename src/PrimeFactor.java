import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/*
 * Find prime factors of a number in 3 steps		(uses Long, & the program freezes beyond 16 digits)
 * 1) Find ALL prime factors
 * 2) Remove duplicated & increment the value in another list storing exponents for repeated numbers
 * 3) Copy the modified 2 arrayLists (all factors & corresponding exponents) to a new 2d ArrayList
 * Maybe not the most efficient way since it takes 3 passes, but it gives the desired result
 * 
 * Actually finding ALL the prime factors (my step 1) is adapted from a video by Michael Triantafelow https://youtu.be/9flsVKN4tZM
 */
public class PrimeFactor {
	public static void main(String[] args) {
		PrimeFactor pFact = new PrimeFactor();		//call nonexistant constructor to start program

		System.out.println("Find prime factors of a number");
		Scanner input = new Scanner(System.in);
		String strUserNum = "";		//string version of inut, attempt to convert to a number which can raise an exception
		long userNum = 10;		//create user input variable with default value
		
		String wantAnother = "y";		//controls if the user wants to check another 
		while(wantAnother.equals("y")){
			boolean isGoodVal = false;		//assume they got it wrong
			while(!isGoodVal){		//make sure they entered an integer, then check if it's positive
				try{
					System.out.print("Enter a positive whole number & press ENTER: ");
					strUserNum = input.next();		//get string input
					while(strUserNum.length() > 16){			//check s the STRING value to see if it's too many digits. Only encountered if it couln't convert to a number. Some 9-digits numers stil can be factors, but in that case the exception is never encountered
						System.out.println("   Error! Number is too many digits");
						System.out.print("Enter a positive whole number & press ENTER: ");
						strUserNum = input.next();		//get string input
					}
					userNum = Long.parseLong(strUserNum);		//attempt to convert to number, (raises exception here)
					isGoodVal = true;			//if it reaches here, it's surely a number
				}
				catch(NumberFormatException e){		//if they entered a special character, letter or decimal instead of a whole number
						System.out.println("   Error! Must be an whole number, no decimals, symbols or letters");
						input.nextLine();		//advance & eat up the bad value
				}
				
				if(userNum < 1){			//check to make sure their number is positive (at this point it must be a number)
					isGoodVal = false;
					System.out.println("Number must be POSITIVE (greater than 0)");
				}
			}
			
			
			ArrayList<ArrayList<Long>> factorsAndExponents = pFact.getPrimeFactors(userNum);
			for(int i = 0; i< factorsAndExponents.size(); i++){			//display results in a nice format to read
				Integer exponent = factorsAndExponents.get(i).get(1).intValue();			//remember to "cast" to an Integer value
				if(exponent == 1){		//don't bother printing "^1" if exponent is 1
					System.out.print(factorsAndExponents.get(i).get(0) );		//only print number
				}
				else{
					System.out.print(factorsAndExponents.get(i).get(0) + "^" + exponent );		//print "number^exponent"
				}
				if(i != (factorsAndExponents.size()-1) ){		//print asterisks for multiplication, but only  if it's not the very last element
					System.out.print("*");
				}
			}
			if(factorsAndExponents.size() == 1){
				System.out.print("\n" + userNum + " is prime");
			}
			
			
			System.out.print("\n\nFactorize another number (y/n): ");
			wantAnother = input.next().toLowerCase();		//get input & convert to lowercase
			wantAnother = wantAnother.charAt(0) + "";		//get only the very 1st character in case they types "Yes" instead of "y"
		}
		System.out.println("Thanks");
	}
	
	/**
	 * Get Prime factorization of a number when for multiplying it all out
	 * @param num	a number to get the prime factorization of
	 * @return a 2d arraylist. Uuter array represents a number as a 2-element arraylist, the 1st element is the actual number, the 2nd is the exponent it's raised to 
	 */
	public ArrayList<ArrayList<Long>> getPrimeFactors(long num){
		ArrayList<Long> allFactors = new ArrayList<Long>();		//stores all the factors initially, then duplicates are removed later
		for(long divisor = 2; num>1; divisor++){		//start @ 2 since it's the 1st prime go as long as num is >1 (num will be decreased with the inner loop), then increment divisor (doesn't actually check all numbers less than num, it's better. try System.out.println("div "+ divisor);
			for(; num%divisor == 0; num /=divisor){		//kind of like: if(divisor actuall is a divisor){add to list} & then divide num by that divisor. (So num=12, divisor=2, we find 12%2==0 and now check 12/2=6 for the next value of num
				allFactors.add(divisor);		//add the prime divisor to list
			}
		}
		//could "return allFactors;" here if you DID want duplicates
				
		//remove duplicates & add to exponents array
		ArrayList<Integer> exponents = new ArrayList<Integer>();
		for(int i = 0; i< allFactors.size(); i++){	//i is really the index for exponents
			exponents.add(i, 1);		//add a 1 @ the current index. Looping over allFactors, so all numbers when multiplied together must have 1 as their exponent @ the least, 2*2*3 is really [(2^1)*(2^1)*(3^1)] which will become (2^2)*(3^1)
			long temp = allFactors.remove(i);		//remove an element & store its value, need to remove to see if the list contains any more occurrence of the same value
			while(allFactors.contains(temp)){			//check if the list has a repeated value
				allFactors.remove( allFactors.indexOf(temp) );		//remove that repeated value using the indexOf(that-repeated-value)
				exponents.set(i,  (exponents.get(i)+1)  );				//IMPORTANT: must use set, not add to overwrite. Basically increment the value for its exponent for repeated values (started @ 1)
			}
			allFactors.add(i, temp);			//add the value back in once duplicates are gone (put back in same place)
		}
		
		//allFactors is now modified & duplicates are removed
		ArrayList<ArrayList<Long>> factors = new ArrayList<ArrayList<Long>>();		//2d list holds the numbers as 1d 2-element arraylists with the 1st element representing the actual number & the 2nd the exponent it's raised to  
		for(int i = 0; i < allFactors.size(); i++){			//loop over allFactors (could loop over exponents since they're the same size now
			factors.add(new ArrayList<Long>());		//add a black arraylist to the inner arraylist
			factors.get(i).add(allFactors.get(i));			//add the number
			factors.get(i).add(exponents.get(i).longValue() );			//add the exponent (need to convert to long)
		}
		return factors;
	}
}