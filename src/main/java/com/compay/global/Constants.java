package com.compay.global;

import org.springframework.http.MediaType;

public final class Constants {
    public static final String UTF_8 = "UTF-8";
    public static final String ADMIN = "admin";


    private Constants() {
    }

    public static final class Profile {
        public static final String NAME = "name";
        public static final String SURNAME = "surname";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";

        private Profile() {
        }
    }

    public static final class MimeTypes {
        public static final String MIME_SPLITTER = ";";
        //"text/plain;charset=UTF-8"
        public static final String UTF_8_PLAIN_TEXT = MediaType.TEXT_PLAIN_VALUE + MIME_SPLITTER + "charset=" + UTF_8;

        private MimeTypes() {
        }
    }
}
