import java.io.File;


public class GedcomApp {

	public static void main(String args[]) {
		if(args.length != 1)
		{
			System.out.println("Enter a GEDCOM filename argument");
		}
		else
		{
			String fileName = args[0];
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
					System.out.println("File parsed! No Errors Found.");
				}
				else{
					System.out.println( pl.buildOutputString() );
				}
			}
		}
	}
}