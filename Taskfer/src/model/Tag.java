package model;

public class Tag {
//ATRIBUTES
private String name = "";
private String color = "";
//GETTERS E SETTERS
//NAME
public String getName() {
	
	return name;
}
public void setName(String name) {
	this.name = name;
}
//COLOR
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
@Override
public String toString() {
	return "Tag [name=" + name + ", color=" + color + "]";
}

}
