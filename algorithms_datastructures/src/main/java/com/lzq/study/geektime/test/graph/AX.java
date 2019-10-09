package com.lzq.study.geektime.test.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * A* 算法
 *
 * Dijkstra 是要找到最短路径才结束
 *
 * A* 算法 平衡距离和权重
 * 只需要找到一条路径就结束
 */
public class AX {

    static class Graph{
        private int v;
        private LinkedList<Edge> adj[];
        private Vertex[] vertices ;

        public Graph(int v){
            this.v = v;
            this.adj = new LinkedList[v];
            for (int i = 0; i < v; ++i){
                this.adj[i] = new LinkedList<>();
            }
            this.vertices = new Vertex[this.v];
        }

        public void addVertex(int id, int x, int y){
            vertices[id] = new Vertex(id,x,y);
        }

        public void addEdge(int s,int t,int w){
            this.adj[s].add(new Edge(s,t,w));
        }

        int hManhattan(Vertex v1, Vertex v2) { // Vertex 表示顶点，后面有定义
            return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
        }

        public void astar(int s,int t){
            int[] predecessor = new int[this.v];
            PriorityQueue<Vertex> queue = new PriorityQueue<>(v);
            boolean[] inqueue = new boolean[this.v];
            vertices[s].dist = vertices[s].f = 0;//出发点
            queue.add(vertices[s]);
            inqueue[s] = true;

            while (!queue.isEmpty()){
                Vertex minVertex = queue.poll();
                for (int i = 0; i < adj[minVertex.id].size(); i++){ // 出发点临近边
                    Edge e = adj[minVertex.id].get(i);
                    Vertex next = vertices[e.tid]; // 临近点
                    if (minVertex.dist + e.w < next.dist){
                        next.dist = minVertex.dist + e.w;
                        next.f = next.dist + hManhattan(next,vertices[t]); // 距离 + 权重
                        predecessor[next.id] = minVertex.id;
                        if (inqueue[next.id] == true){
                            queue.remove(next);
                            queue.add(next);
                        }else {
                            queue.add(next);
                            inqueue[next.id] = true;
                        }
                    }
                    if (next.id == t){ // 遍历到终点即结束
                        queue.clear();
                        break;
                    }
                }
            }

            System.out.println(s);
            print(s,t,predecessor);

        }

        private void print(int s,int t,int[] predecessor){
            if (s == t) return;
            print(s,predecessor[t],predecessor);
            System.out.println("->" + t);
        }
    }

    static class Edge{
        public int sid;
        public int tid;
        public int w;
        public Edge(int sid,int tid,int w){
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    static class Vertex implements Comparable{
        public int id;
        public int dist;
        public int f;
        public int x, y;
        public Vertex(int id, int x, int y){
            this.id = id;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.dist = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(Object o) {
            Vertex vertex = (Vertex) o;
            return this.f - vertex.f;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0,1,10);
        graph.addEdge(0,4,15);
        graph.addEdge(1,2,15);
        graph.addEdge(1,3,2);
        graph.addEdge(3,2,1);
        graph.addEdge(2,5,5);
        graph.addEdge(3,5,12);
        graph.addEdge(4,5,10);

        graph.addVertex(0,0,0);
        graph.addVertex(1,2,2);
        graph.addVertex(2,5,3);
        graph.addVertex(3,3,1);
        graph.addVertex(4,2,-2);
        graph.addVertex(5,7,0);

        graph.astar(0,5);

    }
}
