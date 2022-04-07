package com.meta.springboot.redis.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum Gender {
        MALE("male"), FEMALE("female");

        private final String value;

        private Gender(String value) {
                this.value = value;
        }

        @JsonCreator
        public static Gender fromValue(String item) {
                var gender = Optional.of(Gender.valueOf(item));
                return  gender.orElseThrow(()->  new RuntimeException("can't find value "  + item ));
        }

        @JsonValue
        @Override
        public String toString() {
                return value;
        }

}
