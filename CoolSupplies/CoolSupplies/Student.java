/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/


import java.util.*;

// line 65 "uml.ump"
public class Student
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Student> studentsById = new HashMap<Integer, Student>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Student Attributes
  private String name;
  private int id;

  //Student Associations
  private School school;
  private Grade grade;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Student(String aName, int aId, School aSchool, Grade aGrade)
  {
    name = aName;
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id. See https://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddSchool = setSchool(aSchool);
    if (!didAddSchool)
    {
      throw new RuntimeException("Unable to create student due to school. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGrade = setGrade(aGrade);
    if (!didAddGrade)
    {
      throw new RuntimeException("Unable to create student due to grade. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    Integer anOldId = getId();
    if (anOldId != null && anOldId.equals(aId)) {
      return true;
    }
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      studentsById.remove(anOldId);
    }
    studentsById.put(aId, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template attribute_GetUnique */
  public static Student getWithId(int aId)
  {
    return studentsById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithId(int aId)
  {
    return getWithId(aId) != null;
  }
  /* Code from template association_GetOne */
  public School getSchool()
  {
    return school;
  }
  /* Code from template association_GetOne */
  public Grade getGrade()
  {
    return grade;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSchool(School aSchool)
  {
    boolean wasSet = false;
    if (aSchool == null)
    {
      return wasSet;
    }

    School existingSchool = school;
    school = aSchool;
    if (existingSchool != null && !existingSchool.equals(aSchool))
    {
      existingSchool.removeStudent(this);
    }
    school.addStudent(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGrade(Grade aGrade)
  {
    boolean wasSet = false;
    if (aGrade == null)
    {
      return wasSet;
    }

    Grade existingGrade = grade;
    grade = aGrade;
    if (existingGrade != null && !existingGrade.equals(aGrade))
    {
      existingGrade.removeStudent(this);
    }
    grade.addStudent(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    studentsById.remove(getId());
    School placeholderSchool = school;
    this.school = null;
    if(placeholderSchool != null)
    {
      placeholderSchool.removeStudent(this);
    }
    Grade placeholderGrade = grade;
    this.grade = null;
    if(placeholderGrade != null)
    {
      placeholderGrade.removeStudent(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "school = "+(getSchool()!=null?Integer.toHexString(System.identityHashCode(getSchool())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "grade = "+(getGrade()!=null?Integer.toHexString(System.identityHashCode(getGrade())):"null");
  }
}