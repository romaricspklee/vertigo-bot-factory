package io.vertigo.chatbot.designer.domain.commons;

import io.vertigo.core.lang.Generated;
import io.vertigo.datamodel.structure.model.DtObject;
import io.vertigo.datamodel.structure.stereotype.Field;
import io.vertigo.datamodel.structure.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class BotPredefinedTopic implements DtObject {
	private static final long serialVersionUID = 1L;

	private Long topId;
	private String ttoCd;
	private String value;
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Id topic'.
	 * @return Long topId <b>Obligatoire</b>
	 */
	@Field(smartType = "STyId", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Id topic")
	public Long getTopId() {
		return topId;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Id topic'.
	 * @param topId Long <b>Obligatoire</b>
	 */
	public void setTopId(final Long topId) {
		this.topId = topId;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Type topic code'.
	 * @return String ttoCd <b>Obligatoire</b>
	 */
	@Field(smartType = "STyCode", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Type topic code")
	public String getTtoCd() {
		return ttoCd;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Type topic code'.
	 * @param ttoCd String <b>Obligatoire</b>
	 */
	public void setTtoCd(final String ttoCd) {
		this.ttoCd = ttoCd;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'SmallTalk / Script'.
	 * @return String value <b>Obligatoire</b>
	 */
	@Field(smartType = "STyText", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "SmallTalk / Script")
	public String getValue() {
		return value;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'SmallTalk / Script'.
	 * @param value String <b>Obligatoire</b>
	 */
	public void setValue(final String value) {
		this.value = value;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
