package business;

/**
 *
 * @author Oriyn
 */
public class AssetSYD extends Asset {
    
    private double [] begBal,endBal;
    private double [] annDepr, annDepRate;
    private boolean built;

    public AssetSYD(String name, double cost, double salvage, int life){
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
            this.annDepr = new double [getLife()];
            int usefulLife = getLife();
            int life = getLife();
            double sumOfYears = life * (life + 1) / 2;
            this.begBal[0] = getCost();
            
            
            for (int i=0; i < getLife(); i++){
                if (i > 0){
                    this.begBal[i] = this.endBal[i-1];
                }               

            double depricationRate = usefulLife / sumOfYears;
            this.annDepr[i] = this.begBal[i] * depricationRate;
            this.endBal[i] = this.begBal[i] - this.annDepr[i];
            this.annDepRate[i] = depricationRate;
            usefulLife--;
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
            return this.annDepr[year - 1];       
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
       public double getAnnDepRate(int year){
          if (!this.built){
            BuildAsset();
            if (!this.built){
                return -1;
            }
        }
            return this.annDepRate[year - 1];       
    }    
}
