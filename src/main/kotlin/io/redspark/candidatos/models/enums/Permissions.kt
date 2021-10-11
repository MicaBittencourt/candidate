package io.redspark.candidatos.models.enums

enum class Permissions {

    ROLE_ADMIN,
    ROLE_INTERVIEWER,
    ROLE_PEOPLE,
    ROLE_USER;

    class Constants {
        companion object {
            const val ROLE_ADMIN = "ROLE_ADMIN"
            const val ROLE_INTERVIEWER = "ROLE_INTERVIEWER"
            const val ROLE_PEOPLE = "ROLE_PEOPLE"
            const val ROLE_USER = "ROLE_USER"
        }
    }

}