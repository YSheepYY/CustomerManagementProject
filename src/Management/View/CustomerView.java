package Management.View;

import Management.Bean.Customer;
import Management.Controller.CustomerList;
import Management.Util.CMUtility;

/**
 * @author Yang Yang
 * @create 2022-01-01-5:05 PM
 */
public class CustomerView {

    private CustomerList customerList = new CustomerList(10);

    public CustomerView() {
        Customer test = new Customer("Yang", "Ben", 'M', 21, "+36307380043", "yang20001117@gmail.com");
        customerList.addCustomer(test);
    }

    public void enterMainMenu() {

        boolean isFlag = true;

        while (isFlag) {
            System.out.println("\n--------------Customer Management System--------------");
            System.out.println("              1 Add Customer");
            System.out.println("              2 Replace Customer");
            System.out.println("              3 Delete Customer");
            System.out.println("              4 Customer List");
            System.out.println("              5 Exit\n");
            System.out.print("              Please choose (1-5): ");

            char menu = CMUtility.readMenuSelection();
            switch (menu) {
                case '1': {
                    addNewCustomer();
                    break;
                }
                case '2': {
                    modifyCustomer();
                    break;
                }
                case '3': {
                    deleteCustomer();
                    break;
                }
                case '4': {
                    listAllCustomer();
                    break;
                }
                case '5': {
                    System.out.print("Confirm that you really want to quit (Y/N): ");
                    char quit = CMUtility.readConfirmSelection();
                    if (quit == 'Y') {
                        isFlag = false;
                    }
                }
            }

//            isFlag = false;
        }

    }


    public void addNewCustomer() {
        System.out.println("--------------Add Customer--------------");
        System.out.print("First Name: ");
        String firstName = CMUtility.readString(10);
        System.out.print("Last Name: ");
        String lastName = CMUtility.readString(10);
        System.out.print("Gender: ");
        char gender = Character.toUpperCase(CMUtility.readChar());
        System.out.print("Age: ");
        int age = CMUtility.readInt();
        System.out.print("Phone Number: ");
        String phoneNumber = CMUtility.readString(20);
        System.out.print("Email: ");
        String email = CMUtility.readString(30);

        Customer customer = new Customer(firstName, lastName, gender, age, phoneNumber, email);
        boolean success = customerList.addCustomer(customer);
        if (success) {
            System.out.println("--------------Add Customer Done--------------");
        } else {
            System.out.println("--------------Customer List is Full! Could not add new Customer!--------------");
        }

    }


    public void modifyCustomer() {
        System.out.println("--------------Modify Customer-------------");
        Customer cust;
        int id;
        for (; ; ) {
            System.out.println("Please choose the ID of the customer you want to modify(-1 to exit): ");
            id = CMUtility.readInt();
            if (id == -1) {
                return;
            }

            cust = customerList.getCustomer(id - 1);
            if (cust == null) {
                System.out.println("Can't find this customer!");
            } else { // found this customer with the id
                break;
            }
        }

        System.out.print("FirstName(" + cust.getFirstName() + "): ");
        String firstName = CMUtility.readString(10, cust.getFirstName());
        System.out.print("LastName(" + cust.getLastName() + "): ");
        String lastName = CMUtility.readString(10, cust.getLastName());
        System.out.print("Gender(" + cust.getGender() + "): ");
        char gender = Character.toUpperCase(CMUtility.readChar(cust.getGender()));
        System.out.print("Age(" + cust.getAge() + "): ");
        int age = CMUtility.readInt(cust.getAge());
        System.out.print("PhoneNumber(" + cust.getPhoneNumber() + "): ");
        String phoneNumber = CMUtility.readString(20, cust.getPhoneNumber());
        System.out.print("Email(" + cust.getEmail() + "): ");
        String email = CMUtility.readString(30, cust.getEmail());

        Customer customer = new Customer(firstName, lastName, gender, age, phoneNumber, email);
        boolean success = customerList.replaceCustomer(id - 1, customer);

        if (success) {
            System.out.println("--------------Modify Customer Done-------------");
        } else { //this will never be the case
            System.out.println("--------------Modify Customer Failed-------------");
        }


    }


    public void deleteCustomer() {
        System.out.println("--------------Delete Customer-------------");

        Customer cust;
        int id;
        for (; ; ) {
            System.out.print("Please choose the ID of the customer you want to delete(-1 to exit):");
            id = CMUtility.readInt();
            if (id == -1) {
                return;
            }

            cust = customerList.getCustomer(id - 1);
            if (cust == null) {
                System.out.println("Can't find this customer!");
            } else {
                break;
            }
        }
        System.out.print("Confirm that you really want to delete (Y/N): ");
        char delete = CMUtility.readConfirmSelection();
        if (delete == 'Y') {
            boolean success = customerList.deleteCustomer(id - 1);
            if (success) {
                System.out.println("--------------Delete Customer Done-------------");
            } else {
                System.out.println("--------------Delete Customer Failed-------------");
            }
        } else {
            return;
        }
    }


    public void listAllCustomer() {
        System.out.println("----------------------------------Customer List----------------------------------");
        if (customerList.getTotal() == 0) {
            System.out.println("No Customer Records!");
        } else {
            System.out.println("ID\tFirstName\tLastName\tGender\tAge\t\tPhoneNumber\t\tEmail");
            Customer[] custs = customerList.getAllCustomers();
            for (int i = 0; i < custs.length; i++) {
                Customer customer = custs[i];
                String firstName = customer.getFirstName();
                String lastName = customer.getLastName();
                char gender = customer.getGender();
                int age = customer.getAge();
                String PhoneNumber = customer.getPhoneNumber();
                String email = customer.getEmail();
                System.out.println((i + 1) + "\t" + firstName + "\t\t" + lastName +
                        "\t\t" + gender + "\t\t" + age + "\t\t" + PhoneNumber + "\t" + email);
            }
        }


        System.out.println("----------------------------------Customer List Done----------------------------------");
    }

    public static void main(String[] args) {

        CustomerView customerView = new CustomerView();
        customerView.enterMainMenu();

    }


}


