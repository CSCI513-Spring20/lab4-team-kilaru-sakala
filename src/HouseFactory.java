
public abstract class HouseFactory {
	//factory method
	abstract HouseEntity createHouse(String item);
	//an operation calling factory method
	public HouseEntity buildHouse(String type) {
		HouseEntity houseEntity = createHouse(type);
		return houseEntity;
		
	}

}
