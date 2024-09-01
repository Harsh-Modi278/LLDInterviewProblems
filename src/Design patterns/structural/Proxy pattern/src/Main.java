public class Main {
    public static void main(String[] args) {
        try {
            EmployeeDao employeeDaoObj = new EmployeeDaoProxy();
            employeeDaoObj.create("ADMIN", new EmployeeDaoImpl());
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}