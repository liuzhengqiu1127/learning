package com.lzq.study.geektime.structure.graph;

import java.util.LinkedList;

/**
 * Dijkstra最短路径算法
 */
public class Dijkstra {
    private LinkedList<Edge> adj[]; // 邻接表
    private int v; // 顶点个数
    private Vertex[] vertices = new Vertex[v];//在构造函数中初始化

    public void addVetex(int id, int x, int y){
        vertices[id] = new Vertex(id,x,y);
    }

    public Dijkstra(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            this.adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t, int w) { // 添加一条边
        this.adj[s].add(new Edge(s, t, w));
    }

    private class Edge {
        public int sid; // 边的起始顶点编号
        public int tid; // 边的终止顶点编号
        public int w; // 权重
        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }
    // 下面这个类是为了 dijkstra 实现用的
    private class Vertex {
        public int id; // 顶点编号 ID
        public int dist; // 从起始顶点到这个顶点的距离
        //添加A*算法
        public int f; //新增:f(i) = g(i) + h(i)
        public int x,y; // 新增: 顶点在地图中的坐标(x,y)
        public Vertex(int id,int x,int y){
            this.id = id;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.dist = Integer.MAX_VALUE;
        }

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }
    // 因为 Java 提供的优先级队列，没有暴露更新数据的接口，所以我们需要重新实现一个
    private class PriorityQueue { // 根据 vertex.dist 构建小顶堆
        private Vertex[] nodes;//数组，从下标1开始存储数据
        private int n;//表示堆可以存储的最大数据个数
        private int count;//表示已经存储的数据个数
        public PriorityQueue(int v) {
            this.nodes = new Vertex[v + 1];
            this.nodes[0] = new Vertex(Integer.MAX_VALUE,Integer.MIN_VALUE);
            this.n = v;
            this.count = 0;
        }
        public void clear(){
            this.count = 0;
        }
        public Vertex poll() { // TODO: 留给读者实现...
            if (isEmpty()) return null;
            Vertex tmp = nodes[1];
            nodes[1] = nodes[count];
            --count;
            heapify(nodes,count,1);
            return tmp;
        }
        private void remove(Vertex vertex){
            if (isEmpty()) return ;
            int pos = 0;
            for (Vertex vertex1 : nodes){
                ++pos;
                if (vertex1.id == vertex.id) {
                    break;
                }
            }
            if (pos == count){
                --count;
                return;
            }
            nodes[pos] = nodes[count];
            --count;
            heapify(nodes,count,pos);
        }
        private void heapify(Vertex[] vertices,int n,int i){
            while (true){
                int maxPos = i;
                if (i*2 <= n && vertices[i].dist > vertices[i*2].dist) maxPos = i * 2;
                if (i*2+1 <= n && vertices[maxPos].dist > vertices[i*2+1].dist) maxPos = i*2 + 1;
                if (maxPos == i) break;
                Vertex tmp = vertices[i];
                vertices[i] = vertices[maxPos];
                vertices[maxPos] = tmp;
                i = maxPos;

            }
        }
        public void add(Vertex vertex) { // TODO: 留给读者实现...
            if (count >= n) return;//堆满了
            ++count;
            nodes[count] = vertex;
            int i = count;
            while (i/2 > 0 && nodes[i].f < nodes[i/2].f){//自下而上堆化
                Vertex tmp = nodes[i];
                nodes[i] = nodes[i/2];
                nodes[i/2] = tmp;
                i = i/2;
            }
        }
        // 更新结点的值，并且从下往上堆化，重新符合堆的定义。时间复杂度 O(logn)。
        public void update(Vertex vertex) { // TODO: 留给读者实现...
            remove(vertex);
            add(vertex);
        }
        public boolean isEmpty() { // TODO: 留给读者实现...
            if (this.count <= 0 ) return true;
            else return false;
        }
    }

    public void astar(int s,int t){ // 从顶点s 到顶点 t的路径
        int[] predecessor = new int[this.v]; // 用来还原路径
        //按照vertex的f值构建的小顶堆，而不是按照dist
        PriorityQueue queue = new PriorityQueue(this.v);
        boolean[] inqueue = new boolean[this.v]; //标记是否进入过队列
        vertices[s].dist = 0;
        vertices[s].f = 0;
        queue.add(vertices[s]);
        inqueue[s] = true;
        while (!queue.isEmpty()){
            Vertex minVertex = queue.poll(); //取堆顶元素并删除
            for (int i = 0; i < adj[minVertex.id].size(); ++i){
                Edge edge = adj[minVertex.id].get(i);
                Vertex nextVertex = vertices[edge.tid];
                if (minVertex.dist + edge.w < nextVertex.dist){ // 更新next的dist,f
                    nextVertex.dist = minVertex.dist + edge.w;
                    nextVertex.f = nextVertex.dist + hManhattan(nextVertex,vertices[t]);
                    predecessor[nextVertex.id] = minVertex.id;
                    if (inqueue[nextVertex.id] == true){
                        queue.update(nextVertex);
                    }else {
                        queue.add(nextVertex);
                        inqueue[nextVertex.id] = true;
                    }
                }
                if (nextVertex.id == t){
                    queue.clear();
                    break;
                }

            }

        }

        System.out.println(s);
        print(s,t,predecessor);


    }

    public void dijkstra(int s, int t) { // 从顶点 s 到顶点 t 的最短路径
        int[] predecessor = new int[this.v]; // 用来还原最短路径
        Vertex[] vertexes = new Vertex[this.v];
        for (int i = 0; i < this.v; ++i) {
            vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        PriorityQueue queue = new PriorityQueue(this.v);// 小顶堆
        boolean[] inqueue = new boolean[this.v]; // 标记是否进入过队列
        vertexes[s].dist = 0;
        queue.add(vertexes[s]);
        inqueue[s] = true;
        while (!queue.isEmpty()) {
            Vertex minVertex= queue.poll(); // 取堆顶元素并删除
            if (minVertex.id == t) break; // 最短路径产生了
            for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                Edge e = adj[minVertex.id].get(i); // 取出一条 minVetex 相连的边
                Vertex nextVertex = vertexes[e.tid]; // minVertex-->nextVertex
                if (minVertex.dist + e.w < nextVertex.dist) { // 更新 next 的 dist
                    nextVertex.dist = minVertex.dist + e.w;
                    predecessor[nextVertex.id] = minVertex.id;
                    if (inqueue[nextVertex.id] == true) {
                        queue.update(nextVertex); // 更新队列中的 dist 值
                    } else {
                        queue.add(nextVertex);
                        inqueue[nextVertex.id] = true;
                    }
                }
            }
        }
        // 输出最短路径
        System.out.print(s);
        print(s, t, predecessor);
    }

    private void print(int s, int t, int[] predecessor) {
        if (s == t) return;
        print(s, predecessor[t], predecessor);
        System.out.print("->" + t);
    }

    private int hManhattan(Vertex v1, Vertex v2){ // Vertex 表示顶点
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
    }
}
