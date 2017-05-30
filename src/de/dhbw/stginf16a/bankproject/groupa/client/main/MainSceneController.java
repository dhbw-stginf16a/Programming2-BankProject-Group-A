package de.dhbw.stginf16a.bankproject.groupa.client.main;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStoreWrapper;
import de.dhbw.stginf16a.bankproject.groupa.data.DummyData;
import de.dhbw.stginf16a.bankproject.groupa.data.account_types.Deposit;
import de.dhbw.stginf16a.bankproject.groupa.data.card_types.Card;
import de.dhbw.stginf16a.bankproject.groupa.data.lending_types.Lending;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.CustomerTooYoungException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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
        ObservableList<Customer> observableCustomers = FXCollections.observableArrayList(dataStore.getCustomers());
        listview_content.setItems(observableCustomers);
    }
    public void fillCardsData(){
        ObservableList<Card> observableCards = FXCollections.observableArrayList(dataStore.getAllCards());
        listview_content.setItems(observableCards);
    }
    public void fillDepositsData(){
        ObservableList<Deposit> observableDeposits = FXCollections.observableArrayList(dataStore.getAllDeposits());
        listview_content.setItems(observableDeposits);
    }
    public void fillLendingsData(){
        ObservableList<Lending> observableLendings = FXCollections.observableArrayList(dataStore.getAllLendigs());
        listview_content.setItems(observableLendings);
    }
}
