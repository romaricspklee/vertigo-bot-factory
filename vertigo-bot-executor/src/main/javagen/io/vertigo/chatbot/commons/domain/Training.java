package io.vertigo.chatbot.commons.domain;

import io.vertigo.dynamo.domain.model.Entity;
import io.vertigo.dynamo.domain.model.UID;
import io.vertigo.dynamo.domain.model.VAccessor;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class Training implements Entity {
	private static final long serialVersionUID = 1L;

	private Long traId;
	private java.time.Instant startTime;
	private java.time.Instant endTime;
	private Long versionNumber;
	private String status;
	private String log;
	private String infos;
	private String warnings;

	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "ATrainingChatbot",
			fkFieldName = "botId",
			primaryDtDefinitionName = "DtChatbot",
			primaryIsNavigable = true,
			primaryRole = "Chatbot",
			primaryLabel = "Chatbot",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtTraining",
			foreignIsNavigable = false,
			foreignRole = "Training",
			foreignLabel = "Training",
			foreignMultiplicity = "0..*")
	private final VAccessor<io.vertigo.chatbot.commons.domain.Chatbot> botIdAccessor = new VAccessor<>(io.vertigo.chatbot.commons.domain.Chatbot.class, "Chatbot");

	@io.vertigo.dynamo.domain.stereotype.Association(
			name = "ATrainingMediaFileInfo",
			fkFieldName = "filIdModel",
			primaryDtDefinitionName = "DtMediaFileInfo",
			primaryIsNavigable = true,
			primaryRole = "MediaFileInfo",
			primaryLabel = "Model",
			primaryMultiplicity = "0..1",
			foreignDtDefinitionName = "DtTraining",
			foreignIsNavigable = false,
			foreignRole = "Training",
			foreignLabel = "Training",
			foreignMultiplicity = "0..*")
	private final VAccessor<io.vertigo.chatbot.commons.domain.MediaFileInfo> filIdModelAccessor = new VAccessor<>(io.vertigo.chatbot.commons.domain.MediaFileInfo.class, "MediaFileInfo");

	/** {@inheritDoc} */
	@Override
	public UID<Training> getUID() {
		return UID.of(this);
	}
	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'ID'.
	 * @return Long traId <b>Obligatoire</b>
	 */
	@Field(domain = "DoId", type = "ID", required = true, label = "ID")
	public Long getTraId() {
		return traId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'ID'.
	 * @param traId Long <b>Obligatoire</b>
	 */
	public void setTraId(final Long traId) {
		this.traId = traId;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Start time'.
	 * @return Instant startTime <b>Obligatoire</b>
	 */
	@Field(domain = "DoInstant", required = true, label = "Start time")
	public java.time.Instant getStartTime() {
		return startTime;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Start time'.
	 * @param startTime Instant <b>Obligatoire</b>
	 */
	public void setStartTime(final java.time.Instant startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'End time'.
	 * @return Instant endTime
	 */
	@Field(domain = "DoInstant", label = "End time")
	public java.time.Instant getEndTime() {
		return endTime;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'End time'.
	 * @param endTime Instant
	 */
	public void setEndTime(final java.time.Instant endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Version'.
	 * @return Long versionNumber <b>Obligatoire</b>
	 */
	@Field(domain = "DoNumber", required = true, label = "Version")
	public Long getVersionNumber() {
		return versionNumber;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Version'.
	 * @param versionNumber Long <b>Obligatoire</b>
	 */
	public void setVersionNumber(final Long versionNumber) {
		this.versionNumber = versionNumber;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Status'.
	 * @return String status <b>Obligatoire</b>
	 */
	@Field(domain = "DoCode", required = true, label = "Status")
	public String getStatus() {
		return status;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Status'.
	 * @param status String <b>Obligatoire</b>
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Log'.
	 * @return String log
	 */
	@Field(domain = "DoText", label = "Log")
	public String getLog() {
		return log;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Log'.
	 * @param log String
	 */
	public void setLog(final String log) {
		this.log = log;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Informations'.
	 * @return String infos
	 */
	@Field(domain = "DoText", label = "Informations")
	public String getInfos() {
		return infos;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Informations'.
	 * @param infos String
	 */
	public void setInfos(final String infos) {
		this.infos = infos;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Warnings'.
	 * @return String warnings
	 */
	@Field(domain = "DoText", label = "Warnings")
	public String getWarnings() {
		return warnings;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Warnings'.
	 * @param warnings String
	 */
	public void setWarnings(final String warnings) {
		this.warnings = warnings;
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Chatbot'.
	 * @return Long botId <b>Obligatoire</b>
	 */
	@Field(domain = "DoId", type = "FOREIGN_KEY", required = true, label = "Chatbot")
	public Long getBotId() {
		return (Long) botIdAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Chatbot'.
	 * @param botId Long <b>Obligatoire</b>
	 */
	public void setBotId(final Long botId) {
		botIdAccessor.setId(botId);
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Model'.
	 * @return Long filIdModel
	 */
	@Field(domain = "DoId", type = "FOREIGN_KEY", label = "Model")
	public Long getFilIdModel() {
		return (Long) filIdModelAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Model'.
	 * @param filIdModel Long
	 */
	public void setFilIdModel(final Long filIdModel) {
		filIdModelAccessor.setId(filIdModel);
	}
	
	/**
	 * Champ : COMPUTED.
	 * Récupère la valeur de la propriété calculée 'Duration'.
	 * @return String duration
	 */
	@Field(domain = "DoLabel", type = "COMPUTED", persistent = false, label = "Duration")
	public String getDuration() {
		return io.vertigo.chatbot.commons.ChatbotUtils.durationBetween(getStartTime(), getEndTime());
	}

 	/**
	 * Association : Chatbot.
	 * @return l'accesseur vers la propriété 'Chatbot'
	 */
	public VAccessor<io.vertigo.chatbot.commons.domain.Chatbot> chatbot() {
		return botIdAccessor;
	}

 	/**
	 * Association : Model.
	 * @return l'accesseur vers la propriété 'Model'
	 */
	public VAccessor<io.vertigo.chatbot.commons.domain.MediaFileInfo> mediaFileInfo() {
		return filIdModelAccessor;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
