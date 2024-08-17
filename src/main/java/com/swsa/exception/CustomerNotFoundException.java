package com.swsa.exception;

    public  class CustomerNotFoundException  extends RuntimeException {
        public CustomerNotFoundException (long aadhaarNo) {
            super("aadhaarNo Not Found " + aadhaarNo);
        }
    }


