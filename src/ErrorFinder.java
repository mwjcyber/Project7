import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class ErrorFinder {
	
	public static boolean deathBeforeBirth(Individual i){
		for ( GregorianCalendar g : i.getDeathDate() ) {
			if ( i.getBirthDate().after(g) ) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean marriedToSibling(Hashtable<String, Family> familyIndex, Hashtable<String, Individual> indIndex, Individual ind)
	{
		ArrayList<String> spouses = ind.getAllSpousesIDs(familyIndex);
		ArrayList<String> familyIDsSpouseChildFamily = new ArrayList<String>();
		for(String spouseID: spouses)
		{
			familyIDsSpouseChildFamily.addAll(indIndex.get(spouseID).getFamC());
		}
		Iterator<String> i = ind.getFamC().iterator();
		while(i.hasNext())
		{
			if(familyIDsSpouseChildFamily.contains(i.next()))
				return true;
		}
		return false;
	}
}