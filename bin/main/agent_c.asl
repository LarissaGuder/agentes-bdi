// !create_and_use.
//
// +!create_and_use : true
//   <- checkFinish;
//   .wait(100);
//   !create_and_use.

+tasks_done
  <-  .println("PO > Entrega recebida").

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
