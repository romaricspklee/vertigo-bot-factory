package io.vertigo.chatbot.domain;

import java.util.Arrays;
import java.util.Iterator;

import io.vertigo.dynamo.domain.metamodel.DtFieldName;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class DtDefinitions implements Iterable<Class<?>> {

	/**
	 * Enumération des DtDefinitions.
	 */
	public enum Definitions {
		/** Objet de données Action. */
		Action(io.vertigo.chatbot.commons.domain.Action.class),
		/** Objet de données Chatbot. */
		Chatbot(io.vertigo.chatbot.commons.domain.Chatbot.class),
		/** Objet de données ExecutorTrainingCallback. */
		ExecutorTrainingCallback(io.vertigo.chatbot.commons.domain.ExecutorTrainingCallback.class),
		/** Objet de données Intent. */
		Intent(io.vertigo.chatbot.commons.domain.Intent.class),
		/** Objet de données IntentTrainingSentence. */
		IntentTrainingSentence(io.vertigo.chatbot.commons.domain.IntentTrainingSentence.class),
		/** Objet de données MediaFileInfo. */
		MediaFileInfo(io.vertigo.chatbot.commons.domain.MediaFileInfo.class),
		/** Objet de données RunnerInfo. */
		RunnerInfo(io.vertigo.chatbot.commons.domain.RunnerInfo.class),
		/** Objet de données SmallTalkExport. */
		SmallTalkExport(io.vertigo.chatbot.commons.domain.SmallTalkExport.class),
		/** Objet de données TrainerInfo. */
		TrainerInfo(io.vertigo.chatbot.commons.domain.TrainerInfo.class),
		/** Objet de données Training. */
		Training(io.vertigo.chatbot.commons.domain.Training.class),
		/** Objet de données UtterText. */
		UtterText(io.vertigo.chatbot.commons.domain.UtterText.class)		;

		private final Class<?> clazz;

		private Definitions(final Class<?> clazz) {
			this.clazz = clazz;
		}

		/** 
		 * Classe associée.
		 * @return Class d'implémentation de l'objet 
		 */
		public Class<?> getDtClass() {
			return clazz;
		}
	}

	/**
	 * Enumération des champs de Action.
	 */
	public enum ActionFields implements DtFieldName<io.vertigo.chatbot.commons.domain.Action> {
		/** Propriété 'ID'. */
		actId,
		/** Propriété 'Text'. */
		title,
		/** Propriété 'Chatbot'. */
		botId	}

	/**
	 * Enumération des champs de Chatbot.
	 */
	public enum ChatbotFields implements DtFieldName<io.vertigo.chatbot.commons.domain.Chatbot> {
		/** Propriété 'ID'. */
		botId,
		/** Propriété 'Name'. */
		name,
		/** Propriété 'Creation date'. */
		creationDate,
		/** Propriété 'Status'. */
		status	}

	/**
	 * Enumération des champs de ExecutorTrainingCallback.
	 */
	public enum ExecutorTrainingCallbackFields implements DtFieldName<io.vertigo.chatbot.commons.domain.ExecutorTrainingCallback> {
		/** Propriété 'Name'. */
		name,
		/** Propriété 'Succes'. */
		success,
		/** Propriété 'Model version'. */
		trainedModelVersion,
		/** Propriété 'Training logs'. */
		trainingLog	}

	/**
	 * Enumération des champs de Intent.
	 */
	public enum IntentFields implements DtFieldName<io.vertigo.chatbot.commons.domain.Intent> {
		/** Propriété 'ID'. */
		intId,
		/** Propriété 'Title'. */
		title,
		/** Propriété 'Description'. */
		description,
		/** Propriété 'SmallTalk'. */
		isSmallTalk,
		/** Propriété 'Enabled'. */
		isEnabled,
		/** Propriété 'Chatbot'. */
		botId	}

	/**
	 * Enumération des champs de IntentTrainingSentence.
	 */
	public enum IntentTrainingSentenceFields implements DtFieldName<io.vertigo.chatbot.commons.domain.IntentTrainingSentence> {
		/** Propriété 'ID'. */
		itsId,
		/** Propriété 'Text'. */
		text,
		/** Propriété 'Intent'. */
		intId	}

	/**
	 * Enumération des champs de MediaFileInfo.
	 */
	public enum MediaFileInfoFields implements DtFieldName<io.vertigo.chatbot.commons.domain.MediaFileInfo> {
		/** Propriété 'Id'. */
		filId,
		/** Propriété 'Name'. */
		fileName,
		/** Propriété 'MimeType'. */
		mimeType,
		/** Propriété 'Size'. */
		length,
		/** Propriété 'Modification Date'. */
		lastModified,
		/** Propriété 'path'. */
		filePath,
		/** Propriété 'data'. */
		fileData	}

	/**
	 * Enumération des champs de RunnerInfo.
	 */
	public enum RunnerInfoFields implements DtFieldName<io.vertigo.chatbot.commons.domain.RunnerInfo> {
		/** Propriété 'Name'. */
		name,
		/** Propriété 'Node state'. */
		state,
		/** Propriété 'Component version'. */
		agentVersion,
		/** Propriété 'Model version'. */
		loadedModelVersion	}

	/**
	 * Enumération des champs de SmallTalkExport.
	 */
	public enum SmallTalkExportFields implements DtFieldName<io.vertigo.chatbot.commons.domain.SmallTalkExport> {
		/** Propriété 'intent'. */
		intent,
		/** Propriété 'intentTrainingSentences'. */
		intentTrainingSentences,
		/** Propriété 'utterTexts'. */
		utterTexts	}

	/**
	 * Enumération des champs de TrainerInfo.
	 */
	public enum TrainerInfoFields implements DtFieldName<io.vertigo.chatbot.commons.domain.TrainerInfo> {
		/** Propriété 'Name'. */
		name,
		/** Propriété 'Training in progress'. */
		trainingInProgress,
		/** Propriété 'Training log'. */
		latestTrainingLog,
		/** Propriété 'Start time'. */
		startTime,
		/** Propriété 'End time'. */
		endTime,
		/** Propriété 'Duration'. */
		duration	}

	/**
	 * Enumération des champs de Training.
	 */
	public enum TrainingFields implements DtFieldName<io.vertigo.chatbot.commons.domain.Training> {
		/** Propriété 'ID'. */
		traId,
		/** Propriété 'Start time'. */
		startTime,
		/** Propriété 'End time'. */
		endTime,
		/** Propriété 'Version'. */
		versionNumber,
		/** Propriété 'Tag'. */
		tag,
		/** Propriété 'Valid'. */
		valid,
		/** Propriété 'Duration'. */
		duration,
		/** Propriété 'Chatbot'. */
		botId	}

	/**
	 * Enumération des champs de UtterText.
	 */
	public enum UtterTextFields implements DtFieldName<io.vertigo.chatbot.commons.domain.UtterText> {
		/** Propriété 'ID'. */
		utxId,
		/** Propriété 'Text'. */
		text,
		/** Propriété 'Intent'. */
		intId,
		/** Propriété 'Action'. */
		actId	}

	/** {@inheritDoc} */
	@Override
	public Iterator<Class<?>> iterator() {
		return new Iterator<Class<?>>() {
			private Iterator<Definitions> it = Arrays.asList(Definitions.values()).iterator();

			/** {@inheritDoc} */
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			/** {@inheritDoc} */
			@Override
			public Class<?> next() {
				return it.next().getDtClass();
			}
		};
	}
}
