package andrew.cmu.edu.abhineec;

public class RedBlackTree {
    private RedBlackNode root;

    private RedBlackNode nil;

    private int countNodes = 0;

    public RedBlackTree(){
        this.nil = new RedBlackNode(-1, -1, RedBlackNode.BLACK, null, null, null);
        this.root = this.nil;
    }

    public int insert(int key){
        int keyIndexInTree = find(key);
        if(keyIndexInTree == -1) {
            keyIndexInTree = this.countNodes++;
            RedBlackNode z = new RedBlackNode(key, keyIndexInTree, RedBlackNode.RED, null, null, null);
            RedBlackNode y = this.nil;
            RedBlackNode x = this.root;
            while (x != this.nil) {
                y = x;
                if (z.getKey()< x.getKey())
                    x = x.getLc();
                else
                    x = x.getRc();
            }
            z.setP(y);
            if (y == this.nil)
                this.root = z;
            else if (z.getKey() < y.getKey())
                y.setLc(z);
            else
                y.setRc(z);
            z.setLc(this.nil);
            z.setRc(this.nil);
            z.setColor(RedBlackNode.RED);
            RBInsertFixup(z);
        }
        return keyIndexInTree;
    }

    public int find(int key){
        RedBlackNode iterator = this.root;
        while(iterator != this.nil){
            if(iterator.getKey() == key)
                return iterator.getIndex();
            else if(iterator.getKey() > key)
                iterator = iterator.getLc();
            else
                iterator = iterator.getRc();
        }
        return -1;
    }

    public void  RBInsertFixup(RedBlackNode z){
        while(z.getP().getColor() == RedBlackNode.RED){
            if(z.getP() == z.getP().getP().getLc()){
                RedBlackNode y = z.getP().getP().getRc();
                if(y.getColor() == RedBlackNode.RED){
                    z.getP().setColor(RedBlackNode.BLACK);
                    y.setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    z = z.getP().getP();
                }
                else{
                    if(z == z.getP().getRc()){
                        z = z.getP();
                        leftRotate(z);
                    }
                    z.getP().setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    rightRotate(z.getP().getP());
                }
            }
            //if z.parent is a right child of z.grandparent
            else {
                RedBlackNode y = z.getP().getP().getLc();
                //if z has an uncle
                if(y.getColor() == RedBlackNode.RED){
                    z.getP().setColor(RedBlackNode.BLACK);
                    y.setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    z = z.getP().getP();
                }
                //if z doesn't have an uncle
                else{
                    //if z is a left child of its parent
                    if(z == z.getP().getLc()){
                        z = z.getP();
                        rightRotate(z);
                    }
                    z.getP().setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    leftRotate(z.getP().getP());
                }
            }
        }
        this.root.setColor(RedBlackNode.BLACK);
    }

    public void leftRotate(RedBlackNode x){
        if(x.getRc() != this.nil && this.root.getP() == this.nil){
            RedBlackNode y = x.getRc();

            x.setRc(y.getLc());
            y.getLc().setP(x);
            y.setP(x.getP());

            if(x.getP() == this.nil)
                this.root = y;
            else
            if(x==x.getP().getLc())
                x.getP().setLc(y);
            else
                x.getP().setRc(y);

            y.setLc(x);
            x.setP(y);
        }
    }

    public void rightRotate(RedBlackNode x){
        RedBlackNode y =x.getLc();
        x.setLc(y.getRc());
        y.getRc().setP(x);
        y.setP(x.getP());

        if(x.getP() == this.nil) this.root = y;
        else
        if(x == x.getP().getLc())
            x.getP().setLc(y);
        else
            x.getP().setRc(y);

        y.setRc(x);
        x.setP(y);
    }

    public void inOrderTraversal(){
        inOrderTraversal(this.root);
        System.out.println();
    }

    private void inOrderTraversal(RedBlackNode current){
        if(current == this.nil)
            return;
        inOrderTraversal(current.getLc());
        String color = current.getColor()==RedBlackNode.RED?"RED":"BLACK";
        System.out.println(current.getKey()+", "+color);
        inOrderTraversal(current.getRc());
    }

    public static void main(String[] args){
        RedBlackTree redBlackTree = new RedBlackTree();
//        int[] arr = {1, 2, 3, 4, 10, 14, 7 , 6 , 12};
        int[] arr = {3, 1, 5, 7, 6, 8, 9 , 10};
//        int[] arr = {10, 85, 15, 70, 20, 60, 30, 50, 65, 80, 90, 40, 5, 55, };
//        String[] arr = {"S3", "S1", "S5", "S7", "S6", "S8", "S9", "S10"};
//        String[] arr = {"S1", "S2", "S3", "S4", "S10", "S14", "S7", "S6", "S12"};
        for(int x : arr){
            redBlackTree.insert(x);
        }
        redBlackTree.inOrderTraversal();


    }
}
