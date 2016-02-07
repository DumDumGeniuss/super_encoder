package dumdumgenius.superEncoder;

import java.util.List;

public class AppTestRunner{
	public static void main(String[] args){
		ExtensionEncoder extenEncoder = ExtensionEncoder.getInstance();
		List<String> codes = extenEncoder.mask("152457", new int[]{0,1,2,3,4,5}, new int[]{0,3}, 10, 1000);
		int i=0;
		for(String code : codes){
			i++;
			System.out.println(i+": "+code);
		}
		
		for(String code : codes){
			//System.out.println(extenEncoder.unmask(code , new int[]{0,1,2,3,4,5}, new int[]{0,3}));	
		}
	}
}