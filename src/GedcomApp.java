import java.io.File;


public class GedcomApp 
{
    public static void main(String args[]) 
    {
        String fileName = "GedcomFile.ged";
        File file = new File(fileName);
        if(!file.exists())
            System.out.println("File '"+fileName+"' can't be found.");
        else
        {
            GedcomReader parser = new GedcomReader();
            parser.readGED(file);
            ErrorList pl = parser.findErrors();
            
            if ( pl.size() == 0 )
                System.out.println("No Errors Found.");
            else
                System.out.println( pl.buildOutputString() );
        }
    }
    
}
