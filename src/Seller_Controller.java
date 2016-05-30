/*
 * @author : Aditya Dwivedi (2014128), Nishant Yadav(2014067)
 */
import java.sql.*;

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

public class Seller_Controller {

    @FXML
    private Button Signup;

    @FXML
    private Button login;

    @FXML
    private AnchorPane login_pane;

    @FXML
    private TextField ssid;

    @FXML
    private TextField sid;

    @FXML
    private Label up_er;
    @FXML
    private Label up_cr;

    @FXML
    private TextField sname;

    @FXML
    private AnchorPane welcome_pane;

    @FXML
    private TextField sspwd;

    @FXML
    private TextField sad;

    @FXML
    private PasswordField spwd;

    @FXML
    private Label log_er;
    
    @FXML
    private Label add_er;
    
    @FXML
    private Label add_cr;

    @FXML
    private TextField scn;
    
    @FXML
    private TableColumn<?, ?> shidt;

    @FXML
    private TableColumn<?, ?> lia;

    @FXML
    private TextField shid;

    @FXML
    private TableView<Shipper> ship_table;

    @FXML
    private TableColumn<?, ?> shname;

    @FXML
    private TextField pid;
    
    @FXML
    private TableColumn<?, ?> speed;

    @FXML
    private TextField price;
    
    @FXML
    private Button add;
    
    @FXML
    private ComboBox<String> ship_cb;

    @FXML
    private TableColumn<?, ?> cost;

    @FXML
    private TableColumn<?, ?> work;

    @FXML
    private TextField pname;

    @FXML
    private Button go;
    @FXML
    private ComboBox<String> cat;
    
    @FXML
    private Button logout;
    
    @FXML
    private Button finalb;
    
    @FXML
    private Label balance;

	String ID;
	ObservableList<Shipper> P;

    
    @FXML
    void initialize()
    {
    	login_pane.setVisible(true);
		welcome_pane.setVisible(false);
    	cat.getItems().add("Electronics");
    	cat.getItems().add("Clothing");
    	cat.getItems().add("Books");
    	ship_cb.getItems().add("Speed");
    	ship_cb.getItems().add("Cost");
    	ship_cb.getItems().add("Liability");
    	ship_cb.getItems().add("Working Days");
    	try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS","DBMS","1234");
			Statement stat = con.createStatement();
			
			login.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String id=sid.getText();
					String pwd=spwd.getText();
					try {
						ResultSet res =stat.executeQuery("Select * from seller where sid='"+id+"' and spwd='"+pwd+"';");
						if(res.next()==false)
							log_er.setVisible(true);
						else
						{
							login_pane.setVisible(false);
							welcome_pane.setVisible(true);
							P=FXCollections.observableArrayList();
							shname.setCellValueFactory(new PropertyValueFactory<>("shname"));
							speed.setCellValueFactory(new PropertyValueFactory<>("speed"));
							cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
							lia.setCellValueFactory(new PropertyValueFactory<>("lia"));
							work.setCellValueFactory(new PropertyValueFactory<>("work"));
							shidt.setCellValueFactory(new PropertyValueFactory<>("shidt"));
							ID=sid.getText();
							ship_table.setItems(P);
							res=stat.executeQuery("Select * from shipper;");
							while(res.next())
							{
							P.add(new Shipper(res.getString(3), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(1)));
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			Signup.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String id=ssid.getText();
					String pwd=sspwd.getText();
					String name=sname.getText();
					String cn=scn.getText();
					String ad=sad.getText();
					try {
						ResultSet res =stat.executeQuery("Select * from seller where sid='"+id+"';");
						if(res.next()==false)
						{
							
							stat.executeUpdate("Insert into seller values ('"+id+"','"+pwd+"','"+name+"','"+cn+"','"+ad+"');");
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
			add.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String id=pid.getText();
					
					try {
						ResultSet res =stat.executeQuery("Select * from product where pid='"+id+"';");
						if(res.next()==true)
						{
							add_er.setVisible(true);
							add_cr.setVisible(false);
						}
						else
						{
							

							add_er.setVisible(false);
							String name=pname.getText();
							String pr=price.getText();
							String ca=cat.getValue();
							String sh=shid.getText();
							stat.executeUpdate("Insert into product values ('"+id+"','"+name+"',"+pr+",'"+ca+"','"+ID+"','"+sh+"');");
							add_cr.setVisible(true);

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
					P.clear();
					String order = null;
					if(ship_cb.getValue().equals("Speed"))
					{
						order="speed";
					}
					else if(ship_cb.getValue().equals("Working Days"))
					{
						order="work";
					}
					else if(ship_cb.getValue().equals("Liability"))
					{
						order="lia";
					}
					else if(ship_cb.getValue().equals("Cost"))
					{
						order="cost";
					}
					ResultSet res;
					ship_table.setItems(P);

					try {
						res = stat.executeQuery("Select * from shipper order by "+order+";");
						while(res.next())
						{
						P.add(new Shipper(res.getString(3), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(1)));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

				}
			});
			logout.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					welcome_pane.setVisible(false);
					login_pane.setVisible(true);
				}
			});
			
			finalb.setOnAction(new EventHandler<ActionEvent> () {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String my_id = sid.getText();
					double sum = 0;
					double price = 0, cost, lia;
					//System.out.println(my_id);
					
					try {
						ResultSet res = stat.executeQuery("select * from product natural join seller natural join shipper natural join ordert where sid='"+my_id+"';");
						
						while(res.next()) {
							if (res.getString(20).equals("Delivered")) {
								price = Double.parseDouble(res.getString(5));
								cost = Double.parseDouble(res.getString(15)) / 100;
								
								sum += (price-(price * cost));
							}
							else if (res.getString(20).equals("Failed")) {
								lia = Double.parseDouble(res.getString(16)) / 100;
								sum += price * lia;
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String ans = String.valueOf(sum);
					balance.setText(ans);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    

}
