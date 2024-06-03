package Project;

import java.util.ArrayList;
import java.util.List;
/**
 * The class is used as Database/Repository and its a singleton.
 */

public class Repository 
{
	/**
	 * The list holds all expenses added by user.
	 */
	public List<Expense>expList=new ArrayList();
	/**
	 * The list holds all expenses-categories added by user.
	 */
	public List<Category>catList=new ArrayList();
	/**
	 * A singleton reference of repository.
	 */
	
	private static Repository repository;
	/**
	 * Private constructor to restrict object  creation from outside.
	 */
	/*private Repository() 
	{
		// Initialize sample categories
        catList.add(new Category(1, "Food"));
        catList.add(new Category(2, "Transportation"));
        catList.add(new Category(3, "Utilities"));
        catList.add(new Category(4, "Entertainment"));
    }*/
	/**
	 * The method provides a singleton object of Repository.
	 * @return
	 */

		
	
	
	public static Repository getRepository()
	{
		if(repository==null) 
		{
			repository=new Repository();
		}
		return repository;
		
	}

}
