//!do_test.
// !get_task.

// !discover("c0").
+!focus(C)
   <- lookupArtifact(C,Id);
	 	.println("Focussssss ", C);
    focus(Id).

// +step2_completed
// 	<- .println("[userB] pode receber tarefas.").

+!get_task(D)
  <- .println("[userB] verificando board. " , D);
	getTaskDev(D, Habilidade);
	.println("[userB] tem task ", Habilidade);
	// D = Habilidade;
	!get_task(Habilidade).
  // !discover("c0"); /// isso aqui precisa virar um lopp

  // http://jason.sourceforge.net/api/jason/stdlib/wait.html
  // .wait(50000);
  // !get_task.

-!get_task[error(E), error_msg(M)]
<-
    .print("error '", E, "' while executing 'start' goal: ", M).

// +!discover(ArtName)
//  <- lookupArtifact(ArtName,_).
// -!discover(ArtName)
//  <- .wait(100);
//     !discover(ArtName).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
