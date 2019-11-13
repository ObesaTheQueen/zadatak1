package hr.combis.common;

public class ObjectUtil {

	public static boolean isLenghtOk(String in, int size) {
		if(in == null)
			return true;
		
		if(in.length() > size)
			return false;
		return true;
	}
}
