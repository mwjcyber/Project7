import java.util.Vector;
import java.util.GregorianCalendar;

public class Family 
{
	private Vector<String> children;
	private String famID;
	private String husband;
	private String wife;
	private GregorianCalendar divorced;
	private GregorianCalendar married;
	private int lineNumber;
	
	public Family(String id)
	{
		children = new Vector<String>();
		famID = id;
		divorced = null;
		married = null;
		setLineNumber(0);
	}
	
	public Family(String id, int LineNum)
	{
		children = new Vector<String>();
		famID = id;
		divorced = null;
		married = null;
		setLineNumber(LineNum);
	}
	
	public void addChild(String cID)
	{
		children.add(cID);
	}
	
	public Vector<String> getChildren()
	{
		return children;
	}
	
	public void setHusb(String hID)
	{
		husband = hID;
	}
	
	public void setWife(String wID)
	{
		wife = wID;
	}
	
	public void setMarriage(GregorianCalendar m)
	{
		married = m;
	}
	
	public void setDivorce(GregorianCalendar d)
	{
		divorced = d;
	}
	
	public int getChildSize()
	{
		return children.size();
	}
	
	public String getHusb()
	{
		return husband;
	}
	
	public String getWife()
	{
		return wife;
	}
	
	public GregorianCalendar getMD()
	{
		return married;
	}
	
	public GregorianCalendar getDD()
	{
		return divorced;
	}
	
	public String getID()
	{
		return famID;
	}
	
	public int getLineNumber() 
	{
		return lineNumber;
	}
	
	public void setLineNumber(int lineNum) 
	{
		this.lineNumber = lineNum;
	}
	
}