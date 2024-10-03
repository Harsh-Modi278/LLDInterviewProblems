import tests.scenarios.BaseTest;
import tests.scenarios.Case1Tests;

public class Main {
    public static void main(String[] args) {

        Case1Tests testScenario1 = new Case1Tests();
        testScenario1.setUp();
        testScenario1.runTests();
    }
}