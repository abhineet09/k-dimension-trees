package andrew.cmu.edu.abhineec;

/******************************************************************************
 * Queue class extends SinglyLinkedList to implement queue data structure.
 ******************************************************************************/
public class Queue extends SinglyLinkedList {

    /**
     * Initialize a Queue object
     * @postcondition
     *   An empty SinglyLinkedList object is initialized
     **/
    public Queue(){
        super();
    }

    /**
     * Add an element to the end of list
     * @precondition
     *  SinglyLinkedList representing the queue is initialized
     * @postcondition
     *   Object is added to the end of list
     **/
    public void enqueue(Object c){
        super.addAtEnd(c);
    }

    /**
     * Remove an element from the front of list
     * @precondition
     *  SinglyLinkedList representing the queue is initialized
     * @postcondition
     *   Object is removed from to the front of list and list's
     *   head pointer points to the first element in list
     **/
    public Object dequeue(){
        return super.removeFromFront();
    }

    /**
     * Helper function to check if stack (i.e. list) is empty or not
     */
    public boolean isEmpty(){
        return super.countNodes == 0;
    }
}
