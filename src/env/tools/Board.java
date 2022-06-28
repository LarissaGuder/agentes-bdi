// CArtAgO artifact code for project agentes

package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cartago.*;

public class Board extends Artifact {
	int internalCount;
	boolean sprintOK = false;
	ArrayList<Tasks> tasksListDev;
	ArrayList<Tasks> tasksListDesign;
	ArrayList<Tasks> tasksListDatabase;
	double timeSpent;
	double sizeSprint;
	boolean finalizadas[];
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
		finalizadas = new boolean[3];
		finalizadas[0] = false;
		finalizadas[1] = false;
		finalizadas[2] = false;
		

	}

	@OPERATION
	void createSprint(OpFeedbackParam tarefas) {
		createTask(tarefas);
		signal("sprint_criada");
		// TO DO : ADICIONAR CRIA��O DE AGENTES
		// ver oq precisa qui para executar
		// await("sprintOKGuard", true);
		// signal("step2_completed");
	}

	// @GUARD
	// boolean sprintOKGuard(boolean a) {
	// return this.sprintOK;
	// }

	@OPERATION
	void checkFinish(OpFeedbackParam pronto) {
		if (finalizadas[0] == true && finalizadas[1] == true && finalizadas[2] == true) {
			pronto.set("Sim");
			signal("tasks_done");

		} else {
			pronto.set("Nao");
		}
	}

	@OPERATION
	void getTaskDev(String habilidadeAtual, OpFeedbackParam habilidade, OpFeedbackParam tarefa) {
		if (checkIfDone()) {
			finalizadas[0] = true;
			signal("tasks_dev_done");
			checkIfAllDone();
		}
		// String tarefaFeita = "";
		// Ordenar, pegar por prioridade.
		// Calcular o tempo
		// Para os pontos, dividir pela habilidade, isso vai dar o tempo.
		for (Tasks task : tasksListDev) {
			if (task != null && task.getStatus() == "TODO") {
				timeSpent += task.getTaskValue() / Double.parseDouble(habilidadeAtual);
				tarefa.set(task.getTaskName());
				task.setStatus("DONE");
				break;
				// }
			}
		}
		double habilidadeNova = Double.parseDouble(habilidadeAtual) + 0.01 * (1 / Double.parseDouble(habilidadeAtual));
		habilidade.set("" + habilidadeNova);

	}

	@OPERATION
	void getTaskDesign(String habilidadeAtual, OpFeedbackParam habilidade, OpFeedbackParam tarefa) {
		if (checkIfDoneDesign()) {
			finalizadas[1] = true;
			signal("tasks_design_done");
			checkIfAllDone();

		}
		// Ordenar, pegar por prioridade.
		// Calcular o tempo
		// Para os pontos, dividir pela habilidade, isso vai dar o tempo.
		for (Tasks task : tasksListDesign) {
			if (task != null && task.getStatus() == "TODO") {
				timeSpent += task.getTaskValue() / Double.parseDouble(habilidadeAtual);
				tarefa.set(task.getTaskName());
				task.setStatus("DONE");
				break;
				// }
			}
		}
		double habilidadeNova = Double.parseDouble(habilidadeAtual) + 0.01 * (1 / Double.parseDouble(habilidadeAtual));
		habilidade.set("" + habilidadeNova);
	}

	@OPERATION
	void getTaskDatabase(String habilidadeAtual, OpFeedbackParam habilidade, OpFeedbackParam tarefa) {
		if (checkIfDoneDatabase()) {
			finalizadas[2] = true;
			signal("tasks_dba_done");
			checkIfAllDone();

		}
		// Ordenar, pegar por prioridade.
		// Calcular o tempo
		// Para os pontos, dividir pela habilidade, isso vai dar o tempo.
		for (Tasks task : tasksListDatabase) {
			if (task != null && task.getStatus() == "TODO") {
				timeSpent += task.getTaskValue() / Double.parseDouble(habilidadeAtual);
				tarefa.set(task.getTaskName());
				task.setStatus("DONE");
				break;
				// }
			}
		}
		double habilidadeNova = Double.parseDouble(habilidadeAtual) + 0.01 * (1 / Double.parseDouble(habilidadeAtual));
		habilidade.set("" + habilidadeNova);
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

	private void checkIfAllDone() {
		if (finalizadas[0] == true && finalizadas[1] == true && finalizadas[2] == true) {
			defineObsProperty("timeSpent", 0);
			ObsProperty prop = getObsProperty("timeSpent");
			double val = timeSpent;
			val = val * 100;
			val = (double) ((int) val);
			val = val / 100;

			prop.updateValue(
					"O tempo disponivel para a SPRINT era de " + sizeSprint + " o tempo gasto foi de " + val);
			signal("tasks_done");
		}
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
