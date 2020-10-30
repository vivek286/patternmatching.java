import java.util.ArrayList;
import java.util.Scanner;

class TrieNode{
	char data;
	boolean isTerminating;
	TrieNode children[];
	int childCount;

	public TrieNode(char data) {
		this.data = data;
		isTerminating = false;
		children = new TrieNode[26];
		childCount = 0;
	}
}

public class Trie {
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode('\0');
	}

	private void add(TrieNode root, String word){
		if(word.length() == 0){
			root.isTerminating = true;
			return;
		}		
		int childIndex = word.charAt(0) - 'a';
		TrieNode child = root.children[childIndex];
		if(child == null){
			child = new TrieNode(word.charAt(0));
			root.children[childIndex] = child;
			root.childCount++;
		}
		add(child, word.substring(1));
	}

	public void add(String word){
		add(root, word);
	}
	 private boolean search(TrieNode root, String word){
		if(word.length() == 0){
			
			if(root.isTerminating)return true;
            else{
                return false;
            }
		}		
		int childIndex = word.charAt(0) - 'a';
		TrieNode child = root.children[childIndex];
		if(child == null){
			return false;
		}
		return search(child, word.substring(1));
	}
	
	public boolean search(String word){
        return search(root,word);
    }
	public boolean patternMatching(ArrayList<String> input, String pattern) {
		for(int k=0;k<input.size();k++){
            Trie t=new Trie();
            String str=input.get(k);
            t.add(str);
            int n=str.length();
             for (int i = 0; i < n; i++) 
           for (int j = i+1; j < n; j++)
               t.add(str.substring(j));
           if(t.search(pattern))return true;
        }
		
	return false;}
}
public class Runner {

	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		Trie t = new Trie();
		int n = s.nextInt();
		ArrayList<String> input = new ArrayList<String>();
		for(int i = 0; i < n; i++) {
			String word = s.next();
			input.add(word);
		}
		String pattern = s.next();
		System.out.println(t.patternMatching(input, pattern));
	}
}
