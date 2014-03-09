import java.util.Vector;


/**
 * This is the list of all problems in a gedcom file.
 *
 */
public class ErrorList {

	ErrorList() {
		errors = new Vector<Error>();
	}
	
	public void add(Error p) {
		errors.add(p);
	}
	
	public Error get(int lineNumber) {
		for (Error p : errors) {
			if ( p.getLineNumber() == lineNumber ) {
				return p;
			}
		}
		return null;
	}
	
	private Vector<Error> errors;

	public int size() {
		return errors.size();
	}

	public String buildOutputString() {
		String s = "";
		
		for (Error p : errors) {
			s += p.toString();
		}
		return s;
	}
}