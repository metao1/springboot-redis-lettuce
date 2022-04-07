package com.meta.springboot.redis.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

@Getter
public enum Gender {
        MALE("male"), FEMALE("female");

        private final String value;

        private Gender(String value) {
                this.value = value;
        }

        @JsonCreator
        public static Gender fromValue(String item) {
                var gender = Optional.ofNullable(Gender.valueOf(item));
                if(gender!)
        }

        @JsonValue
        @Override
        public String toString() {
                return value;
        }

}
