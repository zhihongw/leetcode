package Cache;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class LRUCache {
	private HashMap<Integer, CacheEntity> map;
	private int capacity;
	CacheEntity queue;
	CacheEntity tail;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*LRUCache cache = new LRUCache(2);
		cache.set(2,1);
		cache.set(2,2);
		cache.get(2);
		cache.set(1,1);
		cache.set(4,1);
		cache.get(2);*/
		LRUCache cache = new LRUCache(2);
		cache.set(2,1);
		cache.print();
		cache.set(3,2);
		cache.print();
		System.out.println(cache.get(3));
		cache.print();
		System.out.println(cache.get(2));
		cache.print();
		cache.set(4,3);
		cache.print();
		System.out.println(cache.get(2));
		cache.print();
		System.out.println(cache.get(3));
		cache.print();
		System.out.println(cache.get(4));
		cache.print();
	}

	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<Integer, CacheEntity>(capacity);
		queue = null;
	}
	
	public void moveToTop(CacheEntity en) {
		if(tail==en&&en.prev!=null)
			tail = en.prev;
		CacheEntity next=en.next;
		CacheEntity prev=en.prev;
		if (prev!=null)
			prev.next=next;
		if (next!=null)
			next.prev=prev;
		en.prev=null;
		en.next=queue;
		if(queue!=null)
			queue.prev=en;
		queue=en;
	}
	
	public int get(int key) {
		CacheEntity en = map.get(key);
		if(en!=null) {
			moveToTop(en);
			return en.getValue();
		}
		return -1;
	}
    public void print() {
    	CacheEntity head = queue;
    	while(head!=null) {
    		System.out.println("++++"+head.key+":"+head.value+"++++++");
    		head=head.next;
    	}
    }
	public void set(int key, int value) {
		CacheEntity en = map.get(key);
		if (en!=null) {
			en.setValue(value);
			if(map.size()>1)
			moveToTop(en);
		} else {
			if(map.size()==capacity) {
				if (tail.prev!=null)
					tail.prev.next=null;
				map.remove(tail.key);
				if (map.size()>0)
					tail=tail.prev;
				else 
					tail = null;
				tail.next=null;
			}
			CacheEntity entity = new CacheEntity(key, value, null, null);
			moveToTop(entity);

			if (tail==null)
				tail=queue;
			map.put(key, entity);
		}
	}

	public class CacheEntity {
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public int getKey() {
			return key;
		}

		private int key;
		private int value;
		public CacheEntity getPrev() {
			return prev;
		}
		public void setPrev(CacheEntity prev) {
			this.prev = prev;
		}
		public CacheEntity getNext() {
			return next;
		}
		public void setNext(CacheEntity next) {
			this.next = next;
		}

		private CacheEntity prev;
		private CacheEntity next;
		public CacheEntity(int key, int value, CacheEntity prev, CacheEntity next) {
			this.key=key;
			this.value=value;
			this.prev = prev;
			this.next = next;
		}
	}
}

