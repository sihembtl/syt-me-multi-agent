package Agents;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.Properties;
import jade.util.ExtendedProperties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;


public class MainContainer {

	public static void main(String[] args) {
		try {
			Runtime runtime=Runtime.instance(); // cree une instance de type runtime
			Properties p=new ExtendedProperties(); 
			p.setProperty("gui", "true"); // creation de la platforme
			ProfileImpl pI=new ProfileImpl(p);// creation d'une instance de type profile
			
			//Creation du main container pour demarrer la plateform JADE
			AgentContainer Maincontainer=runtime.createMainContainer(pI);
			
			//Creation du main agent "teacher"
			AgentController mainagent=Maincontainer.createNewAgent("teacher", "Agents.Teacher_mainAgent", new Object[]{});
			mainagent.start();
			
		} catch (ControllerException e) {
			
			e.printStackTrace();
		}

	}

}
