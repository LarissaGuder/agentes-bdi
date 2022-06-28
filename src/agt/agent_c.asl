// !create_and_use.
//
// +!create_and_use : true
//   <- checkFinish;
//   .wait(100);
//   !create_and_use.
// discover("c0").

+tasks_done [artifact_name(Id,"c0")]
  <-  .println("PO > Entrega recebida").

+!discover(ArtName)
 <- lookupArtifact(ArtName,_).
-!discover(ArtName)
 <- .wait(10);
    !discover(ArtName).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
