package io.vertigo.chatbot.commons.confluence.model.result;

public class ConfluenceSpaceResponse extends AbstractConfluenceResponses {

	private ConfluenceSpace[] results;

	private Integer size;

	private Integer limit;

	public ConfluenceSpace[] getResults() {
		return results;
	}

	public void setResults(final ConfluenceSpace[] results) {
		this.results = results;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(final Integer size) {
		this.size = size;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(final Integer limit) {
		this.limit = limit;
	}

}
