package com.example.loginfirebase.models

class Employee () {
    var employerEmail = ""
        get() {
            return field
        }
        set(value) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Employer email must not be empty")
            }
            field = value
        }
    var id = ""
        get() {
            return field
        }
        set(value) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Id must not be empty")
            }
            field = value
        }
    var name = ""
        get() {
            return field
        }
        set(value) {
            if(value.isEmpty()) {
                throw IllegalArgumentException("Name must not be empty")
            }
            field = value
        }
    var email = ""
        get() {
            return field
        }
        set(value) {
            if(value.isEmpty()) {
                throw IllegalArgumentException("Email must not be empty")
            }
            field = value
        }
    var job = ""
        get() {
            return field
        }
        set(value) {
            if(value.isEmpty()) {
                throw IllegalArgumentException("Job must not be empty")
            }
            field = value
        }
    var department = ""
        get() {
            return field
        }
        set(value) {
            if(value.isEmpty()) {
                throw IllegalArgumentException("Department must not be empty")
            }
            field = value
        }
}