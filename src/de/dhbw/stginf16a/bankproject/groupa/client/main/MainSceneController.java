package de.dhbw.stginf16a.bankproject.groupa.client.main;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStoreWrapper;
import de.dhbw.stginf16a.bankproject.groupa.data.DataStoreUpdateEventListener;
import de.dhbw.stginf16a.bankproject.groupa.data.DummyData;
import de.dhbw.stginf16a.bankproject.groupa.data.account_types.Deposit;
import de.dhbw.stginf16a.bankproject.groupa.data.card_types.Card;
import de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions.DeleteCustomerAction;
import de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions.DeleteCustomerCardAction;
import de.dhbw.stginf16a.bankproject.groupa.data.lending_types.Lending;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.CustomerTooYoungException;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.awt.datatransfer.StringSelection;
import java.lang.reflect.Array;
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

    public void fillCustomersData() {
        ArrayList<Customer> customers = dataStore.getCustomers();
        ArrayList<String> customerStrings = new ArrayList<>();
        ObservableMap<String, Customer> observableCustomers = FXCollections.observableHashMap();
        for (Customer customer : customers) {
            String customerString =
                    "Customer ID: "
                            + customer.customerId
                            + ", Name: "
                            + customer.lastName
                            + ", "
                            + customer.firstName
                            + ", Address: "
                            + customer.address
                            + ", born "
                            + customer.birthday.toString()
                            + ", "
                            + customer.gender
                            + ", EMail: "
                            + customer.email;
            customerStrings.add(customerString);
            observableCustomers.put(customerString, customer);
        }

        listview_content.setItems(FXCollections.observableArrayList(customerStrings));

        listview_content.setCellFactory(lv -> {

            ListCell<String> cell = new ListCell<>();

            ContextMenu contextMenu = new ContextMenu();


            MenuItem deleteItem = new MenuItem();
            deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.itemProperty()));
            deleteItem.setOnAction(event -> {
                String item = cell.getItem();
                Customer customerItem = observableCustomers.get(item);
                dataStore.dispatch(new DeleteCustomerAction(customerItem.customerId));
                listview_content.getItems().remove(cell.getItem());
            });
            contextMenu.getItems().add(deleteItem);

            cell.textProperty().bind(cell.itemProperty());

            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });

            return cell;
        });
    }
    public void fillCardsData(){
        ArrayList<Card> cards = dataStore.getAllCards();
        ArrayList<String> cardStrings = new ArrayList<>();
        ObservableMap<String, Card> observableCards = FXCollections.observableHashMap();
        for(Card card : cards){
            String cardstring =
                    "Card id: "
                    + card.getCardId()
                    + ", Card owner: "
                    + dataStore.getCustomerById(card.getCardHolderId())
                    + ", Deposit id:" + card.getDepositId();
            cardStrings.add(cardstring);
            observableCards.put(cardstring, card);
        }

        listview_content.setItems(FXCollections.observableArrayList(cardStrings));
        listview_content.setCellFactory(lv -> {

            ListCell<String> cell = new ListCell<>();

            ContextMenu contextMenu = new ContextMenu();


            MenuItem deleteItem = new MenuItem();
            deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.itemProperty()));
            deleteItem.setOnAction(event -> {
                String item = cell.getItem();
                Card carditem = observableCards.get(item);
                dataStore.dispatch(new DeleteCustomerCardAction(carditem.cardHolderId, carditem.depositId, carditem.cardId));
                listview_content.getItems().remove(cell.getItem());
            });

            contextMenu.getItems().add(deleteItem);

            cell.textProperty().bind(cell.itemProperty());

            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });
            return cell ;
        });
    }
    public void fillDepositsData(){
        ArrayList<Deposit> deposits = dataStore.getAllDeposits();
        ArrayList<String> depositStrings = new ArrayList<>();
        for (Deposit deposit : deposits) {
            depositStrings.add("Deposit ID: " + deposit.getId() + ", Deposit holder: " + dataStore.getCustomerById(deposit.accountHolderId) + ", Balance: " + (deposit.getBalance() / 100) + "€");
        }
        ObservableList<String> observableDeposits = FXCollections.observableArrayList(depositStrings);
        listview_content.setItems(observableDeposits);
    }
    public void fillLendingsData(){
        ObservableList<Lending> observableLendings = FXCollections.observableArrayList(dataStore.getAllLendigs());
        listview_content.setItems(observableLendings);
    }
}
