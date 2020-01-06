package gdb.classmodelingexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Model a house as if the class were to be part of a 3-D design system
public class House2 {
    private String softwarePackage;
    private String designFirm;

    //Constructor
    public House2(String softwarePackage, String designFirm) {
        this.softwarePackage = softwarePackage;
        this.designFirm = designFirm;
    }
    
    //Getters and setters
    public String getSoftwarePackage() {
        return softwarePackage;
    }

    public void setSoftwarePackage(String softwarePackage) {
        this.softwarePackage = softwarePackage;
    }

    public String getDesignFirm() {
        return designFirm;
    }

    public void setDesignFirm(String designFirm) {
        this.designFirm = designFirm;
    }
    
    //Behavior method
    public String sellDesign() {
        return "";
    }
}
