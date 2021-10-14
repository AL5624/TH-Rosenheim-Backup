package de.thro.inf.prg3.a02;

class Employee {
    private String name;
    private String email;
    private String address;

    public Employee(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public static void main(String[] args)
    {
        Employee e = new Employee("name", "email", "address");

        Employee.Validator ev =  new Employee.Validator();

        boolean x = ev.validateEmployee(e);

        boolean y = Employee.validateEmployee(e);

        System.out.println(x);
        System.out.println(y);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    static class Validator {
        boolean validateEmployee(Employee employee) {
            if (!isValidName(employee.getName())) {
                return false;
            }

            if (!isValidEmail(employee.getEmail())) {
                return false;
            }
            return true;
        }

        private boolean isValidName(String name) {
            if (name == null) {
                return false;
            }

            if (name.length() == 0) {
                return false;
            }

            return true;
        }

        private boolean isValidEmail(String email) {
            if (email == null) {
                return false;
            }

            if (email.length() == 0) {
                return false;
            }

            return true;
        }
    }

    static boolean validateEmployee(Employee employee) {
        if (!isValidName(employee.getName())) {
            return false;
        }

        if (!isValidEmail(employee.getEmail())) {
            return false;
        }
        return true;
    }

    static private boolean isValidName(String name) {
        if (name == null) {
            return false;
        }

        if (name.length() == 0) {
            return false;
        }

        return true;
    }

    static private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }

        if (email.length() == 0) {
            return false;
        }

        return true;
    }

}
