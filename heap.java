tion for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
import java.util.*;
public class Solution {
	public ListNode mergeKLists(List<ListNode> lists) {
		int K = lists.size();
		if (K == 0)
			return null;
		if (K == 1)
			return lists.get(0);
		MyPriorityQueue<ListNode> pq = new MyPriorityQueue<ListNode>(10, new Comparator<ListNode>(){
			public int compare(ListNode n1, ListNode n2) {
				return n1.val-n2.val;
			};
		});
		
		ListNode result = null;
		int i = 0;
		while(i<lists.size()) {
			ListNode list = lists.get(i);
			if (list != null) {
				pq.offer(list);
			}
			i++;
		}
		ListNode tail = null;

		while(!pq.isEmpty()) {
			ListNode min = pq.poll();
			if (result==null) {
				result = min;
				tail = result;
			}
			else {
				tail.next=min;
				tail=tail.next;
			}

			if (min.next!=null)
		        pq.offer(min.next);
		}
		return result;
	}

	public class MyPriorityQueue<E> {
		private Comparator<E> cmp;
		private Vector<E> vector;
		
		public MyPriorityQueue(int size, Comparator cmp) {
			this.cmp = cmp;
			this.vector = new Vector<E>();
		}
		
		public void offer(E node) {
			vector.add(node);
			downadjust();
		}

		public E poll() {
			if(vector.size()==0)
				return null;
			E result = vector.get(0);
			swap(0, vector.size()-1);
			vector.remove(vector.size()-1);
			topadjust();
			return result;
		}		
		
		public boolean isEmpty() {
			return vector.size()==0;
		}
		
		private void topadjust() {
			if(vector.size()<=1)
				return;
			int size = vector.size();
			int i = 0;

			while (i <= size-1) {
				int left = 2 * i + 1;
				int right = 2 * i + 2;
                if (left>=size)
                    return;
                int min = left;
                if (right<size&&cmp.compare(vector.get(left), vector.get(right)) > 0)
                    min = right;
                if(cmp.compare(vector.get(min), vector.get(i)) < 0) {
                    swap(min, i);
                    i = min;
                } else {
                    return;
                }
			}
		}
		
		private void downadjust() {
			if(vector.size()<=1)
				return;
			int size = vector.size();
			int i = size-1;

			while (i >= 0) {
				int parent = (i-1)/2;
                if (cmp.compare(vector.get(parent),vector.get(i))>0) {
                    swap(parent, i);
                    i=parent;
                } else 
                    return;
			}
		}
		
		private void swap(int index1, int index2) {
			E temp = vector.get(index1);
			vector.set(index1, vector.get(index2));
			vector.set(index2, temp);
		}
	}
}gg
