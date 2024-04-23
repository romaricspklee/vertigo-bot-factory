package io.vertigo.chatbot.commons.confluence.impl;

import io.vertigo.chatbot.commons.LogsUtils;
import io.vertigo.chatbot.commons.PasswordEncryptionServices;
import io.vertigo.chatbot.commons.confluence.helper.ConfluenceHttpRequestHelper;
import io.vertigo.chatbot.commons.confluence.helper.ConfluenceSearchHelper;
import io.vertigo.chatbot.commons.confluence.helper.JsonHelper;
import io.vertigo.chatbot.commons.confluence.model.result.ConfluenceSearchResponse;
import io.vertigo.chatbot.commons.confluence.model.result.ConfluenceSearchResult;
import io.vertigo.chatbot.commons.confluence.model.result.ConfluenceSpace;
import io.vertigo.chatbot.commons.confluence.model.result.ConfluenceSpaceResponse;
import io.vertigo.chatbot.commons.confluence.model.search.ConfluenceSearchObject;
import io.vertigo.commons.transaction.Transactional;
import io.vertigo.core.lang.VSystemException;
import io.vertigo.core.node.component.Component;

import javax.inject.Inject;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.vertigo.chatbot.commons.confluence.helper.ConfluenceHttpRequestHelper.*;

public class ConfluenceServices implements IConfluenceService {

	private String baseUrl;
	private String user;
	private String password;
	private String limit;

	@Inject
	private PasswordEncryptionServices passwordEncryptionServices;

    public ConfluenceServices(String baseUrl, String user, String password, String limit) {
        this.baseUrl = baseUrl;
		this.user = user;
		this.password = password;
		this.limit = limit;
    }

    @Override
	public ConfluenceSearchResponse searchOnConfluence(Map<String, String> params, Map<String, String> headers, ConfluenceSearchObject filter) {
		final String searchArgs = "space=test+AND+"+ ConfluenceHttpRequestHelper.createSearchArgs(filter, true);
		params.put("cql", searchArgs);
		params.put("limit", limit);
		final HttpResponse<String> response = sendRequestToConfluence(params, headers, SEARCH_URL, 200, BodyHandlers.ofString());
		return JsonHelper.getObjectFromJson(response.body(), ConfluenceSearchResponse.class);
	}

	@Override
	public ConfluenceSpaceResponse searchAllSpaceOnConfluence(final Map<String, String> headers) {
		final HttpResponse<String> response = sendRequestToConfluence(null, headers, SPACE_URL, 200, BodyHandlers.ofString());
		return JsonHelper.getObjectFromJson(response.body(), ConfluenceSpaceResponse.class);
	}

	@Override
	public ConfluenceSpace searchSpaceOnClonfluence(final Map<String, String> headers, final String spaceKey) {
		final HttpResponse<String> response = sendRequestToConfluence(null, headers, SPACE_URL + spaceKey, 200, BodyHandlers.ofString());
		return JsonHelper.getObjectFromJson(response.body(), ConfluenceSpace.class);
	}

	private <T> HttpResponse<T> sendRequestToConfluence(final Map<String, String> params, final Map<String, String> headers, final String endUrl, final int successCode, final BodyHandler<T> handler) {
		final var apiUrl = getApiUrl();
		final var request = ConfluenceHttpRequestHelper.createGetRequest(apiUrl + endUrl, headers, params);
		return ConfluenceHttpRequestHelper.sendRequest(null, request, handler, successCode);
	}

	private String getBaseUrl() {
		return baseUrl;
	}

	private String getApiUrl() {
		return baseUrl + API_URL;
	}

	private Map<String, String> getHeadersWithAuthorization() {
		return ConfluenceHttpRequestHelper.getHeadersWithAuthorization(user, password);
	}

	@Override
	public List<String> searchOnConfluenceCommand(final String search) {
		final Map<String, String> headers = getHeadersWithAuthorization();
		final Map<String, String> params = new HashMap<>();
		final var searchObject = ConfluenceSearchHelper.createConfluenceSearchObject(search);
		final ConfluenceSearchResponse searchResult = searchOnConfluence(params, headers, searchObject);
		final List<ConfluenceSearchResult> results = Arrays.asList(searchResult.getResults());
		return results.stream().map(x -> createLinkUrl(x.getUrl(), x.getDetail().getTitle())).collect(Collectors.toList());
	}

	private String createLinkUrl(final String link, final String name) {
		final String url = getBaseUrl() + link;
		final var builder = new StringBuilder();
		builder.append("<a href=\"");
		builder.append(url);
		builder.append("\" target=\"_blank\" rel=\"noopener noreferrer\">");
		builder.append(name);
		builder.append("</a>");
		return builder.toString();
	}
}
