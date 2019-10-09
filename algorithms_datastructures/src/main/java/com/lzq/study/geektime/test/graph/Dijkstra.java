package com.lzq.study.geektime.test.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 最短路径算法
 */
public class Dijkstra {

    static class Graph{
        private LinkedList<Edge> adj[];
        private int v;

        public Graph(int v){
            this.v = v;
            this.adj = new LinkedList[v];
            for (int i = 0; i < v; ++i){
                this.adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s,int t,int w){
            this.adj[s].add(new Edge(s,t,w));
        }

        /**
         * 从s 到 t的最短路径
         * @param s
         * @param t
         */
        public void dijkstra(int s,int t){
            int[] predecessor = new int[this.v];
            Vertex[] vertices = new Vertex[v];
            for (int i = 0; i < v; ++i){
                vertices[i] = new Vertex(i,Integer.MAX_VALUE);
            }

            PriorityQueue<Vertex> queue = new PriorityQueue(this.v);
            boolean[] inqueue = new boolean[v];
            vertices[s].dist = 0;
            queue.add(vertices[s]);
            inqueue[s] = true;

            while (!queue.isEmpty()){
                Vertex minVertex = queue.poll();
                if (minVertex.id == t) break;
                for (int i = 0; i < adj[minVertex.id].size(); ++i){
                    Edge edge = adj[minVertex.id].get(i); // 取出一条minVetex相连的边
                    Vertex next = vertices[edge.tid];
                    if (minVertex.dist + edge.w < next.dist){ // 得到最小的next.dist
                        next.dist = minVertex.dist + edge.w;
                        predecessor[next.id] = minVertex.id;
                        if (inqueue[next.id] == true){
                            queue.remove(next);
                            queue.add(next);
                        }else {
                            queue.add(next);
                            inqueue[next.id] = true;
                        }
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
        public Vertex(int id, int dist){
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Object o) {
            Vertex vertex = (Vertex) o;
            return this.id - vertex.id;
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
        graph.dijkstra(0,5);
    }
}
