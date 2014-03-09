public abstract class Error 
{
	private String message;
	private int lineNumber;
	
	public Error(int lineNum, String msg) 
	{
		this.lineNumber = lineNum;
		this.message = msg;
	}
	
	public abstract String toString();
	
	public int getLineNumber() 
	{
		return lineNumber;
	}
	
	public String getMessage() 
	{
		return message;
	}

}
