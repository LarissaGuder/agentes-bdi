+!focus(C)
   <- lookupArtifact(C,Id);
	 .println("Entrei para o squad");
    focus(Id).
// verificar se tem tarefa antes
+!get_task(D)
	<- getTaskDev(D, Habilidade, Tarefa);
	// .println(">>>>>>>>> HABBBB ", D);
	// Tem que descansar, ninguém é de ferro
	.wait(50);
	!get_task(Habilidade).


-!get_task[error(E), error_msg(M)]
<-
    .print("error '", E, "' while executing 'start' goal: ", M).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
