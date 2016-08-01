
public class MorseToTextVV {

	public static void main(String[] args) {
		String sos = "... --- ... / ... / ... --- ... ";
		String hello = ".... . .-.. .-.. --- / .-- --- .-. .-.. -..";
		String f = "..-._..-_-._-.-._-.-_!_-.--_---_..-";

		MorseCodeTree tree = new MorseCodeTree();

		String mCode = tree.TextToMorse("sos s sos");
		String tCode_invalid = tree.MorseToText("");

		String getMCode = tree.TextToMorse("");
		System.out.println(getMCode);
		getMCode="...... t-.ajdfaksf....dpoiasnfp----oa";
		
		String answer = tree.MorseToText(getMCode);
		System.out.println(answer);
		
		
		
		// System.out.println(tree.MorseToText(f));
		// System.out.println(tree.MorseToText(hello));
	}
	

}
