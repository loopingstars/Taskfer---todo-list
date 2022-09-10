package model;
//IMPORTS
import java.util.Date;
import util.Dati;

public class Task {
//ATRIBUTES
private String name = "";
private String description = ""; 
private Date dateCreate;
private Date dateLimit;
private int ID = 0;
private boolean completed = false;
private String status = "";
private int time = 0;
private int idProject = 0;
private int idTag = 0;
public Task(String stringDateLimit) {
	//verificações
	Dati dat = new Dati();
	String datte = dat.StringDate();
	Date convertedLimit = dat.DateDate(datte);
	
	Date dateLimit = dat.DateDate(stringDateLimit);

	//seta a data limite
	this.setDateLimit(dateLimit);
	//seta parar não conclido
	this.setCompleted(false);
	//seta data de criação para data atual do sistema
	this.setDateCreate(convertedLimit);
	//seta tempo de decorrido para zero
	this.setTime(0);
	//id vinculado ao projeto

	this.setStatus((String)dat.statusDate(dateLimit,convertedLimit));
	
	
}
//name task
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
//DESCRIPTION
//description task
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
//DATE CREATE
//creation date task
public Date getDateCreate() {
	return dateCreate;
}
public void setDateCreate(Date dateCreate) {
	this.dateCreate = dateCreate;
}
public Date getDateLimit() {
	return dateLimit;
}
//DATE LIMIT
//date limit task
public void setDateLimit(Date newDatte) {
	this.dateLimit = newDatte;
}
public boolean getCompleted() {
	return completed;
}
//STATUS
//completed task active,progress,finished,lost
public void setCompleted(boolean completed) {
	this.completed = completed;
}
public int getTime() {
	return time;
}
public void setTime(int time) {
	this.time = time;
}
public int getIdProject() {
	return idProject;
}
public void setIdProject(int idProject) {
	this.idProject = idProject;
}
public int getIdTag() {
	return idTag;
}
public void setIdTag(int idTag) {
	this.idTag = idTag;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
}
