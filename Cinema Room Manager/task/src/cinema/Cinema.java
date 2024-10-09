package cinema;

import java.util.Scanner;

public class Cinema {

    public static final int TICKET_PRICE = 10;
    public static final int REDUCED_TICKET_PRICE = 8;
    public static int INCOME_EARNED = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int numberOfSeatsInRow = scanner.nextInt();

        if (rows < 0 || numberOfSeatsInRow < 0) {
            System.out.println("Invalid input!");
            return;
        }

        char[][] cinemaSeats = new char[rows][];

        for (int i=0;i<rows;i++) {
            cinemaSeats[i] = new char[numberOfSeatsInRow];
            for (int j=0;j<numberOfSeatsInRow;j++) {
                cinemaSeats[i][j] = 'S';
            }
        }

        boolean keepLooping = true;

        while (keepLooping) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            String choice = scanner.next();

            switch (choice) {
                case "1":
                    printSeats(cinemaSeats);
                    break;
                case "2":
                    buyTicket(scanner, rows, numberOfSeatsInRow, cinemaSeats);
                    break;
                case "3":
                    showStatistics(rows, numberOfSeatsInRow, cinemaSeats);
                    break;
                case "0":
                    keepLooping = false;
                    break;
            }
        }

        scanner.close();
    }

    public static void showStatistics(int rows, int numberOfSeatsInRow, char[][] cinemaSeats) {
        int totalTickets = rows*numberOfSeatsInRow;
        int ticketsBought = 0;
        int income = totalTickets * TICKET_PRICE;
        for (char[] arr:cinemaSeats) {
            for (char c:arr) {
                if (c == 'B') {
                    ticketsBought++;
                }
            }
        }
        if (totalTickets > 60) {
            income = (rows/2)*numberOfSeatsInRow*TICKET_PRICE;
            income += (rows+1)/2*numberOfSeatsInRow*REDUCED_TICKET_PRICE;
        }

        System.out.println("Number of purchased tickets: " + ticketsBought);
        System.out.printf("Percentage: %.2f%%%n",(float)(ticketsBought)/totalTickets*100);
        System.out.println("Current income: $" + INCOME_EARNED);
        System.out.println("Total income: $" + income);
    }

    private static void buyTicket(Scanner scanner, int rows, int numberOfSeatsInRow, char[][] cinemaSeats) {
        int ticketPrice = TICKET_PRICE;
        int row,seatNumber;

        while (true) {

            try {
                System.out.println("Enter a row number:");
                row = scanner.nextInt();

                System.out.println("Enter a seat number in that row:");
                seatNumber = scanner.nextInt();

                if (rows * numberOfSeatsInRow > 60 && row > rows / 2) {
                    ticketPrice = REDUCED_TICKET_PRICE;
                }

                if (row < 0 || row > rows || seatNumber < 0 || seatNumber > numberOfSeatsInRow) {
                    System.out.println("Wrong input!");
                    continue;
                }

                if (cinemaSeats[row - 1][seatNumber - 1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                    continue;
                }
                break;
            }catch (Exception ex) {
                System.out.println("Exception occurred!");
            }
        }
        cinemaSeats[row-1][seatNumber-1] = 'B';
        INCOME_EARNED += ticketPrice;
        System.out.println("Ticket price: $" + ticketPrice);
    }

    public static void printSeats(char[][] cinemaSeats) {
        System.out.println("Cinema:");
        System.out.print(" ");
        int rows = cinemaSeats.length;
        int seats = cinemaSeats[0].length;

        for (int i=1;i<seats+1;i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i=1;i<rows+1;i++) {
            System.out.print(i);
            for (int j=0;j<seats;j++) {
                System.out.print(" " + cinemaSeats[i-1][j]);
            }
            System.out.println();
        }
    }
}