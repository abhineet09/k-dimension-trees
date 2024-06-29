package andrew.cmu.edu.abhineec;

/******************************************************************************
 * Neighbor class is used to track the nearest neighbor to a point in 2-d plane
 * attribute distance represents the distance between a point in tree and
 * its nearest neighbor
 * attribute node represents the nearest neighbor node in tree
 * attribute examinedNodes is used to track explored nodes while searching for
 * the nearest neighbor of a point
 ******************************************************************************/
public class Neighbor {
    double distance;
    TreeNode node;
    int examinedNodes;

    /**
     * Initialize a Neighbor object
     * @postcondition
     *   An empty Neighbor object is initialized
     *   with passed values
     **/
    public Neighbor(double distance, TreeNode node){
        this.distance = distance;
        this.node = node;
        this.examinedNodes = 0;
    }
}
