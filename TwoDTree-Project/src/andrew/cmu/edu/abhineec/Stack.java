package andrew.cmu.edu.abhineec;

import edu.colorado.nodes.ObjectNode;

/******************************************************************************
 * Stack class extends SinglyLinkedList to implement stack data  structure.
 * attribute top represents the top pointer of the stack
 ******************************************************************************/
public class Stack extends SinglyLinkedList{
    ObjectNode top;

    /**
     * Initialize a Stack object
     * @postcondition
     *   An empty SinglyLinkedList object is initialized
     *   and top points to the head of the list
     **/
    public Stack(){
        super();
        this.top = super.head;
    }

    /**
     * Push an element in stack
     * @precondition
     *  SinglyLinkedList representing the stack is initialized
     * @postcondition
     *   Object is added to the end of list and top represents the
     *   last element in the list
     **/
    public void push(Object c){
        this.top = super.addAtEnd(c);
    }

    /**
     * Pop an element from stack
     * @precondition
     *  SinglyLinkedList representing the stack is initialized
     * @postcondition
     *   The last element in list pointed by top pointer is dereferenced
     *   and top now points to the last element in list
     **/
    public Object pop(){
        if(this.top == this.head) {
            this.countNodes--;
            return this.head.getData();
        }
        ObjectNode localIterator = super.head;
        while(localIterator.getLink() != this.top){
            localIterator = localIterator.getLink();
        }
        ObjectNode returnObject =  localIterator.getLink();
        localIterator.setLink(null);
        super.countNodes--;
        this.top = localIterator;
        return returnObject.getData();
    }

    /**
     * Helper function to check if stack (i.e. list) is empty or not
     */
    public boolean isEmpty(){
        return super.countNodes == 0;
    }
}
