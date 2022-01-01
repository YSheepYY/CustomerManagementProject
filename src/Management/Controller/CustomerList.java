package Management.Controller;

import Management.Bean.Customer;

/**
 * @author Yang Yang
 * @create 2022-01-01-5:05 PM
 */
public class CustomerList {

    private Customer[] customers;
    private int total = 0; //the total number of already stored customers


    /**
     * @param maxCustomer: the max numeber of customers that could be stored
     */
    public CustomerList(int maxCustomer) {
        customers = new Customer[maxCustomer];
    }

    /**
     * @param customer: the customer object that should be added
     * @return true: if add operation is successful
     * false: full
     */
    public boolean addCustomer(Customer customer) {
        if (total >= customers.length) {
            return false;
        }

        customers[total++] = customer;
//        customers[total] = customer;
//        total++;
        return true;
    }

    public boolean replaceCustomer(int index, Customer customer) {
        if (index >= total || index < 0) {
            return false;
        }
        customers[index] = customer;
        return true;
    }

    public boolean deleteCustomer(int index) {
        if (index < 0 || index >= total) {
            return false;
        }
        //move the customers after the index position 1 position to the left
        int pointer = index + 1;
        while (customers[pointer] != null){
            customers[pointer - 1] = customers[pointer];
            pointer++;
        }
        customers[pointer - 1] = null;
        total--;
        return true;
    }

    public Customer[] getAllCustomers() {
//        return customers;
        
        Customer[] custs = new Customer[total];
        for (int i = 0; i < total; i++) {
            custs[i] = customers[i];
        }
        return custs;
    }
    

    public Customer getCustomer(int index) {
        if (index < 0 || index >= total) {
            return null;
        }
        return customers[index];
    }

    public int getTotal() {
        return total;
    }
}
