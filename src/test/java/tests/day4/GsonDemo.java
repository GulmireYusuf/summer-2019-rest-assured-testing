package tests.day4;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import pojos.Job;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonDemo {
   // read a file
    @Test
    public void deserializeThis() throws FileNotFoundException {
        // converts the input to java object,objects to output
        Gson gson = new Gson();
        //file that we want to read
        FileReader reader = new FileReader("src/test/resources/it_job.json");

        //fromJson--> takes json input and converts to object
        Job job = gson.fromJson(reader, Job.class);
        System.out.println(job);
    }
    //write a file
    @Test
    public void serializeThis() throws IOException {
        // te converter
        Gson gson = new Gson();
        //java object we want to serialize
        Job job = new Job("TE", "Teacher", 100, 1000);
        //class that writes file
        FileWriter output = new FileWriter("src/test/resources/te_job.json");
        //toJson-->serialization is happening here
        gson.toJson(job, output);
        //write into the file
        output.flush();
        output.close();

    }
}