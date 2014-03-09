import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;


public class Individual 
{
	private String id;
	private String name;
	private String sex;
	private GregorianCalendar birthDate;
	private ArrayList<GregorianCalendar> deathDates;
	private HashSet<String> famS;
	private HashSet<String> famC;
	private int lineNumber;
	
	public Individual(String id)
	{
		this.id = id;
		this.deathDates = new ArrayList<GregorianCalendar>();
		this.famS = new HashSet<String>();
		this.famC = new HashSet<String>();
		setLineNumber(0);
	}
	
	public Individual(String id, int lineNumber)
	{
		this.id = id;
		this.deathDates = new ArrayList<GregorianCalendar>();
		this.famS = new HashSet<String>();
		this.famC = new HashSet<String>();
		setLineNumber(lineNumber);
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getSex()
	{
		return this.sex;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public GregorianCalendar getBirthDate()
	{
		return this.birthDate;
	}
	
	public void setBirthDate(GregorianCalendar birthDate)
	{
		this.birthDate = birthDate;
	}
	
	public ArrayList<GregorianCalendar> getDeathDate()
	{
		return this.deathDates;
	}
	
	public void addDeathDate(GregorianCalendar deathDate)
	{
		this.deathDates.add(deathDate);
	}
	
	public HashSet<String> getFamS()
	{
		return this.famS;
	}
	
	public void addFamS(String famS)
	{
		this.famS.add(famS);
	}
	
	public HashSet<String> getFamC()
	{
		return this.famC;
	}
	
	public void addFamC(String famC)
	{
		this.famC.add(famC);
	}

	public int getLineNumber() {
		return lineNumber;
	}
	
	public void setLineNumber(int lineNum) {
		this.lineNumber = lineNum;
	}
	
	public ArrayList<String> getAllSpousesIDs(Hashtable<String, Family> family)
	{
		ArrayList<String> spouses = new ArrayList<String>();
		
		Iterator<String> i = getFamS().iterator();
		while(i.hasNext())
		{
			if(sex.equals("F"))
			{
				String s = i.next();
				if ( family.containsKey(s) )
				{
					spouses.add(family.get(s).getHusb());
				}
			}
				
			else if(sex.equals("M"))
			{
				String s = i.next();
				if ( family.containsKey(s) )
				{
					spouses.add(family.get(s).getWife());
				}
			}
		}
		
		return spouses;
	}
	
	
}