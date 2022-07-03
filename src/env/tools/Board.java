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

	}

	// @GUARD
	// boolean sprintOKGuard(boolean a) {
	// return this.sprintOK;
	// }

	@OPERATION
	void getTaskDev(double habilidadeAtual, OpFeedbackParam habilidade, OpFeedbackParam tarefa) {
		if (checkIfDone()) {
			finalizadas[0] = true;
			signal("tasks_dev_done");
			checkIfAllDone();
		}
		for (Tasks task : tasksListDev) {
			if (task != null && task.getStatus() == "TODO") {
				timeSpent += task.getTaskValue() / habilidadeAtual;
				tarefa.set(task.getTaskName());
				task.setStatus("DONE");
				break;
				// }
			}
		}
		double habilidadeNova = (habilidadeAtual + 0.01 * (1 / habilidadeAtual));
		habilidade.set(habilidadeNova);
	}

	@OPERATION
	void getTaskDesign(double habilidadeAtual, OpFeedbackParam habilidade, OpFeedbackParam tarefa) {
		if (checkIfDoneDesign()) {
			finalizadas[1] = true;
			signal("tasks_design_done");
			checkIfAllDone();

		}

		for (Tasks task : tasksListDesign) {
			if (task != null && task.getStatus() == "TODO") {
				timeSpent += task.getTaskValue() / habilidadeAtual;
				tarefa.set(task.getTaskName());
				task.setStatus("DONE");
				break;
				// }
			}
		}
		double habilidadeNova = Math.ceil(habilidadeAtual + 0.01 * (1 / habilidadeAtual));
		habilidade.set(habilidadeNova);
	}

	@OPERATION
	void getTaskDatabase(double habilidadeAtual, OpFeedbackParam habilidade, OpFeedbackParam tarefa) {
		if (checkIfDoneDatabase()) {
			finalizadas[2] = true;
			signal("tasks_dba_done");
			checkIfAllDone();

		}
		for (Tasks task : tasksListDatabase) {
			if (task != null && task.getStatus() == "TODO") {
				timeSpent += task.getTaskValue() / habilidadeAtual;
				tarefa.set(task.getTaskName());
				task.setStatus("DONE");
				break;
				// }
			}
		}
		double habilidadeNova = habilidadeAtual + 0.01 * (1 / habilidadeAtual);
		habilidade.set(habilidadeNova);
	}

	@OPERATION
	void validaDBA() {
		for (Tasks task : tasksListDatabase) {
			if (task != null && task.getStatus() == "DONE") {
				timeSpent += 1;
				task.setStatus("APPROVED");
			}
		}
	}

	@OPERATION
	void validaDev() {
		for (Tasks task : tasksListDev) {
			if (task != null && task.getStatus() == "DONE") {
				timeSpent += 1;
				task.setStatus("APPROVED");
			}
		}
	}

	@OPERATION
	void validaDesign() {
		for (Tasks task : tasksListDesign) {
			if (task != null && task.getStatus() == "DONE") {
				timeSpent += 1;
				task.setStatus("APPROVED");
			}
		}
	}

	private void createTask(OpFeedbackParam tarefas) {
		int pontosBaixar = 0;
		tasksListDev = new ArrayList<Tasks>();
		tasksListDesign = new ArrayList<Tasks>();
		tasksListDatabase = new ArrayList<Tasks>();
		int ue = 0;
		for (int i = 0; i < 200; i++) {
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

		for (int i = 0; i < 200; i++) {
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

		for (int i = 0; i < 100; i++) {
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
			int numSprints = (int) Math.ceil(val / sizeSprint);

			prop.updateValue(
					"O tempo disponivel para cada SPRINT foi de " + sizeSprint + " o tempo gasto foi de " + val
							+ ". Para essa entrega, foram necessarias "
							+ numSprints + " sprints");
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

	private double round(double val) {
		val = val * 100;
		val = (double) ((int) val);
		val = val / 100;
		return val;
	}
}
