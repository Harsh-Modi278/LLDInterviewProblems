import tests.scenarios.*;

public class Main {
    public static void main(String[] args) {

        Case1Tests testScenario1 = new Case1Tests();
        testScenario1.setUp();
        testScenario1.runTests();

        Case2Tests testScenario2= new Case2Tests();
        testScenario2.setUp();
        testScenario2.runTests();

        Case3Tests testScenario3= new Case3Tests();
        testScenario3.setUp();
        testScenario3.runTests();
    }
}