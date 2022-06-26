// Agent dev in project agentes

/* Initial beliefs and rules */
codingCapability(math.floor(math.random(5))).
databaseCapability(math.floor(math.random(5))).
designCapability(math.floor(math.random(5))).
testingCapability(math.floor(math.random(5))).
//count(8).

/* Initial goals */

!start.
!observe.
//!do_task.

/* Plans */


+!start : true <- .print("eu sou o dev").

+!observe : true
  <- ?myTool(C);  // discover the tool
     focus(C).

+task(V)
  <- println("observed new value: ",V).

+tick[artifact_name(Id,"c0")]
  <- println("perceived a tick").

+?myTool(TaskId): true
  <- lookupArtifact("c0",TaskId).

-?myTool(TaskId): true
  <- .wait(1);
     ?myTool(TaskId).

//+!do_task(A)
//  <- lookupArtifact("c0",TaskId)
//     doTask("DONE").
//     sumAndSub(0.5, 1.5, NewSum, Sub);
//     println("The new sum is ",NewSum," and the sub is ",Sub).
     
//+fact(X,Y) : X<5 <- +fact(X+1, (X+1)*Y).
//+fact(X,Y) : X == 5 <- .print("fact 5 == ", Y).
+codingCapability(X) : X<5 <- .print("Dev n Especialista", X). 
+codingCapability(X) : X>5 <- .print("Dev n Especialista", X). 

+codingCapability(X) : X == 5 <- .print("Dev Especialista"). 

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
