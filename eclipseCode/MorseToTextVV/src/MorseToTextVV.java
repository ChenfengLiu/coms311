import java.util.Arrays;

public class MorseToTextVV {

	public static void main(String[] args) {
		// String a=_"..._---_..._!...!..._---_..._!";
		// String_hello_=_"...._._.-.._.-.._---_!_.--_---_.-._.-.._-..";
		String f = "..-._..-_-._-.-._-.-_!_-.--_---_..-";

		MorseCodeTree tree = new MorseCodeTree();

		String mCode = tree.TextToMorse("sos s sos");
		String tCode = tree.MorseToText(mCode);
		System.out.println(mCode);
		System.out.println(tCode);

		// System.out.println(tree.MorseToText(f));
		// System.out.println(tree.MorseToText(hello));
	}

}
