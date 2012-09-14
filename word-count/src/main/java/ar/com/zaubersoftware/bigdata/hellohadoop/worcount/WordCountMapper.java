/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package ar.com.zaubersoftware.bigdata.hellohadoop.worcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Mapper de word count.  
 * 
 * 
 * @author Juan Edi
 * @since Sep 14, 2012
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    
    @Override
    protected final void map(
            final LongWritable key,
            final Text value,
            final Mapper<LongWritable, Text, Text, IntWritable>.Context ctx) throws IOException, InterruptedException {
        final String[] words = value.toString().split(" ");
        for (String word : words) {
            ctx.write(new Text(word), new IntWritable(1));
        }
    }

}
