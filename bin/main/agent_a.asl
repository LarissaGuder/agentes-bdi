// Agent agent_a in project agentes
liberado(off).

!do_test.

+!do_test
  <- .println("[userA] preparando sprint...");
     createSprint;
     .create_agent(dev2,"agent_b.asl");
//     update;
	 .println("[userA] tarefas criadas ").

+sprint_criada
  <-  .println("[userA] sprint criada.");
      .send(agent_b, tell, liberado(on)).
      // liberado(on).

+tasks_done
  <-  .println("Todas as tarefas foram finalizadas");
      .println("<SPRINT ENCERRADA>");
      .kill_agent(agent_b);
      .kill_agent(dev2);
      .kill_agent(agent_a);
      .wait(100000000).

//

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
