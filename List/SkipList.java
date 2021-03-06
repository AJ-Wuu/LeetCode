/**
 * @author AJWuu
 */

package skipList;

import java.util.Random;

public class SkipList {
	
	//Skip List introduces "express lanes" into a LinkedList to speed up the look-up operations (so L0, which is the original LinkedList, is the slowest in search)
	//It's faster because it can skip several elements when searching (only taking half of the elements every time)
	// 1 → 14 → 22 → 26 → 33 → 57 → 89 → 105 → 103    L0 (slowest)
	// ↓         ↓         ↓         ↓          ↓
	// 1    →   22    →   33    →   89    →    103    L1
	// ↓                   ↓                    ↓
	// 1         →        33         →         103    L2
	// ↓                                        ↓
	// 1                   →                   103    L3 (fastest)
	//Note: it could have whatever number of lanes (1 to ∞)
	//For searching process: start with the fastest (the last lane),
	//                       then gradually move upwards (step down) to smaller lane or move one step right until the target is found
	
	//Skip List is mainly used for small number of elements (not enough for BST) with all elements known
	//It is relatively fast and easy to compute and only needs a little memory
	
	//In a skip list containing n nodes, we'd like about n/(2^(x-1)) nodes with >=x levels
	
	//Complexity                      |  Average  |  Worst
	//Access, Search, Insert, Delete  |  O(logn)  |  O(n)     -> insertion may happen to multiple lanes, as long as it's in the middle of the src and dest
	//Space                           |     ~     |  O(nlogn)
	
	class Node {
		int val;
		int count; //handle duplicates
		int h; //highest level: [0,32]
		Node[] next;
		
		public Node(int a, int b) {
			val = a; h = b; count = 1;
			next = new Node[33];
		}
	}
	
	Node sentinel;
	int topLevel;
	Node[] stack; //keep track of search path
	Random rand;

	public SkipList() {
		sentinel = new Node(Integer.MIN_VALUE, 32);
		topLevel = 0;
		stack = new Node[33];
		rand = new Random();
	}

	private Node findPredecessor(int num) {
		Node cur = sentinel;
		for (int r=topLevel; r>=0; r--) {
			while (cur.next[r]!=null && cur.next[r].val<num) {
				cur = cur.next[r];
			}
			stack[r] = cur;
		}
		return cur;
	}

	public boolean search(int target) {
		Node pred = findPredecessor(target);
		return (pred.next[0]!=null && pred.next[0].val==target);
	}

	public void add(int num) {
		//1. Flip coin for each LX (X > 0) from lowest to highest: head -> the new element is on the lane; tail -> stop flipping
		//   Probability that the element is on L0 -> 100%; L1 -> 50%; L2 -> 25%; L3 -> 12.5%; ...
		//2. Do look-up for the new element and insert it into each of the lanes that it's on
		Node pred = findPredecessor(num);
		if (pred.next[0]!=null && pred.next[0].val==num) {
			pred.next[0].count++;
			return;
		}
		Node w = new Node(num, pickHeight());
		while (topLevel < w.h) {
			stack[++topLevel] = sentinel;
		}
		for (int i=0; i<=w.h; i++) {
			w.next[i] = stack[i].next[i];
			stack[i].next[i] = w;
		}
	}

	public boolean erase(int num) {
		//1. Do look-up for the value to delete
		//2. Delete value from every lane it's on
		Node pred = findPredecessor(num);
		if (pred.next[0]==null || pred.next[0].val!=num) {
			return false;
		}
		boolean noNeedToRemove = (--pred.next[0].count) != 0;
		if (noNeedToRemove) {
			return true;
		}
		for (int r=topLevel; r>=0; r--) {
			Node cur = stack[r];
			if (cur.next[r] != null && cur.next[r].val == num) {
				cur.next[r] = cur.next[r].next[r];
			}
			if (cur == sentinel && cur.next[r] == null) {
				topLevel--;
			}
		}
		return true;
	}

	private int pickHeight() { //number of trailing 0s of a random integer: [0, 32]
		return Integer.numberOfTrailingZeros(rand.nextInt());
	}

	public static void main(String[] args) {
		SkipList list = new SkipList();
		list.add(11);
		list.add(22);
		list.add(33);
		System.out.println("Search 0: " + list.search(0));
		list.add(44);
		list.add(22);
		System.out.println("Search 22: " + list.search(22));
		System.out.println("Erase 0: " + list.erase(0));
		System.out.println("Erase 11: " + list.erase(11));
		System.out.println("Erase 22: " + list.erase(22));
		System.out.println("Search 11: " + list.search(11));
		System.out.println("Search 22: " + list.search(22));
		System.out.println("Erase 22: " + list.erase(22));
		System.out.println("Search 22: " + list.search(22));
	}
	
}
