import java.util.Vector;

public class ErrorList 
{
	ErrorList() 
	{
		errors = new Vector<Error>();
	}
	
	public void add(Error p) 
	{
		errors.add(p);
	}
	
	private Vector<Error> errors;

	public int size() 
	{
		return errors.size();
	}
	
	public String buildStringOutput() 
	{
		String s = "";
		
		for (Error p : errors) 
		{
			s += p.toString();
		}
		return s;
	}
	
	public Error get(int lineNumber) 
	{
		for (Error p : errors) {
			if ( p.getLineNumber() == lineNumber ) 
			{
				return p;
			}
		}
		return null;
	}
	
}