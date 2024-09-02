public class Loan {
    private int year;
    double totalLoanAmount;
    int totalInstallments;
    private double interestRate;
    InterestType interestType;
    Person lender;
    Person borrower;

    Loan(int year, double totalLoanAmount, double interestRate, Person lender, Person borrower) {
        this.year = year;
        this.totalLoanAmount = totalLoanAmount;
        this.interestRate = interestRate;
        this.lender = lender;
        this.borrower = borrower;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getYear() {
        return year;
    }
}

enum InterestType {
    SIMPLE,
    COMPOUND
}
