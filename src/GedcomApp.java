import java.io.File;


public class GedcomApp {

	public static void main(String args[]) {
		if(args.length != 1)
		{
			System.out.println("Please enter exactly 1 GEDCOM File");
		}
		else
		{
			String fileName = args[0];
			if(!fileName.endsWith(".ged"))
			{
				System.out.println("Please enter a file ending in \".ged\"");
			}
			else
			{
				File file = new File(fileName);
				if(!file.exists())
				{
					System.out.println("File '"+fileName+"' can't be found or doesn't exist");
				}
				else
				{
					GedcomReader parser = new GedcomReader();
					parser.readGED(file);
					ErrorList pl = parser.findProblems();
					
					if ( pl.size() == 0 ) {
						System.out.println("File parsed! No Errors or Anomalies Found.");
					}
					else{
						System.out.println( pl.buildOutputString() );
					}
				}
			}			
		}
	}
}