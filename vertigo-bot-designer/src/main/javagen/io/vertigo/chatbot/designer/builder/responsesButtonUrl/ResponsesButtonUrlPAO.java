package io.vertigo.chatbot.designer.builder.responsesButtonUrl;

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
public final class ResponsesButtonUrlPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public ResponsesButtonUrlPAO(final TaskManager taskManager) {
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
	 * Execute la tache TkRemoveAllButtonsUrlByBotId.
	 * @param botId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveAllButtonsUrlByBotId",
			request = "delete from response_button_url\n" + 
 " 			where bot_id_welcome = #botId#\n" + 
 " 			or bot_id_default = #botId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeAllButtonsUrlByBotId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkRemoveAllButtonsUrlByBotId")
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkRemoveAllButtonsUrlBySmtId.
	 * @param smtId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveAllButtonsUrlBySmtId",
			request = "delete from response_button_url\n" + 
 " 			where smt_id = #smtId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeAllButtonsUrlBySmtId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "smtId", smartType = "STyId") final Long smtId) {
		final Task task = createTaskBuilder("TkRemoveAllButtonsUrlBySmtId")
				.addValue("smtId", smtId)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkRemoveAllSMTButtonsUrlByBotId.
	 * @param botId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveAllSMTButtonsUrlByBotId",
			request = "delete from response_button_url\n" + 
 " 			using small_talk smt\n" + 
 " 			join topic top on (top.top_id = smt.top_id)\n" + 
 " 			where smt.smt_id = response_button_url.smt_id\n" + 
 " 			and top.bot_id = #botId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeAllSMTButtonsUrlByBotId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkRemoveAllSMTButtonsUrlByBotId")
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
