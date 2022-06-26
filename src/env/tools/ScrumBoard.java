package tools;
import cartago.*;

public class ScrumBoard extends Artifact{
	 Tasks task = new Tasks();
    void init(){
	    defineObsProperty("task",0);
	  }

	  @OPERATION void createTask(){
        signal("tick");
	    ObsProperty prop = getObsProperty("task");

//        Tasks task = new Tasks();
        task.setStatus("TODO");
        task.setTaskName("name01");
        task.setTaskSizeOnCoding(1);
        task.setTaskSizeOnDatabase(0);
        task.setTaskSizeOnDesign(3);
        task.setTaskSizeOnTesting(0);
        task.setTaskValue(2);
        
	    prop.updateValue(prop.intValue()-1);
	    
	  }

}
