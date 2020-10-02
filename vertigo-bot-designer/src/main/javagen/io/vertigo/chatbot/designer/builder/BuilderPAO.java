package io.vertigo.chatbot.designer.builder;

import javax.inject.Inject;

import io.vertigo.core.node.Node;
import io.vertigo.core.lang.Assertion;
import io.vertigo.core.lang.Generated;
import io.vertigo.datamodel.task.TaskManager;
import io.vertigo.datamodel.task.definitions.TaskDefinition;
import io.vertigo.datamodel.task.model.Task;
import io.vertigo.datamodel.task.model.TaskBuilder;
import io.vertigo.datastore.impl.dao.StoreServices;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
 @Generated
public final class BuilderPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public BuilderPAO(final TaskManager taskManager) {
		Assertion.check().isNotNull(taskManager);
		//-----
		this.taskManager = taskManager;
	}

	/**
	 * Creates a taskBuilder.
	 * @param name  the name of the task
	 * @return the builder 
	 */
	private static TaskBuilder createTaskBuilder(final String name) {
		final TaskDefinition taskDefinition = Node.getNode().getDefinitionSpace().resolve(name, TaskDefinition.class);
		return Task.builder(taskDefinition);
	}

	/**
	 * Execute la tache TkCleanOldTrainings.
	 * @param botId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkCleanOldTrainings",
			request = "update training" + 
 "			set status = 'KO'" + 
 "			where status = 'TRAINING'" + 
 "			and bot_id = #botId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void cleanOldTrainings(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkCleanOldTrainings")
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkGetNextModelNumber.
	 * @param botId Long
	 * @return Long nextModelNumber
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkGetNextModelNumber",
			request = "select coalesce(max(version_number) + 1, 1)" + 
 "			from training tra" + 
 "			where bot_id = #botId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(smartType = "STyNumber")
	public Long getNextModelNumber(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkGetNextModelNumber")
				.addValue("botId", botId)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	/**
	 * Execute la tache TkRemoveAllButtonsByBotId.
	 * @param botId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveAllButtonsByBotId",
			request = "delete from response_button" + 
 "			where bot_id_welcome = #botId#" + 
 "			or bot_id_default = #botId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeAllButtonsByBotId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkRemoveAllButtonsByBotId")
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkRemoveAllButtonsBySmtId.
	 * @param smtId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveAllButtonsBySmtId",
			request = "delete from response_button" + 
 "			where smt_id = #smtId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeAllButtonsBySmtId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "smtId", smartType = "STyId") final Long smtId) {
		final Task task = createTaskBuilder("TkRemoveAllButtonsBySmtId")
				.addValue("smtId", smtId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkRemoveAllUtterTextBySmtId.
	 * @param smtId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveAllUtterTextBySmtId",
			request = "delete from utter_text" + 
 "			where smt_id = #smtId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeAllUtterTextBySmtId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "smtId", smartType = "STyId") final Long smtId) {
		final Task task = createTaskBuilder("TkRemoveAllUtterTextBySmtId")
				.addValue("smtId", smtId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkResetDevNode.
	 * @param botId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkResetDevNode",
			request = "update chatbot_node" + 
 "			set is_dev = false" + 
 "			where bot_id = #botId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void resetDevNode(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkResetDevNode")
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
