package com.lzq.study.geektime.test.graph;

import java.util.LinkedList;

/**
 * 拓扑排序
 */
public class Topological {

    static class Graph {
        private int v; // 顶点的个数
        private LinkedList<Integer> adj[]; // 邻接表
        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i=0; i<v; ++i) {
                adj[i] = new LinkedList<>();
            }
        }
        public void addEdge(int s, int t) { // s 先于 t，边 s->t
            adj[s].add(t);
        }

        /**
         * kahn算法
         */
        public void topoSortByKahn(){
            int[] inDegree = new int[v];
            for (int i = 0; i < v; ++i){
                for (int j = 0; j < adj[i].size(); ++j){
                    int w = adj[i].get(j);
                    inDegree[w]++; // 计算每个点的入度
                }
            }

            LinkedList<Integer> queue = new LinkedList<>();
            for (int i = 0; i < v; ++i){
                if (inDegree[i] == 0) queue.add(i); // 统计入度=0的顶点
            }

            while (!queue.isEmpty()){
                int i = queue.remove();
                System.out.println("->" + i);
                for (int j = 0; j < adj[i].size(); ++j){
                    int k = adj[i].get(j);
                    inDegree[k]--;
                    if (inDegree[k] == 0) queue.add(k); //入度--，每次都统计入度为0的顶点
                }
            }
        }

        /**
         * DFS算法
         */
        public void topoSortByDFS(){
            LinkedList<Integer> inverseAdj[] = new LinkedList[v];
            for (int i = 0; i < v; ++i){
                inverseAdj[i] = new LinkedList<>();
            }
            for (int i = 0; i < v; ++i){
                for (int j = 0; j < adj[i].size(); ++j){
                    int w = adj[i].get(j);
                    inverseAdj[w].add(i);
                }
            }
            boolean[] visited = new boolean[v];
            for (int i = 0; i <v; ++i){
                if (visited[i] == false){
                    visited[i] = true;
                    dfs(i,inverseAdj,visited);
                }
            }

        }

        private void dfs(int vertex,LinkedList<Integer> inverseAdj[],boolean[] visited){
            for (int i = 0; i < inverseAdj[vertex].size(); ++i){
                int w = inverseAdj[vertex].get(i);
                if (visited[w] == true) continue;
                visited[w] = true;
                dfs(w,inverseAdj,visited);
            }
            System.out.println("->" + vertex);
        }

    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(2,1);
        graph.addEdge(2,3);
        graph.addEdge(2,4);
        graph.addEdge(3,5);
        graph.addEdge(4,5);

        System.out.println("===========Kahn算法==============");
        graph.topoSortByKahn();
        System.out.println("===========DFS算法==============");
        graph.topoSortByDFS();
    }


}
