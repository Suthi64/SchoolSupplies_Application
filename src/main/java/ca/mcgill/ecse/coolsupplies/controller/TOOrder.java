/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.controller;
import java.sql.Date;

// line 43 "../../../../../CoolSuppliesTransferObjects.ump"
public class TOOrder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOOrder Attributes
  private String parentEmail;
  private String studentName;
  private String status;
  private String number;
  private Date date;
  private String level;
  private String authorizationCode;
  private String penaltyAuthorizationCode;
  private double totalPrice;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOOrder(String aParentEmail, String aStudentName, String aStatus, String aNumber, Date aDate, String aLevel, String aAuthorizationCode, String aPenaltyAuthorizationCode, double aTotalPrice)
  {
    parentEmail = aParentEmail;
    studentName = aStudentName;
    status = aStatus;
    number = aNumber;
    date = aDate;
    level = aLevel;
    authorizationCode = aAuthorizationCode;
    penaltyAuthorizationCode = aPenaltyAuthorizationCode;
    totalPrice = aTotalPrice;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getParentEmail()
  {
    return parentEmail;
  }

  public String getStudentName()
  {
    return studentName;
  }

  public String getStatus()
  {
    return status;
  }

  public String getNumber()
  {
    return number;
  }

  public Date getDate()
  {
    return date;
  }

  public String getLevel()
  {
    return level;
  }

  public String getAuthorizationCode()
  {
    return authorizationCode;
  }

  public String getPenaltyAuthorizationCode()
  {
    return penaltyAuthorizationCode;
  }

  public double getTotalPrice()
  {
    return totalPrice;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "parentEmail" + ":" + getParentEmail()+ "," +
            "studentName" + ":" + getStudentName()+ "," +
            "status" + ":" + getStatus()+ "," +
            "number" + ":" + getNumber()+ "," +
            "level" + ":" + getLevel()+ "," +
            "authorizationCode" + ":" + getAuthorizationCode()+ "," +
            "penaltyAuthorizationCode" + ":" + getPenaltyAuthorizationCode()+ "," +
            "totalPrice" + ":" + getTotalPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}