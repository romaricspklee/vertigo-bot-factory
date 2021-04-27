package io.vertigo.chatbot.designer.builder.services.bot;

import java.time.LocalDate;
import java.util.Optional;

import javax.inject.Inject;

import io.vertigo.account.authorization.annotations.Secured;
import io.vertigo.account.authorization.annotations.SecuredOperation;
import io.vertigo.chatbot.authorization.GlobalAuthorizations;
import io.vertigo.chatbot.authorization.SecuredEntities.ChatbotOperations;
import io.vertigo.chatbot.commons.dao.ChatbotDAO;
import io.vertigo.chatbot.commons.domain.Chatbot;
import io.vertigo.chatbot.commons.domain.topic.NluTrainingSentence;
import io.vertigo.chatbot.commons.domain.topic.ResponseButton;
import io.vertigo.chatbot.commons.domain.topic.ResponseTypeEnum;
import io.vertigo.chatbot.commons.domain.topic.SmallTalk;
import io.vertigo.chatbot.commons.domain.topic.Topic;
import io.vertigo.chatbot.commons.domain.topic.TopicCategory;
import io.vertigo.chatbot.commons.domain.topic.UtterText;
import io.vertigo.chatbot.designer.builder.services.NodeServices;
import io.vertigo.chatbot.designer.builder.services.ResponsesButtonServices;
import io.vertigo.chatbot.designer.builder.services.TrainingServices;
import io.vertigo.chatbot.designer.builder.services.UtterTextServices;
import io.vertigo.chatbot.designer.builder.services.topic.SmallTalkServices;
import io.vertigo.chatbot.designer.builder.services.topic.TopicCategoryServices;
import io.vertigo.chatbot.designer.builder.services.topic.TopicServices;
import io.vertigo.chatbot.designer.commons.services.FileServices;
import io.vertigo.chatbot.designer.utils.AuthorizationUtils;
import io.vertigo.chatbot.designer.utils.UserSessionUtils;
import io.vertigo.commons.transaction.Transactional;
import io.vertigo.core.lang.Assertion;
import io.vertigo.core.node.component.Component;
import io.vertigo.datamodel.criteria.Criterions;
import io.vertigo.datamodel.structure.model.DtList;
import io.vertigo.datamodel.structure.model.DtListState;
import io.vertigo.datastore.filestore.model.FileInfoURI;
import io.vertigo.datastore.filestore.model.VFile;
import io.vertigo.datastore.impl.filestore.model.StreamFile;

@Transactional
@Secured("BotUser")
public class ChatbotServices implements Component {

	@Inject
	private ChatbotDAO chatbotDAO;

	@Inject
	private ResponsesButtonServices responsesButtonServices;

	@Inject
	private UtterTextServices utterTextServices;

	@Inject
	private SmallTalkServices smallTalkServices;

	@Inject
	private TopicServices topicServices;

	@Inject
	private FileServices fileServices;

	@Inject
	private ChatbotProfilServices chatbotProfilServices;

	@Inject
	private TrainingServices trainingServices;

	@Inject
	private NodeServices nodeServices;

	@Inject
	private TopicCategoryServices topicCategoryServices;

	public Chatbot saveChatbot(@SecuredOperation("botAdm") final Chatbot chatbot, final Optional<FileInfoURI> personPictureFile,
			final UtterText utterTextFailure,
			final UtterText utterTextStart,
			final UtterText utterTextEnd,
			final Topic topicFailure, final Topic topicStart, final Topic topicEnd, final TopicCategory topicCategory) {

		Assertion.check().isNotNull(chatbot);
		Assertion.check().isNotNull(utterTextFailure);
		Assertion.check().isNotNull(utterTextStart);
		Assertion.check().isNotNull(utterTextEnd);
		// ---

		// Avatar
		Long oldAvatar = null;
		if (personPictureFile.isPresent()) {
			oldAvatar = chatbot.getFilIdAvatar();
			final VFile fileTmp = fileServices.getFileTmp(personPictureFile.get());
			final FileInfoURI fileInfoUri = fileServices.saveFile(fileTmp);
			chatbot.setFilIdAvatar((Long) fileInfoUri.getKey());
		}

		// chatbot save
		chatbot.setStatus("OK");
		final Chatbot savedChatbot = chatbotDAO.save(chatbot);

		// clean old avatar
		if (oldAvatar != null) {
			fileServices.deleteFile(oldAvatar);
		}

		// save default topics
		topicCategory.setBotId(chatbot.getBotId());
		topicCategoryServices.saveCategory(chatbot, topicCategory);

		//TopicFailure
		manageBasicTopic(savedChatbot, topicCategory, topicFailure, utterTextFailure);

		//Topic Start
		manageBasicTopic(savedChatbot, topicCategory, topicStart, utterTextStart);

		//Topic End
		manageBasicTopic(savedChatbot, topicCategory, topicEnd, utterTextEnd);

		return savedChatbot;
	}

	public void manageBasicTopic(@SecuredOperation("botAdm") final Chatbot chatbot, final TopicCategory topicCategory, final Topic topic, final UtterText utterText) {
		topic.setBotId(chatbot.getBotId());
		topic.setTopCatId(topicCategory.getTopCatId());
		SmallTalk smt = smallTalkServices.getSmallTalkByTopId(topic.getTopId());
		//Saving the topic is executed after, because a null response is needed if the topic has no topId yet
		topicServices.save(topic);

		if (smt == null) {
			smt = new SmallTalk();
			smt.setTopId(topic.getTopId());
			smt.setRtyId(ResponseTypeEnum.RICH_TEXT.name());
		}
		final DtList<UtterText> utterTexts = new DtList<UtterText>(UtterText.class);
		utterTexts.add(utterText);

		smallTalkServices.saveSmallTalk(chatbot, smt, new DtList<NluTrainingSentence>(NluTrainingSentence.class), new DtList<NluTrainingSentence>(NluTrainingSentence.class), utterTexts,
				new DtList<>(ResponseButton.class), topic);
	}

	public Boolean deleteChatbot(@SecuredOperation("botAdm") final Chatbot bot) {

		// Delete node
		nodeServices.deleteChatbotNodeByBot(bot);
		// Delete training and all media file
		trainingServices.removeAllTraining(bot);
		utterTextServices.removeAllUtterTextByBotId(bot);
		responsesButtonServices.removeAllSMTButtonsByBot(bot);
		// Delete training, reponsetype and smallTalk
		topicServices.removeAllNTSFromBot(bot);
		smallTalkServices.removeAllSmallTalkFromBot(bot);
		topicServices.removeAllTopicsFromBot(bot);
		topicCategoryServices.removeAllCategoryByBot(bot);

		chatbotProfilServices.deleteAllProfilByBot(bot);
		chatbotDAO.delete(bot.getBotId());

		// Delete avatar file reference in bot
		if (bot.getFilIdAvatar() != null) {
			fileServices.deleteChatbotFile(bot, bot.getFilIdAvatar());
		}
		return true;
	}

	public DtList<Chatbot> getMySupervisedChatbots() {
		if (AuthorizationUtils.hasAuthorization(GlobalAuthorizations.AtzSuperAdm)) {
			return getAllChatbots();
		}
		return chatbotDAO.getChatbotByPerId(UserSessionUtils.getLoggedPerson().getPerId());
	}

	@Secured("SuperAdm")
	public DtList<Chatbot> getAllChatbots() {
		return chatbotDAO.findAll(Criterions.alwaysTrue(), DtListState.of(100));
	}

	@Secured("SuperAdm")
	public Chatbot getNewChatbot() {
		final Chatbot newChatbot = new Chatbot();
		newChatbot.setCreationDate(LocalDate.now());

		return newChatbot;
	}

	public Chatbot getChatbotById(final Long botId) {
		Assertion.check().isNotNull(botId);
		// ---
		final Chatbot chatbot = chatbotDAO.get(botId);
		AuthorizationUtils.checkRights(chatbot, ChatbotOperations.botVisitor, "can't get the chatbot : not enough right");
		return chatbot;
	}

	public VFile getAvatar(@SecuredOperation("botVisitor") final Chatbot bot) {
		if (bot.getFilIdAvatar() == null) {
			return getNoAvatar();
		}
		return fileServices.getFile(bot.getFilIdAvatar());
	}

	public VFile getNoAvatar() {
		return StreamFile.of(
				"noAvatar.png",
				"image/png",
				ChatbotServices.class.getResource("/noAvatar.png"));
	}

}
