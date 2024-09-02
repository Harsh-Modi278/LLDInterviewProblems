public class CompoundInterestLoanStrategy implements LoanStrategy {

    @Override
    public double calculateRemainingLoanAmount(Loan loan) {
        return 0;
    }

    @Override
    public double calculateMonthlyPayment(Loan loan) {
        return 0;
    }

    @Override
    public int calculateRemainingInstallments(Loan loan) {
        return 0;
    }
}
