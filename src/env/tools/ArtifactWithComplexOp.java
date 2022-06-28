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
	void createSprint(OpFeedbackParam tarefas) {
		createTask(tarefas);
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
	void getTaskDev(String coding, OpFeedbackParam habilidade, OpFeedbackParam tarefa) {
		signal("step2_completed");

		if (checkIfDone()) {
			signal("tasks_done");
			System.out.println("TEMPO GASTO -= ====== " + timeSpent);
		}
		// String tarefaFeita = "";
		// Ordenar, pegar por prioridade.
		// Calcular o tempo
		// Para os pontos, dividir pela habilidade, isso vai dar o tempo.
		for (Tasks task : tasksListDev) {
			if (task != null && task.getStatus() == "TODO") {
					timeSpent += task.getTaskValue() / Integer.parseInt(coding);
					tarefa.set(task.getTaskName());
					task.setStatus("DONE");
					break;
				// }
			}
		}
		int habilidadeNova = Integer.parseInt(coding) + 1;
		habilidade.set("" + habilidadeNova);


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
				// if (task.getTaskSizeOnCoding() <= Integer.parseInt(coding)) {
					timeSpent += task.getTaskValue() / Integer.parseInt(coding);
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
					task.setStatus("DONE");
					// break;
				// }
			}
		}

		tarefa.set(checkIfDoneDatabase());
	}

	private void createTask(OpFeedbackParam tarefas) {
		int pontosBaixar = 0;
		tasksListDev = new ArrayList<Tasks>();
		tasksListDesign = new ArrayList<Tasks>();
		tasksListDatabase = new ArrayList<Tasks>();

		for (int i = 0; i < 10; i++) {
			int pontos = getStoryPoint();
			pontosBaixar += pontos;
			Tasks aux = new Tasks();
			aux.setStatus("TODO");
			aux.setTaskName("task coding " + i);
			aux.setTaskSizeOnCoding((int) Math.floor(Math.random() * (5 - 1 + 1) + 1));
			aux.setTaskSizeOnDatabase(0);
			aux.setTaskSizeOnDesign(0);
			aux.setTaskSizeOnTesting(0);
			aux.setTaskValue(pontos);
			tasksListDev.add(aux);
		}

		for (int i = 0; i < 10; i++) {
			int pontos = getStoryPoint();
			pontosBaixar += pontos;
			Tasks aux = new Tasks();
			aux.setStatus("TODO");
			aux.setTaskName("task design " + i);
			aux.setTaskSizeOnCoding(0);
			aux.setTaskSizeOnDatabase(0);
			aux.setTaskSizeOnDesign((int) Math.floor(Math.random() * (5 - 1 + 1) + 1));
			aux.setTaskSizeOnTesting(0);
			aux.setTaskValue(pontos);
			tasksListDesign.add(aux);
		}

		for (int i = 0; i < 10; i++) {
			int pontos = getStoryPoint();
			pontosBaixar += pontos;

			Tasks aux = new Tasks();
			aux.setStatus("TODO");
			aux.setTaskName("task database " + i);
			aux.setTaskSizeOnCoding(0);
			aux.setTaskSizeOnDatabase(0);
			aux.setTaskSizeOnDesign(0);
			aux.setTaskSizeOnTesting((int) Math.floor(Math.random() * (5 - 1 + 1) + 1));
			aux.setTaskValue(pontos);
			tasksListDatabase.add(aux);
		}
		tarefas.set(pontosBaixar + " pontos.");

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
				break;
			}
		}
		return allDone;
	}
}
