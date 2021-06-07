package io.vertigo.chatbot.commons.domain.topic;

import io.vertigo.core.lang.Generated;
import io.vertigo.datamodel.structure.model.DtStaticMasterData;
import io.vertigo.datamodel.structure.model.UID;
import io.vertigo.datamodel.structure.stereotype.Field;
import io.vertigo.datamodel.structure.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class KindTopic implements DtStaticMasterData {
	private static final long serialVersionUID = 1L;

	private String ktoCd;
	private String label;
	private String titleEnglish;
	private String titleFrench;
	private String descriptionEnglish;
	private String descriptionFrench;
	private String defaultEnglish;
	private String defaultFrench;

	/** {@inheritDoc} */
	@Override
	public UID<KindTopic> getUID() {
		return UID.of(this);
	}
	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'ID'.
	 * @return String ktoCd <b>Obligatoire</b>
	 */
	@Field(smartType = "STyCode", type = "ID", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "ID")
	@io.vertigo.datamodel.structure.stereotype.SortField
	public String getKtoCd() {
		return ktoCd;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'ID'.
	 * @param ktoCd String <b>Obligatoire</b>
	 */
	public void setKtoCd(final String ktoCd) {
		this.ktoCd = ktoCd;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Label'.
	 * @return String label <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Label")
	@io.vertigo.datamodel.structure.stereotype.DisplayField
	public String getLabel() {
		return label;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Label'.
	 * @param label String <b>Obligatoire</b>
	 */
	public void setLabel(final String label) {
		this.label = label;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Title (English)'.
	 * @return String titleEnglish <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Title (English)")
	public String getTitleEnglish() {
		return titleEnglish;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Title (English)'.
	 * @param titleEnglish String <b>Obligatoire</b>
	 */
	public void setTitleEnglish(final String titleEnglish) {
		this.titleEnglish = titleEnglish;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Title (French)'.
	 * @return String titleFrench <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Title (French)")
	public String getTitleFrench() {
		return titleFrench;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Title (French)'.
	 * @param titleFrench String <b>Obligatoire</b>
	 */
	public void setTitleFrench(final String titleFrench) {
		this.titleFrench = titleFrench;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Description (English)'.
	 * @return String descriptionEnglish
	 */
	@Field(smartType = "STyLabel", label = "Description (English)")
	public String getDescriptionEnglish() {
		return descriptionEnglish;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Description (English)'.
	 * @param descriptionEnglish String
	 */
	public void setDescriptionEnglish(final String descriptionEnglish) {
		this.descriptionEnglish = descriptionEnglish;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Description (French)'.
	 * @return String descriptionFrench
	 */
	@Field(smartType = "STyLabel", label = "Description (French)")
	public String getDescriptionFrench() {
		return descriptionFrench;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Description (French)'.
	 * @param descriptionFrench String
	 */
	public void setDescriptionFrench(final String descriptionFrench) {
		this.descriptionFrench = descriptionFrench;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Default text (English)'.
	 * @return String defaultEnglish <b>Obligatoire</b>
	 */
	@Field(smartType = "STyText", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Default text (English)")
	public String getDefaultEnglish() {
		return defaultEnglish;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Default text (English)'.
	 * @param defaultEnglish String <b>Obligatoire</b>
	 */
	public void setDefaultEnglish(final String defaultEnglish) {
		this.defaultEnglish = defaultEnglish;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Default text (French)'.
	 * @return String defaultFrench <b>Obligatoire</b>
	 */
	@Field(smartType = "STyText", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Default text (French)")
	public String getDefaultFrench() {
		return defaultFrench;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Default text (French)'.
	 * @param defaultFrench String <b>Obligatoire</b>
	 */
	public void setDefaultFrench(final String defaultFrench) {
		this.defaultFrench = defaultFrench;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}