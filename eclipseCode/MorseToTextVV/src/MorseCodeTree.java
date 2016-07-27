import java.util.ArrayList;
import java.util.Arrays;

public class MorseCodeTree {
	private ArrayList<treeNode> tree;

	public MorseCodeTree() {
		tree = new ArrayList<treeNode>();
		init();
	}

	private void init() {

		// add root node to tree
		tree.add(new treeNode("start"));

		// index    1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26;
		// treeNode e, t, i, a, n, m, s, u, r, w, d, k, g, o, h, v, f, l, p, j, b, x, c, y, z, q;

		// assign values to each node
		String values = "etianmsurwdkgohvflpjbxcyzq";
		for (int index = 0; index < 26; index++) {
			tree.add(new treeNode(values.charAt(index) + ""));
		}
		// assign parents
		tree.get(1).parent = tree.get(0);
		tree.get(2).parent = tree.get(0);

		tree.get(3).parent = tree.get(1);
		tree.get(4).parent = tree.get(1);
		tree.get(5).parent = tree.get(2);
		tree.get(6).parent = tree.get(2);

		tree.get(7).parent = tree.get(3);
		tree.get(8).parent = tree.get(3);
		tree.get(9).parent = tree.get(4);
		tree.get(10).parent = tree.get(4);
		tree.get(11).parent = tree.get(5);
		tree.get(12).parent = tree.get(5);
		tree.get(13).parent = tree.get(6);
		tree.get(14).parent = tree.get(6);

		tree.get(15).parent = tree.get(7);
		tree.get(16).parent = tree.get(7);
		tree.get(17).parent = tree.get(8);
		tree.get(18).parent = tree.get(9);
		tree.get(19).parent = tree.get(10);
		tree.get(20).parent = tree.get(10);
		tree.get(21).parent = tree.get(11);
		tree.get(22).parent = tree.get(11);
		tree.get(23).parent = tree.get(12);
		tree.get(24).parent = tree.get(12);
		tree.get(25).parent = tree.get(13);
		tree.get(26).parent = tree.get(13);

		// assign left children
		tree.get(0).left = tree.get(1);
		tree.get(1).left = tree.get(3);
		tree.get(2).left = tree.get(5);

		tree.get(3).left = tree.get(7);
		tree.get(4).left = tree.get(9);
		tree.get(5).left = tree.get(11);
		tree.get(6).left = tree.get(13);

		tree.get(7).left = tree.get(15);
		tree.get(8).left = tree.get(17);
		tree.get(9).left = tree.get(18);
		tree.get(10).left = tree.get(19);
		tree.get(11).left = tree.get(21);
		tree.get(12).left = tree.get(23);
		tree.get(13).left = tree.get(25);

		// assign right children
		tree.get(0).right = tree.get(2);
		tree.get(1).right = tree.get(4);
		tree.get(2).right = tree.get(6);

		tree.get(3).right = tree.get(8);
		tree.get(4).right = tree.get(10);
		tree.get(5).right = tree.get(12);
		tree.get(6).right = tree.get(14);

		tree.get(7).right = tree.get(16);
		tree.get(10).right = tree.get(20);
		tree.get(11).right = tree.get(22);
		tree.get(12).right = tree.get(24);
		tree.get(13).right = tree.get(26);
		
		//assign if left child
		tree.get(1).isLeftChild = true;
		tree.get(3).isLeftChild = true;
		tree.get(5).isLeftChild = true;
		
		tree.get(7).isLeftChild = true;
		tree.get(9).isLeftChild = true;
		tree.get(11).isLeftChild = true;
		tree.get(13).isLeftChild = true;
		
		tree.get(15).isLeftChild = true;
		tree.get(17).isLeftChild = true;
		tree.get(18).isLeftChild = true;
		tree.get(19).isLeftChild = true;
		tree.get(21).isLeftChild = true;
		tree.get(23).isLeftChild = true;
		tree.get(25).isLeftChild = true;

	}

	public ArrayList<treeNode> getTree() {
		return tree;
	}

	public String MorseToText(String morseCode) {
		String answer = "";
		String[] words = morseCode.split("!");
		for (int i = 0; i < words.length; i++) {
			String[] letters = words[i].split("_");
			for (int j = 0; j < letters.length; j++) {
				answer = answer + findLetter(letters[j]);
			}
			answer = answer + " ";
		}
		return answer;

	}
	
	public String TextToMorse(String text){
		String answer = "";
			for(int i = 0; i< text.length(); i++){
				String oneletter = text.charAt(i)+"";
				answer += findMCode(oneletter) + "_";
			}
		return answer;
	}

	private String findLetter(String letter) {
		treeNode target = tree.get(0);
		for (int i = 0; i < letter.length(); i++) {
			if (letter.charAt(i) == '.') {
				target = target.left;
			} else {
				target = target.right;
			}
		}
		if (target.val.equals("start")) {
			return "";
		} else {
			return target.val;
		}
	}

	private String findMCode(String oneletter){
		String answer = "";
		treeNode target = tree.get(0);
		for(int i = 1; i < 27; i++){
			target = tree.get(i);
			if(target.getVal().equals(oneletter)){
				if(target.isLeftChild()){
					answer = answer + ".";
					break;
				}else{
					answer = answer + "-";
					break;
				}
			}
		}
		if(answer.equals("")){
			return "!";
		}else{
			while(target.getParent() != null && !target.getParent().getVal().equals("start")){
				target = target.getParent();
				if(target.isLeftChild()){
					answer = "." + answer;
				}else{
					answer = "-" + answer;
				}
			}//end while loop
		}//end if-else
		
		return answer;
	}
	class treeNode {

		private treeNode parent, left, right;
		private String val;
		private boolean isLeftChild;

		public treeNode() {
			this.parent = null;
			this.left = null;
			this.right = null;
			this.val = null;
			this.isLeftChild = false;
		}

		public treeNode(String val) {
			this.parent = null;
			this.left = null;
			this.right = null;
			this.val = val;
			this.isLeftChild = false;
		}

		public treeNode getParent() {
			return parent;
		}

		public treeNode getLeft() {
			return left;
		}

		public treeNode getRight() {
			return right;
		}

		public String getVal() {
			return val;
		}

		public boolean isLeftChild() {
			return isLeftChild;
		}

	}
}
