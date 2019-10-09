package com.lzq.study.geektime.structure.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int v;//顶点个数
    private LinkedList<Integer> adj[];//邻接表
    private boolean found = false;

    public Graph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t){ // 无向图
        adj[s].add(t);
        adj[t].add(s);
    }

    public void addEdge2(int s,int t){
        adj[s].add(t);//s -> t
    }

    /**
     * 拓扑排序有向图 Kahn算法
     */
    public void topSortByKahn(){
        int[] inDegree = new int[v];//统计每个顶点的入度
        for (int i = 0; i < v; i++){
            for (int j = 0; j < adj[i].size(); ++j){
                int w = adj[i].get(j);
                inDegree[w]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; ++i){
            if (inDegree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()){
            int i = queue.remove();
            System.out.println("->"+i);
            for (int j = 0; j < adj[i].size();++j){
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) queue.add(k);
            }
        }
    }

    /**
     * 拓扑排序，DFS算法
     *
     */
    public void topSortByDFS(){
        LinkedList<Integer> inverseAdj[] = new LinkedList[v];
        for (int i = 0; i < v; ++i){
            inverseAdj[i] = new LinkedList<>();
        }
        for (int i = 0; i < v; ++i){
            for (int j=0;j<adj[i].size();++j){
                int w = adj[i].get(j);
                inverseAdj[w].add(i);
            }
        }
        boolean[] visited = new boolean[v];
        for (int i=0;i<v;++i){
            if (visited[i]==false){
                visited[i] = true;
                dfs(i,inverseAdj,visited);
            }
        }
    }
    private void dfs(int vertex,LinkedList<Integer> inverseAdj[],boolean[] visited){
        for (int i=0;i<inverseAdj[vertex].size();++i){
            int w = inverseAdj[vertex].get(i);
            if (visited[w] == true) continue;
            visited[w] = true;
            dfs(w,inverseAdj,visited);
        }
        System.out.println("->"+vertex);
    }

    /**
     * 寻找s到t的最短路径,广度搜索算法
     * @param s
     * @param t
     */
    public void bfs(int s,int t){
        if (s == t) return;
        boolean[] visited = new boolean[v];//是否访问过
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();//记录还存在临顶点未访问的顶点
        queue.add(s);
        int[] prev = new int[v];//记录每次访问顶点的上个顶点
        for (int i = 0; i < v; ++i){
            prev[i] = -1;
        }
        while (queue.size() != 0){
            int w = queue.poll();//queue作为起点
            for (int i = 0; i < adj[w].size(); ++i){ // 存储相邻顶点
                int q = adj[w].get(i);
                if (!visited[q]){//判断邻近顶点是否被访问过，如果没有
                    prev[q] = w;//记录是w来访问q顶点的
                    if (q == t){//如果q = 要查找的顶点则退出
                        print(prev,s,t); // 需要使用递归思想，以开始作为起点，倒叙打印出
                        return;
                    }
                    visited[q] = true;//表示q已经访问过
                    queue.add(q);//q作为s的临近顶点访问过，但是还需要访问q的所有邻近顶点
                }
            }
        }
    }

    /**
     * 深度搜索算法
     * @param s
     * @param t
     */
    public void dfs(int s, int t){
        this.found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; i++){
            prev[i] = -1;
        }
        recurDfs(s,t,visited,prev);
        print(prev,s,t);
    }

    /**
     * 递归思想不停去遍历未访问过的相邻节点
     * 如果相邻节点遍历完成则遍历自己
     * @param w
     * @param t
     * @param visited
     * @param prev
     */
    private void recurDfs(int w, int t, boolean[] visited, int[] prev){
        if (found == true) return;
        visited[w] = true;
        if (w == t){
            found = true;
            return;
        }
        for (int i=0; i < adj[w].size(); ++i){
            if (found == true) return;
            int q = adj[w].get(i);
            if (!visited[q]){
                prev[q] = w;
                recurDfs(q,t,visited,prev);
            }
        }
    }

    private void print(int[] prev, int s, int t){
        if (prev[t] != -1 && t != s){
            print(prev,s,prev[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(3,4);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,7);
        graph.bfs(0,6);
        System.out.println();
        graph.dfs(0,6);
        System.out.println();
        System.out.println("==============================");
        Graph graph2 = new Graph(7);
        graph2.addEdge2(0,2);
        graph2.addEdge2(2,4);
        graph2.addEdge2(4,6);
        graph2.addEdge2(5,6);
        graph2.addEdge2(0,1);
        graph2.addEdge2(1,3);
        graph2.addEdge2(5,1);
//        graph2.topSortByKahn();
        graph2.topSortByDFS();
    }
}
