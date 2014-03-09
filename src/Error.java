/**
 * This is the base class for any "problem"
 * that occurs in the gedcom file.  Every problem 
 * contains the line number where it occurred as
 * well as a message describing the problem.
 *
 */
public abstract class Error {
	public Error(int lineNum, String msg) 
	{
		this.lineNumber = lineNum;
		this.message = msg;
	}
	
	public abstract String toString();
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public String getMessage() {
		return message;
	}
	private String message;
	private int lineNumber;
}
