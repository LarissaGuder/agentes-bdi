// Agent scrum_master in project agentes

/* Initial beliefs and rules */

/* Initial goals */

!start.
!create_and_use.

/* Plans */

+!start : true <- .print("eu sou o scrum master").

+!create_and_use : true
  <- !setupTool(Id);
     createTask [artifact_id(Id)].

+!setupTool(C): true
  <- makeArtifact("c0","tools.ScrumBoard",[],C).


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
