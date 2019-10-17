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
public final class BotExport implements DtObject {
	private static final long serialVersionUID = 1L;

	private io.vertigo.chatbot.commons.domain.Chatbot bot;
	private io.vertigo.chatbot.commons.domain.UtterText defaultText;
	private io.vertigo.chatbot.commons.domain.UtterText welcomeText;
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'chatbot'.
	 * @return Chatbot bot <b>Obligatoire</b>
	 */
	@Field(domain = "DoDtChatbotDto", required = true, label = "chatbot")
	public io.vertigo.chatbot.commons.domain.Chatbot getBot() {
		return bot;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'chatbot'.
	 * @param bot Chatbot <b>Obligatoire</b>
	 */
	public void setBot(final io.vertigo.chatbot.commons.domain.Chatbot bot) {
		this.bot = bot;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'welcome'.
	 * @return UtterText defaultText <b>Obligatoire</b>
	 */
	@Field(domain = "DoDtUtterTextDto", required = true, label = "welcome")
	public io.vertigo.chatbot.commons.domain.UtterText getDefaultText() {
		return defaultText;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'welcome'.
	 * @param defaultText UtterText <b>Obligatoire</b>
	 */
	public void setDefaultText(final io.vertigo.chatbot.commons.domain.UtterText defaultText) {
		this.defaultText = defaultText;
	}
	
	/**
	 * Champ : DATA.
	 * Récupère la valeur de la propriété 'welcome'.
	 * @return UtterText welcomeText <b>Obligatoire</b>
	 */
	@Field(domain = "DoDtUtterTextDto", required = true, label = "welcome")
	public io.vertigo.chatbot.commons.domain.UtterText getWelcomeText() {
		return welcomeText;
	}

	/**
	 * Champ : DATA.
	 * Définit la valeur de la propriété 'welcome'.
	 * @param welcomeText UtterText <b>Obligatoire</b>
	 */
	public void setWelcomeText(final io.vertigo.chatbot.commons.domain.UtterText welcomeText) {
		this.welcomeText = welcomeText;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
