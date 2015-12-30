package com.hungnv132.config.component;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

public class MyLocalResolver implements LocaleResolver {
	private enum LANG {
		vi, en
	};

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		Locale locale = new Locale("vi", "VN");
		String language = (request.getParameter("lang"));
		if (language != null && this.contains(language)) {
			LANG lang = LANG.valueOf(request.getParameter("lang"));
			switch (lang) {
			case vi:
				locale = new Locale("vi", "VN");
				break;
			case en:
				locale = new Locale("en", "US");
				break;
			}
		} else {

			return locale;
		}
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, Locale locale) {

	}

	private boolean contains(String lang) {
		for (LANG l : LANG.values()) {
			if (lang.equals(l.name())) {
				return true;
			}
		}
		return false;
	}

}
