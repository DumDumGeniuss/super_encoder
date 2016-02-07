package dumdumgenius.superEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

class ExtensionEncoder{
	private static ExtensionEncoder INSTANCE = new ExtensionEncoder();
	public static ExtensionEncoder getInstance(){
		return INSTANCE;
	}
	private ExtensionEncoder(){}
	
	public List<String> mask(String oriCode, int[] pos, int[] keys, int len, int nums){
		
		int operTimes = 0;
		int dupTimes = 0;
		
		//Return null if condition is wrong
		if(oriCode.length() != pos.length || keys.length > oriCode.length() || len < oriCode.length()){
			return null;
		}
		for(int posistion : pos){
			if(posistion >= oriCode.length() ){return null;}
		}
		for(int key : keys){
			if(key >= oriCode.length() ){return null;}
		}
		
		//It's result
		List<String> codes = new ArrayList<String>();
		HashMap<String,Boolean> checkRepeatCode = new HashMap<String,Boolean>();
		
		Random rand = new Random();
		
		int i = 0;
		while( i < nums ){
			operTimes++;
			int[] codeInts=new int[len];
			int offset=0;
			for(int j=0 ; j < len ; j++){
				if(offset<pos.length && j == pos[offset]){
					codeInts[j] = Integer.parseInt(oriCode.substring(offset,offset+1));
					offset++;
				}else{
					codeInts[j] =rand.nextInt(10);
				}	
			}
			
			for(int key : keys){
				for(int j=0 ; j < codeInts.length ; j++){
					if(j!=key){
						codeInts[j] = (codeInts[j]+codeInts[key])%10;
					}
				}
			}
			
			String code = "";
			for(int codeInt : codeInts){
				code += codeInt;
			}
			
			if(checkRepeatCode.get(code) != null){
				System.out.println("Duplication occurs!");
				dupTimes++;
				continue;
			}else{
				checkRepeatCode.put(code, true);
				codes.add(code);
				i++;
			}
		}
		System.out.println("Total create times: " + operTimes);
		System.out.println("Total duplication times: " + dupTimes);
		return codes;
	}
	
	public String unmask(String code, int[] pos, int[] keys){
		
		if(pos.length>code.length() || keys.length >code.length()){return null;}
		for(int posistion : pos){
			if(posistion >= code.length()){return null;}
		}
		for(int key : keys){
			if(key >= code.length()){return null;}
		}
		
		
		int[] codeInts = new int[code.length()];
		for(int i=0 ; i < code.length() ; i++){
			codeInts[i] = Integer.parseInt( code.substring (i,i+1) );
		}
		for(int i=keys.length-1 ; i >= 0 ; i--){
			for(int j=0 ; j < codeInts.length ; j++){
				if(j!=keys[i]){
					codeInts[j] = (10+codeInts[j]-codeInts[keys[i]])%10;
				}
			}			
		}
		String oriCode = "";
		for(int position : pos){
			oriCode += codeInts[position];
		}
		return oriCode;
	}
	
}