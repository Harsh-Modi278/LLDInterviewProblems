public interface LoanStrategy {
    double calculateRemainingLoanAmount(Loan loan);
    double calculateMonthlyPayment(Loan loan);
    int calculateRemainingInstallments(Loan loan);
}
