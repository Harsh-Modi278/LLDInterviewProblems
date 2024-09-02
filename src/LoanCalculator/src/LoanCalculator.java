import java.util.ArrayList;

public class LoanCalculator {
    ArrayList<Loan> loanList = new ArrayList<Loan>();

    public void addLoan(Loan loan) {
        loanList.add(loan);
    }

    public double calculateRemainingLoanAmount(Loan loan) {

    }
}


// follow up: a method to get loan details
// solution: implement a facade on top of loanCalculator and Loan class to hide complexities from user

// database:
// map<int, ArrayList<>>: personId -> ArrayList of loan ids
// ArrayList<Loan> : list of loans
// no need of implementing separate person table
