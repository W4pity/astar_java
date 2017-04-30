package astar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by w4pity on 4/30/17.
 */
public class Astar {
    public Astar()
    {}

    public boolean egal(Node current, Node goal)
    {
        return current.x == goal.x && current.y == goal.y;
    }

    public void display_debug(ArrayList<ArrayList<Integer>> map)
    {
        return;/*
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(0).size(); j++) {
                if (map.get(i).get(j) == 0)
                    System.out.print(".");
                if (map.get(i).get(j) == 3)
                    System.out.print("0");
            }
            System.out.println("");
        }

        System.out.println("");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public ArrayList<Integer[]> Run(Node start, Node goal, ArrayList<ArrayList<Node>> map)
    {
        ArrayList<Node> open_liste = new ArrayList<>();
        ArrayList<Node> close_list = new ArrayList<>();
        open_liste.add(start);
        ArrayList<ArrayList<Integer>> debug_map= new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            debug_map.add(new ArrayList<Integer>());
            for (int j = 0; j < map.get(0).size(); j++) {
                debug_map.get(i).add(0);
            }
        }


        while (open_liste.size() > 0)
        {
            display_debug(debug_map);
            Node current = open_liste.get(0);
            for (Node e :open_liste) {
                if (e.f < current.f)
                    current = e;
            }
            if (egal(current, goal))
            {
                return construct_path(current);
            }
            for (Node e: open_liste) {
                if (egal(e, current))
                {
                    open_liste.remove(e);
                    break;
                }
            }

            debug_map.get(current.x).set(current.y, 3);
            close_list.add(current);
            for (Integer[] neighborint : neighbors(current, map)) {
                Node neighbor = map.get(neighborint[0]).get(neighborint[1]);
                if (close_list.contains(neighbor))
                    continue;
                if (neighbor.f < current.f || !open_liste.contains(neighbor))
                {
                    neighbor.f = neighbor.g + heuristic(neighbor, goal);
                    neighbor.parent = current;
                }
                if (!open_liste.contains(neighbor))
                {
                    open_liste.add(neighbor);

                }
            }
        }

        return new ArrayList<Integer[]>();
    }

    public int heuristic(Node s, Node g)
    {
        return Math.abs(s.x - g.x) + Math.abs(s.y -g.y);
    }

    public ArrayList<Integer[]> neighbors(Node n, ArrayList<ArrayList<Node>> map)
    {
        ArrayList<Integer[]> v = new ArrayList<>();
        if (n.x-1 >= 0 && n.y-1 >= 0)
            v.add(new Integer[]{n.x-1, n.y-1});
        if (n.x-1 >= 0)
            v.add(new Integer[]{n.x-1, n.y});
        if (n.x-1 >= 0 && n.y+1 < map.get(0).size())
            v.add(new Integer[]{n.x-1, n.y+1});
        if (n.y-1 >= 0)
            v.add(new Integer[]{n.x, n.y-1});
        if (n.y+1 < map.get(0).size())
            v.add(new Integer[]{n.x, n.y+1});
        if (n.x+1 < map.size() && n.y-1 >= 0)
            v.add(new Integer[]{n.x+1, n.y-1});
        if (n.x+1 < map.size())
            v.add(new Integer[]{n.x+1, n.y});
        if (n.x+1 <map.size() && n.y+1 < map.get(0).size())
            v.add(new Integer[]{n.x+1, n.y+1});
        return v;
    }

    ArrayList<Integer[]> construct_path(Node g)
    {
        Node tmp = g;
        ArrayList<Integer[]> v = new ArrayList<>();
        while (tmp != null)
        {
            v.add(new Integer[]{tmp.x, tmp.y});
            tmp = tmp.parent;
        }
        Collections.reverse(v);
        return v;
    }



}
