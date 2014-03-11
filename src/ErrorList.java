import java.util.Vector;

public class ErrorList 
{
	ErrorList() 
	{
		errors = new Vector<Error>();
	}
	
	private Vector<Error> errors;

	public int size() 
	{
		return errors.size();
	}
	
	public void add(Error e) 
	{
		errors.add(e);
	}
	
	public String buildStringOutput() 
	{
		String s = "";
		
		for (Error e : errors) 
		{
			s += e.toString();
		}
		return s;
	}
	
	public Error get(int lineNumber) 
	{
		for (Error e : errors) {
			if (e.getLineNumber() == lineNumber) 
			{
				return e;
			}
		}
		return null;
	}
	
}