package business;
/**
 *
 * @author Oriyn
 */
public class Asset1_5DL extends AssetDL {
        public static final double DLFACTOR = 1.5;
    
    public Asset1_5DL(String name, double cost, double salvage, int life){
        super(name,cost,salvage,life,Asset1_5DL.DLFACTOR);
    }
}
