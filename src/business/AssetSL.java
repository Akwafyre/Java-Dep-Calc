
package business;

/**
 *
 * @author Oriyn
 */
public class AssetSL extends Asset {
    private double [] begBal,endBal;
    private double annDepr;
    private boolean built;
    
    public AssetSL(String name, double cost, double salvage, int life){
        super(name,cost,salvage,life);
        if (isValid()){
            BuildAsset();
        }
    }
     private void BuildAsset(){
        this.built = false;
        try{
            this.begBal = new double[getLife()];
            this.endBal = new double[getLife()];
            
            double straightLineAnnualdep =(getCost() - getSalvage())/getLife();
            this.begBal[0] = getCost();
            
            for (int i=0; i < getLife(); i++){
                if (i > 0){
                    this.begBal[i] = this.endBal[i-1];
                }
                this.annDepr = straightLineAnnualdep;
                this.endBal[i] = this.begBal[i] - this.annDepr;
            }
            this.built = true; 
        }
        
        catch(Exception e){
            setErrorMsg("Asset build error" + e.getMessage());
            this.built = false;
        }
            
    }
         public double getAnnDep(){
          if (!this.built){
            BuildAsset();
            if (!this.built){
                return -1;
            }
        }
            return this.annDepr;       
    }
        public double getBegBal (int year){
        if (!this.built){
            BuildAsset();
            if (!this.built){
                return -1;
            }
        }
        if (year < 1 || year > getLife()){
             setErrorMsg("Year requested is out of bounds");
            return -1;
        }
            return this.begBal [year -1];
    }
        public double getEndBal (int year){
        if (!this.built){
            BuildAsset();
            if (!this.built){
                return -1;
            }
        }
        if (year < 1 || year > getLife()){
            setErrorMsg("Year requested is out of bounds");
            return -1;
        }
        return this.endBal [year -1];
    }     
}
