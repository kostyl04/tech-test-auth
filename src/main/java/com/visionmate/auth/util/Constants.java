package com.visionmate.auth.util;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class Constants {

    @NoArgsConstructor(access = PRIVATE)
    public static final class Headers{

        public static final String X_TOKEN="X-Token";
        public static final String X_DEVICE="X-Device";
        public static final String X_LANG="X-Lang";
    }

    // TODO add all exceptions codes here
    @NoArgsConstructor(access = PRIVATE)
    public static final class Errors{

    }
}
