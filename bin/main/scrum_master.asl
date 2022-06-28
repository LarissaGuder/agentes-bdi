// Agent agent_a in project agentes
// !do_test.
// !observe.
!start.

!create_and_use.
// !check_board.

+!start
  <- makeArtifact(c0,"tools.Board",[],Id);
     focus(Id);
     .println("__> Artefato criado <__").

+!create_and_use : true
  <- .println("__> Preparando SPRINT <__");
      // !setupTool(Id);
     createSprint(Tarefas);
     .println("__! Nessa sprint, precisamos baixar ", Tarefas, " !__");
     .println("__> Vou avisar o PO <__");
     .create_agent(po,"po.asl");
     .send(po,achieve,focus(c0));
     .create_agent(designer,"designer.asl");
     .send(designer,achieve,focus(c0));
     .send(designer,achieve,get_task("5"));
     .create_agent(dba,"dba.asl");
     .send(dba,achieve,focus(c0));
     .send(dba,achieve,get_task("5"));
     .create_agent(dev_1,"dev.asl");
     .send(dev_1,achieve,focus(c0));
     .send(dev_1,achieve,get_task("1"));
     .create_agent(dev_2,"dev.asl");
     .send(dev_2,achieve,focus(c0));
     .send(dev_2,achieve,get_task("4")).

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
      .println("<SPRINT ENCERRADA>").
      // .kill_agent(dev_b);
      // .kill_agent(dev_d).

+timeSpent(X)
  <- .println(X).

+tasks_dev_done
  <-  .println("__> Todas as tarefas de desenvolvimento foram finalizadas <___");
      .kill_agent(dev_1);
      .kill_agent(dev_2).

+tasks_design_done
  <-  .println("__> Todas as tarefas de design foram finalizadas <___");
      .kill_agent(designer).

+tasks_dba_done
  <-  .println("__> Todas as tarefas de dba foram finalizadas <___");
    .kill_agent(dba).

//

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
