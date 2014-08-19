package com.teckfiesta.horn.model;

public class OfferDetails {
  String title,message,img_url;
  boolean expired;
  public OfferDetails(){
	  
  }
  
  public OfferDetails( String title,String message,String img_url,boolean expired){
	  this.title=title;
	  this.message=message;
	  this.img_url=img_url;
	  this.expired=expired;
  }
  
  public boolean getExpired(){
	  return this.expired;
  }
  public void setExpired(Boolean expired){
	  this.expired=expired;
  }
  
  
  public String getTitle(){
	  return this.title;
  }
  public void setTitle(String title){
	  this.title=title;
  }
  
  public String getMessage(){
	  return this.message;
  }
  public void setMessage(String message){
	  this.message=message;
  }
  
  public String getImg_url(){
	  return this.img_url;
  }
  public void setImg_url(String img_url){
	  this.img_url=img_url;
  }
  
}
