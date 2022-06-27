// Agent agent_a in project agentes
liberado(off).

!do_test.
!observe.

+!observe : true
  <- ?myTool(C);  // discover the tool
     focus(C).


+!do_test
  <- .println("[userA] preparando sprint...");
     createSprint;
     // .create_agent(dev2,"agent_b.asl");
//     update;
	 .println("[userA] tarefas criadas ").

+sprint_criada
  <-  .println("[userA] sprint criada.").
      // liberado(on).
+prontas(V)
    <- println("---------------------------------------------: ",V).

+tasks_done
  <-  .println("Todas as tarefas foram finalizadas");
      .println("<SPRINT ENCERRADA>");
      .kill_agent(agent_b).
      // .kill_agent(dev2).
      /// Professor, eu peço perdão por isso
      // .wait(100000000).

//
+?myTool(CounterId): true
  <- lookupArtifact("a0",CounterId).

-?myTool(CounterId): true
  <- .wait(10);
     ?myTool(CounterId).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
