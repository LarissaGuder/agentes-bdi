+!focus(C)
   <- lookupArtifact(C,Id);
	 	.println("---> Boa sorte a todos, eu sou o PO <---");
    focus(Id).

+tasks_done
  <-  .println("---> Entrega recebida <---").


// +!end(T) : T <= 0 <- .stopMAS.
// +!end(T) : true
//    <- .print("The MAS will stop in ",T/1000," seconds!");
//       .wait(2000); !end(T-2000).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
