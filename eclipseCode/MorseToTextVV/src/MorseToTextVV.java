import java.util.Arrays;

public class MorseToTextVV{

	public static void main(String[] args){
//		String a=_"..._---_..._!...!..._---_..._!";
//		String_hello_=_"...._._.-.._.-.._---_!_.--_---_.-._.-.._-..";
		String f = "..-._..-_-._-.-._-.-_!_-.--_---_..-";
		MorseCodeTree tree = new MorseCodeTree();

		System.out.println(tree.MorseToText(f));
//		System.out.println(tree.MorseToText(hello));
	}
	

}
