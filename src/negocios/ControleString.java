package negocios;

public class ControleString {
	public static String arrumaString(String a) {
		int cont =a.length();
		int ultimo =0;
		for (int i=a.length();i>0;i--) {
			System.out.println(cont);
			cont=cont-1;
			if(i<a.length()) {
			
			
			if (!a.substring(i, i+1).equals(a.substring(a.length()-1, a.length()))) {
			
			
			
				ultimo = i;
			break;
			}
			}
			
		}
		a=a.substring(0,ultimo+1);
		
		
		return a;
		
		
	

	}
}
