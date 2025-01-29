package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//Question link:
// https://leetcode.com/discuss/interview-question/5838801/Uber-SDE-2-or-Phone-screen
public class Counter
{
    //seconds
    long window;
    HashMap<Integer, ArrayList<Long>> map;

    public Counter(long wsize)
    {
        map = new HashMap<>();
        window=wsize;
    }

    public void put(int x)
    {
        if(map.containsKey(x))
        {
            map.get(x).add(System.currentTimeMillis());
        }
        else
        {
            ArrayList<Long> li = new ArrayList<>();
            li.add(System.currentTimeMillis());
            map.put(x,li);
        }
    }

    protected void putTest(int x, long t)
    {
        if(map.containsKey(x))
        {
            map.get(x).add(t);
        }
        else
        {
            ArrayList<Long> li = new ArrayList<>();
            li.add(t);
            map.put(x,li);
        }
    }

    public int countAll()
    {
        int ct =0;
        long cutoff = System.currentTimeMillis()-(1000*window);
        for(int x: map.keySet())
        {
            int eligible = countAboveCutoffTime(cutoff,map.get(x));
            if(eligible>0)ct++;
        }
        return ct;
    }

    public int count(int x)
    {
       if(map.containsKey(x))
       {
           ArrayList<Long> li = map.get(x);
           long cutoff = System.currentTimeMillis()-(1000*window);
           return countAboveCutoffTime(cutoff,li);
        }
       else {
           return -1;
       }
    }

    protected int countTest(int x, long cutoff)
    {
        if(map.containsKey(x))
        {
            ArrayList<Long> li = map.get(x);
            //long cutoff = System.currentTimeMillis()-(1000*window);
            return countAboveCutoffTime(cutoff,li);
        }
        else {
            return -1;
        }
    }

    private int countAboveCutoffTime(long cutoff, ArrayList<Long> timedata)
    {
       long[] arr = new long[timedata.size()];
       for(int i=0;i<arr.length;i++) {
           arr[i] = timedata.get(i);
           System.out.print(arr[i]+" ");
       }
       System.out.println();
       int ind = Arrays.binarySearch(arr,cutoff);
       int n = arr.length;
       int ans=-1;
       if(ind >= 0)
       {
           ans = n-1-ind;
       }
       else
       {
           int mod = -1*ind;
           mod--;
           ans = n-mod;
       }
       return ans;
    }

}
