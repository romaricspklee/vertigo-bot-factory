package io.vertigo.chatbot.designer.builder.topicFileExport;

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
public final class TopicFileExportPAO implements StoreServices {
	private final TaskManager taskManager;

	/**
	 * Constructeur.
	 * @param taskManager Manager des Task
	 */
	@Inject
	public TopicFileExportPAO(final TaskManager taskManager) {
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
	 * Execute la tache TkGetTopicFileExport.
	 * @param botId Long
	 * @param tcaIds List de Long
	 * @return DtList de TopicFileExport topic
	*/
	@io.vertigo.datamodel.task.proxy.TaskAnnotation(
			name = "TkGetTopicFileExport",
			request = "select top.code,\n" + 
 " 			top.tto_cd as type_topic,\n" + 
 " 			top.kto_cd as kind_topic,\n" + 
 " 			top.title,\n" + 
 " 			tca.code as category,\n" + 
 " 			top.description,\n" + 
 " 			null as tag,\n" + 
 " 			null as date_start,\n" + 
 " 			null as date_end,\n" + 
 " 			CASE \n" + 
 " 				WHEN top.is_enabled THEN 'ACTIVE'\n" + 
 " 				ELSE 'INACTIVE'\n" + 
 " 			END	as active,\n" + 
 " 			sin.script,\n" + 
 " 			tph.agg as training_phrases,\n" + 
 " 			res.agg as response,\n" + 
 " 			buttons.doublons as buttons,\n" + 
 " 			buttons_url.doublons as buttons_url,\n" + 
 " 			CASE \n" + 
 " 				WHEN smt.is_end THEN 'TRUE'\n" + 
 " 				ELSE 'FALSE'\n" + 
 " 			END	as is_end,\n" + 
 " 			string_agg(tpl.label, ',') as labels\n" + 
 " 			from topic top\n" + 
 " 			left join topic_topic_label ttl on (ttl.top_id = top.top_id)\n" + 
 " 			left join topic_label tpl on (tpl.label_id = ttl.label_id)\n" + 
 " 			left join (\n" + 
 " 					select nts.top_id,\n" + 
 " 					string_agg(nts.text,'|') agg	\n" + 
 " 					from nlu_training_sentence nts\n" + 
 " 					join topic top on top.top_id = nts.top_id					\n" + 
 " 					group by (nts.top_id)\n" + 
 " 				) as tph  on top.top_id = tph.top_id\n" + 
 " 			left join topic_category tca on tca.top_cat_id = top.top_cat_id	\n" + 
 " 			left join script_intention sin on sin.top_id = top.top_id\n" + 
 " 			left join small_talk smt on smt.top_id = top.top_id		\n" + 
 " 			left join (\n" + 
 " 					select smt.top_id,\n" + 
 " 					string_agg(utt.text,'|') agg	\n" + 
 " 					from utter_text utt\n" + 
 " 					join small_talk smt on smt.smt_id = utt.smt_id\n" + 
 " 					join topic top on smt.top_id = top.top_id									\n" + 
 " 					group by (smt.top_id)\n" + 
 " 				) as res  on top.top_id = res.top_id				\n" + 
 " 			left join (\n" + 
 " 					select t.top_id, string_agg(concat('[',rbu.text,'¤',tre.code,']'),'|') as doublons\n" + 
 " 					from response_button rbu\n" + 
 " 					join topic tre on tre.top_id = rbu.top_id_response\n" + 
 " 					join small_talk st on st.smt_id = rbu.smt_id \n" + 
 " 					join topic t on t.top_id = st.top_id \n" + 
 " 					group by (t.top_id)\n" + 
 " 				) buttons on buttons.top_id = top.top_id\n" + 
 " 			left join (\n" + 
 "                     select t.top_id,\n" + 
 "                     string_agg(concat('[',rbuurl.text,'¤',rbuurl.url,'¤', CASE WHEN rbuurl.new_tab THEN 'TRUE' ELSE 'FALSE' END,']'),'|') as doublons\n" + 
 "                     from response_button_url rbuurl\n" + 
 "                     join small_talk st on st.smt_id = rbuurl.smt_id\n" + 
 "                     join topic t on t.top_id = st.top_id\n" + 
 "                     group by (t.top_id)\n" + 
 "                 ) buttons_url on buttons_url.top_id = top.top_id\n" + 
 " 			where top.bot_id = #botId#\n" + 
 "             and tca.top_cat_id in (#tcaIds.rownum#)\n" + 
 " 			group by top.code,\n" + 
 " 				type_topic,\n" + 
 " 				kind_topic,\n" + 
 " 				top.title,\n" + 
 " 				category,\n" + 
 " 				top.description,\n" + 
 " 				tag,\n" + 
 " 				date_start,\n" + 
 " 				date_end,\n" + 
 " 				active,\n" + 
 " 				sin.script,\n" + 
 " 				training_phrases,\n" + 
 " 				response,\n" + 
 " 				buttons,\n" + 
 " 				buttons_url,\n" + 
 " 				is_end\n" + 
 " 			order by top.code",
			taskEngineClass = io.vertigo.basics.task.TaskEngineSelect.class)
	@io.vertigo.datamodel.task.proxy.TaskOutput(smartType = "STyDtTopicFileExport", name = "topic")
	public io.vertigo.datamodel.structure.model.DtList<io.vertigo.chatbot.commons.domain.topic.TopicFileExport> getTopicFileExport(@io.vertigo.datamodel.task.proxy.TaskInput(name = "botId", smartType = "STyId") final Long botId, @io.vertigo.datamodel.task.proxy.TaskInput(name = "tcaIds", smartType = "STyId") final java.util.List<Long> tcaIds) {
		final Task task = createTaskBuilder("TkGetTopicFileExport")
				.addValue("botId", botId)
				.addValue("tcaIds", tcaIds)
				.build();
		return getTaskManager()
				.execute(task)
				.getResult();
	}

	private TaskManager getTaskManager() {
		return taskManager;
	}
}
