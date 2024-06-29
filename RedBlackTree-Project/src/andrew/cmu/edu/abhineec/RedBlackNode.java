package andrew.cmu.edu.abhineec;
public class RedBlackNode {
    public static final int RED = 0;
    public static final int BLACK = 1;
    private int key;
    private Integer index;
    private int color;
    private RedBlackNode p;
    private RedBlackNode lc;
    private RedBlackNode rc;

    public RedBlackNode(int key, int index, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
        this.key = key;
        this.index = index;
        this.color = color;
        this.p = p;
        this.lc = lc;
        this.rc = rc;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        if(this.key != -1)
            this.color = color;
    }

    public RedBlackNode getP() {
        return p;
    }

    public void setP(RedBlackNode p) {
        this.p = p;
    }

    public RedBlackNode getLc() {
        return lc;
    }

    public void setLc(RedBlackNode lc) {
        this.lc = lc;
    }

    public RedBlackNode getRc() {
        return rc;
    }

    public void setRc(RedBlackNode rc) {
        this.rc = rc;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
