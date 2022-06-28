// Agent agent_a in project agentes
liberado(off).

// !do_test.
// !observe.
!start.

!create_and_use.

+!start
  <- makeArtifact(c0,"tools.ArtifactWithComplexOp",[],Id);
     focus(Id);
     .println("__> Artefato criado <__").

+!create_and_use : true
  <- .println("__> Preparando SPRINT <__");
      // !setupTool(Id);
     createSprint(Tarefas);
     .println("__! Nessa sprint, precisamos baixar ", Tarefas, " !__");
     .println("__> Vou avisar o PO <__");
     .create_agent(po,"agent_c.asl");
     .send(po,achieve,focus(c0));
     .create_agent(dev_b,"dev.asl");
     .send(dev_b,achieve,focus(c0));
     .send(dev_b,achieve,get_task("5"));
     .create_agent(dev_d,"dev.asl");
     .send(dev_d,achieve,focus(c0));
     .send(dev_d,achieve,get_task("4")).




// +!do_test
//   <- .println("[userA] preparando sprint...");
//      createSprint;
//      // .create_agent(dev2,"agent_b.asl");
// //     update;
// 	 .println("[userA] tarefas criadas ").

+sprint_criada
  <-  .println("__> Acabei de criar a SPRINT, vou montar o squad <___").
      // liberado(on).
+tasks_done
  <-  .println("__> Todas as tarefas foram finalizadas <___");
      .println("<SPRINT ENCERRADA>");
      .kill_agent(dev_b);
      .kill_agent(dev_d).

      // .kill_agent(dev2).
      /// Professor, eu peço perdão por isso
      // .wait(100000000).

//

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
