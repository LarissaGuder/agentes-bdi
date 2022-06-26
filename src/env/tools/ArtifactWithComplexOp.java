// CArtAgO artifact code for project agentes

package tools;

import java.util.ArrayList;
import java.util.List;

import cartago.*;

public class ArtifactWithComplexOp extends Artifact {
	int internalCount;
	boolean sprintOK = false;
	ArrayList<Tasks> tasksList;
	// TODO:
	// Fazer a parte de priorização de tarefas.
	// Fazer ler os valores das crenças, e não do parametro
	
	void init() {
		defineObsProperty("prontas",0);
		internalCount = 0;
		tasksList = new ArrayList<Tasks>();

	}

	@OPERATION
	void createSprint() {
		createTask();
		signal("sprint_criada");
		// TO DO : ADICIONAR CRIA��O DE AGENTES
		// ver oq precisa qui para executar
		// await("sprintOKGuard", true);

		signal("step2_completed");
	}

	@GUARD
	boolean sprintOKGuard(boolean a) {
		return this.sprintOK;
	}

	@OPERATION
	void update() {
		this.sprintOK = true;
	}

	@OPERATION
	void getTask(String coding, String database, String design, String testing, OpFeedbackParam tarefa) {
		if (checkIfDone()) {
			signal("tasks_done");
		}

		for (Tasks task : tasksList) {
			if (task != null && task.getStatus() == "TODO") {
				System.out.print(">>>> BUSCANDO TAREFAS <<<<<<<<");
				if (task.getTaskSizeOnCoding() <= Integer.parseInt(coding)) {
					System.out.println("Fazendo tarefa " + task.getTaskName());
					task.setStatus("DONE");
					break;
				} else if (task.getTaskSizeOnDatabase() <= Integer.parseInt(database)) {
					System.out.println("Fazendo tarefa " + task.getTaskName());

					task.setStatus("DONE");
					break;
				} else if (task.getTaskSizeOnDesign() <= Integer.parseInt(design)) {
					System.out.println("Fazendo tarefa " + task.getTaskName());

					task.setStatus("DONE");
					break;
				} else if (task.getTaskSizeOnTesting() <= Integer.parseInt(testing)) {
					System.out.println("Fazendo tarefa " + task.getTaskName());

					task.setStatus("DONE");
					break;
				}
			}
		}
		
		tarefa.set(checkIfDone());
	}

	private void createTask() {
		for (int i = 0; i < 10; i++) {
			Tasks aux = new Tasks();
			aux.setStatus("TODO");
			aux.setTaskName("task " + i);
			aux.setTaskSizeOnCoding((int) Math.floor(Math.random() * (5 - 1 + 1) + 1));
			aux.setTaskSizeOnDatabase((int) Math.floor(Math.random() * (5 - 1 + 1) + 1));
			aux.setTaskSizeOnDesign((int) Math.floor(Math.random() * (5 - 1 + 1) + 1));
			aux.setTaskSizeOnTesting((int) Math.floor(Math.random() * (5 - 1 + 1) + 1));
			aux.setTaskValue((int) Math.floor(Math.random() * (5 - 1 + 1) + 1));
			tasksList.add(aux);
		}

		// tasks[1] = new Tasks();
		// tasks[1].setStatus("DONE");
		// tasks[1].setTaskName("task 01");
		// tasks[1].setTaskSizeOnCoding((int) Math.floor(Math.random()*(5-1+1)+1));
		// tasks[1].setTaskSizeOnDatabase((int) Math.floor(Math.random()*(5-1+1)+1));
		// tasks[1].setTaskSizeOnDesign((int) Math.floor(Math.random()*(5-1+1)+1));
		// tasks[1].setTaskSizeOnTesting((int) Math.floor(Math.random()*(5-1+1)+1));
		// tasks[1].setTaskValue((int) Math.floor(Math.random()*(5-1+1)+1));
		// System.out.print("acabou de criar as tarefas");
		// sprintOK = true;
		// for (Tasks tasks2 : tasks) {
		// tasks2 = new Tasks();
		// tasks2.setStatus("TODO");
		// tasks2.setTaskName("name01");
		// tasks2.setTaskSizeOnCoding(1);
		// tasks2.setTaskSizeOnDatabase(0);
		// tasks2.setTaskSizeOnDesign(3);
		// tasks2.setTaskSizeOnTesting(0);
		// tasks2.setTaskValue(2);
		// System.out.print(tasks2.getStatus());
		// }
		// for (Tasks task : tasks) {
		// if(task != null) {
		// System.out.println(task.getStatus());
		// } else {
		// System.out.println("Sem tarefas");
		// }
		// }
	}

	private boolean checkIfDone() {
		boolean allDone = true;
		for (Tasks tasks2 : tasksList) {
			if (tasks2 != null && tasks2.getStatus() != "DONE") {
				allDone = false;
				System.out.println("TASK N PRONTA " + tasks2.getTaskName() + tasks2.getStatus());
				break;
			}
		}

		return allDone;
	}
}
