/**
 * 
 * All items in the house (rooms, floors, furniture are House Entities) 
 */
public interface HouseEntity {
	public void listHouseSpecs(int level);
    public int countContents();
    public void add(HouseEntity houseEntity);
}
