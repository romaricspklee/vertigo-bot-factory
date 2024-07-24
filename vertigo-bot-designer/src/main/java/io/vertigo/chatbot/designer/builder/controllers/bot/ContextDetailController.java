/**
 * vertigo - simple java starter
 *
 * Copyright (C) 2020, Vertigo.io, team@vertigo.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vertigo.chatbot.designer.builder.controllers.bot;

import io.vertigo.account.authorization.annotations.Secured;
import io.vertigo.chatbot.commons.domain.Chatbot;
import io.vertigo.chatbot.commons.domain.ContextPossibleValue;
import io.vertigo.chatbot.commons.domain.ContextValue;
import io.vertigo.chatbot.designer.builder.services.bot.ChatbotServices;
import io.vertigo.chatbot.designer.builder.services.bot.ContextEnvironmentServices;
import io.vertigo.chatbot.designer.builder.services.bot.ContextEnvironmentValueServices;
import io.vertigo.chatbot.designer.builder.services.bot.ContextPossibleValueServices;
import io.vertigo.chatbot.designer.builder.services.bot.ContextTypeOperatorServices;
import io.vertigo.chatbot.designer.builder.services.bot.ContextValueServices;
import io.vertigo.chatbot.designer.domain.TypeOperator;
import io.vertigo.datamodel.structure.model.DtList;
import io.vertigo.ui.core.ViewContext;
import io.vertigo.ui.core.ViewContextKey;
import io.vertigo.ui.impl.springmvc.argumentresolvers.ViewAttribute;
import io.vertigo.vega.webservice.validation.UiMessageStack;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/bot/{botId}/contextValue")
@Secured("Chatbot$botAdm")
public class ContextDetailController extends AbstractBotCreationController<ContextValue> {

	private static final ViewContextKey<Chatbot> botKey = ViewContextKey.of("bot");

	private static final ViewContextKey<ContextValue> contextValueKey = ViewContextKey.of("contextValue");

	private static final ViewContextKey<ContextPossibleValue> contextPossibleValueListKey = ViewContextKey.of("contextPossibleValueList");

	private static final ViewContextKey<ContextPossibleValue> newContextPossibleValueKey = ViewContextKey.of("newContextPossibleValue");

	private static final ViewContextKey<TypeOperator> typeOperators = ViewContextKey.of("typeOperators");

	private static final ViewContextKey<String> localeKey = ViewContextKey.of("locale");

	@Inject
	private ChatbotServices chatbotServices;

	@Inject
	private ContextValueServices contextValueServices;

	@Inject
	private ContextEnvironmentServices contextEnvironmentServices;

	@Inject
	private ContextEnvironmentValueServices contextEnvironmentValueServices;

	@Inject
	private ContextPossibleValueServices contextPossibleValueServices;

	@Inject
	private ContextTypeOperatorServices contextTypeOperatorServices;

	@GetMapping("/{cvaId}")
	public void initContext(final ViewContext viewContext, final UiMessageStack uiMessageStack, @PathVariable("botId") final Long botId,
			@PathVariable("cvaId") final Long cvaId) {

		super.initCommonContext(viewContext, uiMessageStack, botId);
		final Chatbot chatbot = chatbotServices.getChatbotById(botId);
		final ContextValue contextValue = contextValueServices.findContextValueById(cvaId);
		final DtList<ContextPossibleValue> contextPossibleValueList = contextPossibleValueServices.getAllContextPossibleValuesByCvaId(chatbot, cvaId);

		viewContext.publishDto(botKey, chatbot);
		viewContext.publishDto(contextValueKey, contextValue);
		viewContext.publishDtList(contextPossibleValueListKey, contextPossibleValueList);
		viewContext.publishDtList(typeOperators, contextTypeOperatorServices.getAllTypeOperators(chatbot));
		viewContext.publishDto(newContextPossibleValueKey, new ContextPossibleValue());

		viewContext.publishRef(localeKey, localeManager.getCurrentLocale().toString());

		super.initBreadCrums(viewContext, contextValue);
		toModeReadOnly();
	}

	@GetMapping("/new")
	public void getNewContextValue(final ViewContext viewContext, final UiMessageStack uiMessageStack, @PathVariable("botId") final Long botId) {
		final Chatbot bot = initCommonContext(viewContext, uiMessageStack, botId);
		viewContext.publishDto(contextValueKey, contextValueServices.getNewContextValue(bot));
		viewContext.publishDtList(contextPossibleValueListKey, new DtList<>(ContextPossibleValue.class));
		viewContext.publishDto(newContextPossibleValueKey, new ContextPossibleValue());
		super.initEmptyBreadcrums(viewContext);
		toModeCreate();
	}

	@PostMapping("/_edit")
	public void doEdit() {
		toModeEdit();
	}

	@PostMapping("/_save")
	public String saveContextValue(final ViewContext viewContext,
			final UiMessageStack uiMessageStack,
			@ViewAttribute("bot") final Chatbot bot,
			@ViewAttribute("contextValue") final ContextValue contextValue) {
		boolean creation = contextValue.getCvaId() == null;
		final ContextValue contextValueSaved = contextValueServices.save(bot, contextValue);
		if (creation) {
			contextEnvironmentServices.addContextToAllContextEnvironmentForBot(bot.getBotId(), contextValueSaved.getCvaId());
		}
		return "redirect:/bot/" + contextValue.getBotId() + "/contextValue/" + contextValueSaved.getCvaId();
	}

	@PostMapping("/_saveContextPossibleValue")
	public ViewContext saveContextPossibleValue(final ViewContext viewContext,
								   final UiMessageStack uiMessageStack,
								   @ViewAttribute("bot") final Chatbot bot,
								   @ViewAttribute("newContextPossibleValue") final ContextPossibleValue newContextPossibleValue,
								   @ViewAttribute("contextValue") final ContextValue contextValue) {

		contextPossibleValueServices.save(bot, newContextPossibleValue);

		viewContext.publishDtList(contextPossibleValueListKey, contextPossibleValueServices.getAllContextPossibleValuesByCvaId(bot, contextValue.getCvaId()));
		return viewContext;
	}

	@PostMapping("/_deleteContextValue")
	public String deleteContextValue(final ViewContext viewContext,
			@ViewAttribute("bot") final Chatbot bot,
			@ViewAttribute("contextValue") final ContextValue contextValue) {
		contextEnvironmentValueServices.deleteContextEnvironmentValue(contextValue.getCvaId());
		contextValueServices.deleteContextValue(bot, contextValue.getCvaId());
		return "redirect:/bot/" + bot.getBotId() + "/contextList/";
	}

	@PostMapping("/_deleteContextPossibleValue")
	public ViewContext deletePossibleContextValue(final ViewContext viewContext,
									 @ViewAttribute("bot") final Chatbot bot,
									 @ViewAttribute("contextValue") final ContextValue contextValue,
									 @RequestParam("cpvId") final Long cpvId) {

		contextPossibleValueServices.deleteContextPossibleValue(bot, cpvId);

		viewContext.publishDtList(contextPossibleValueListKey, contextPossibleValueServices.getAllContextPossibleValuesByCvaId(bot, contextValue.getCvaId()));
		return viewContext;
	}

	@Override
	protected String getBreadCrums(final ContextValue object) {
		return object.getLabel();
	}

}
