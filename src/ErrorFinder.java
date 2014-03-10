import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class ErrorFinder 
{
	
	public static boolean checkDeathBeforeBirth(Individual ind)
	{
		for ( GregorianCalendar g : ind.getDeathDate() ) 
		{
			if ( ind.getBirthDate().after(g) ) 
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkGender(Hashtable<String, Family> family, Individual ind)
	{
		String indGender = ind.getGender();
		Iterator<String> i = ind.getFamS().iterator();
		while(i.hasNext())
		{
			if(indGender.equals("F"))
			{
				String s = i.next();
				if ( family.containsKey(s) )
				{
					family.get(s).getWife();
				}
				return true;				
			}
				
			else if(indGender.equals("M"))
			{
				String s = i.next();
				if ( family.containsKey(s) )
				{
					family.get(s).getHusb();
				}
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkIncest(Hashtable<String, Family> famIndex, Hashtable<String, Individual> indIndex, Individual ind)
	{
		ArrayList<String> indSpouses = ind.getAllSpousesIDs(famIndex);
		ArrayList<String> allRelations = new ArrayList<String>();
		for(String spouseID: indSpouses)
		{
			allRelations.addAll(indIndex.get(spouseID).getFamC());
		}
		Iterator<String> i = ind.getFamC().iterator();
		while(i.hasNext())
		{
			if(allRelations.contains(i.next()))
				return true;
		}
		return false;
	}
	
}