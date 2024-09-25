/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/


import java.util.*;

// line 73 "uml.ump"
public class School
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //School Attributes
  private String name;

  //School Associations
  private List<Admin> admins;
  private CoolSupplies coolSupplies;
  private List<Student> students;
  private List<Grade> grades;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public School(String aName, CoolSupplies aCoolSupplies)
  {
    name = aName;
    admins = new ArrayList<Admin>();
    boolean didAddCoolSupplies = setCoolSupplies(aCoolSupplies);
    if (!didAddCoolSupplies)
    {
      throw new RuntimeException("Unable to create school due to coolSupplies. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    students = new ArrayList<Student>();
    grades = new ArrayList<Grade>();
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

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetOne_relatedSpecialization */
  public CoolSupplies getCoolSupplies_OneCoolSupplies()
  {
    return (CoolSupplies)coolSupplies;
  } 
  /* Code from template association_GetMany */
  public Admin getAdmin(int index)
  {
    Admin aAdmin = admins.get(index);
    return aAdmin;
  }

  public List<Admin> getAdmins()
  {
    List<Admin> newAdmins = Collections.unmodifiableList(admins);
    return newAdmins;
  }

  public int numberOfAdmins()
  {
    int number = admins.size();
    return number;
  }

  public boolean hasAdmins()
  {
    boolean has = admins.size() > 0;
    return has;
  }

  public int indexOfAdmin(Admin aAdmin)
  {
    int index = admins.indexOf(aAdmin);
    return index;
  }
  /* Code from template association_GetOne */
  public CoolSupplies getCoolSupplies()
  {
    return coolSupplies;
  }
  /* Code from template association_GetOne_clear */
  protected void clear_coolSupplies()
  {
    coolSupplies = null;
  }
  /* Code from template association_GetMany */
  public Student getStudent(int index)
  {
    Student aStudent = students.get(index);
    return aStudent;
  }

  public List<Student> getStudents()
  {
    List<Student> newStudents = Collections.unmodifiableList(students);
    return newStudents;
  }

  public int numberOfStudents()
  {
    int number = students.size();
    return number;
  }

  public boolean hasStudents()
  {
    boolean has = students.size() > 0;
    return has;
  }

  public int indexOfStudent(Student aStudent)
  {
    int index = students.indexOf(aStudent);
    return index;
  }
  /* Code from template association_GetMany */
  public Grade getGrade(int index)
  {
    Grade aGrade = grades.get(index);
    return aGrade;
  }

  public List<Grade> getGrades()
  {
    List<Grade> newGrades = Collections.unmodifiableList(grades);
    return newGrades;
  }

  public int numberOfGrades()
  {
    int number = grades.size();
    return number;
  }

  public boolean hasGrades()
  {
    boolean has = grades.size() > 0;
    return has;
  }

  public int indexOfGrade(Grade aGrade)
  {
    int index = grades.indexOf(aGrade);
    return index;
  }
  /* Code from template association_set_specialization_reqCommonCode */  /* Code from template association_SetOneToMany_relatedSpecialization */
  public boolean setCoolSupplies_CoolSupplies(CoolSupplies aCoolSupplies)
  {
    boolean wasSet = false;
    if (aCoolSupplies == null)
    {
      return wasSet;
    }

    CoolSupplies existingCoolSupplies = (CoolSupplies)coolSupplies;
    coolSupplies = aCoolSupplies;
    if (existingCoolSupplies != null && !existingCoolSupplies.equals(aCoolSupplies))
    {
      existingCoolSupplies.removeSchool(this);
    }
    coolSupplies.addSchool(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfAdminsValid()
  {
    boolean isValid = numberOfAdmins() >= minimumNumberOfAdmins();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAdmins()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Admin addAdmin()
  {
    Admin aNewAdmin = new Admin(this);
    return aNewAdmin;
  }

  public boolean addAdmin(Admin aAdmin)
  {
    boolean wasAdded = false;
    if (admins.contains(aAdmin)) { return false; }
    School existingSchool = aAdmin.getSchool();
    boolean isNewSchool = existingSchool != null && !this.equals(existingSchool);

    if (isNewSchool && existingSchool.numberOfAdmins() <= minimumNumberOfAdmins())
    {
      return wasAdded;
    }
    if (isNewSchool)
    {
      aAdmin.setSchool(this);
    }
    else
    {
      admins.add(aAdmin);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAdmin(Admin aAdmin)
  {
    boolean wasRemoved = false;
    //Unable to remove aAdmin, as it must always have a school
    if (this.equals(aAdmin.getSchool()))
    {
      return wasRemoved;
    }

    //school already at minimum (1)
    if (numberOfAdmins() <= minimumNumberOfAdmins())
    {
      return wasRemoved;
    }

    admins.remove(aAdmin);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAdminAt(Admin aAdmin, int index)
  {  
    boolean wasAdded = false;
    if(addAdmin(aAdmin))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAdmins()) { index = numberOfAdmins() - 1; }
      admins.remove(aAdmin);
      admins.add(index, aAdmin);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAdminAt(Admin aAdmin, int index)
  {
    boolean wasAdded = false;
    if(admins.contains(aAdmin))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAdmins()) { index = numberOfAdmins() - 1; }
      admins.remove(aAdmin);
      admins.add(index, aAdmin);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAdminAt(aAdmin, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCoolSupplies(CoolSupplies aCoolSupplies)
  {
    boolean wasSet = false;
    if (aCoolSupplies == null)
    {
      return wasSet;
    }

    CoolSupplies existingCoolSupplies = coolSupplies;
    coolSupplies = aCoolSupplies;
    if (existingCoolSupplies != null && !existingCoolSupplies.equals(aCoolSupplies))
    {
      existingCoolSupplies.removeSchool(this);
    }
    coolSupplies.addSchool(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStudents()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Student addStudent(String aName, int aId, Grade aGrade)
  {
    return new Student(aName, aId, this, aGrade);
  }

  public boolean addStudent(Student aStudent)
  {
    boolean wasAdded = false;
    if (students.contains(aStudent)) { return false; }
    School existingSchool = aStudent.getSchool();
    boolean isNewSchool = existingSchool != null && !this.equals(existingSchool);
    if (isNewSchool)
    {
      aStudent.setSchool(this);
    }
    else
    {
      students.add(aStudent);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStudent(Student aStudent)
  {
    boolean wasRemoved = false;
    //Unable to remove aStudent, as it must always have a school
    if (!this.equals(aStudent.getSchool()))
    {
      students.remove(aStudent);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addStudentAt(Student aStudent, int index)
  {  
    boolean wasAdded = false;
    if(addStudent(aStudent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStudents()) { index = numberOfStudents() - 1; }
      students.remove(aStudent);
      students.add(index, aStudent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStudentAt(Student aStudent, int index)
  {
    boolean wasAdded = false;
    if(students.contains(aStudent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStudents()) { index = numberOfStudents() - 1; }
      students.remove(aStudent);
      students.add(index, aStudent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStudentAt(aStudent, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfGradesValid()
  {
    boolean isValid = numberOfGrades() >= minimumNumberOfGrades();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGrades()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Grade addGrade(String aName, Bundle aBundle)
  {
    Grade aNewGrade = new Grade(aName, this, aBundle);
    return aNewGrade;
  }

  public boolean addGrade(Grade aGrade)
  {
    boolean wasAdded = false;
    if (grades.contains(aGrade)) { return false; }
    School existingSchool = aGrade.getSchool();
    boolean isNewSchool = existingSchool != null && !this.equals(existingSchool);

    if (isNewSchool && existingSchool.numberOfGrades() <= minimumNumberOfGrades())
    {
      return wasAdded;
    }
    if (isNewSchool)
    {
      aGrade.setSchool(this);
    }
    else
    {
      grades.add(aGrade);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGrade(Grade aGrade)
  {
    boolean wasRemoved = false;
    //Unable to remove aGrade, as it must always have a school
    if (this.equals(aGrade.getSchool()))
    {
      return wasRemoved;
    }

    //school already at minimum (1)
    if (numberOfGrades() <= minimumNumberOfGrades())
    {
      return wasRemoved;
    }

    grades.remove(aGrade);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGradeAt(Grade aGrade, int index)
  {  
    boolean wasAdded = false;
    if(addGrade(aGrade))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGrades()) { index = numberOfGrades() - 1; }
      grades.remove(aGrade);
      grades.add(index, aGrade);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGradeAt(Grade aGrade, int index)
  {
    boolean wasAdded = false;
    if(grades.contains(aGrade))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGrades()) { index = numberOfGrades() - 1; }
      grades.remove(aGrade);
      grades.add(index, aGrade);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGradeAt(aGrade, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=admins.size(); i > 0; i--)
    {
      Admin aAdmin = admins.get(i - 1);
      aAdmin.delete();
    }
    CoolSupplies placeholderCoolSupplies = coolSupplies;
    this.coolSupplies = null;
    if(placeholderCoolSupplies != null)
    {
      placeholderCoolSupplies.removeSchool(this);
    }
    for(int i=students.size(); i > 0; i--)
    {
      Student aStudent = students.get(i - 1);
      aStudent.delete();
    }
    for(int i=grades.size(); i > 0; i--)
    {
      Grade aGrade = grades.get(i - 1);
      aGrade.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "coolSupplies = "+(getCoolSupplies()!=null?Integer.toHexString(System.identityHashCode(getCoolSupplies())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "coolSupplies = "+(getCoolSupplies()!=null?Integer.toHexString(System.identityHashCode(getCoolSupplies())):"null");
  }
}