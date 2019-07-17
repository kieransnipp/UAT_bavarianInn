package demo;

public class ExceptionHandlingExample {

	public static void main(String[] args) throws Exception  {

		try {
			demo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		demo2();

	}// End Main method

	public static void demo() throws Exception {
		try {
			System.out.println("demo()  Hello world");
			int i = 1 / 0;
			System.out.println("Completed");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("I am inside the try catch");
			System.out.println("Message is " + e.getMessage());
			System.out.println("Cause is " + e.getCause());
		} finally {
			System.out.println("This is the finally bit");
		}
		
	}
	
	public static void demo2() throws Exception{
			System.out.println("demo2() Hello world");
			//int i = 1 / 0;
			System.out.println("Completed");
		
			System.out.println("This is the finally bit");
		}
	}

