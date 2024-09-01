public class EmployeeDaoProxy implements EmployeeDao {
    EmployeeDao employeeDaoObj;

    EmployeeDaoProxy() {
        employeeDaoObj = new EmployeeDaoImpl();
    }

    @Override
    public void create(String client, EmployeeDao obj) throws Exception {
        if (client.equals("ADMIN")) {
            employeeDaoObj.create(client, obj);
            return;
        }
        throw new Exception("Access Denied");
    }

    @Override
    public void delete(String client, int employeeId) throws Exception {
        if (client.equals("ADMIN")) {
            employeeDaoObj.delete(client, employeeId);
            return;
        }
        throw new Exception("Access Denied");
    }

    @Override
    public EmployeeDao get(String client, int employeeId) {
        if (client.equals("ADMIN") || client.equals("EMPLOYEE")) {
            return employeeDaoObj.get(client, employeeId);
        }
        return null;
    }
}
