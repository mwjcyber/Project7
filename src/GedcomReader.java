import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.GregorianCalendar;
import java.lang.Integer;
import java.util.Vector;

public class GedcomReader 
{
	private boolean birthDate = false;
	private boolean deathDate = false;
	private boolean marrDate = false;
	private boolean divDate = false;
	private Hashtable<String, Family> famIndex;
	private Hashtable<String, Individual> indIndex;
	private Vector<String> listOfInds;
	private Vector<String> listOfFams;
	
	public GedcomReader()
	{
		famIndex = new Hashtable<String, Family>(50);
		indIndex = new Hashtable<String, Individual>(200);
		listOfInds = new Vector<String>(200);
		listOfFams = new Vector<String>(50);
	}
	
	public void readGED(File input)
	{
		String line;
		Scanner file;
		String id;
		Family fam = null;
		Individual ind = null;
		boolean parseInd = false;
		boolean parseFam = false;
		
		try
		{
			if(input != null)
			{
				file = new Scanner(input);
				int lineNumber = 1;
				while(file.hasNextLine())
				{
					line = file.nextLine();
					if(line.length() > 0)
					{
						if(line.charAt(0) == '0')
						{
							if(line.indexOf("FAM") != -1 || line.indexOf("INDI") != -1)
							{
								if(parseInd == true)
								{
									indIndex.put(ind.getId(), ind);
									parseInd = false;
								}
								else if(parseFam == true)
								{
									famIndex.put(fam.getID(), fam);
									parseFam = false;
								}
								if(line.indexOf("FAM") != -1)
								{
									parseFam = true;
									parseInd = false;
									id = parseID(line);
									fam = new Family(id, lineNumber);
									listOfFams.add(id);
								}
								else if(line.indexOf("INDI") != -1)
								{
									parseInd = true;
									parseFam = false;
									id = parseID(line);
									ind = new Individual(id, lineNumber);
									listOfInds.add(id);
								}
							}
						}
						else
						{
							if(parseInd == true)
								addIndData(line, ind);
							else if(parseFam == true)
								addFamData(line, fam);
						}
					}
					lineNumber++;
				}
			}
		} 
		catch(FileNotFoundException e)
		{
			System.out.println("The file provided was invalid.");
		}
	}
	
	public ErrorList findErrors() 
	{
		ErrorList el = new ErrorList();
		
		for ( String s : indIndex.keySet() ) 
		{
			if ( ErrorFinder.checkDeathBeforeBirth(indIndex.get(s) )) 
			{
				el.add( new ErrorMessage( indIndex.get(s).getLineNumber(), "Person " + indIndex.get(s).getId() + "'s death occurs before birth."));
			}
			
			if ( ErrorFinder.checkGender(famIndex, indIndex.get(s)))
			{
				el.add( new ErrorMessage( indIndex.get(s).getLineNumber(), "Person " + indIndex.get(s).getId() + "'s gender and spouse role don't match."));
			}
				
			if( ErrorFinder.checkIncest(famIndex, indIndex, indIndex.get(s)))
			{
				el.add(new ErrorMessage(indIndex.get(s).getLineNumber(), "Person " + indIndex.get(s).getId() + " married  a sibling."));
			}			
		}
		
		return el;
	}
	
	public String parseID(String entry)
	{
		String delim = "[ ]+";
		String[] tokens = entry.split(delim);
		String found = "";
		int i;
		
		for(i = 0; i < tokens.length; i++)
		{
			if(tokens[i].indexOf("@") != -1)
			{
				found = tokens[i];
				i = tokens.length;
			}
		}
			
		return found;
	}
	
	public int getMonth(String month)
	{
		if(month.equalsIgnoreCase("JAN"))
			return 1;
		else if(month.equalsIgnoreCase("FEB"))
			return 2;
		else if(month.equalsIgnoreCase("MAR"))
			return 3;
		else if(month.equalsIgnoreCase("APR"))
			return 4;
		else if(month.equalsIgnoreCase("MAY"))
			return 5;
		else if(month.equalsIgnoreCase("JUN"))
			return 6;
		else if(month.equalsIgnoreCase("JUL"))
			return 7;
		else if(month.equalsIgnoreCase("AUG"))
			return 8;
		else if(month.equalsIgnoreCase("SEP"))
			return 9;
		else if(month.equalsIgnoreCase("OCT"))
			return 10;
		else if(month.equalsIgnoreCase("NOV"))
			return 11;
		else if(month.equalsIgnoreCase("DEC"))
			return 12;
		return 1;
	}
	
	public void addIndData(String entry, Individual cur)
	{
		String delim = "[ ]+";
		String[] tokens = entry.split(delim);
		String tag = tokens[1];
		String name = "";
		int i;
		int month;
		
		if(tag.equalsIgnoreCase("NAME"))
		{
			for(i = 2; i < tokens.length; i++)
				name = name + tokens[i];
			cur.setName(name);
		}
		else if(tag.equalsIgnoreCase("SEX"))
		{
			cur.setGender(tokens[2]);
		}
		else if(tag.equalsIgnoreCase("BIRT"))
		{
			birthDate = true;
		}
		else if(tag.equalsIgnoreCase("DEAT"))
		{
			deathDate = true;
		}
		else if(tag.equalsIgnoreCase("FAMC"))
		{
			cur.addFamC(tokens[2]);
		}
		else if(tag.equalsIgnoreCase("FAMS"))
		{
			cur.addFamS(tokens[2]);
		}
		else if(tag.equalsIgnoreCase("DATE"))
		{
			if(tokens.length > 3)
			{
				month = getMonth(tokens[3]);
				GregorianCalendar m = new GregorianCalendar(Integer.parseInt(tokens[2]), month, Integer.parseInt(tokens[4]));
				if(birthDate == true)
				{
					birthDate = false;
					cur.setBirthDate(m);
				}
				else if(deathDate == true)
				{
					deathDate = false;
					cur.addDeathDate(m);
				}	
			}
		}
	}
	
	public void addFamData(String entry, Family cur)
	{
		String delim = "[ ]+";
		String[] tokens = entry.split(delim);
		String tag = tokens[1];
		int month = 1;
		
		if(tag.equalsIgnoreCase("HUSB"))
		{
			cur.setHusb(tokens[2]);
		}
		else if(tag.equalsIgnoreCase("WIFE"))
		{
			cur.setWife(tokens[2]);
		}
		else if(tag.equalsIgnoreCase("CHIL"))
		{
			cur.addChild(tokens[2]);
		}
		else if(tag.equalsIgnoreCase("MARR"))
		{
			marrDate = true;
		}
		else if(tag.equalsIgnoreCase("DIV"))
		{
			divDate = true;
		}
		else if(tag.equalsIgnoreCase("DATE"))
		{
			if(tokens.length > 3)
			{
				month = getMonth(tokens[3]);
				GregorianCalendar m = new GregorianCalendar(Integer.parseInt(tokens[2]), month, Integer.parseInt(tokens[4]));
				if(marrDate == true)
				{
					marrDate = false;
					cur.setMarriage(m);
				}
				else if(divDate == true)
				{
					divDate = false;
					cur.setDivorce(m);
				}	
			}
		}
	}
	
}