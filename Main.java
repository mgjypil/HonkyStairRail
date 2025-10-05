import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {

    // returns index of char in chars, -1 if doesnt exist
    public static int containsCharacter(ArrayList<Character> chars, Character c) {
        for (int i = 0; i < chars.size(); i++) {
            if (chars.get(i).getName().compareTo(c.getName()) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        int salary = 180;
        int balance = 0;
        int pulls = 10;

        ArrayList<Character> roster = new ArrayList<Character>();   // owned characters
        ArrayList<Character> banners = new ArrayList<Character>();  // banner characters
        int days = 0;   // cycle number
        int b1 = 0;     // cycle's 1st banner
        int b2 = 1;     // cycle's 2nd banner
        banners.add(new Character("fish wife", "nihlity", 5));
        banners.add(new Character("white swan", "nihlity", 5));
        banners.add(new Character("bird", "harmony", 5));
        banners.add(new Character("furina", "harmony", 5));

        int choice = 0;
        while (choice != 7) {
            System.out.printf("\n[Current balance] $%d\t[Pulls] %d\t[Daily salary] $%d\n", balance, pulls, salary);
            System.out.printf("Days left until current banner cycle ends: %d\n", days);
            
            System.out.printf("[1] Do a 10 pull on %s\n[2] Do 1 pull on %s\n[3] Do a 10 pull on %s\n[4] Do 1 pull on %s\n[5] Go to work\n[6] Buy pulls\n[7] EXIT\n",
                banners.get(b1).getBanner(), banners.get(b1).getBanner(),
                banners.get(b2).getBanner(), banners.get(b2).getBanner());
            System.out.print("What do you want to do?: ");

            choice = scan.nextInt();
            System.out.println();
            
            if ((choice == 1 || choice == 3) && pulls < 10) {
                System.out.println("Insufficient funds.");
            } else if ((choice == 2 || choice == 4) && pulls < 1) {
                System.out.println("Insufficient funds.");
            }
            else {
                switch(choice) {
                    case 1:
                        // pulls -= 10;
                        for (int i = 0; i < 10; i++) {
                            if (rand.nextInt(4) == 0)  {
                                int index = containsCharacter(roster, banners.get(b1));
                                if (index == -1) {
                                    // not yet obtained
                                    System.out.printf("*** OBTAINED %s***\n", banners.get(b1).getBanner());
                                    roster.add(banners.get(b1));
                                } else {
                                    // obtained copy
                                    System.out.printf("***%s EIDOLON OBTAINED***\n", banners.get(b1).getBanner());
                                    roster.get(index).addEidolon();
                                }
                            } else {
                                System.out.println("Failed to obtain character.");
                            }
                        }
                    break;
                    case 2:
                    if (rand.nextInt(4) == 0)  {
                        int index = containsCharacter(roster, banners.get(b1));
                        if (index == -1) {
                            // not yet obtained
                            System.out.printf("*** OBTAINED %s***\n", banners.get(b1).getBanner());
                            roster.add(banners.get(b1));
                        } else {
                            // obtained copy
                            System.out.printf("***%s EIDOLON OBTAINED***\n", banners.get(b1).getBanner());
                            roster.get(index).addEidolon();
                        }
                    } else {
                        System.out.println("Failed to obtain character.");
                    }
                    break;
                    case 3:
                    break;
                    case 4:
                    break;
                    case 5:
                    balance += salary;
                    System.out.printf("\n[Current balance] $%d\t[Pulls] %d\t[Daily salary] $%d\n", balance, pulls, salary);
                    break;
                    case 6:
                    System.out.printf("How many pulls do you want to buy? [Current balance: $%d]: ", balance);
                    System.out.println();
                    int num_pulls = scan.nextInt();
                    if (num_pulls > balance) {
                        System.out.println("Insufficient funds.");
                    } else {
                        pulls += num_pulls;
                        balance -= num_pulls;
                        System.out.printf("Purchased %d pulls. Current number of pulls: %d\n", num_pulls, pulls);
                    }
                    break;
                    default:
                    break;
                }
            }
            days++;
            if (days == 4) {
                b1 += 2;
                b2 += 2;
                if (b1 >= banners.size() || b2 >= banners.size()) {
                    b1 = 0;
                    b2 = 1;
                }
                days = 0;
            }
        }
        scan.close();
  }
}
