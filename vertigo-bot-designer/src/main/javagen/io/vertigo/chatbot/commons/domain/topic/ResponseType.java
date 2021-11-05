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
public final class ResponseType implements DtStaticMasterData {
	private static final long serialVersionUID = 1L;

	private String rtyId;
	private String label;
	private String labelFr;
	private Long sortOrder;

	/** {@inheritDoc} */
	@Override
	public UID<ResponseType> getUID() {
		return UID.of(this);
	}
	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'ID'.
	 * @return String rtyId <b>Obligatoire</b>
	 */
	@Field(smartType = "STyCode", type = "ID", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "ID")
	public String getRtyId() {
		return rtyId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'ID'.
	 * @param rtyId String <b>Obligatoire</b>
	 */
	public void setRtyId(final String rtyId) {
		this.rtyId = rtyId;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Title'.
	 * @return String label <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Title")
	@io.vertigo.datamodel.structure.stereotype.DisplayField
	public String getLabel() {
		return label;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Title'.
	 * @param label String <b>Obligatoire</b>
	 */
	public void setLabel(final String label) {
		this.label = label;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'TitleFR'.
	 * @return String labelFr <b>Obligatoire</b>
	 */
	@Field(smartType = "STyLabel", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "TitleFR")
	public String getLabelFr() {
		return labelFr;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'TitleFR'.
	 * @param labelFr String <b>Obligatoire</b>
	 */
	public void setLabelFr(final String labelFr) {
		this.labelFr = labelFr;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'Order'.
	 * @return Long sortOrder <b>Obligatoire</b>
	 */
	@Field(smartType = "STyNumber", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "Order")
	@io.vertigo.datamodel.structure.stereotype.SortField
	public Long getSortOrder() {
		return sortOrder;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'Order'.
	 * @param sortOrder Long <b>Obligatoire</b>
	 */
	public void setSortOrder(final Long sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
