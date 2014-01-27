package store.model;

public class MainTest {

	public static void main(String[]args){
		System.out.println(getString());
	}
	
	private static String getString(){
		try{
			return "try";
		}
		finally{
			return "finally";
		}
	}
}
