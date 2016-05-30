/*
 * @author : Aditya Dwivedi (2014128), Nishant Yadav(2014067)
 */
import javafx.beans.property.SimpleStringProperty;

public class Order {
SimpleStringProperty Product,Shipper,Status;
	
	public Order(String Product,String Shipper,String Status)
	{
		this.Product = new SimpleStringProperty(Product);
		this.Shipper = new SimpleStringProperty(Shipper);
		this.Status = new SimpleStringProperty(Status);

		
	}

	public String getProduct() {
		return Product.get();
	}

	public String getShipper() {
		return Shipper.get();
	}

	public String getStatus() {
		return Status.get();
	}

	
}
