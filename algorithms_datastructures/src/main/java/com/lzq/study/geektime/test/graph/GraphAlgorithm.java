package com.lzq.study.geektime.test.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的两种常用算法：广度优先搜索算法 和 深度优先搜索算法
 */
public class GraphAlgorithm {

    public static void main(String[] args) {
        Graph graph = new Graph(9);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(3,4);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,7);
        graph.bfs(0,6);
        graph.dfs(0,6);
    }

    static class Graph{
        int v;
        LinkedList<Integer> adj[];
        boolean found = false;

        public Graph(int v){
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++){
                adj[i] = new LinkedList<>();
            }
        }
        public void addEdge(int s,int t){
            adj[s].add(t);
            adj[t].add(s);
        }
        /**
         * 广度优先搜索算法 s -> t
         * @param s
         * @param t
         */
        public void bfs(int s,int t){
            if (s == t) return;
            boolean[] visited = new boolean[v];
            visited[s] = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(s);
            int[] prev = new int[v];
            for (int i = 0; i < v; ++i) prev[i] = -1;
            while (queue.size() != 0){
                int w = queue.poll();
                for (int i = 0; i < adj[w].size(); ++i){
                    int q = adj[w].get(i);
                    if (!visited[q]){
                        prev[q] = w;
                        if (q == t){
                            print(prev,s,t);
                            return;
                        }
                        visited[q] = true;//标记q已经访问
                        queue.add(q);
                    }
                }
            }
        }

        private void print(int[] prev, int s, int t){
            if (prev[t] != -1 && t != s){
                print(prev,s,prev[t]);
            }
            System.out.println(t + " ");
        }

        /**
         * 深度优先搜索
         * @param s
         * @param t
         */
        public void dfs(int s, int t){
            found = false;
            boolean[] visited = new boolean[v];
            int[] prev = new int[v];
            for (int i=0;i<v;i++){
                prev[i] = -1;
            }
            recurDfs(s,t,visited,prev);
            print(prev,s,t);
        }

        private void recurDfs(int w,int t,boolean[] visited, int[] prev){
            if (found==true) return;
            visited[w] = true;
            if (w == t) {
                found = true;
                return;
            }
            for (int i=0;i < adj[w].size();i++){
                int q = adj[w].get(i);
                if (!visited[q]){
                    prev[q] = w;
                    recurDfs(q,t,visited,prev);
                }
            }
        }

    }

}
