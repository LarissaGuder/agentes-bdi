// Agent agent_a in project agentes
liberado(off).

// !do_test.
// !observe.
!start.

!create_and_use.

+!start
  <- makeArtifact(c0,"tools.ArtifactWithComplexOp",[],Id);
     focus(Id);
     .print("Artifact created.").

+!create_and_use : true
  <- .println("[userA] preparando sprint...");
      // !setupTool(Id);
     createSprint(Tarefas);
     .println("Nessa sprint, precisamos baixar ", Tarefas);
     .create_agent(agent_b,"agent_b.asl");
     .send(agent_b,achieve,focus(c0));
     .send(agent_b,achieve,get_task("5"));
     .create_agent(agent_d,"agent_b.asl");
     .send(agent_d,achieve,focus(c0));
     .send(agent_d,achieve,get_task("4")).




// +!do_test
//   <- .println("[userA] preparando sprint...");
//      createSprint;
//      // .create_agent(dev2,"agent_b.asl");
// //     update;
// 	 .println("[userA] tarefas criadas ").

+sprint_criada
  <-  .println("[userA] sprint criada.").
      // liberado(on).
+tasks_done
  <-  .println("Todas as tarefas foram finalizadas");
      .println("<SPRINT ENCERRADA>");
      .kill_agent(agent_b);
      .kill_agent(agent_d).

      // .kill_agent(dev2).
      /// Professor, eu peço perdão por isso
      // .wait(100000000).

//

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
