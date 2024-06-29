package andrew.cmu.edu.abhineec;

import java.util.Scanner;

/******************************************************************************
 * TwoDTreeDriver class is used to demonstrate exercises mentioned in
 * Project 2's description
 ******************************************************************************/

public class TwoDTreeDriver {

    /**
     * Main function demonstrating program execution
     **/
    public static void main(String[] args) throws Exception {
        //Driver code for TwoDTree class
        System.out.println("Hello and welcome!\n");
        System.out.println("This assignment was submitted by:\nName: Abhineet Chaudhary\nAndrewId: abhineec\nCourse: 95-771 A Fall 2023\n\n");

        String userDirectory = System.getProperty("user.dir");
        System.out.println("Please ensure your project directory is correct. It should be the absolute path till ...Project2/TwoDTree-Project \nProject Directory: " + userDirectory + "\n");

        TwoDTree twoDTree = new TwoDTree(userDirectory+"/TwoDTree-Project/CrimeLatLonXY.csv");
        System.out.println("Crime file loaded into 2d tree with " + twoDTree.getCountNodes() + " records.\n");
        boolean USER_WANTS_TO_CONTINUE = true;
        while(USER_WANTS_TO_CONTINUE){
            System.out.println("What would you like to do?\n1: inorder\n2: preorder\n3: levelorder\n4: postorder\n5: reverseLevelOrder\n6: search for points within rectangle\n7: search for nearest neighbor\n8: quit");
            Scanner userScanner = new Scanner(System.in);
            Integer userInput = Integer.valueOf(userScanner.nextLine());
            switch(userInput){
                case 1:
                    twoDTree.inOrderPrint(twoDTree.head);
                    System.out.println();
                    break;
                case 2:
                    twoDTree.preOrderPrint(twoDTree.head);
                    System.out.println();
                    break;
                case 3:
                    twoDTree.levelOrderPrint(twoDTree.head);
                    System.out.println();
                    break;
                case 4:
                    twoDTree.postOrderPrint(twoDTree.head);
                    System.out.println();
                    break;
                case 5:
                    twoDTree.reverseLevelOrderPrint(twoDTree.head);
                    System.out.println();
                    break;
                case 6:
                    String coordinates = userScanner.nextLine();
                    double x1 = Double.parseDouble(coordinates.split(" ")[0]);
                    double y1 = Double.parseDouble(coordinates.split(" ")[1]);
                    double x2 = Double.parseDouble(coordinates.split(" ")[2]);
                    double y2 = Double.parseDouble(coordinates.split(" ")[3]);
                    TreeNode localHead = twoDTree.head;
                    ListOfCrimes crimesDetectedWithinRectangle = new ListOfCrimes();
                    twoDTree.findPointsInRange(x1, y1, x2, y2, localHead, crimesDetectedWithinRectangle);
                    System.out.println("Searching for points within ("+x1+","+y1+")"+" ("+x2+","+y2+")\n");
                    System.out.println("Examined " + crimesDetectedWithinRectangle.examinedNodes + " nodes during search.");
                    System.out.println("Found " + crimesDetectedWithinRectangle.listOfCrimes.countNodes() + " crimes.\n");
                    System.out.println(crimesDetectedWithinRectangle.toString());
                    System.out.println("The crime data has been written to " + crimesDetectedWithinRectangle.toKML() + " and will be viewable in Google Earth Pro.\n");
                    break;
                case 7:
                    coordinates = userScanner.nextLine();
                    x1 = Double.parseDouble(coordinates.split(" ")[0]);
                    y1 = Double.parseDouble(coordinates.split(" ")[1]);
                    Neighbor neighbor = new Neighbor(Double.MAX_VALUE, null);
                    TreeNode localHead2 = twoDTree.head;
                    twoDTree.nearestNeighbor(x1, y1, localHead2, neighbor);
                    if(neighbor.node != null){
                        System.out.println("Looked at "+ neighbor.examinedNodes + " nodes in tree. Found the nearest crime at:");
                        neighbor.node.listOfCrimes.print();
                        System.out.println();
                    }
                    else
                        System.out.println("Couldn't identify nearest neighbor for points.\n");
                    break;
                default:
                    System.out.println("Thank you for exploring Pittsburgh crimes in the 1990’s.\n");
                    USER_WANTS_TO_CONTINUE = false;
                    break;
            }
        }
    }

}
