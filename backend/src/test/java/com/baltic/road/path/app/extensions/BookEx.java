package com.baltic.road.path.app.extensions;

import com.baltic.road.path.app.dto.BookDto;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class BookEx implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(Book.class);
    }

    @Override
    public BookDto resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Book book = parameterContext.getParameter().getDeclaredAnnotation(Book.class);
        return new BookDto(book.id(), book.token(), book.title());
    }
}
