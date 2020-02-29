import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * 
 * This is the main application.  Note that while it is a JavaFX application it doesn't
 * actually "show" the main scene.  We just need the application for the fileChooser.
 */
public class HouseBuilder extends Application{
	
	HouseFactory areaFactory = new HouseAreaFactory();
	HouseEntity house = areaFactory.createHouse("House");
	FurnitureFactory furniture = new FurnitureFactory();
	
	public void buildHouse(){
		HouseEntity downstairs = areaFactory.createHouse("Downstairs");
		HouseEntity kitchen = areaFactory.createHouse("Kitchen");
		HouseEntity upstairs = areaFactory.createHouse("Upstairs");
		HouseEntity bedroom = areaFactory.createHouse("Bedroom");
		HouseEntity bathroom = areaFactory.createHouse("Bathroom");
		
		HouseEntity sink = furniture.createHouse("Sink");
		HouseEntity counter = furniture.createHouse("Counter");
		HouseEntity bed = furniture.createHouse("Bed");
		HouseEntity dresser = furniture.createHouse("Dresser");
		HouseEntity bathtub = furniture.createHouse("Bathtub");
		
		//build the house
		house.add(downstairs);
		house.add(upstairs);
		
		upstairs.add(bedroom);
		upstairs.add(bathroom);
        
		downstairs.add(kitchen);
        
		kitchen.add(sink);
		kitchen.add(counter);
		bedroom.add(bed);
		bedroom.add(dresser);
		bathroom.add(bathtub);     
	}
	
	/**
	 * Save using serialization
	 * @param fileName
	 */
	public void save(String fileName){
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream( new FileOutputStream(fileName));
			oos.writeObject(house);  //serializing employee
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
	public void countHouseContents(){
		System.out.println("House includes: " + house.countContents() + " areas and/or furniture items.");
	}
	
	public void printHouseSpecs(){
		house.listHouseSpecs(0);
	}
	
	public HouseEntity getHouse(){
		return house;
	}
	
	
	/**
	 * Restore from serialized form
	 * @param fileName
	 */
	public void restore(String fileName){
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream( new FileInputStream(fileName));
			house = (HouseArea) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public String getFileName(Stage primaryStage){
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setInitialDirectory(new File("C:\\temp"));  // This is optional
		 fileChooser.setTitle("Serialization File");
		 File file = fileChooser.showOpenDialog(primaryStage);
		 return file.getAbsolutePath();
	}
	
	 public static void main(String[] args) {
		 launch(args);      
	 }

	@Override
	public void start(Stage primaryStage) throws Exception {
		  HouseBuilder houseBuilder = new HouseBuilder();
	      houseBuilder.buildHouse();
	      houseBuilder.save("C:\\temp\\myHouse.ser");
	      String filename = houseBuilder.getFileName(primaryStage);
	      houseBuilder.restore(filename);
	      houseBuilder.printHouseSpecs();
	      houseBuilder.countHouseContents();		
	}      	       
}
