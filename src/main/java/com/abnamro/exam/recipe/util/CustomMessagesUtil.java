package com.abnamro.exam.recipe.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

/**
 * @author Vishal
 *
 */
@Component
public class CustomMessagesUtil {

	@Autowired
    private MessageSource messageSource;


    /**
     * @param messageCode
     * @return
     */
    public String getMessage(String messageCode) {
        return getMessage(messageCode, null);
    }

    /**
     * @param messageCode
     * @param args
     * @return
     */
    public String getMessage(String messageCode, List<Object> args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(messageCode, args != null ? args.toArray() : null, locale);
    }
}
