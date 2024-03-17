package altimetrix;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class Test {

	public static void main(String[] arg) throws Exception
	{
		System.out.println("Hello");  
	 
	 int num =10/0;
	 System.out.println(num);
	 
		throw new Exception("Arithmetic Exception: "+ num);
		
		
		
		/*
		 * try{                                                //code that may raise exception
		 *  int data=100/0; }       catch(Exception e) 
		 * {System.out.println(e);} catch(ArithmeticException e)
		 * {System.out.println(e);} catch(ArrayIndexOutofBound Exception e) 
		 * {System.out.println(e);} 
		 * //rest code of the program
		 * System.out.println("rest of the code...");
		 * 
		 * }
		 */ 
			
		
		/*
		 * try { // code that may raise exception int data = 10/0 ; } catch
		 * (ArrayIndexOutOfBoundsException e) {
		 * System.out.println("ArrayIndexOutOfBoundsException: " + e); } catch
		 * (ArithmeticException e) { System.out.println("ArithmeticException: " + e); }
		 * catch (Exception e) { System.out.println("Exception: " + e); }
		 */
		// rest of the code
		//System.out.println("Rest of the code...");
	}

}
