import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StudentList {
    public static String studentNames;
    public static Constants constants = new Constants();
    public static String names[];

    public static void Reader(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.FILE_NAME)));
            studentNames = bufferedReader.readLine();
            names = studentNames.split(constants.SPLIT);
        }catch (Exception e){

        }
    }
    
    public static void Writer(String StudentsUpdate){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constants.FILE_NAME, false));
            bufferedWriter.flush();
            bufferedWriter.write(StudentsUpdate);
            bufferedWriter.close();
        }catch (Exception e){
            
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(constants.INVALID);
            System.exit(1);

        }
        if(args[0].equals(constants.SHOW_NAMES)){
            //Showing All Names
            System.out.println(constants.LOADING_DATA);
            Reader();
            for(String name : names){
                System.out.println(name);
            }
            System.out.println(constants.LOADED_DATA);
        } else if (args[0].equals(constants.RANDOM_NAME)) {
            //Random Name printing
            System.out.println(constants.LOADING_DATA);
            Reader();
            Random random = new Random();
            int randomName = random.nextInt(names.length);
            System.out.println(names[randomName]);
            System.out.println(constants.LOADED_DATA);
        } else if(args[0].contains(constants.ADD_NAME)) {
            System.out.println(constants.LOADING_DATA);
            Reader();
            studentNames = studentNames + constants.SPLIT + args[0].substring(1);
            DateFormat dateFormat = new SimpleDateFormat(constants.DATE_FORMAT);
            Writer(studentNames + constants.LAST_UPDATE + dateFormat.format(new Date()));
            System.out.println(constants.LOADED_DATA);

        } else if (args[0].equals(constants.QUERY)) {
            //Searching A name
            System.out.println(constants.LOADING_DATA);
            Reader();
            String targetName = args[0].substring(1);
            for(int idx = 0; idx < names.length; idx++) {
                if(names[idx].equals(args[0].substring(1))) {
                    System.out.println(constants.FOUND);
                    break;
                }
            }
            System.out.println(constants.LOADED_DATA);
        } else if(args[0].equals(constants.COUNT_WORDS)) {
            System.out.println(constants.LOADING_DATA);
            Reader();
            System.out.println(names.length + constants.WORDS_FOUND);
            System.out.println(constants.LOADED_DATA);

        } else {
            System.out.println(constants.INVALID);
        }
    }
}
