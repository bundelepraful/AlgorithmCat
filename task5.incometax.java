import java.util.Scanner;

public class IncomeTaxCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your annual income: ");
        double income = scanner.nextDouble();

        System.out.print("Enter your deductions (e.g., investments under Section 80C): ");
        double deductions = scanner.nextDouble();

        double taxableIncome = Math.max(0, income - deductions);
        double tax = calculateIncomeTax(taxableIncome);

        System.out.println("\n--- Income Tax Summary ---");
        System.out.println("Total Income: " + income);
        System.out.println("Deductions: " + deductions);
        System.out.println("Taxable Income: " + taxableIncome);
        System.out.println("Income Tax Payable: " + tax);

        suggestTaxSavingOptions();

        scanner.close();
    }

    private static double calculateIncomeTax(double taxableIncome) {
        double tax = 0;

        if (taxableIncome <= 250000) {
            tax = 0; // No tax for income up to 2,50,000
        } else if (taxableIncome <= 500000) {
            tax = (taxableIncome - 250000) * 0.05; // 5% tax for income between 2,50,001 and 5,00,000
        } else if (taxableIncome <= 1000000) {
            tax = (250000 * 0.05) + ((taxableIncome - 500000) * 0.2); // 20% tax for income between 5,00,001 and 10,00,000
        } else {
            tax = (250000 * 0.05) + (500000 * 0.2) + ((taxableIncome - 1000000) * 0.3); // 30% tax for income above 10,00,000
        }

        return tax;
    }

    private static void suggestTaxSavingOptions() {
        System.out.println("\n--- Tax Saving Suggestions ---");
        System.out.println("1. Invest in tax-saving instruments under Section 80C (e.g., PPF, ELSS, NSC, etc.).");
        System.out.println("2. Avail benefits of health insurance premiums under Section 80D.");
        System.out.println("3. Claim deductions for interest paid on home loans under Section 24.");
        System.out.println("4. Utilize deductions for donations to charitable organizations under Section 80G.");
        System.out.println("5. Contribute to the National Pension System (NPS) under Section 80CCD(1B).");
    }
}
//program of tax calculator
