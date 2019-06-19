package com.jc.jctest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author：jc   2019/4/19 15:40
 */
public class BuilderTest2 {

    public String test1;
    public String test2;
    public String test3;

    private BuilderTest2(Builder builder) {
        test1 = builder.test1;
        test2 = builder.test2;
        test3 = builder.test3;
    }


    public static final class Builder {
        private String test1;
        private String test2;
        private String test3;

        public Builder() {
        }

        public Builder test1(String val) {
            test1 = val;
            return this;
        }

        public Builder test2(String val) {
            test2 = val;
            return this;
        }

        public Builder test3(String val) {
            test3 = val;
            return this;
        }

        public BuilderTest2 build() {
            return new BuilderTest2(this);
        }
    }


    public static void main(String[] argv) {
        new BuilderTest2.Builder()
                .test1("")
                .test2("")
                .build();


    }
}
