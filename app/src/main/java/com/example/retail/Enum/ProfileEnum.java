package com.example.retail.Enum;

public enum ProfileEnum {
    OK,ENTERPRISE_EMPTY{
        @Override
        public String toString() {
            return "enterprise name cannot be empty";
        }
    },PROPIETOR_EMPTY{
        @Override
        public String toString() {
            return "proprietor name cannot be empty";
        }
    },MOBILE_EMPTY{
        @Override
        public String toString() {
            return "mobile number cannot be empty";
        }
    };
}
