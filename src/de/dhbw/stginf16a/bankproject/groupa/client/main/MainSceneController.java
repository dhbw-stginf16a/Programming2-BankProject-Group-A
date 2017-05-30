package de.dhbw.stginf16a.bankproject.groupa.client.main;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStoreWrapper;
import de.dhbw.stginf16a.bankproject.groupa.data.DummyData;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.CustomerTooYoungException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * Created by Jan-Robin Aumann on 23.05.2017.
 */
public class MainSceneController  {

    private BankDataStoreWrapper dataStore = BankDataStoreWrapper.mainDataStore;

    @FXML
    private ListView listview_sidemenu;

    @FXML
    private ListView listview_content;

    @FXML //Closing via the listview (we know that it will always be there)
    public void close(){
        Stage stage = (Stage) listview_sidemenu.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void fillCustomersDatafx(){
        fillCustomersData();
    }

    @FXML //has to be executed at startup
    public void initialize(){
        //For Testing purposes only
        try {
            DummyData.insertDummyData(dataStore);
        } catch (CustomerTooYoungException e) {
            e.printStackTrace();
        }

            listview_sidemenu.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
                switch (newValue.intValue()) {
                    case 0: //Customers
                        fillCustomersData();
                        break;
                    case 1: //Deposits
                        fillDepositsData();
                        break;
                    case 2: //Lendings
                        fillLendingsData();
                        break;
                    case 3: //Cards
                        fillCardsData();
                        break;
                    default:
                        throw new IllegalArgumentException("Not implemented for this case");
                }
            });

    }

    public void fillCustomersData(){
        ArrayList<Customer> customers = dataStore.getCustomers();
        ObservableList<Customer> observableCustomers = FXCollections.observableArrayList(customers);

        listview_content.setItems(observableCustomers);
    }
    public void fillCardsData(){

    }
    public void fillInvestmentsData(){

    }
    public void fillDepositsData(){

    }
    public void fillLendingsData(){

    }
}
