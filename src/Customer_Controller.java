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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Customer_Controller {
	
	@FXML
    private Button buy;
	@FXML
    private Button back;

	@FXML
    private TableColumn<?, ?> Product;

    @FXML
    private TableColumn<?, ?> Shipper;
    @FXML
    private Button track;
    @FXML
    private TableColumn<?, ?> Status;
    @FXML
    private Label buy_er;
    @FXML
    private TableView<Order> track_table;
    @FXML
    private Label buy_cr;
    @FXML
    private AnchorPane track_pane;
	
	@FXML
    private Button sn;
	
	@FXML
    private Button spr;
	
	@FXML
    private Button scg;
	
	@FXML
    private Button sd;

	@FXML
    private TableColumn<?, ?> cusp;

    @FXML
    private TextField rhigh;

    @FXML
    private Button log;

    @FXML
    private TextField cno;

    @FXML
    private TextField pname;

    @FXML
    private Button sign;

    @FXML
    private TableView<Items> cus_table;

    @FXML
    private TextField pid;

    @FXML
    private TableColumn<?, ?> cusd;
    
    @FXML
    private TableColumn<?, ?> speed;

    @FXML
    private ComboBox<String> devs;

    @FXML
    private TextField ads;

    @FXML
    private Text fail = new Text();

    @FXML
    private Button logout;

    @FXML
    private TableColumn<?, ?> cusid;

    @FXML
    private TextField rlow;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<?, ?> cusname;

    @FXML
    private TableColumn<?, ?> cuss;
    
    @FXML
    private TableColumn<?, ?> cata;

    @FXML
    private PasswordField pwd;

    @FXML
    private PasswordField npwd;

    @FXML
    private TextField nuser;

    @FXML
    private TextField user;

    @FXML
    private ComboBox<String> catg;
    
    @FXML
    private AnchorPane my_cart;
    
    @FXML
    private AnchorPane login_page;


    
    String ID;
	ObservableList<Items> P;
	ObservableList<Order> Q;
    
    @FXML
    void initialize() {
    	login_page.setVisible(true);
		my_cart.setVisible(false);
    	
    	catg.getItems().add("Electronics");
    	catg.getItems().add("Clothing");
    	catg.getItems().add("Books");
    	
    	/*devs.getItems().add("Same Day");
    	devs.getItems().add("One Day");
    	devs.getItems().add("Two to Four Days");
    	devs.getItems().add("One Week");*/
    	
    	
    	try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBMS","DBMS","1234");
			Statement stat = con.createStatement();
			
			log.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//fail.setVisible(true);
					String id = user.getText();
					String pass = pwd.getText();
					
					try {
						
						ResultSet res = stat.executeQuery("Select * from customer where cid='"+id+"' and cpwd='"+pass+"';");
						
						if (!res.next()) {
							fail.setVisible(true);
						}
						
						else {
							login_page.setVisible(false);
							my_cart.setVisible(true);
							P = FXCollections.observableArrayList();
							cusid.setCellValueFactory(new PropertyValueFactory<>("pid"));
							cusname.setCellValueFactory(new PropertyValueFactory<>("pname"));
							cusp.setCellValueFactory(new PropertyValueFactory<>("price"));
							cusd.setCellValueFactory(new PropertyValueFactory<>("shid"));
							cuss.setCellValueFactory(new PropertyValueFactory<>("sid"));
							cata.setCellValueFactory(new PropertyValueFactory<>("cat"));
							speed.setCellValueFactory(new PropertyValueFactory<>("speed"));
							ID = user.getText();
							cus_table.setItems(P);
							
							res = stat.executeQuery("Select * from product, shipper where product.shid = shipper.shid;");
							
							while(res.next())
							{
								//do something here		
								//System.out.println(res.getString(1));

								P.add(new Items(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(11)));
							}							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			);
			
			sign.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String id = nuser.getText();
					String pass = npwd.getText();
					String nname = name.getText();
					String contact = cno.getText();
					String adds = ads.getText();
					
					
					try {
						ResultSet res = stat.executeQuery("Select * from customer where cid='"+id+"';");
						
						if (!res.next()) {						
							stat.executeUpdate("Insert into customer values ('"+id+"','"+pass+"','"+nname+"','"+contact+"','"+adds+"');");
							//add label here
						}
						else {
							//add label here about id not available
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			
			logout.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					login_page.setVisible(true);
					my_cart.setVisible(false);
				}
				
			});
			
			sn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					
					P.clear();
					String pn = pname.getText();
									
					ResultSet res;
					cus_table.setItems(P);
					
					try {
						res = stat.executeQuery("Select * from product, shipper where product.shid = shipper.shid and pname='"+pn+"';");
						while(res.next())
						{
							//do something here
							P.add(new Items(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(11)));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//System.out.println("Meh!");
				}
				
				}
			);
			
			spr.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					
					P.clear();
					String high = rhigh.getText();
					String low = rlow.getText();
									
					ResultSet res;
					cus_table.setItems(P);
					
					try {
						res = stat.executeQuery("Select * from product, shipper where product.shid = shipper.shid and price>='"+low+"' and price<='"+high+"';");
						while(res.next())
						{
							//do something here
							P.add(new Items(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(11)));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//System.out.println("Meh!");
				}
				
				}
			);
			
			scg.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					P.clear();
					
					String catagory = null;
					
					if (catg.getValue().equals("Electronics"))
						catagory = "Electronics";
					else if (catg.getValue().equals("Clothing"))
						catagory = "Clothing";
					else if (catg.getValue().equals("Books"))
						catagory = "Books";
					
					ResultSet res;
					cus_table.setItems(P);
					
					try {
						res = stat.executeQuery("Select * from product, shipper where product.shid = shipper.shid and cat = '"+catagory+"';");
						while(res.next())
						{
							//do something here
							P.add(new Items(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(11)));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
			});
			buy.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					String Pid=pid.getText();
					String shid,sid;
					try {
						ResultSet res=stat.executeQuery("Select * from product where pid='"+Pid+"';");
						if(res.next())
						{
							sid=res.getString(5);
							shid=res.getString(6);
							buy_er.setVisible(false);
							buy_cr.setVisible(true);
							res=stat.executeQuery("Select * from dbms.ordert;");
							
							Integer oid=0;
							if(res.next())
							{
								res=stat.executeQuery("Select max(oid) from dbms.ordert;");
								res.next();
								String temp=res.getString("max(oid)");
								oid=Integer.parseInt(temp)+1;
							}
							//System.out.println(Pid);
							
							stat.executeUpdate("Insert into ordert values ("+oid+",'"+Pid+"','"+sid+"','"+shid+"','"+ID+"','"+"order placed');");

							
								
						}
						else
						{
							buy_er.setVisible(true);
							buy_cr.setVisible(false);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			});
			track.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					my_cart.setVisible(false);
					track_pane.setVisible(true);
					Q = FXCollections.observableArrayList();
					Product.setCellValueFactory(new PropertyValueFactory<>("Product"));
					Shipper.setCellValueFactory(new PropertyValueFactory<>("Shipper"));
					Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
					try {
						ResultSet res=stat.executeQuery("Select * from ordert,shipper,product where ordert.shid=shipper.shid and product.pid=ordert.pid and ordert.cid='"+ID+"';");
						while(res.next())
							Q.add(new Order(res.getString(16),res.getString(9),res.getString(6)));
						track_table.setItems(Q);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			});
			back.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					track_pane.setVisible(false);
					my_cart.setVisible(true);
					
				}
			});
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
