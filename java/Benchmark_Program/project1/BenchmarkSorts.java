//David Mejia
//CMSC451
//Project 1

/***************************    manual warming up of the JVM   *****************************/
//this code was copied from baeldung.com/java-jvm-warmup
class Dummy {
    public void m() {
    }
}
class ManualClassLoader {
    protected static void load() {
        for (int i = 0; i < 100000; i++) {
            Dummy dummy = new Dummy();
            dummy.m();
        }
    }
}
/******************************************************************************************/

public class BenchmarkSorts {
    /**This static block is to warm up JVM based on Baeldung.com*/
    static{
        ManualClassLoader.load();
    }

    public static void main(String[] args){
        /**This call to load() is to warm up JVM based on Baeldung.com*/
        ManualClassLoader.load();

        int times = 0;
        MySort recursiveSort = new MySort();
        MySort iterSort = new MySort();
        FileCreator rec = new FileCreator();
        FileCreator iter = new FileCreator();
        rec.openFile("recursive_records.txt");
        iter.openFile("iterative_records.txt");


        //program will increase n by 100 every time until it reaches 1000 n
        while(times<1000){
            times+=100;
            rec.addToFile(times);
            iter.addToFile(times);
            for (int i = 0; i<50; i++) {
                    int[] arrayTest = recursiveSort.generateSets(times);
                    int[] iterArray = arrayTest.clone();
                    recursiveSort.recursiveSort(arrayTest);
                    iterSort.iterativeSort(iterArray);

                    /**add all the information to the file*/
                    rec.addToFile(recursiveSort.getCount(), recursiveSort.getTime());
                    iter.addToFile(iterSort.getCountIterative(), iterSort.getTimeIterative());
            }
            rec.addToFile("\n");
            iter.addToFile("\n");
        }
        rec.closeFiles();
        iter.closeFiles();
    }
}
