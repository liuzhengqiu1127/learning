package com.lzq.study.geektime.test.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * 图的表示方法
 */
public class GraphStruct {

    /**
     * 邻接矩阵表示的图
     */
    static class GraphArr{
        int v; //顶点个数
        int[][] adj; // 邻接矩阵

        public GraphArr(int v){
            this.v = v;
            adj = new int[v][v];
            for (int i = 0; i < v; ++i){
                for (int j = 0;j < v;++j){
                    adj[i][j] = 0;
                }
            }
        }

        /**
         *  邻接矩阵无向图
         * @param s 顶点s
         * @param t 顶点t
         */
        public void addUnDirectEdge(int s,int t){
            adj[s][t] = adj[t][s] = 1;
        }

        /**
         *  邻接矩阵带权无向图
         * @param s
         * @param t
         * @param weight
         */
        public void addWeightUnDirectEdge(int s,int t,int weight){
            adj[s][t] = adj[t][s] = weight;
        }

        /**
         * 邻接矩阵有向图
         * @param s 顶点s (s -> t)
         * @param t 顶点t
         */
        public void addDirectEdge(int s,int t){
            adj[s][t] = 1;
        }

        /**
         *  邻接矩阵带权有向图
         * @param s 顶点s (s -> t)
         * @param t 顶点t
         * @param weight
         */
        public void addWeightDirectEdge(int s,int t,int weight){
            adj[s][t] = weight;
        }
    }

    /**
     * 邻接表 表示的图
     */
    static class GraphLink{
        int v; // 顶点个数
        Map<Integer,Integer> adj[];
        public GraphLink(int v){
            this.v = v;
            adj = new HashMap[v];
            for (int i = 0; i < v; i++){
                adj[i] = new HashMap<>();
            }
        }

        /**
         *  邻接矩阵无向图
         * @param s 顶点s
         * @param t 顶点t
         */
        public void addUnDirectEdge(int s,int t){
            adj[s].put(t,1);
            adj[t].put(s,1);
        }

        /**
         *  邻接矩阵带权无向图
         * @param s
         * @param t
         * @param weight
         */
        public void addWeightUnDirectEdge(int s,int t,int weight){
            adj[s].put(t,weight);
            adj[t].put(s,weight);
        }

        /**
         * 邻接矩阵有向图
         * @param s 顶点s (s -> t)
         * @param t 顶点t
         */
        public void addDirectEdge(int s,int t){
            adj[s].put(t,1);
        }

        /**
         *  邻接矩阵带权有向图
         * @param s 顶点s (s -> t)
         * @param t 顶点t
         * @param weight
         */
        public void addWeightDirectEdge(int s,int t,int weight){
            adj[s].put(t,weight);
        }


    }

}
