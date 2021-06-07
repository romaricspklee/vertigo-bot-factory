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
public final class SelectionOption implements DtObject {
	private static final long serialVersionUID = 1L;

	private String label;
	private String value;
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Label'.
	 * @return String label <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Label")
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
	 * Récupère la valeur de la propriété 'Value'.
	 * @return String value <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Value")
	public String getValue() {
		return value;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Value'.
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