package model;
//IMPORTS
import java.util.Date;

public class Project {
//ATRIBUTES
private String name = "";
private String description = "";
private Date date;
private String color = "";
//GETTERS E SETTERS

//NAME
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
//DESCRIPTION
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
//DATE
////date create project
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
//COLOR
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
//TO STRING
@Override
public String toString() {
	return "Project [name=" + name + ", description=" + description + ", date=" + date + ", color=" + color + "]";
}
}
