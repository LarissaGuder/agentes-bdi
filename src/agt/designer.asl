+!focus(C)
   <- lookupArtifact(C,Id);
    focus(Id).

+!get_task(D)
	<- getTaskDesign(D, Habilidade, Tarefa);
	.println("|> Task ", Tarefa, " finalizada <| ");
	// Tem que descansar, ninguém é de ferro
	.wait(50);
	!get_task(Habilidade).


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
