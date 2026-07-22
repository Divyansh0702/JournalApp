package com.LearningSB.JournalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import com.LearningSB.JournalApp.entity.User;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {

    @Disabled
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
//        return Stream.empty();
        return Stream.of(
                Arguments.of(User.builder().userName("Rohit").password("Rohit").build()),
                Arguments.of(User.builder().userName("Romeo").password("").build())
        );
    }
}

