package io.vertigo.chatbot.commons;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

public class HtmlInputUtils {

	private HtmlInputUtils() {
		//utils class
	}

	public static String sanitizeHtml(final String in) {
		final PolicyFactory sanitizer = Sanitizers.FORMATTING
				.and(Sanitizers.BLOCKS)
				.and(Sanitizers.LINKS)
				.and(Sanitizers.STYLES)
				.and(Sanitizers.IMAGES)
				.and(new HtmlPolicyBuilder()
						.allowElements("font", "hr")
						.allowAttributes("size").onElements("font")
						.allowAttributes("class").onElements("img").allowElements("img")
						.allowAttributes("target").onElements("a").allowElements("a")
						.toFactory());

		return sanitizer.sanitize(in);
	}

	public static String sanitizeHtmlWithTargetBlank(final String in, final String baseUrl) {
		final PolicyFactory sanitizer = Sanitizers.FORMATTING
				.and(Sanitizers.BLOCKS)
				.and(Sanitizers.LINKS)
				.and(Sanitizers.STYLES)
				.and(Sanitizers.IMAGES)
				.and(Sanitizers.TABLES)
				.and(new HtmlPolicyBuilder()
						.allowElements(
								(elementName, attrs) -> {
									if ("a".equals(elementName)) {
										int targetIndex = attrs.indexOf("target");
										if (targetIndex < 0) {
											attrs.add("target");
											attrs.add("_blank");
										} else {
											attrs.set(targetIndex + 1, "_blank");
										}
									}
									if ("img".equals(elementName)) {
										int srcIndex = attrs.indexOf("src");
										if (srcIndex >= 0) {
											String srcValue = attrs.get(srcIndex + 1);
											attrs.set(srcIndex + 1, "https://confluence.rct01.kleegroup.com" + srcValue);
										}
									}
									return elementName;
								},
								"a", "img"
						)
						.allowElements("font", "hr")
						.allowAttributes("size").onElements("font")
						.allowAttributes("class").onElements("img").allowElements("img")
						.allowAttributes("target").onElements("a").allowElements("a")
						.toFactory());
		return sanitizer.sanitize(in);
	}
}
