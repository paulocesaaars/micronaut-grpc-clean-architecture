package com.deviot.adapters.repositories.queries

class UserQuery {
    companion object{
        fun checkEmaillQuery(): String {
            return StringBuilder()
                .append("SELECT")
                .append("  CASE WHEN COUNT(*) > 0")
                .append("       THEN TRUE")
                .append("       ELSE FALSE")
                .append("   END")
                .append("  FROM UserEntity")
                .append(" WHERE email = :email")
                .toString()
        }
    }
}