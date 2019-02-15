
package business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.Math;


/**
 *
 * @author Gabe Haggard
 */
abstract public class Asset {
    private String assetNm, errorMessage;
    private int Life;
    private double cost, salvage;
    
    public Asset(){
      this.assetNm = "";
      this.cost = 0;
      this.salvage = 0;
      this.Life = 0;
      this.errorMessage = "";
    }
    public Asset (String name, double cost, double salvage, int life){
      this.assetNm = name;
      this.cost = cost;
      this.salvage = salvage;
      this.Life = life;
      this.errorMessage = "";
    }
    protected boolean isValid(){
        this.errorMessage = "";
        if (this.getAssetName().isEmpty()){
            this.errorMessage += "Asset name is missing";
        }
        else if (this.getCost() <= 0){
            this.errorMessage += "Cost must be positive";
        }
        else if (this.getSalvage() < 0){
            this.errorMessage += "Salvage cannot be negative";
        }
        else if (this.Life <= 0){
            this.errorMessage += "Life must be positive";
        }
        else if (this.getSalvage() >= this.getCost()){
            this.errorMessage += "Salvage must be less than cost";
        }
        return this.errorMessage.isEmpty();
    }
    
    public String getAssetName(){
        return this.assetNm;
    }
    public double getCost(){
        return this.cost;
    }
    public double getSalvage(){
        return this.salvage;
    }
    public int getLife(){
        return this.Life;
    }
    public String GetErrorMessage(){
        return this.errorMessage;
    }
    protected void setErrorMsg(String em){
        this.errorMessage = em;
    }
    public void setAssetNm(String assetNm) {
        this.assetNm = assetNm;
    }
    public void setLife(int Life) {
        this.Life = Life;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setSalvage(double salvage) {
        this.salvage = salvage;
    }
    abstract public double getBegBal(int year);
    abstract public double getAnnDep(int year);
    abstract public double getEndBal(int year);
}

