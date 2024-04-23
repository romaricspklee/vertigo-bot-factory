package io.vertigo.chatbot.commons.confluence.model.search;

public interface ConfluenceSearchObject {

	String accept(ConfluenceVisitor visitor);

}
