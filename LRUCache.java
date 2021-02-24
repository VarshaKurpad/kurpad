import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("hiding")
class LRUCache<Integer,T> {

	 private Map<Integer, Node> map;
	    private int capacity;
	    private Node head;
	    private Node tail;
	    private class Node{
	    	Integer key;
	        T val;
	        Node pre;
	        Node next;
	        public Node (Integer k, T v) {
	            key = k;
	            val = v;
	        }
	    }
	    public LRUCache(int capacity) {
	        map = new HashMap<>();
	        this.capacity = capacity;
	    }
	    
	    public T get(Integer key) {
	        if(!map.containsKey(key)) return null;
	        Node node = map.get(key);
	        remove(node);
	        setHead(node);
	        
	        return node.val;
	    }
	    
	    public void put(Integer key, T value) {
	        if(map.containsKey(key)) {
	            Node node = map.get(key);
	            node.val = value;
	            remove(node);
	            setHead(node);
	        } else {
	            if(map.size() >= capacity) {
	                map.remove(tail.key);
	                remove(tail);
	            }
	            Node node = new Node(key, value);
	            setHead(node);
	            map.put(key, node);
	        }
	    }
	    
	    private void remove(Node node) {
	        if(node.pre == null) {
	            head = node.next;
	        } else {
	            node.pre.next = node.next;
	        }
	        
	        if(node.next == null) {
	            tail = node.pre;
	        } else {
	            node.next.pre = node.pre;
	        }
	    }
	    
	    private void setHead(Node node) {
	        node.pre = null;
	        node.next = head;
	        if(head != null) {
	            head.pre = node;
	        }
	        
	        head = node;      
	        if(tail == null) {
	            tail = node;
	        }
	    }
	   
}