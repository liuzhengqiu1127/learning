package com.lzq.study.geektime.test.array;

public class FixArray {

    private int size;
    private Object[] objects;
    private int count;

    public FixArray(int size)
    {
        this.size = size;
        this.objects = new Object[size];
        this.count = 0;
    }

    public boolean add(Object object)
    {
        if (count >= size) return false;
        this.count++;
        objects[count] = object;
        return true;
    }

    public boolean remove(Object object)
    {
        if (count <= 0) return false;
        for (int i = 0; i < count; i++){
            if (objects[i].equals(object)){
                move(i,count);
                this.count--;
                break;
            }
        }
        return true;
    }

    public void move(int i,int count){
        for (int j = i; j < count - 1; j++)
        {
            objects[j] = objects[j+1];
        }
        objects[count - 1] = null;
    }


}
