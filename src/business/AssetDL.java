package business;

/**
 *
 * @author Oriyn
 */
public class AssetDL extends Asset {
    private double [] begBal,endBal;
    private double [] annDepr;
    private boolean built;
    private double dlFactor;
    
    public AssetDL (String name, double cost, double salvage, int life, double dlFac){
        super(name,cost,salvage,life);
        this.dlFactor = dlFac;
        if (isValid()){
            BuildAsset();
        }
    }
private void BuildAsset(){
        this.built = false;
        try{
            this.begBal = new double[getLife()];
            this.endBal = new double[getLife()];
            this.annDepr = new double [getLife()];
            
            double straightLineAnnualdep = (getCost() - getSalvage())/getLife();
            double DDAnnualRate = (1.0/getLife())* this.dlFactor;
            this.begBal[0] = getCost();
            
            
            for (int i=0; i < getLife(); i++){
                if (i > 0){
                    this.begBal[i] = this.endBal[i-1];
                }               
                double currentDDL = this.begBal[i] * DDAnnualRate;
                
                if (currentDDL < straightLineAnnualdep ){
                    currentDDL = straightLineAnnualdep; 
                }
                if ((this.begBal[i] - currentDDL) < getSalvage()){
                    currentDDL = this.begBal[i] - getSalvage();
                }
                this.annDepr [i] = currentDDL;
                this.endBal [i] = this.begBal[i] - this.annDepr[i]; 
                
            }
            this.built = true;
 
        }
        
        catch(Exception e){
            setErrorMsg("Asset build error" + e.getMessage());
            this.built = false;
        }       
    }
    @Override
         public double getAnnDep(int year){
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
            return this.annDepr[year -1];       
    }
    @Override
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
    @Override
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
