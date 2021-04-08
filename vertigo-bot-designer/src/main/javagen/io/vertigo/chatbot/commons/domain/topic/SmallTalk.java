package io.vertigo.chatbot.commons.domain.topic;

import io.vertigo.core.lang.Generated;
import io.vertigo.datamodel.structure.model.Entity;
import io.vertigo.datastore.impl.entitystore.EnumStoreVAccessor;
import io.vertigo.datamodel.structure.model.UID;
import io.vertigo.datastore.impl.entitystore.StoreVAccessor;
import io.vertigo.datamodel.structure.stereotype.Field;
import io.vertigo.datamodel.structure.util.DtObjectUtil;

/**
 * This class is automatically generated.
 * DO NOT EDIT THIS FILE DIRECTLY.
 */
@Generated
public final class SmallTalk implements Entity {
	private static final long serialVersionUID = 1L;

	private Long smtId;

	@io.vertigo.datamodel.structure.stereotype.Association(
			name = "ASmallTalkTopic",
			fkFieldName = "topId",
			primaryDtDefinitionName = "DtTopic",
			primaryIsNavigable = true,
			primaryRole = "Topic",
			primaryLabel = "Topic",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtSmallTalk",
			foreignIsNavigable = false,
			foreignRole = "SmallTalk",
			foreignLabel = "SmallTalk",
			foreignMultiplicity = "0..*")
	private final StoreVAccessor<io.vertigo.chatbot.commons.domain.topic.Topic> topIdAccessor = new StoreVAccessor<>(io.vertigo.chatbot.commons.domain.topic.Topic.class, "Topic");

	@io.vertigo.datamodel.structure.stereotype.Association(
			name = "ASmallTalkResponseType",
			fkFieldName = "rtyId",
			primaryDtDefinitionName = "DtResponseType",
			primaryIsNavigable = true,
			primaryRole = "ResponseType",
			primaryLabel = "Response type",
			primaryMultiplicity = "1..1",
			foreignDtDefinitionName = "DtSmallTalk",
			foreignIsNavigable = false,
			foreignRole = "SmallTalk",
			foreignLabel = "SmallTalk",
			foreignMultiplicity = "0..*")
	private final EnumStoreVAccessor<io.vertigo.chatbot.commons.domain.topic.ResponseType, io.vertigo.chatbot.commons.domain.topic.ResponseTypeEnum> rtyIdAccessor = new EnumStoreVAccessor<>(io.vertigo.chatbot.commons.domain.topic.ResponseType.class, "ResponseType", io.vertigo.chatbot.commons.domain.topic.ResponseTypeEnum.class);

	/** {@inheritDoc} */
	@Override
	public UID<SmallTalk> getUID() {
		return UID.of(this);
	}
	
	/**
	 * Champ : ID.
	 * Récupère la valeur de la propriété 'ID'.
	 * @return Long smtId <b>Obligatoire</b>
	 */
	@Field(smartType = "STyId", type = "ID", cardinality = io.vertigo.core.lang.Cardinality.ONE, label = "ID")
	public Long getSmtId() {
		return smtId;
	}

	/**
	 * Champ : ID.
	 * Définit la valeur de la propriété 'ID'.
	 * @param smtId Long <b>Obligatoire</b>
	 */
	public void setSmtId(final Long smtId) {
		this.smtId = smtId;
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Topic'.
	 * @return Long topId <b>Obligatoire</b>
	 */
	@io.vertigo.datamodel.structure.stereotype.ForeignKey(smartType = "STyId", label = "Topic", fkDefinition = "DtTopic" )
	public Long getTopId() {
		return (Long) topIdAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Topic'.
	 * @param topId Long <b>Obligatoire</b>
	 */
	public void setTopId(final Long topId) {
		topIdAccessor.setId(topId);
	}
	
	/**
	 * Champ : FOREIGN_KEY.
	 * Récupère la valeur de la propriété 'Response type'.
	 * @return String rtyId <b>Obligatoire</b>
	 */
	@io.vertigo.datamodel.structure.stereotype.ForeignKey(smartType = "STyCode", label = "Response type", fkDefinition = "DtResponseType" )
	public String getRtyId() {
		return (String) rtyIdAccessor.getId();
	}

	/**
	 * Champ : FOREIGN_KEY.
	 * Définit la valeur de la propriété 'Response type'.
	 * @param rtyId String <b>Obligatoire</b>
	 */
	public void setRtyId(final String rtyId) {
		rtyIdAccessor.setId(rtyId);
	}

 	/**
	 * Association : Response type.
	 * @return l'accesseur vers la propriété 'Response type'
	 */
	public EnumStoreVAccessor<io.vertigo.chatbot.commons.domain.topic.ResponseType, io.vertigo.chatbot.commons.domain.topic.ResponseTypeEnum> responseType() {
		return rtyIdAccessor;
	}

 	/**
	 * Association : Topic.
	 * @return l'accesseur vers la propriété 'Topic'
	 */
	public StoreVAccessor<io.vertigo.chatbot.commons.domain.topic.Topic> topic() {
		return topIdAccessor;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
