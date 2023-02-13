package Agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Teacher_mainAgent extends Agent {
    
   
    protected void setup(){
        
        try {
            System.out.println("Main Agent : "+this.getAID().getName());//affichage du nom et ID du main agent "teacher"
          
            //Creation du container qui va contenir les agents cree par le main agent "students"
            Runtime runtime=Runtime.instance();//cree une instance de type runtime
			ProfileImpl pc=new ProfileImpl(false);// en passe false comme argument car c'est pas le main container
			pc.setParameter(ProfileImpl.MAIN_HOST, "localhost");// en relie le container au main container dont l'adresse est localhost 
            AgentContainer agentcontainer=runtime.createAgentContainer(pc);//creation du container pour les students
            
            //Creation des agents etudiants
            for(int i=1; i<5; i++){
			AgentController Student=agentcontainer.createNewAgent("Student" + String.valueOf(i),"Agents.Students", new Object[]{String.valueOf(i)});
			Student.start();// start pour cree les agents students
          
            }
            
            
            //Envoi des messages
           ACLMessage message = new ACLMessage(ACLMessage.INFORM);
           
             //Destination des messages 
            for(int i=1; i<5; i++){
           message.addReceiver(new AID("Student"+String.valueOf(i), AID.ISLOCALNAME));//envoie du msg pour chaque etudiant
          }
            
            //Le contenu des messages 
            message.setContent("Hello, Iam the teacher (main agent)");
           send(message);

           
           //Reception des reponses envoyees par les agents students
              addBehaviour(new CyclicBehaviour()
                {
                     public void action() 
                     {
                        ACLMessage msg= receive();
                        if (msg!=null){
                        	 
                            System.out.println("The student has answerd : " + msg.getContent() + msg.getSender().getLocalName());
                        } 
                       else{
                       block();
                         }
                     }
                });
            }
        



            
        catch (StaleProxyException ex) {
            Logger.getLogger(Teacher_mainAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
   



}

    