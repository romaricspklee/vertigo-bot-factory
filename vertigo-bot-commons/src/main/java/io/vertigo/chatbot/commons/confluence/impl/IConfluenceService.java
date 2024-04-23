package io.vertigo.chatbot.commons.confluence.impl;

import io.vertigo.chatbot.commons.confluence.model.result.ConfluenceSearchResponse;
import io.vertigo.chatbot.commons.confluence.model.result.ConfluenceSpace;
import io.vertigo.chatbot.commons.confluence.model.result.ConfluenceSpaceResponse;
import io.vertigo.chatbot.commons.confluence.model.search.ConfluenceSearchObject;

import java.util.List;
import java.util.Map;

public interface IConfluenceService {

	abstract ConfluenceSearchResponse searchOnConfluence(final Map<String, String> params, final Map<String, String> headers, ConfluenceSearchObject filter);

	abstract ConfluenceSpaceResponse searchAllSpaceOnConfluence(final Map<String, String> headers);

	abstract ConfluenceSpace searchSpaceOnClonfluence(final Map<String, String> headers, final String spaceKey);

	abstract List<String> searchOnConfluenceCommand(final String search);
}
