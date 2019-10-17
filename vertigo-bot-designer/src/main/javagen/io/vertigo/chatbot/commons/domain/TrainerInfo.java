package io.vertigo.chatbot.commons.domain;

import io.vertigo.dynamo.domain.model.DtObject;
import io.vertigo.dynamo.domain.stereotype.Field;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.lang.Generated;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class TrainerInfo implements DtObject {
	private static final long serialVersionUID = 1L;

	private String name;
	private Boolean trainingInProgress;
	private String trainingState;
	private String latestTrainingLog;
	private java.time.Instant startTime;
	private java.time.Instant endTime;
	private Long trainingPercent;
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Name'.
	 * @return String name <b>Obligatoire</b>
	 */
	@Field(domain = "DoLabel", required = true, label = "Name")
	public String getName() {
		return name;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Name'.
	 * @param name String <b>Obligatoire</b>
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Training in progress'.
	 * @return Boolean trainingInProgress <b>Obligatoire</b>
	 */
	@Field(domain = "DoYesNo", required = true, label = "Training in progress")
	public Boolean getTrainingInProgress() {
		return trainingInProgress;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Training in progress'.
	 * @param trainingInProgress Boolean <b>Obligatoire</b>
	 */
	public void setTrainingInProgress(final Boolean trainingInProgress) {
		this.trainingInProgress = trainingInProgress;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Training state'.
	 * @return String trainingState <b>Obligatoire</b>
	 */
	@Field(domain = "DoLabel", required = true, label = "Training state")
	public String getTrainingState() {
		return trainingState;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Training state'.
	 * @param trainingState String <b>Obligatoire</b>
	 */
	public void setTrainingState(final String trainingState) {
		this.trainingState = trainingState;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Training log'.
	 * @return String latestTrainingLog <b>Obligatoire</b>
	 */
	@Field(domain = "DoText", required = true, label = "Training log")
	public String getLatestTrainingLog() {
		return latestTrainingLog;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Training log'.
	 * @param latestTrainingLog String <b>Obligatoire</b>
	 */
	public void setLatestTrainingLog(final String latestTrainingLog) {
		this.latestTrainingLog = latestTrainingLog;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Start time'.
	 * @return Instant startTime
	 */
	@Field(domain = "DoInstant", label = "Start time")
	public java.time.Instant getStartTime() {
		return startTime;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Start time'.
	 * @param startTime Instant
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
	 * Récupère la valeur de la propriété 'Training percentage'.
	 * @return Long trainingPercent
	 */
	@Field(domain = "DoNumber", label = "Training percentage")
	public Long getTrainingPercent() {
		return trainingPercent;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Training percentage'.
	 * @param trainingPercent Long
	 */
	public void setTrainingPercent(final Long trainingPercent) {
		this.trainingPercent = trainingPercent;
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
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
