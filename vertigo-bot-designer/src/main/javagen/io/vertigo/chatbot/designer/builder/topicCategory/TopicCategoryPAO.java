package io.vertigo.chatbot.designer.builder.topicCategory;

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
public final class TopicCategoryPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public TopicCategoryPAO(final TaskManager taskManager) {
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
	 * Execute la tache TkAddTopicWithCategory.
	 * @param topCatId Long
	 * @param topIds List de Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkAddTopicWithCategory",
			request = "update topic" + 
 "			set top_cat_id = #topCatId#" + 
 "			where top_id in (#topIds.rownum#)",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void addTopicWithCategory(@io.vertigo.datamodel.task.proxy.TaskInput(name = "topCatId", smartType = "STyId") final Long topCatId, @io.vertigo.datamodel.task.proxy.TaskInput(name = "topIds", smartType = "STyId") final java.util.List<Long> topIds) {
		final Task task = createTaskBuilder("TkAddTopicWithCategory")
				.addValue("topCatId", topCatId)
				.addValue("topIds", topIds)
				.build();
		getTaskManager().execute(task);
	}

	/**
	 * Execute la tache TkRemoveAllCategoryByBotId.
	 * @param botId Long
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkRemoveAllCategoryByBotId",
			request = "delete from topic_category" + 
 "			where bot_id = #botId#",
			taskEngineClass = io.vertigo.basics.task.TaskEngineProc.class)
	public void removeAllCategoryByBotId(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId) {
		final Task task = createTaskBuilder("TkRemoveAllCategoryByBotId")
				.addValue("botId", botId)
				.build();
		getTaskManager().execute(task);
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
