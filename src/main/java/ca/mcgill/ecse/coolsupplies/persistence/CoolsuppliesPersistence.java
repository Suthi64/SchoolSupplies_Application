package ca.mcgill.ecse.coolsupplies.persistence;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;

public class CoolsuppliesPersistence {

  private static String filename = "app.data";
  private static JsonSerializer serializer = new JsonSerializer("ca.mcgill.ecse.coolsupplies");
  public static void setFilename(String filename) {
    CoolsuppliesPersistence.filename = filename;
  }
  public static void save() {
    save(CoolSuppliesApplication.getCoolSupplies());
  }
  public static void save(CoolSupplies coolSupplies) {
    serializer.serialize(coolSupplies, filename);
  }
  public static CoolSupplies load() {
    var coolSupplies = (CoolSupplies) serializer.deserialize(filename);
    // model cannot be loaded - create empty BTMS
    if (coolSupplies == null) {
      coolSupplies = new CoolSupplies();
    } else {
      coolSupplies.reinitialize();
    }
    return coolSupplies;
  }
}
