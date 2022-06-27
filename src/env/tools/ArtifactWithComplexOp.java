// CArtAgO artifact code for project agentes

package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cartago.*;

public class ArtifactWithComplexOp extends Artifact {
	int internalCount;
	boolean sprintOK = false;
	ArrayList<Tasks> tasksListDev;
	ArrayList<Tasks> tasksListDesign;
	ArrayList<Tasks> tasksListDatabase;
	double timeSpent;
	double sizeSprint;
	// TODO:
	// Fazer a parte de priorização de tarefas.
	// Fazer ler os valores das crenças, e não do parametro

	void init() {
		internalCount = 0;
		timeSpent = 0;
		sizeSprint = 10 * 8 * 3;
		tasksListDev = new ArrayList<Tasks>();
		tasksListDesign = new ArrayList<Tasks>();
		tasksListDatabase = new ArrayList<Tasks>();

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

	// @GUARD
	// boolean sprintOKGuard(boolean a) {
	// 	return this.sprintOK;
	// }

	// @OPERATION
	// void checkFinish() {
	// 	ObsProperty prop = getObsProperty("prontas");
	// 	for (Tasks tasks2 : tasksListDev) {
	// 		if (tasks2 != null && tasks2.getStatus() != "DONE") {
	// 			prop.updateValue(2222);
	// 			break;
	// 		}
	// 	}
	// }

	@OPERATION
	void getTaskDev(String coding, OpFeedbackParam tarefa) {
		if (checkIfDone()) {
			signal("tasks_done");
			System.out.println("TEMPO GASTO -= ====== " + timeSpent);
		}
		// Ordenar, pegar por prioridade.
		// Calcular o tempo
		// Para os pontos, dividir pela habilidade, isso vai dar o tempo.
		for (Tasks task : tasksListDev) {
			if (task != null && task.getStatus() == "TODO") {
				System.out.print(">>>> BUSCANDO TAREFAS <<<<<<<<");
				// if (task.getTaskSizeOnCoding() <= Integer.parseInt(coding)) {
					timeSpent += task.getTaskValue() / Integer.parseInt(coding);
					System.out.println("Fazendo tarefa " + task.getTaskName());
					task.setStatus("DONE");
					// break;
				// }
			}
		}

		tarefa.set(checkIfDone());
	}

	@OPERATION
	void getTaskDesign(String coding, OpFeedbackParam tarefa) {
		if (checkIfDoneDesign()) {
			signal("tasks_done");
			System.out.println("TEMPO GASTO -= ====== " + timeSpent);
		}
		// Ordenar, pegar por prioridade.
		// Calcular o tempo
		// Para os pontos, dividir pela habilidade, isso vai dar o tempo.
		for (Tasks task : tasksListDesign) {
			if (task != null && task.getStatus() == "TODO") {
				System.out.print(">>>> BUSCANDO TAREFAS <<<<<<<<");
				// if (task.getTaskSizeOnCoding() <= Integer.parseInt(coding)) {
					timeSpent += task.getTaskValue() / Integer.parseInt(coding);
					System.out.println("Fazendo tarefa " + task.getTaskName());
					task.setStatus("DONE");
					// break;
				// }
			}
		}

		tarefa.set(checkIfDoneDesign());
	}

	@OPERATION
	void getTaskDatabase(String coding, OpFeedbackParam tarefa) {
		if (checkIfDoneDatabase()) {
			signal("tasks_done");
			System.out.println("TEMPO GASTO -= ====== " + timeSpent);
		}
		// Ordenar, pegar por prioridade.
		// Calcular o tempo
		// Para os pontos, dividir pela habilidade, isso vai dar o tempo.
		for (Tasks task : tasksListDatabase) {
			if (task != null && task.getStatus() == "TODO") {
				System.out.print(">>>> BUSCANDO TAREFAS <<<<<<<<");
				// if (task.getTaskSizeOnCoding() <= Integer.parseInt(coding)) {
					timeSpent += task.getTaskValue() / Integer.parseInt(coding);
					System.out.println("Fazendo tarefa " + task.getTaskName());
					task.setStatus("DONE");
					// break;
				// }
			}
		}

		tarefa.set(checkIfDoneDatabase());
	}

	private void createTask() {
		tasksListDev = new ArrayList<Tasks>();
		tasksListDesign = new ArrayList<Tasks>();
		tasksListDatabase = new ArrayList<Tasks>();

		for (int i = 0; i < 10; i++) {
			Tasks aux = new Tasks();
			aux.setStatus("TODO");
			aux.setTaskName("task coding " + i);
			aux.setTaskSizeOnCoding((int) Math.floor(Math.random() * (5 - 1 + 1) + 1));
			aux.setTaskSizeOnDatabase(0);
			aux.setTaskSizeOnDesign(0);
			aux.setTaskSizeOnTesting(0);
			aux.setTaskValue(getStoryPoint());
			tasksListDev.add(aux);
		}

		for (int i = 0; i < 10; i++) {
			Tasks aux = new Tasks();
			aux.setStatus("TODO");
			aux.setTaskName("task design " + i);
			aux.setTaskSizeOnCoding(0);
			aux.setTaskSizeOnDatabase(0);
			aux.setTaskSizeOnDesign((int) Math.floor(Math.random() * (5 - 1 + 1) + 1));
			aux.setTaskSizeOnTesting(0);
			aux.setTaskValue(getStoryPoint());
			tasksListDesign.add(aux);
		}

		for (int i = 0; i < 10; i++) {
			Tasks aux = new Tasks();
			aux.setStatus("TODO");
			aux.setTaskName("task database " + i);
			aux.setTaskSizeOnCoding(0);
			aux.setTaskSizeOnDatabase(0);
			aux.setTaskSizeOnDesign(0);
			aux.setTaskSizeOnTesting((int) Math.floor(Math.random() * (5 - 1 + 1) + 1));
			aux.setTaskValue(getStoryPoint());
			tasksListDatabase.add(aux);
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
	private int getStoryPoint() {
		List<Integer> givenList = Arrays.asList(1, 2, 3, 5, 8, 13, 20, 40, 100);
		Random rand = new Random();
		return givenList.get(rand.nextInt(givenList.size()));
	}

	private boolean checkIfDone() {
		boolean allDone = true;
		for (Tasks tasks2 : tasksListDev) {
			if (tasks2 != null && tasks2.getStatus() != "DONE") {
				allDone = false;
				System.out.println("TASK N PRONTA " + tasks2.getTaskName() + tasks2.getStatus());
				break;
			}
		}
		return allDone;
	}

	private boolean checkIfDoneDesign() {
		boolean allDone = true;
		for (Tasks tasks2 : tasksListDesign) {
			if (tasks2 != null && tasks2.getStatus() != "DONE") {
				allDone = false;
				System.out.println("TASK N PRONTA " + tasks2.getTaskName() + tasks2.getStatus());
				break;
			}
		}
		return allDone;
	}

	private boolean checkIfDoneDatabase() {
		boolean allDone = true;
		for (Tasks tasks2 : tasksListDatabase) {
			if (tasks2 != null && tasks2.getStatus() != "DONE") {
				allDone = false;
				System.out.println("TASK N PRONTA " + tasks2.getTaskName() + tasks2.getStatus());
				break;
			}
		}
		return allDone;
	}
}
