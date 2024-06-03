package Project;

import java.io.Serializable;
import java.util.Date;
/**
 * This is a domain class represents Expense.
 */

public class Expense  implements Serializable {
	/**
	 * A unique expense id,here its auto-generated as current milliseconds,but should use 
	 * some standard algo for primary key generation.
	 */
	
	private long expenseId=System.currentTimeMillis();
	/**
	 * Represents a category of this expense.
	 */
	private long categoryId; //FK
	private float amount;
	private Date date;
    private String remark;
    
    public Expense() {
    	
    }

	public Expense( long categoryId, float amount, Date date, String remark) {
	
		this.categoryId = categoryId;
		this.amount = amount;
		this.date = date;
		this.remark = remark;
	}

	public long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(long expenseId) {
		this.expenseId = expenseId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
