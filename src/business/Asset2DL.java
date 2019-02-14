package business;

/**
 *
 * @author Oriyn
 */
public class Asset2DL extends AssetDL {
    public static final double DLFACTOR = 2.0;
    
    public Asset2DL(String name, double cost, double salvage, int life){
        super(name,cost,salvage,life,Asset2DL.DLFACTOR);
    }
}
