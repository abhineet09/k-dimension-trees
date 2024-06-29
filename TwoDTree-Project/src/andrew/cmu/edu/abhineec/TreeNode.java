package andrew.cmu.edu.abhineec;

/******************************************************************************
 * TreeNode represents any point in a 2-d plane where
 * attribute x denotes its  position on x-axis and
 * attribute y denotes its  position on y-axis.
 * attribute orientation is used  to construct a 2-d tree where
 *      0 represents vertical orientation (point cuts the plane across x-axis)
 *      1 represents horizontal orientation (point cuts the plane across y-axis)
 * attribute listOfCrimes represents a list of crimes held at a 2-d point
 * attribute left represents its left child
 * attribute right represents its right child
 ******************************************************************************/

public class TreeNode {
    Double x;
    Double y;
    /*
        0 implies vertical orientation
        1 implies horizontal orientation
     */
    int orientation;
    ListOfCrimes listOfCrimes;
    TreeNode left;
    TreeNode right;

    /**
     * Initialize a TreeNode object
     * @postcondition
     *   An empty TreeNode object is initialized
     *   with passed values
     **/
    public TreeNode(Double i, Double j, Crime crime, int orientation){
        this.x = i;
        this.y = j;
        this.listOfCrimes = new ListOfCrimes();
        this.listOfCrimes.addCrime(crime);
        this.orientation = orientation;
        this.left=null;
        this.right=null;
    }

    /**
     * Writes crimes at this 2-d point in the list to console
     * @postcondition
     *   All crimes occurring at this point is printed on the screen
     **/
    /*
        Asymptotic Notations:
            Big-O : O(1)
            Big-Omega: Ω(1)
            Big-Theta: Θ(1)
     */
    public void print(){
        this.listOfCrimes.print();
    }

    /**
     * Adds a new crime in the listOfCrimes
     * @postcondition
     *   A new crime has been added to listOfCrimes
     **/
    /*
        Asymptotic Notations:
            Big-O : O(1)
            Big-Omega: Ω(1)
            Big-Theta: Θ(1)
     */
    public void addACrime(Crime c){
        this.listOfCrimes.addCrime(c);
    }
}
