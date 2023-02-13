package Agents;

import jade.core.Agent;
import jade.core.AID;
import jade.core.Location;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;



public class Students extends Agent {
	
	
	
	
	
       protected void setup() {
    	   
    	   
      System.out.println(this.getAID().getName()); //affichage des noms et ID des students
    	   
    	  
    	   
           addBehaviour(new CyclicBehaviour() {
        	  
			public void action() {
        		           		   
        		//Reception du message envoyees par le main agent (teacher)
                ACLMessage message=receive();
                   if (message!=null) {
                	   System.out.println("message sent by the teacher (main agent) : " + message.getContent());
                   
                   
                   //Envoi de la reponse au main agent (teacher)
                   final ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                            msg.addReceiver(new AID(message.getSender().getLocalName(), AID.ISLOCALNAME));     
                            msg.setContent("Hi,I am : ");
                           send(msg);
                         }
                    else {
                        block();
                         }
             }

			private Agent getAID() {
				
				return null;
			}

                
                
    	   });  }

           protected void takeDown(){
    	   System.out.println("The agent is killed");//cette methode est activee quand l'agent est tuer 
       }
       public void doMove(Location loc){
    	   System.out.println("Migration of agent to :"+loc.getName());//cette methode est activee quand il y a une migration de l'agent
       
}
}
