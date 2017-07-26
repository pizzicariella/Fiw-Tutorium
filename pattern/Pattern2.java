package klausurvorbereitung.pattern;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Pattern2 {
	LinkedHashSet<MyLine> set = new LinkedHashSet<>();
	
	public void addLine(MyLine line){
		set.add(line);
	}
}
