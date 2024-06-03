package Project;
//This class is an entry point of execution for Personal Expense Manager Application(PEMApp)

public class MainOfPEM {
	
	/**
	 * this method is creating<code> PEMService </code> object  and show app
	 *  menu by calling showMenu ( method
	 * @param args
	 */
	public static void main(String[]args) {
		PEMService service= new PEMService();
		service.showMenu();
		
	}

}
