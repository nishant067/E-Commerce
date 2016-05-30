/*
 * @author : Aditya Dwivedi (2014128), Nishant Yadav(2014067)
 */
import javafx.beans.property.SimpleStringProperty;

public class Items {
	
	SimpleStringProperty pid, pname, price, cat, sid, shid, speed;
	
	public Items(String pid, String pname, String price, String cat, String sid, String shid, String speed)
	{
		this.pid = new SimpleStringProperty(pid);
		this.pname = new SimpleStringProperty(pname);
		this.price = new SimpleStringProperty(price);
		this.cat = new SimpleStringProperty(cat);
		this.sid = new SimpleStringProperty(sid);
		this.shid = new SimpleStringProperty(shid);
		this.speed = new SimpleStringProperty(speed);
	}

	public String getPid() {
		return pid.get();
	}

	public String getPname() {
		return pname.get();
	}

	public String getPrice() {
		return price.get();
	}

	public String getCat() {
		return cat.get();
	}

	public String getSid() {
		return sid.get();
	}

	public String getShid() {
		return shid.get();
	}
	
	public String getSpeed() {
		return speed.get();
	}
}
