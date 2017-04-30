import astar.Astar;
import astar.Node;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by w4pity on 4/30/17.
 */
public class Main {

    public static void main(String... str)
    {
        int[][] schema = {{1,1,1,1,1,1,1,1,1,1 },
                          {1,1,1,1,1,1,1,1,1,1 },
                          {1,1,1,1,1,1,1,1,1,1 },
                          {1,1,1,1,1,1,1,1,1,1 },
        {1,1,1,1,1,1,1,1,1,1 },
        {1,1,1,1,1,1,1,1,1,1 },
        {1,1,1,1,1,1,1,1,1,1 },
        {1,1,1,1,1,1,1,1,1,1 },
        {1,1,1,1,1,1,1,1,1,1 },
        {1,1,1,1,1,1,1,1,1,1 }};

        display(genMat());
        display(genMat());
        display(genMat());
        display(genMat());

    }

    public static ArrayList<ArrayList<Integer>> genMat()
    {
        ArrayList<ArrayList<Integer>> v = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            v.add(new ArrayList<Integer>());
            for (int j = 0; j < 150; j++) {
                Random rand = new Random();
                int r = rand.nextInt(2);

                if (r == 0)
                    v.get(i).add(1);
                else
                    v.get(i).add(100);
            }
        }
        return v;
    }

    public static void display(ArrayList<ArrayList<Integer>> schema)
    {
        ArrayList<ArrayList<Node>> map = new ArrayList<>();
        for (int i = 0; i < schema.size(); i++)
        {
            map.add(new ArrayList<Node>());
            for (int j = 0; j < schema.get(0).size(); j++) {
                map.get(i).add(new Node(i, j, schema.get(i).get(j)));
            }
        }

        Astar astar = new Astar();
        ArrayList<Integer[]> v = astar.Run(map.get(0).get(0), map.get(149).get(149), map);//TODO change size
        for (int i = 0; i < v.size(); i++) {
            schema.get(v.get(i)[0]).set(v.get(i)[1], 0);
        }
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(0).size(); j++) {
                if (schema.get(i).get(j) == 100)
                    System.out.print("#");
                else if (schema.get(i).get(j) == 0)
                    System.out.print("\033[32;1m0\033[31;0m");
                else
                    System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
    }
}
