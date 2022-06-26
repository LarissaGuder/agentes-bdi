// Agent agent_b in project agentes
codingCapability(math.floor(math.random(5))).
databaseCapability(math.floor(math.random(5))).
designCapability(math.floor(math.random(5))).
testingCapability(math.floor(math.random(5))).
semTask(false).
//!do_test.
!get_task.

+step2_completed [artifact_name(Id,"a0")]
	<- .println("[userB] pode receber tarefas.").

+!get_task
  <- .println("[userB] verificando board.");
  !discover("a0"); /// isso aqui precisa virar um lopp
  getTask("5", "5", "5", "5", Tarefa);
  .println("[userB] tem task ", Tarefa);
  // http://jason.sourceforge.net/api/jason/stdlib/wait.html
  // .wait(50000);
  !get_task.


-!get_task[error(E), error_msg(M)]
<-
    .print("error '", E, "' while executing 'start' goal: ", M).

+!discover(ArtName)
 <- lookupArtifact(ArtName,_).
-!discover(ArtName)
 <- .wait(10);
    !discover(ArtName).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
