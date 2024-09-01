public class EmployeeDaoImpl implements EmployeeDao{
    @Override
    public void create(String client, EmployeeDao obj) {
        System.out.println("created a new row in employee table");
    }

    @Override
    public void delete(String client, int employeeId) {
        System.out.println("deleted a row in employee table for employee id: "+employeeId);
    }

    @Override
    public EmployeeDao get(String client, int employeeId) {
        System.out.println("getting a row in employee table for employee id: "+employeeId);
        return new EmployeeDaoImpl();
    }
}
