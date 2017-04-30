package astar;

import java.util.ArrayList;

/**
 * Created by w4pity on 4/30/17.
 */
public class Node {

    public int g;
    public int f;
    public int x;
    public int y;
    public Node parent = null;

    public Node(int x, int y, int p)
    {
        this.x = x;
        this.y = y;
        this.g = p;
    }
}
