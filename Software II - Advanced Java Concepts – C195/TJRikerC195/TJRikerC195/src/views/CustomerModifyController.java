

package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Customer;
import models.CustomerDB;


public class CustomerModifyController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private int id;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField address;
    
    @FXML
    private ComboBox city;
    
    @FXML
    private TextField country;
    
    @FXML
    private TextField zip;
    
    @FXML
    private TextField phone;
    
    private final ObservableList<String> cities = FXCollections.observableArrayList(
    "New York","Washington DC","Miami","Dallas","Phoenix","Los Angeles","London","Liverpool","Manchester","Oxford");
    
    private final ObservableList<String> errors = FXCollections.observableArrayList();
    
    @FXML
    //sets country based on city
    public void setCountry() {
        String currentCity = city.getSelectionModel().getSelectedItem().toString();
        if(currentCity.equals("London")||currentCity.equals("Liverpool")||currentCity.equals("Manchester")||currentCity.equals("Oxford")) {
            country.setText("England");
        } else {
            country.setText("United States");       
        }
    }
    //handles modify
    public boolean handleModifyCustomer() {
        errors.clear();
        String customerName = name.getText();
        String customerAddress = address.getText();
        int customerCity = city.getSelectionModel().getSelectedIndex() + 1;
        String customerZip = zip.getText();
        String customerPhone = phone.getText();
        if(!validateName(customerName)||!validateAddress(customerAddress)||!validateCity(customerCity)||
                !validateZip(customerZip)||!validatePhone(customerPhone)){
            return false;
        } else {
            return CustomerDB.updateCustomer(id, customerName, customerAddress, customerCity, customerZip, customerPhone);
        }
    }
    // populates the fields
    public void populateFields(Customer customer) {
        id = customer.getCustomerId();
        name.setText(customer.getCustomerName());
        address.setText(customer.getCustomerAddress());
        city.getSelectionModel().select(customer.getCustomerCity());
        setCountry();
        zip.setText(customer.getCustomerZip());
        phone.setText(customer.getCustomerPhone());
    }
    // validates name
    public boolean validateName(String name) {
        if(name.isEmpty()) {
            errors.add("Name must contain characters");
            return false;
        } else {
            return true;
        }
    }
    //validates address
    public boolean validateAddress(String address) {
        if(address.isEmpty()) {
            errors.add("Address must contain characters");
            return false;
        } else {
            return true;
        }
    }
    //validates city
    public boolean validateCity(int city) {
        if(city == 0) {
            errors.add("A City must be selected");
            return false;
        } else {
            return true;
        }
    }
    //validates zip
    public boolean validateZip(String zip) {
        if(zip.isEmpty()) {
            errors.add("Zip must contain characters");
            return false;
        } else {
            return true;
        }
    }
    //validates phone
    public boolean validatePhone(String phone) {
        if(phone.isEmpty()) {
            errors.add("Phone must contain characters");
            return false;
        } else {
            return true;
        }
    }
    // displays errors
    public String displayErrors(){
        String s = "";
        if(errors.size() > 0) {
            for(String err : errors) {
                s = s.concat(err);
            }
            return s;
        } else {
            s = "Database Error";
            return s;
        }
    }
    
    @Override
    //initializes setting items
    public void initialize(URL url, ResourceBundle rb) {
        city.setItems(cities);
    }    
    
}
