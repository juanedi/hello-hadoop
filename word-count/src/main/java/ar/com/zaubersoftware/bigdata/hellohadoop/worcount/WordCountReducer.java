/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package ar.com.zaubersoftware.bigdata.hellohadoop.worcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Reducer de word count.  
 * 
 * 
 * @author Juan Edi
 * @since Sep 14, 2012
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    /** @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context) */
    protected void reduce(Text word, Iterable<IntWritable> appearances, 
                          Reducer<Text, IntWritable, Text, IntWritable>.Context ctx) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable appearance : appearances) {
            sum += appearance.get();
        }
        ctx.write(word, new IntWritable(sum));
    }
    
}
