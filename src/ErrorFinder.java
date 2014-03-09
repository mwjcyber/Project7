import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class ErrorFinder {
	
	public static boolean checkDeathBeforeBirth(Individual ind){
		for ( GregorianCalendar g : ind.getDeathDate() ) {
			if ( ind.getBirthDate().after(g) ) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkGender(Hashtable<String, Family> familyIndex, Hashtable<String, Individual> indIndex, Individual ind)
	{
		String indGender = ind.getGender();
		ArrayList<String> spouses = ind.getAllSpousesIDs(familyIndex);
		for(String spouseID: spouses)
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
	
	public static boolean checkIncest(Hashtable<String, Family> familyIndex, Hashtable<String, Individual> indIndex, Individual ind)
	{
		ArrayList<String> spouses = ind.getAllSpousesIDs(familyIndex);
		ArrayList<String> allRelations = new ArrayList<String>();
		for(String spouseID: spouses)
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