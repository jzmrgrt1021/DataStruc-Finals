import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Guest extends User {

    private static String fullName;
    private static Object guest_reserve;
    private String adress;
    private String contactNumber;
    private int age;


    public Guest(String email, String password, int userID, String fullName, String adress, int age, String contactNumber) {
        super(email, password, userID);

        this.fullName = fullName;
        this.adress = adress;
        this.age = age;
        this.contactNumber = contactNumber;
    }


    public String getFullName() {
        return fullName;
    }

    public String getAdress() {
        return adress;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    public static void guestMenu(Guest loggedInUser) {
        Scanner sc = new Scanner(System.in);


        System.out.println("\n=== Client Menu ===");
        byte option = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("1. Make Reservation");
                System.out.println("2. View Reservations");
                System.out.println("3. Request Cancellation of Reservation");
                System.out.println("4. Exit to Main Menu");
                System.out.print("Select option: ");


                // Get user input and validate
                option = sc.nextByte();
                sc.nextLine(); // Clear the buffer to prevent input issues


                if (option > 6 || option < 1) {
                    System.out.println("Please enter a valid option (1-6)!");
                    continue; // Try again
                }




                // Process user selection
                switch (option) {

                    case 1: { // Create reservation
                        Guest.guest_reserve.getClass();
                        break;
                    }
                    case 2: { // View reservation
                        break;
                    }
                    case 3: { // Request cancellation of reservation

                    }
                    case 4: { // Exit to main menu
                        System.out.println("Returning to main menu...");
                        return;
                    }


                    default: { // Should never reach here due to input validation
                        System.out.println("Invalid option! Please enter a number between 1 and 6.");
                        //clientMenu(isClientLogin);
                        break;
                    }
                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }



    // Reservation details
    private static String transID;      // Transaction ID for the reservation
    private static String reserveDate;  // Check-in date
    private static String checkOutDate; // Check-out date
    private static int facilityNo;      // Number of facilities reserved
    private static int totalGuests;     // Total number of guests

    // Financial details
    private static double grandTotal;   // Total cost of facilities
    private static double foodTotal;    // Total cost of meals
    private static double finalTotal;   // Grand total (facilities + meals)

    // Constants
    private static final String RESERVE_FILE = "RESERVE.txt";
    private static final int TRANS_ID_LENGTH = 6;            // Length of random part of transaction ID

    // Scanner for user input
    private static final Scanner reserve_scanner = new Scanner(System.in);

    // StringBuilder objects to store reservation details
    private static final StringBuilder facilityDetails = new StringBuilder(); // Stores facility information
    private static final StringBuilder paymentDetails = new StringBuilder();  // Stores payment information

    //private static String fullName;
    private static String newUserID;
    private static Object guestMenu;

    // main method for testing
    public static void main(String[] args) throws IOException {
        guest_reserve(true);
    }
    public static void guest_reserve(boolean b) throws IOException {
            // Reset StringBuilder objects for new reservation
            facilityDetails.setLength(0);
            paymentDetails.setLength(0);

            // Display header
            System.out.println("========================================================");
            System.out.println("               HOTEL RESERVATION PORTAL                ");
            System.out.println("========================================================");
            System.out.println();

            // variable declarations
            char facility = ' ';
            int guestNo = 0;
            int maximumGuest = 0;
            double basePrice = 0;
            String facilityName = "";
            int additional = 0;
            double totalAmount = 0;
        int foodTotal = 0;
        int grandTotal = 0;
        int totalGuests = 0;

            //Scanners:
        Scanner reserve_scanner = new Scanner(System.in);


            // --- Facility Number Selection ---

        int facilityNo;
        while (true) {
                try {
                    facilityOptions();  // displays facility options

                    System.out.print("Enter the number of facilities to reserve: ");
                    facilityNo = reserve_scanner.nextInt();
                    reserve_scanner.nextLine();

                    char choice;
                    while (true) {
                        System.out.print("Are you sure you want to reserve " + facilityNo + " " + (facilityNo == 1 ? "facility?" : "facilities?") + " (Y/N): ");
                        choice = reserve_scanner.next().toLowerCase().charAt(0);

                        if (choice != 'y' && choice != 'n') {
                            System.out.println("Invalid option. Please enter a valid choice.");
                            continue;
                        } else {
                            break;
                        }
                    }
                    if (choice == 'n') {
                        continue;
                    } else if (choice == 'y') {
                        break;
                    }

                    break; // ends loop if correct
                } catch (Exception e) {
                    System.out.println("Invalid option. Please enter a valid number.");
                    reserve_scanner.nextLine();
                    continue; // try again
                }
            }

            // --- Facility Details Loop ---
            for (int i = 0; i < facilityNo; i++) {

                facilityOptions();

                additional = 0;
                while (true) {
                    System.out.print("Enter facility " + (i + 1) + " to reserve [A, B, C, D]: ");
                    facility = reserve_scanner.next().charAt(0);
                    facility = Character.toUpperCase(facility);
                    reserve_scanner.nextLine();

                    if (facility == 'A' || facility == 'B' || facility == 'C' || facility == 'D') {
                        switch (facility) {
                            case 'A':
                                facilityName = "Single Room";
                                basePrice = 1500;
                                maximumGuest = 2;
                                break;
                            case 'B':
                                facilityName = "Double";
                                basePrice = 2000;
                                maximumGuest = 3;
                                break;
                            case 'C':
                                facilityName = "King";
                                basePrice = 3000;
                                maximumGuest = 4;
                                break;
                            case 'D':
                                facilityName = "Suite";
                                basePrice = 4000;
                                maximumGuest = 6;
                                break;
                        }

                        while (true) {
                            try {
                                System.out.print("Enter number of guests: ");
                                guestNo = reserve_scanner.nextInt();
                                reserve_scanner.nextLine();
                                System.out.println();

                                char choice;
                                while (true) {
                                    System.out.print("Are you sure you want to have " + guestNo + " " + (guestNo == 1 ? "guest?" : "guests?") + " (Y/N): ");
                                    choice = reserve_scanner.next().toLowerCase().charAt(0);

                                    if (choice != 'y' && choice != 'n') {
                                        System.out.println("Invalid option. Please enter a valid choice.");
                                        continue;
                                    } else {
                                        break;
                                    }
                                }
                                if (choice == 'n') {
                                    continue;
                                } else if (choice == 'y') {
                                    break;
                                }

                                break;
                            } catch (Exception e) {
                                System.out.println("Please enter a valid number.");
                                reserve_scanner.nextLine();
                                continue;
                            }
                        }

                        if (guestNo > maximumGuest) {
                            int extra = guestNo - maximumGuest;
                            additional = extra * 500;
                        }

                        totalAmount = basePrice + additional;
                        totalGuests += guestNo;
                        grandTotal += totalAmount;

                        facilityInfo(facilityName, guestNo, basePrice, additional, totalAmount, i + 1);
                        break;
                    } else {
                        System.out.println("Invalid option. Please choose A, B, C, or D only.\n");
                    }
                }
            }

            // --- Date Input (last step) ---
            try {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                format.setLenient(false); // strictly enforce date format

                boolean isValid1 = false;
                String reserveDate = "";

                while (!isValid1) {
                    System.out.print("Enter reservation date (MM/dd/yyyy): ");
                    reserveDate = reserve_scanner.nextLine();

                    try {
                        Date inputDate = format.parse(reserveDate); // parse input date string to Date object
                        Date today = new Date(); // get current date

                        if (inputDate.before(today)) {
                            System.out.println("Date is in the past or within the same day. Try again.");
                        } else if (isMoreThanTwoYearsAhead(inputDate, today)) {
                            System.out.println("Check-in date cannot be more than 2 years in advance. Try again.");
                        } else {
                            isValid1 = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid format. Use MM/dd/yyyy.");
                    }
                }

                String checkOutDate = "";
                boolean isValid2 = false;
                while (!isValid2) {
                    System.out.print("Enter check-out date (MM/dd/yyyy): ");
                    checkOutDate = reserve_scanner.nextLine();

                    try {
                        Date outDate = format.parse(checkOutDate);
                        Date inDate = format.parse(reserveDate);
                        Date today = new Date();

                        if (!outDate.after(inDate)) {
                            System.out.println("Check-out date must be after check-in date.");
                        } else if (isMoreThanTwoYearsAhead(outDate, today)) {
                            System.out.println("Check-out date cannot be more than 2 years in advance. Try again.");
                        } else {
                            isValid2 = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid format. Use MM/dd/yyyy.");
                    }
                }

            } catch (Exception e) {
                System.out.println("An error occurred while entering dates.");
                e.printStackTrace();
            }

            // --- Payment Selection ---
        int finalTotal = grandTotal + foodTotal;

            char paymentChoice;
            while (true) {
                System.out.println("Pay the 30% reservation fee or pay full amount?: ");
                System.out.println("A. Pay 30% reservation fee");
                System.out.println("B. Pay full amount reservation fee");
                paymentChoice = reserve_scanner.next().charAt(0);
                paymentChoice = Character.toUpperCase(paymentChoice);

                if (paymentChoice == 'A' || paymentChoice == 'B') {
                    break;
                } else {
                    System.out.println("Invalid option. Please choose A or B only.\n");
                    continue;
                }
            }

            double amountToPay;
            if (paymentChoice == 'A') {
                amountToPay = finalTotal * 0.30;
                System.out.println("You chose to pay 30% reservation fee.");
                System.out.printf("Amount to pay: ₱%.2f\n", amountToPay);
            } else {
                amountToPay = finalTotal;
                System.out.println("You chose to pay full amount.");
                System.out.printf("Amount to pay: ₱%.2f\n", amountToPay);
            }

            paymentOption(paymentChoice, finalTotal, amountToPay);

            saveReserveToFile();

            System.out.println("Reservation saved successfully.");
            System.out.println("Thank you for your reservation. Have a great stay!");
            System.out.println("========================================================");
            System.out.println();
        }

    // choose which payment options client wants to do
    private static void paymentOption(char paymentOption, double finalTotal, double amountPaid) {
        // Process based on payment option
        if (paymentOption == 'A') {
            // 30% reservation fee option
            double reservationFee = finalTotal * 0.30;
            double balanceDue = finalTotal - reservationFee;

            // Record payment details
            paymentDetails.append("Payment Option: 30% Reservation Fee\n");
            paymentDetails.append("Total Reservation Cost: ₱").append(String.format("%.2f", finalTotal)).append("\n");
            paymentDetails.append("30% Deposit Amount: ₱").append(String.format("%.2f", reservationFee)).append("\n");
            paymentDetails.append("Balance Due Upon Check-in: ₱").append(String.format("%.2f", balanceDue)).append("\n");

            System.out.println("\nPayment Summary:");
            System.out.printf("Total Reservation Cost: ₱%.2f\n", finalTotal);
            System.out.printf("30%% Deposit Amount: ₱%.2f\n", reservationFee);
            System.out.printf("Balance Due Upon Check-in: ₱%.2f\n", balanceDue);

        } else if (paymentOption == 'B') {
            // Full payment option
            paymentDetails.append("Payment Option: Full Payment\n");
            paymentDetails.append("Total Reservation Cost: ₱").append(String.format("%.2f", finalTotal)).append("\n");
            paymentDetails.append("Amount Paid: ₱").append(String.format("%.2f", amountPaid)).append("\n");
            paymentDetails.append("Balance Due: ₱0.00 (No balance due)\n");

            System.out.println("\nPayment Summary:");
            System.out.printf("Total Reservation Cost: ₱%.2f\n", finalTotal);
            System.out.printf("Amount Paid: ₱%.2f\n", amountPaid);
            System.out.println("Balance Due: ₱0.00 (No balance due)");

        } else {
            // Should never reach here due to input validation
            paymentDetails.append("Payment Option: Unknown\n");
            paymentDetails.append("Please contact hotel staff for payment clarification.\n");

            System.out.println("\nWarning: Invalid payment option selected.");
            System.out.println("Please contact hotel staff for payment clarification.");
        }
    }

    private static boolean isMoreThanTwoYearsAhead(Date futureDate, Date baseDate) {
        Calendar futureCal = Calendar.getInstance();
        Calendar baseCal = Calendar.getInstance();
        futureCal.setTime(futureDate);
        baseCal.setTime(baseDate);

        // Add 2 years to the base date
        baseCal.add(Calendar.YEAR, 2);

        // If future date is after base date + 2 years, it's more than 2 years ahead
        return futureDate.after(baseCal.getTime());
    }


    // Displays available facilities and their details
    public static void facilityOptions() {
        System.out.println("\n--- Available Facilities ---");
        System.out.println("Note: There is an additional ₱500.00 charge for every guest exceeding the maximum capacity.\n");

        System.out.println("FACILITY        PRICE PER NIGHT    MAXIMUM GUESTS");
        System.out.println("A. Single Room  ₱1,500.00          2 persons");
        System.out.println("B. Double       ₱2,000.00          3 persons");
        System.out.println("C. King         ₱3,000.00          4 persons");
        System.out.println("D. Suite        ₱4,000.00          6 persons");
        System.out.println();
    }

    private static void facilityInfo(String facilityName, int guestNo,
                                     double basePrice, double additional, double totalAmount, int facilityNo) {
        // Add facility details to the StringBuilder
        facilityDetails.append("Facility #").append(facilityNo).append(": ").append(facilityName).append("\n");
        facilityDetails.append("Guests: ").append(guestNo).append("\n");
        facilityDetails.append("Base Price: ₱").append(String.format("%.2f", basePrice)).append("\n");

        // Add additional charges if any
        if (additional > 0) {
            facilityDetails.append("Additional Charges for Extra Guests: ₱")
                    .append(String.format("%.2f", additional))
                    .append("\n");
        } else {
            facilityDetails.append("Additional Charges: ₱0.00\n");
        }

        // Add meal options
        facilityDetails.append("Meal Options:\n");

        // Get meal selection and calculate cost
        System.out.println("\nPlease select meal options for Facility #" + facilityNo + ":");
        double food = mealOptions();
        totalAmount += food;
        foodTotal += food;

        // Add subtotal for this facility
        facilityDetails.append("Additional Charges for Extra Guests: ₱")
                .append(String.format("%.2f", additional))
                .append("\n");
        facilityDetails.append("Total Amount: ₱")
                .append(String.format("%.2f", totalAmount))
                .append("\n\n");

        System.out.println("Facility #" + facilityNo + " details recorded successfully.");
    }

    // displays meal options and calculates meal expense
    public static double mealOptions() {
        System.out.println("\n--- Meal Options ---");
        System.out.println("Please select a meal package for your stay:");
        System.out.println("A. Breakfast Only: FREE");
        System.out.println("B. Breakfast + Lunch: ₱250 per person");
        System.out.println("C. Breakfast + Dinner: ₱350 per person");
        //System.out.println("D. All Meals (Breakfast, Lunch, Dinner): ₱600 per person");
        System.out.print("Enter your choice (A/B/C): ");

        char foodOption;
        try {
            String input = reserve_scanner.next().toUpperCase().trim();
            reserve_scanner.nextLine(); // Clear the buffer

            if (input.isEmpty()) {
                System.out.println("Invalid option. Defaulting to Breakfast Only (FREE).");
                facilityDetails.append("Breakfast: FREE\n");
                return 0;
            }

            foodOption = input.charAt(0);
        } catch (Exception e) {
            System.out.println("Invalid input. Defaulting to Breakfast Only (FREE).");
            reserve_scanner.nextLine(); // Clear the buffer
            facilityDetails.append("Breakfast: FREE\n");
            return 0;
        }

        // Meal rates
        int lunchRate = 250;
        int dinnerRate = 350;
        double total_foodrate = 0;

        // Calculate cost based on selection
        switch (foodOption) {
            case 'A': {
                System.out.println("You selected: Breakfast Only (FREE)");
                facilityDetails.append("Breakfast: FREE\n");
                return 0;
            }
            case 'B': {
                total_foodrate = totalGuests * lunchRate;
                System.out.println("You selected: Breakfast + Lunch");
                System.out.printf("Lunch cost: ₱%d × %d guests = ₱%.2f\n", lunchRate, totalGuests, total_foodrate);
                facilityDetails.append("Breakfast: FREE\n");
                facilityDetails.append(String.format("Lunch: ₱%.2f\n", total_foodrate));
                return total_foodrate;
            }
            case 'C': {
                total_foodrate = totalGuests * dinnerRate;
                System.out.println("You selected: Breakfast + Dinner");
                System.out.printf("Dinner cost: ₱%d × %d guests = ₱%.2f\n", dinnerRate, totalGuests, total_foodrate);
                facilityDetails.append("Breakfast: FREE\n");
                facilityDetails.append(String.format("Dinner: ₱%.2f\n", total_foodrate));
                return total_foodrate;
            }
            /*
            case 'D': {
                double lunch = totalGuests * lunchRate;
                double dinner = totalGuests * dinnerRate;
                total_foodrate = lunch + dinner;
                System.out.println("You selected: All Meals (Breakfast, Lunch, Dinner)");
                System.out.printf("Lunch cost: ₱%d × %d guests = ₱%.2f\n", lunchRate, totalGuests, lunch);
                System.out.printf("Dinner cost: ₱%d × %d guests = ₱%.2f\n", dinnerRate, totalGuests, dinner);
                System.out.printf("Total meal cost: ₱%.2f\n", total_foodrate);
                facilityDetails.append("Breakfast: FREE\n");
                facilityDetails.append(String.format("Lunch: ₱%.2f\n", lunch));
                facilityDetails.append(String.format("Dinner: ₱%.2f\n", dinner));
                return total_foodrate;
            }*/
            default: {
                System.out.println("Invalid option. Defaulting to Breakfast Only (FREE).");
                facilityDetails.append("Breakfast: FREE\n");
                return 0;
            }
        }
    }


    // save reservation to RESERVE.txt
    private static void saveReserveToFile() throws IOException {
        try {
            // Open file for appending
            File file = new File(RESERVE_FILE);
            FileWriter fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);

            // Write reservation header
            pw.println("========================================================");
            pw.println("Transaction ID: " + transID);
            pw.println("Client ID: " + newUserID);
            pw.println("Name: " + fullName);
            pw.println();

            // Write reservation dates
            pw.println("Check-in Date: " + reserveDate);
            pw.println("Check-out Date: " + checkOutDate);
            pw.println();

            // Write facility information
            pw.println("No. of facilities reserved: " + facilityNo);
            pw.println();
            pw.println(facilityDetails);

            // Write summary
            pw.println("SUMMARY:");
            pw.println("Total Guests: " + totalGuests);
            pw.println(String.format("Facility Charges: ₱%.2f", grandTotal));
            pw.println(String.format("Meal Charges: ₱%.2f", foodTotal));

            // Write payment details
            pw.println(paymentDetails);

            // Write final total and closing line
            pw.println(String.format("Final Total Amount: ₱%.2f", finalTotal));
            pw.println("========================================================");

            // Close the file
            pw.close();

            System.out.println("\nReservation details saved successfully.");

        } catch (IOException e) {
            System.err.println("Error saving reservation: " + e.getMessage());
            //throw e; // Re-throw to be handled by caller
        }
    }
}


