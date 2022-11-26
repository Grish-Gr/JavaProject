import csv.ReaderFromCSV;

public class Main {

    public static void main(String[] args){
        ReaderFromCSV reader = new ReaderFromCSV();
        reader.getListOscarActor("src/main/resources/csv/oscar_age_female.csv");
        /*Thread thread = newThread();
        thread.start();*/

    }

    public static Thread newThread(){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        });
    }
}
