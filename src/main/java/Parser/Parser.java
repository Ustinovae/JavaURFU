package Parser;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import dataStructure.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Parser implements ParserInterface {
    private final char separator;

    public Parser(){
        separator = ';';
    }

    public DataTable parseFileToDataTable(String fileName) {
        var table = new DataTable();
        table.setCourseName("Java. Основы программирования на РТФ.");
        List<String[]> entries = readFile(fileName);
        for (int i = 3; i < entries.get(0).length; i++) {
            if (!entries.get(0)[i].equals("")) {
                table.addNameTopic(entries.get(0)[i]);
            }
        }
        var size = 0;
        for (int i = 4; i < entries.get(1).length; i++)
            if (entries.get(1)[i].equals("ДЗ")) {
                table.addSizeTopic(size);
                size = 0;
            } else {
                size += 1;
                table.addNameTask(entries.get(1)[i]);
            }
        table.addSizeTopic(size);
        table.setMaxScores(parseLineToCourse(entries.get(2), table));

        for(int i = 3; i < entries.size(); i++){
            table.addStudent(parseLineToStudent(entries.get(i), table));
        }
        return table;
    }

    private Course parseLineToCourse(String[] words, DataTable table){
        var course = new Course(Integer.valueOf(words[2]));
        var i = 3;
            while (i < words.length) {
                var topic = new Topic(table.getTopicsNames().get(course.getTopics().size()));
                topic.setTotalScore(Integer.valueOf(words[i]));
                i += 1;
                for (int j = 0; j < table.getCountTasksInTopic().get(course.getTopics().size()); j++) {
                    topic.addTask(new Task(Integer.valueOf(words[i + j]),
                            table.getTasksNames().get(i-course.getTopics().size()-4 + j)));
                }
                course.addTopic(topic);
                i += table.getCountTasksInTopic().get(course.getTopics().size() - 1);
            }
        return course;
    }

    private StudentFromCSV parseLineToStudent(String[] words, DataTable table){
        String username = words[0];
        String academicGroup = words[1];
        var course = parseLineToCourse(words, table);
        return new StudentFromCSV(username, "developer", academicGroup, course);
    }

    private List<String[]> readFile(String fileName){
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(fileName);
        var parser = new CSVParserBuilder().withSeparator(separator).build();
        var reader = new CSVReaderBuilder(new InputStreamReader(resourceAsStream)).withCSVParser(parser).build();
        try {
            List<String[]> entries = reader.readAll();
            return entries;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
