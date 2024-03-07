package dev.m3s.programming2.homework3;

import java.time.Year;

abstract class Employee extends Person implements Payment{
    private int currentYear = Year.now().getValue();
    private String empId;
    private int startYear = currentYear;
    private Payment payment;

    Employee(String lname, String fname) {
        super(lname, fname);
        this.empId = getEmployeeIdString() + super.getRandomId(ConstantValues.MIN_EMP_ID, ConstantValues.MAX_EMP_ID);
    }

    public String getIdString() {
        return empId;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(final int startYear) {
        if (startYear > 2000 && startYear <= currentYear) {
            this.startYear = startYear;
        }
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        if (payment != null) {
            this.payment = payment;
        }
    }

    public double calculatePayment() {
        if (payment != null) {
            return payment.calculatePayment();
        }
        return 0.0;
    }

    protected abstract String getEmployeeIdString();
}
