package andrew.cmu.edu.abhineec;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/******************************************************************************
 * TwoDTree class represents a 2-d plane's implementation as a Tree with
 * a head pointer reference - pointing to the tree root of type TreeNode
 * a countNodes variable that keeps track of total number of nodes in the list at any given point
 ******************************************************************************/

public class TwoDTree {
    TreeNode head;
    private int countNodes;

    static final double EPSILON = 0.000001d;

    /**
     * Initialize a TwoDTree object
     * @param crimeDataLocation
     *      the file path for the crime data that needs to be loaded
     * @precondition
     *  The String crimeDataLocation contains the path to
     *  a file formatted in the exact same way as CrimeLatLonXY.csv
     * @postcondition
     *    The 2d tree is constructed and may be printed or
     *    queried.
     **/
    public TwoDTree(String crimeDataLocation) throws IOException {
        File fileObj = new File(crimeDataLocation);
        Scanner myReader = new Scanner(fileObj);
        boolean firstLine = true;
        while(myReader.hasNextLine()) {
            //ignore the first line as it contains column headers
            if(firstLine) {
                firstLine = false;
                myReader.nextLine();
            }
            else{
                //create a crime object
                Crime currentCrime = new Crime(myReader.nextLine());
                //if head = null, add crime as new root
                if(head==null){
                    head = new TreeNode(currentCrime.x, currentCrime.y, currentCrime, 0);
                }
                //for all crimes insert in tree recursively
                else {
                    insert(head, currentCrime.x, currentCrime.y, currentCrime, head.orientation);
                }
                //increase the count of nodes after insertion
                this.countNodes++;
            }
        }
        myReader.close();
    }

    /**
     * Insert a crime data in tree
     * @param currentHead
     *      reference to node being processed during insertion
     * @param x
     *      the x-axis position of crime data
     * @param y
     *      the y-axis position of crime data
     * @param crime
     *      the  Crime object containing the crime data
     * @param orientation
     *      represents whether the node being processed cuts the x-axis or  y-axis
     * @precondition
     *      head pointer reference is not null i.e. root of tree is initialized
     * @postcondition
     *      the passed crime data is inserted into its corresponding position in 2-d plane (tree)
     **/
    /*
        Asymptotic Notations:
         Best Case:
            Big-O : O(1)
            Big-Omega: Ω(1)
            Big-Theta: Θ(1)
         Average/Worst Case:
            Big-O : O(log(n))
            Big-Omega: Ω(log(n))
            Big-Theta: Θ(log(n))
     */
    private void insert(TreeNode currentHead, Double x, Double y, Crime crime, int orientation){
        //if head is null, it implies node doesn't exist in  tree, create a new TreeNode
        if(currentHead==null){
            new TreeNode(x, y, crime, orientation);
            return;
        }
        //if node already exists add the crime to its listOfCrimes
        if(currentHead.x.equals(x) && currentHead.y.equals(y)){
            currentHead.addACrime(crime);
        }
        //if the point divides the plane vertically; compare x coordinates
        else if(currentHead.orientation==0){
            //if x coordinate is less than current node's x and node doesn't have a left child
            //add new node with this crime as left child
            if(x < currentHead.x && currentHead.left==null)
                currentHead.left = new TreeNode(x, y, crime, 1);
            //if x coordinate is less than current node's x and node has a left child
            //add new node with this crime to its left subtree recursively
            else if(x < currentHead.x)
                insert(currentHead.left, x, y, crime, 1);
            //if x coordinate is less than current node's x and node doesn't have a right child
            //add new node with this crime as right child
            else if(currentHead.right == null)
                currentHead.right = new TreeNode(x, y, crime, 1);
            //if x coordinate is less than current node's x and node has a right child
            //add new node with this crime to its right subtree recursively
            else
                insert(currentHead.right, x, y, crime, 1);
        }
        //if the point divides the plane horizontally; compare y coordinates
        else{
            //if y coordinate is less than current node's y and node doesn't have a left child
            //add new node with this crime as left child
            if(y < currentHead.y && currentHead.left==null)
                currentHead.left = new TreeNode(x, y, crime, 0);
            //if y coordinate is less than current node's y and node has a left child
            //add new node with this crime to its left subtree recursively
            else if(y < currentHead.y)
                insert(currentHead.left, x, y, crime, 0);
            //if y coordinate is less than current node's y and node doesn't have a right child
            //add new node with this crime as right child
            else if(currentHead.right == null)
                currentHead.right = new TreeNode(x, y, crime, 0);
            //if y coordinate is less than current node's y and node has a right child
            //add new node with this crime to its right subtree recursively
            else
                insert(currentHead.right, x, y, crime, 0);
        }
    }

    /**
     * Insert a crime data in tree
     * @param currentHead
     *      reference to node being processed during traversal
     * @precondition
     *  The 2d tree has been constructed.
     * @postcondition
     *  The 2d tree is displayed with an in-order
     *  traversal.
     **/
    /*
        Asymptotic Notations:
            Big-O : O(n)
            Big-Omega: Ω(n)
            Big-Theta: Θ(n)
     */
    public void inOrderPrint(TreeNode currentHead){
        //if the node being processed is null, return
        if(currentHead==null)
            return;
        //visit left subtree
        inOrderPrint(currentHead.left);
        //visit root
        currentHead.print();
        //visit right subtree
        inOrderPrint(currentHead.right);
    }

    /**
     * Insert a crime data in tree
     * @param currentHead
     *      reference to node being processed during traversal
     * @precondition
     *  The 2d tree has been constructed.
     * @postcondition
     *  The 2d tree is displayed with a pre-order
     *  traversal.
     **/
    /*
        Asymptotic Notations:
            Big-O : O(n)
            Big-Omega: Ω(n)
            Big-Theta: Θ(n)
     */
    public void preOrderPrint(TreeNode currentHead){
        //if the node being processed is null, return
        if(currentHead==null)
            return;
        //visit root
        currentHead.print();
        //visit left subtree
        preOrderPrint(currentHead.left);
        //visit right subtree
        preOrderPrint(currentHead.right);
    }

    /**
     * Insert a crime data in tree
     * @param currentHead
     *      reference to node being processed during traversal
     * @precondition
     *  The 2d tree has been constructed.
     * @postcondition
     *  The 2d tree is displayed with an post-order
     *  traversal.
     **/
    /*
        Asymptotic Notations:
            Big-O : O(n)
            Big-Omega: Ω(n)
            Big-Theta: Θ(n)
     */
    public void postOrderPrint(TreeNode currentHead){
        //if the node being processed is null, return
        if(currentHead==null)
            return;
        //visit left subtree
        postOrderPrint(currentHead.left);
        //visit right subtree
        postOrderPrint(currentHead.right);
        //visit root
        currentHead.print();
    }

    /**
     * Insert a crime data in tree
     * @param currentHead
     *      reference to node being processed during traversal
     * @precondition
     *  The 2d tree has been constructed.
     * @postcondition
     *  The 2d tree is displayed with a level-order
     *  traversal.
     * @note
     *  It uses a queue (implemented using SinglyLinkedList) to traverse through the different levels
     **/
    /*
        Asymptotic Notations:
            Big-O : O(n)
            Big-Omega: Ω(n)
            Big-Theta: Θ(n)
     */
    public void levelOrderPrint(TreeNode currentHead){
        Queue queue = new Queue();
        //add root to queue, first node to  be processed
        queue.enqueue(currentHead);
        while(!queue.isEmpty()){
            //process the front node in queue
            currentHead = (TreeNode) queue.dequeue();
            currentHead.print();
            //if current node has left child add its left child to queue
            if(currentHead.left !=  null)
                queue.addAtEnd(currentHead.left);
            //if current node has right child add its right child to queue
            if(currentHead.right !=  null)
                queue.addAtEnd(currentHead.right);
        }
    }

    /**
     * Insert a crime data in tree
     * @param currentHead
     *      reference to node being processed during traversal
     * @precondition
     *  The 2d tree has been constructed.
     * @postcondition
     *  The 2d tree is displayed with a reverse levelorder traversal.
     * @note
     *  It uses a queue (implemented using SinglyLinkedList) to traverse through the different levels
     *  and a stack to store (implemented using SinglyLinkedList) and print it in reverse order
     **/
    /*
        Asymptotic Notations:
            Big-O : O(n)
            Big-Omega: Ω(n)
            Big-Theta: Θ(n)
     */
    public void reverseLevelOrderPrint(TreeNode currentHead){
        //change stack to custom implemented class!
        Stack stack = new Stack();
        Queue queue = new Queue();
        //add root to queue, first node to  be processed
        queue.enqueue(currentHead);
        while(!queue.isEmpty()){
            //process the front node in queue
            currentHead = (TreeNode) queue.dequeue();
            //push current node in stack
            stack.push(currentHead);
            //if current node has left child add its left child to queue
            if(currentHead.left !=  null)
                queue.enqueue(currentHead.left);
            //if current node has right child add its right child to queue
            if(currentHead.right !=  null)
                queue.enqueue(currentHead.right);
        }
        //pop each element in stack and print it; this gives a reverse order traversal
        while(!stack.isEmpty()){
            TreeNode node = (TreeNode) stack.pop();
            node.print();
        }
    }

    /**
     * Insert a crime data in tree
     * @param x1
     *      x-coordinate of bottom-left point of the rectangle
     * @param y1
     *      y-coordinate of bottom-left point of the rectangle
     * @param x2
     *      x-coordinate of top-right point of the rectangle
     * @param y2
     *      y-coordinate of top-right point of the rectangle
     * @param current
     *      reference to the node being processed
     * @param currentCrimeList
     *      a ListOfCrimes containing crimes identified within the rectangle
     * @precondition
     *  The 2d tree has been constructed.
     * @postcondition
     *   A list of 0 or more crimes is returned. These
     *   crimes occurred within the rectangular range specified by the
     *   four parameters.
     **/
    /*
        Asymptotic Notations:
          Average Case:
            Big-O : O(R + log(n))       R -> dimensions of the targeted rectangle
            Big-Omega: Ω(R + log(n))
            Big-Theta: Θ(R + log(n))
          Worst Case:
            Big-O : O(R + SquareRoot(N))       R -> dimensions of the targeted rectangle
            Big-Omega: Ω(R + SquareRoot(N))
            Big-Theta: Θ(R + SquareRoot(N))
     */
    public ListOfCrimes findPointsInRange(double x1, double y1, double x2, double y2, TreeNode current, ListOfCrimes currentCrimeList) {
        //return the crime list of the node being processed is empty
        if(current==null)
            return currentCrimeList;

        //only count a node as being examined if it actually contains some crime data
        currentCrimeList.examinedNodes++;

        //if the point is contained within a rectangle; add crimes associated to it in the currentCrimeList
        if(isContainedWithinRectangle(x1, y1, x2, y2, current.x,  current.y))
            currentCrimeList.addFromCrimeList(current.listOfCrimes);

        //if the point divides the plane vertically; compare x coordinates
        if(current.orientation == 0){
            //if rectangle lies right of the splitting line, check only the right subtree
            if(Double.compare(x1, current.x) >= 0 && Double.compare(x2, current.x) >= 0)
                findPointsInRange(x1, y1, x2, y2, current.right, currentCrimeList);
            //if rectangle lies left of the splitting line, check only the left subtree
            else if(Double.compare(x1, current.x) <= 0 && Double.compare(x2, current.x) <= 0)
                findPointsInRange(x1, y1, x2, y2, current.left, currentCrimeList);
            //if the splitting line intersects the rectangle, check both children subtrees
            else{
                findPointsInRange(x1, y1, x2, y2, current.left, currentCrimeList);
                findPointsInRange(x1, y1, x2, y2, current.right, currentCrimeList);
            }
        }
        //if the point divides the plane horizontally; compare y coordinates
        else{
            //if rectangle lies above the splitting line, check only the right subtree
            if(Double.compare(y1, current.y) >= 0 && Double.compare(y2, current.y) >= 0)
                findPointsInRange(x1, y1, x2, y2, current.right, currentCrimeList);
            //if rectangle lies below the splitting line, check only the left subtree
            else if(Double.compare(y1, current.y) < 0 && Double.compare(y2, current.y) < 0)
                findPointsInRange(x1, y1, x2, y2, current.left, currentCrimeList);
            //if the splitting line intersects the rectangle, check both children subtrees
            else{
                findPointsInRange(x1, y1, x2, y2, current.left, currentCrimeList);
                findPointsInRange(x1, y1, x2, y2, current.right, currentCrimeList);
            }
        }
        return currentCrimeList;
    }

    /**
     * Helper function to determine if a point is contained within a rectangle
     **/
    private boolean isContainedWithinRectangle(double x1, double y1, double x2, double y2, double x, double y) {
        return x>=x1 && x<=x2 && y>=y1 && y<=y2;
    }

    /**
     * Insert a crime data in tree
     * @param x1
     *      x-coordinate of bottom-left point of the rectangle
     * @param y1
     *      y-coordinate of bottom-left point of the rectangle
     * @param current
     *      reference to the node being processed
     * @param currentNearestNeighbor
     *      represents the nearest neighbor found at any given point,
     *      initially contains null data
     * @precondition
     *  The 2d tree has been constructed.
     * @postcondition
     *   The nearest neighbor for a given point in 2-d plane is returned
     *   as a Neighbor object
     **/
    /*
        Asymptotic Notations:
          Average Case:
            Big-O : O(log(n))
            Big-Omega: Ω(log(n))
            Big-Theta: Θ(log(n))
          Worst Case:
            Big-O : O(n)
            Big-Omega: Ω(n)
            Big-Theta: Θ(n)
     */
    public Neighbor nearestNeighbor(double x1, double y1, TreeNode current, Neighbor currentNearestNeighbor){
        //if the node being processed is null return the currentNearestNeighbor
        if(current == null)
            return currentNearestNeighbor;

        //only count a node as being examined if it actually contains some crime data
        currentNearestNeighbor.examinedNodes++;

        //if distance between current point and point of interest is less than currentNearestNeighbor
        //update currentNearestNeighbor
        double distance = getDistanceBetweenTwoPoints(x1, y1, current.x, current.y);
        if(distance < currentNearestNeighbor.distance){
            currentNearestNeighbor.distance = distance;
            currentNearestNeighbor.node = current;
        }

        //if the point divides the plane vertically and point lies to the left; check left subtree
        if(current.orientation == 0 && x1 < current.x) {
            nearestNeighbor(x1, y1, current.left, currentNearestNeighbor);
            //if the line perpendicular to intersecting line is lesser than currentNearestNeighbor
            //explore right tree as well
            double perpendicular_distance_to_line = getDistanceBetweenTwoPoints(x1, y1, current.x, y1);
            if(perpendicular_distance_to_line <  currentNearestNeighbor.distance)
                nearestNeighbor(x1, y1,  current.right, currentNearestNeighbor);
        }
        //if the point divides the plane horizontally and point lies below; check left subtree
        else if(current.orientation == 1 && y1 < current.y){
            nearestNeighbor(x1, y1, current.left, currentNearestNeighbor);
            //if the line perpendicular to intersecting line is lesser than currentNearestNeighbor
            //explore right tree as well
            double perpendicular_distance_to_line = getDistanceBetweenTwoPoints(x1, y1, x1, current.y);
            if(perpendicular_distance_to_line <  currentNearestNeighbor.distance)
                nearestNeighbor(x1, y1,  current.right, currentNearestNeighbor);
        }
        //if the point divides the plane vertically and point lies to the right; check right subtree
        else if(current.orientation == 0 && x1 >= current.x) {
            nearestNeighbor(x1, y1, current.right, currentNearestNeighbor);
            //if the line perpendicular to intersecting line is lesser than currentNearestNeighbor
            //explore left tree as well
            double perpendicular_distance_to_line = getDistanceBetweenTwoPoints(x1, y1, current.x, y1);
            if(perpendicular_distance_to_line <  currentNearestNeighbor.distance)
                nearestNeighbor(x1, y1,  current.left, currentNearestNeighbor);
        }
        //if the point divides the plane horizontally and point lies above; check right subtree
        else if(current.orientation == 1 && y1 >= current.y) {
            nearestNeighbor(x1, y1, current.right, currentNearestNeighbor);
            //if the line perpendicular to intersecting line is lesser than currentNearestNeighbor
            //explore left tree as well
            double perpendicular_distance_to_line = getDistanceBetweenTwoPoints(x1, y1, x1, current.y);
            if(perpendicular_distance_to_line <  currentNearestNeighbor.distance)
                nearestNeighbor(x1, y1,  current.left, currentNearestNeighbor);
        }
        return currentNearestNeighbor;
    }

    /**
     * Helper function to calculate the distance between two points
     **/
    private double getDistanceBetweenTwoPoints(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
    }

    /**
     * Getter function to return countNodes value
     **/
    public int getCountNodes() {
        return countNodes;
    }

}
