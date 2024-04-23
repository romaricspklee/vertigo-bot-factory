package io.vertigo.chatbot.engine.plugins.bt.confluence.impl;

import io.vertigo.chatbot.commons.LogsUtils;
import io.vertigo.chatbot.commons.PasswordEncryptionServices;
import io.vertigo.chatbot.commons.confluence.impl.ConfluenceServices;
import io.vertigo.chatbot.commons.domain.ConfluenceSettingExport;
import io.vertigo.chatbot.executor.model.ExecutorGlobalConfig;
import io.vertigo.commons.transaction.Transactional;
import io.vertigo.core.lang.VSystemException;
import io.vertigo.core.node.component.Component;

import javax.inject.Inject;

@Transactional
public class ConfluenceServerServices implements Component {

	private String baseUrl;
	private String user;
	private String password;
	private String limit;
	private ConfluenceServices confluenceServices;

	@Inject
	private PasswordEncryptionServices passwordEncryptionServices;

	public void refreshConfig(final ExecutorGlobalConfig config, StringBuilder logs) throws VSystemException {
		LogsUtils.addLogs(logs, "Refreshing Confluence settings ... ");
		final ConfluenceSettingExport confluenceSettingExport = config.getBot().getConfluenceSetting();
		if (confluenceSettingExport == null) {
			LogsUtils.logKO(logs);
			throw new VSystemException("Confluence setting must be set for confluence plugin to work...");
		} else {
			baseUrl = confluenceSettingExport.getUrl();
			user = confluenceSettingExport.getLogin();
			password = passwordEncryptionServices.decryptPassword(confluenceSettingExport.getPassword());
			limit = confluenceSettingExport.getNumberOfResults().toString();
			LogsUtils.logOK(logs);
			confluenceServices = new ConfluenceServices(baseUrl, user, password, limit);
		}
	}

	public ConfluenceServices getConfluenceServices() {
		return confluenceServices;
	}
}
