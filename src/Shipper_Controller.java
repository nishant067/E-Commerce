/*
 * @author : Aditya Dwivedi (2014128), Nishant Yadav(2014067)
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Shipper_Controller {

    @FXML
    private TextField cost;

    @FXML
    private TextField shid;

    @FXML
    private TextField lia;

    @FXML
    private TextField sshid;

    @FXML
    private TextField shname;

    @FXML
    private Button login;
    
    @FXML
    private Button logout;

    @FXML
    private PasswordField shpwd;

    @FXML
    private Button signup;

    @FXML
    private ComboBox<String> speed;
    
    @FXML
    private ComboBox<String> work;

    @FXML
    private AnchorPane login_screen;

    @FXML
    private Label up_er;

    @FXML
    private TextField shcn;

    @FXML
    private Label up_cr;

    @FXML
    private TextField sshpwd;

    @FXML
    private AnchorPane welcome_screen;

    @FXML
    private Label log_er;
    @FXML
    private TableColumn<?, ?> Product;
    @FXML
    private TableView<Order> up_order;

    @FXML
    private TextField oid;
    @FXML
    private TableColumn<?, ?> OrderID;
    @FXML
    private TableColumn<?, ?> Status;

    @FXML
    private ComboBox<String> stat2;
    @FXML
    private Button go;
    @FXML
    private Label go_cr;
    @FXML
    private Label go_er;
    
    @FXML
    private Label shipper_bal;
    
    @FXML
    private Button balance;
 
    ObservableList<Order> Q;
    String ID;

    @FXML
    void initialize()
    {
    	login_screen.setVisible(true);
		welcome_screen.setVisible(false);
		
    	speed.getItems().add("1. Same Day");
    	speed.getItems().add("2. One Day");
    	speed.getItems().add("3. Two to Four Days");
    	speed.getItems().add("4. One Week");
    	work.getItems().add("All");
    	work.getItems().add("Week Days");
    	stat2.getItems().add("Out for Delivery");
    	stat2.getItems().add("Delivered");
    	stat2.getItems().add("Failed");


    	try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS","DBMS","1234");
			Statement stat=con.createStatement();
			
			login.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String id=shid.getText();
					String pwd=shpwd.getText();
					ID=id;
					try {
						ResultSet res =stat.executeQuery("Select * from shipper where shid='"+id+"' and shpwd='"+pwd+"';");
						if(res.next()==false)
							log_er.setVisible(true);
						else
						{
							login_screen.setVisible(false);
							welcome_screen.setVisible(true);
							Q = FXCollections.observableArrayList();
							OrderID.setCellValueFactory(new PropertyValueFactory<>("Product"));
							Product.setCellValueFactory(new PropertyValueFactory<>("Shipper"));
							Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
							try {
								res=stat.executeQuery("Select * from ordert,shipper,product where ordert.shid=shipper.shid and product.pid=ordert.pid and ordert.shid='"+ID+"';");
								while(res.next())
									Q.add(new Order(res.getString(1),res.getString(16),res.getString(6)));
								up_order.setItems(Q);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			go.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					String temp=oid.getText();
					String ch=stat2.getValue();
					try {
						ResultSet res=stat.executeQuery("Select * from ordert,shipper,product where ordert.shid=shipper.shid and product.pid=ordert.pid and ordert.shid='"+ID+"'and ordert.oid='"+temp+"';");
						if(res.next())
						{
							go_cr.setVisible(true);
							go_er.setVisible(false);
							stat.executeUpdate("update ordert set status='"+ch+"' where oid='"+temp+"';");
							res=stat.executeQuery("Select * from ordert,shipper,product where ordert.shid=shipper.shid and product.pid=ordert.pid and ordert.shid='"+ID+"';");
							Q.clear();
							while(res.next())
								Q.add(new Order(res.getString(1),res.getString(16),res.getString(6)));
							up_order.setItems(Q);
						}
						else
						{
							go_cr.setVisible(false);
							go_er.setVisible(true);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			signup.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String id=sshid.getText();
					String pwd=sshpwd.getText();
					String name=shname.getText();
					String cn=shcn.getText();
					String sp=speed.getValue();
					String co=cost.getText();
					String li=lia.getText();
					String wd=work.getValue();
					try {
						ResultSet res =stat.executeQuery("Select * from shipper where shid='"+id+"';");
						if(res.next()==false)
						{
							
							stat.executeUpdate("Insert into shipper values ('"+id+"','"+pwd+"','"+name+"','"+cn+"','"+sp+"',"+co+","+li+",'"+wd+"');");
							up_er.setVisible(false);
							up_cr.setVisible(true);
						}
						else
						{
							up_er.setVisible(true);
							up_cr.setVisible(false);

						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			
			logout.setOnAction(new EventHandler<ActionEvent> () {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					login_screen.setVisible(true);
					welcome_screen.setVisible(false);
				}
				
			});
			
			balance.setOnAction(new EventHandler<ActionEvent> () {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String my_id = shid.getText();
					double sum = 0;
					double price, cost, lia;
					//System.out.println(my_id);
					
					try {
						ResultSet res = stat.executeQuery("select * from product natural join seller natural join shipper natural join ordert where shid='"+my_id+"';");
						
						while(res.next()) {
							if (res.getString(20).equals("Delivered")) {
								price = Double.parseDouble(res.getString(5));
								cost = Double.parseDouble(res.getString(15)) / 100;
								
								sum += price * cost;
							}
							else if (res.getString(20).equals("Failed")) {
								lia = Double.parseDouble(res.getString(16)) / 100;
								sum -= sum * lia;
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String ans = String.valueOf(sum);
					shipper_bal.setText(ans);
				}
				
			});
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
