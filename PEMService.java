package Project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
/**
 * This class contains most of the operation related to PEM application.
 * <p>
 * This class prepares a menu and various method are present  to handle the user action.
 * This class make use of<code>Repository</code> to store the data.
 * Also using <code>ReportService</code> to generate different required reports.
 * 
 */

public class PEMService
/**
 * Declare a reference of repository by calling a static method which returns a singleton repository  object.
 */
{
	Repository repo=Repository.getRepository();
	/**
	 * Declare a reference of repository of ReportService to call different method to calculate reports.
	 */
	ReportService  reportService=new ReportService();
	/**
	 * Declare a scanner object to take standard input from the keyboard.
	 */
	
	private Scanner sc=new Scanner(System.in);
	/**
	 * This variable store the value of menu-choice.
	 */
	private int choice;
	
	/**
	 * call this constructor to create  PEMService object with default details.
	 */
	
	
	public PEMService() {
		// addSampleData(); // Call the method to add sample data when the service is initialized
		resotreRepository();
	}
	

	/**
	 * This method prepares a PEMApp menu using switch case and infinite loop,also wait to ask for users choice.
	 */

	public void showMenu()
	{
		while(true)
		{
			 printMenu();
			 switch(choice) 
			 {
			 case 1: onAddCategory();
				     pressAnyKeyToContinue(); 
			         break;
			 case 2: onCategoryList(); 
				     pressAnyKeyToContinue(); 
	                 break;       
			 case 3: onExpenseEntry();
				     pressAnyKeyToContinue(); 
	                 break;
	         case 4: onExpenseList ();
	        	     pressAnyKeyToContinue(); 
                     break; 
	         case 5: onMonthlyExpenseList();
	        	 	 pressAnyKeyToContinue();
	        	 	 break;
	         case 6: onYearlyExpenseList();
	        	     pressAnyKeyToContinue(); 
	        	     break;       
	         case 7: onCategorizedExpenseList();
			     	 pressAnyKeyToContinue(); 
			     	 break;
	         case 0: onExit();
        	         break; 	     
	         
			 }
			
		}
	}
	/**
	 * This method prints a menu(CUI/CLI menu)
	 */
	
	public void printMenu() {
		System.out.println("--------------------Personal Expense Manager Menu-------------------");
		
		System.out.println("1.Add Category");
		System.out.println("2.Category List");
		System.out.println("3.Expense Entry");
		System.out.println("4.Expense List");
		System.out.println("5.Monthly Expense List");
		System.out.println("6.Yearly Expense List");
		System.out.println("7.Categorized Expense List");
		System.out.println("0.Exit");
		
		
		System.out.println(" -------------------------------------------------------------");
		
		System.out.println("Enter Your Choice:");
		choice=sc.nextInt();
		
		
    }
	/**
	 * This method is called to hold the output screen  after processing the requested task and 
	 * wait for any char input to continue to the menu.
	 */
	
	public void pressAnyKeyToContinue() {
		/*try {
				System.out.println("Press any key to conitnue ");
				System.in.read();
			} 
		catch (IOException e) {
				
				e.printStackTrace();
			}*/
		
		System.out.println("Press any key to continue ");
	    try {
	        System.in.read();
	    } catch (IOException e) {
	        System.out.println("An error occurred while waiting for input.");
	        e.printStackTrace();
	    }
			
		}
	/**'
	 * This method is taking expense category name as input  to add new category in the system.
	 */
	public void onAddCategory() {
		sc.nextLine();//new line
		System.out.print("Enter Category Name:");
		String catName=sc.next();
		Category cat=new Category(catName);
		repo.catList.add(cat);
		System.out.println("Success:Category added");
		
	}
	/**
	 * Call this method to  print existing category list.
	 */

	public void  onCategoryList() {
		System.out.println("Category List");
		List<Category> clist=repo.catList;
		for(int i=0;i<clist.size();i++) {
			Category c=clist.get(i);
			System.out.println((i+1)+". "+c.getName()+", "+c.getCategoryId());
			
		}
		
	} 
	/**
	 * Call this method to enter expense  detail.The entered details will be added in repository.
	 */

	public void onExpenseEntry() {
	    System.out.println("Enter Details for Expense entry...");
	    onCategoryList();
	    System.out.println("Choose Category: ");
	    int catChoice = sc.nextInt();
	    Category selectedCat = repo.catList.get(catChoice - 1);

	    System.out.println("Enter Amount: ");
	    float amount = sc.nextFloat();
	    System.out.println("Enter Remark: ");
	    sc.nextLine(); // Consume newline character
	    String remark = sc.nextLine();

	    System.out.print("Enter Date (DD/MM/YYYY): ");
	    String dateAsString = sc.nextLine(); // Read the whole line for date input

	    Date date = DateUtil.stringToDate(dateAsString);

	    // Add Expense detail in Expense Object
	    Expense exp = new Expense();
	    exp.setCategoryId(selectedCat.getCategoryId());
	    exp.setAmount(amount);
	    exp.setRemark(remark);
	    exp.setDate(date);

	    // Store expense object in repository
	    repo.expList.add(exp);
	    System.out.println("Success: Expense Added");
	}
	/**
	 * This method prints all entered expenses.
	 */

	private void onExpenseList () {
		System.out.println("Expense Listing...");
		 List<Expense> expList = repo.expList;
		    for (int i = 0; i < expList.size(); i++) {
		        Expense exp = expList.get(i);
		        String catName = reportService.getCategoryNameById(exp.getCategoryId());
		        String dateString = DateUtil.dateToString(exp.getDate());
		        System.out.println((i + 1) + ". " + catName + " - ₹" + exp.getAmount() + " - " + exp.getRemark() + " - " + dateString);
		    }
		
	}
	/**
	 * This method is called from menu to prepare monthly-wise-expense -total.Its using <code>ReportService</code>to calculate report.
	 * The returned result is printed by this method.Means this method invokes a call to generate report then result is printed by this method
	 */

	private void onMonthlyExpenseList() {
		System.out.println("Monthly Expense Listing...");
		Map<String,Float>resultMap=reportService.calculateMonthlyTotal();   
		Set<String>keys=resultMap.keySet();
		for(String yearMonth:keys) {
			//2024,01
			String[]arr=yearMonth.split(",");
			String year=arr[0];
			Integer monthNo=new Integer(arr[1]);
			String monthName=DateUtil.getMonthName(monthNo);
			System.out.println(year+","+monthName+" : "+resultMap.get(yearMonth));
		}

		
		}
	/**
	 * This method is called from menu to prepare yearly-wise-expense -total.Its using <code>ReportService</code>to calculate report.
	 * The returned result is printed by this method.Means this method invokes a call to generate report then result is printed by this method
	 */

    private void onYearlyExpenseList() {
		System.out.println("Yearly Expense Total...");
		Map<Integer,Float> resultMap=reportService.calculateYearlyTotal();
		Set<Integer>years=resultMap.keySet();
		Float total=0.0F;
		for(Integer year:years) {
			Float exp=resultMap.get(year);
			total=total+exp;
			System.out.println(year+" : "+exp);
		}
		System.out.println("-----------------------");
		System.out.println("Total Expense (INR) :"+total);
		
		
	}
	/**
	 * This method is called from menu to prepare category-wise-expense -total.Its using <code>ReportService</code>to calculate report.
	 * The returned result is printed by this method.Means this method invokes a call to generate report then result is printed by this method
	 */
	private void onCategorizedExpenseList() {
		System.out.println("Category wise Expense Listing...");
		Map<String,Float>resultMap=reportService.calculateCategorizedTotal();   
		Set<String>categories=resultMap.keySet();
		Float netTotal=0.0F;
		for(String categoryName : categories) {
			Float catWiseToatl=resultMap.get(categoryName);
			netTotal=netTotal+catWiseToatl;
		   System.out.println(categoryName+":"+catWiseToatl);
		}
		System.out.println("-----------------------");
		System.out.println("Net Total  :"+netTotal);
	}
	/**
	 * This method stop a JVM,before that its storing the Repository permanently.
	 * Its closing PEM  application.Its like shutdown hook.
	 */
	
	private void  onExit() {
		persistRepository();
		System.exit(0);
		
	}
	private void persistRepository() {
		
		serialize("expenses.ser",repo.expList);
		serialize("categories.ser",repo.catList);
	}
	public void serialize(String file,Object obj) {
		try {
			FileOutputStream fos=new FileOutputStream(file);
			ObjectOutputStream oos=new  ObjectOutputStream (fos);
			oos.writeObject(obj);//store expense list in file.
			//use-finally block
			oos.close();
			fos.close();
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
		
	}
	public Object deser(String file) {
		try {
			FileInputStream fis=new FileInputStream(file);
			ObjectInputStream ois=new ObjectInputStream(fis);
			Object obj=ois.readObject();//deser
			
			return obj;
			
		} catch (Exception ex) {
			
			System.out.println("NO existing data present.");
			//ex.printStackTrace();
			return null;
		}
		
	}
	private void resotreRepository() {
		List<Expense> expList=(List<Expense>) deser("expenses.ser");
		List<Category> catList=(List<Category>) deser("categories.ser");
		if(expList!=null) {
			//set existing  expenses in repository
			repo.expList=expList;
			
			
		}
		if(catList!= null) {
			repo.catList=catList;
		}
	}



	/**
	 * This method  is preparing sample data for testing purpose.It should be report once app is tested ok.
	 */
	/*private void addSampleData() {
		 // Adding sample categories
        repo.catList.add(new Category("Food"));
        repo.catList.add(new Category("Transportation"));
        repo.catList.add(new Category("Utilities"));
        repo.catList.add(new Category("Entertainment"));
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       try { 
        // Manually specified dates
        Date date1 = dateFormat.parse("10/03/2024");
        Date date2 = dateFormat.parse("15/02/2024");
        Date date3 = dateFormat.parse("20/07/2023");
        Date date4 = dateFormat.parse("25/03/2023");

        // Adding sample expenses
        repo.expList.add(new Expense(1, 20.0f, date1, "Lunch"));
        repo.expList.add(new Expense(2, 30.0f, date2, "Taxi fare"));
        repo.expList.add(new Expense(3, 50.0f, date3, "Electricity bill"));
        repo.expList.add(new Expense(4, 25.0f, date4, "Movie tickets"));
       }
       catch(ParseException e){
    	   e.printStackTrace();
       }
		
	}*/
	/**
	 * This method sleep a thread for 10 ms.
	 */
	private void delay() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	

}
