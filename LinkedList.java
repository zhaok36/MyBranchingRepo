import java.util.NoSuchElementException;

/**
A linked list is a sequence of nodes with efficient
element insertion and removal. This class
contains a subset of the methods of the standard
java.util.LinkedList class.
*/
public class LinkedList
{
    private Node first;

    /**
    Constructs an empty linked list.
    */
    public LinkedList(){
        first = null;
    }

    /**
    Returns the first element in the linked list.
    @return the first element in the linked list
    */
    public Object getFirst(){
        if(first == null) {throw new NoSuchElementException();}
        return first.data;
    }

    /**
    Removes the first element in the linked list.
    @return the removed element
    */
    public Object removeFirst(){
        if(first == null) {throw new NoSuchElementException();}
        Object removed = first.data;
        first = first.next;
        return removed;
    }

    /**
    Adds an element to the front of the linked list.
    @param element the element to add
    */
    public void addFirst(Object o){
        Node newNode = new Node();
        newNode.data = o;
        newNode.next = first;
        first = newNode;
    }

    /**
    Brandon Zhang
    Computes the size of the linked list.
    @return the number of elements in the list

    public int size(){
    /* //sub-optimal solution, you don't need iterator
        ListIterator iter = listIterator();
        int counter = 0;
        while(iter.hasNext()){
        iter.next();
        counter++;
        }
        return counter;
        /*

        //chad solution
        Node temp = first;
        int counter = 0;
        while(temp != null){
        temp = temp.next;
        counter++;
        }
        return counter;
    }
    /**/

    //recursive solution
    public int size(){
        return size(first);
    }
    private static int size(Node start){
        if(start == null) return 0;
        return 1 + size(start.next);
    }

    public Object get(int n){
        Node newNode = getNode(n);
        if(newNode == null) throw new NoSuchElementException();
        return newNode.data;
    }

    public void set(int n, Object element){
        Node temp = getNode(n);
        if(temp == null) throw new NoSuchElementException();
        temp.data = element;
    }

    private Node getNode(int n){
        Node temp = first;
        for (int i = 0; i<n && temp != null; i++){
        temp = temp.next;
        }
        return temp;
    }

    public boolean contains(Object element){
	   Node temp = first;

	   while (temp!=null){
		   if (temp.data.equals(element)){
			   return true;
			}
		   else{
			   temp = temp.next;
		   }
	   }

	   return false;
   }

    /*
    * Brandon Zhang
    * Linked list reverse method

    public void reverse(){
        if(first == null) return;
        Node current = first;
        while(current.next != null){
            current = current.next;
        }
        Node newFirst = current;
        while(current != first){ //loops from last to first
            Node temp = first;
            while(temp.next != null && temp.next != current){ //loops from beg back up to next last
                temp = temp.next;
            }
            current.next = temp;
            current = temp;
        }
        current.next = null;
        first = newFirst;
    }
    /**/

    //CHAD recursive solution
    public void reverse(){
        reverse(first).next = null;
    }

    private Node reverse(Node current){
        if(current.next == null){
            first = current;
            return current;
        }
        reverse(current.next).next = current;
        return current;
    }

    /**
    Returns an iterator for iterating through this list.
    @return an iterator for iterating through this list
    */
    public ListIterator listIterator(){
        return new LinkedListIterator();
    }

    //Class Node
    class Node{
        public Object data; //this object
        public Node next; //next obj
        //public Node previous; //prev obj
    }

    class LinkedListIterator implements ListIterator
    {
        //private data
        private Node position;
        private Node previous;
        private boolean isAfterNext; //tracks whether you're in an inconsistent state (ex. remove twice, remove right after add)

        /**
        Constructs an iterator that points to the front
        of the linked list.
        */
        public LinkedListIterator(){
            position = null;
            previous = null;
            isAfterNext = false;
        }

        /**
        Moves the iterator past the next element.
        @return the traversed element
        */
        public Object next(){
            if(!hasNext()) throw new NoSuchElementException();
            previous = position; //Remember for remove method
            isAfterNext = true;

            if(position == null){ //if its at the beginning of the list
            position = first; //true == i have not called next
            }
            else{ //if it's not at the beginning it moves one forward
            position = position.next; //this can be accessed because it's a class within a class
            }
            return position.data;
        }

        /**
        Tests if there is an element after the iterator position.
        @return true if there is an element after the iterator position
        */
        public boolean hasNext(){
            if(position == null) //if the iterator is at the beginning of the list
            return first != null; //checks if the list is empty or not
            else
            return position.next != null;
        }

        /**
        Adds an element before the iterator position
        and moves the iterator past the inserted element.
        @param element the element to add
        */
        public void add(Object element){
            if(position == null){ //if its at the beginning of the list
            addFirst(element);
            position = first;
            }
            else{
            Node newNode = new Node();
            newNode.data = element; //alias
            //it will insert newNode in between position and position.next
            newNode.next = position.next; //I know who is next
            position.next = newNode; //iterator thinks next is me
            position = newNode; //current position is me, little conflict if you call remove
            }
            isAfterNext = false;
        }

        /**
        Removes the last traversed element. This method may
        only be called after a call to the next() method.
        */
        public void remove(){
            if(!isAfterNext) {throw new IllegalStateException();} //flag false so next has not been called

            if(position == first){ //right at the beginning, just use the LinkedList's remove
            removeFirst(); //if first is null it's counted for
            }
            else{
            previous.next = position.next; //takes the prev's next reference and makes it skip position (moves reference one forward so pos is ignored)
            }
            position = previous; //moves back to the predecessor

            isAfterNext = false;

            //first call to remove the current position reverts to the predecessor
            //of removed element--> thus predecessor is no longer known, so you
            //need to call next again to know the predecessor
        }

        /**
        Sets the last traversed element to a different value.
        @param element the element to set
        */
        public void set(Object o){
            if(!isAfterNext) {throw new IllegalStateException();}
            position.data = o;
        }
    }//LinkedListIterator
}//LinkedList
