/*
 * @author : Aditya Dwivedi (2014128), Nishant Yadav(2014067)
 */
import javafx.beans.property.SimpleStringProperty;

public class Shipper {
SimpleStringProperty shname,speed,cost,lia,work,shidt;
public Shipper(String shname,String speed,String cost,String lia,String work,String shidt)
{
	this.shname=new SimpleStringProperty(shname);
	this.speed=new SimpleStringProperty(speed);
	this.cost=new SimpleStringProperty(cost);
	this.lia=new SimpleStringProperty(lia);
	this.work=new SimpleStringProperty(work);
	this.shidt=new SimpleStringProperty(shidt);
}
public String getShname()
{
	return shname.get();
}

public String getSpeed()
{
	return speed.get();
}

public String getCost()
{
	return cost.get();
}
public String getLia()
{
	return lia.get();
}
public String getWork()
{
	return work.get();
}
public String getShidt()
{
	return shidt.get();
}
public void setShname(String shname)
{
	this.shname.set(shname);
}

public void setSpeed(String speed)
{
	this.shname.set(speed);
}

public void setCost(String cost)
{
	this.shname.set(cost);
}

public void setLia(String lia)
{
	this.shname.set(lia);
}

public void setWork(String work)
{
	this.shname.set(work);
}

public void setShidt(String shidt)
{
	this.shname.set(shidt);
}
}
