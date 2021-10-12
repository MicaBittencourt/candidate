package io.redspark.candidatos.config.logger

import org.slf4j.Logger

fun Logger.logCreated(obj: Any, message: String? = null) = run { info("CREATED: {$obj} | message: $message") }

fun Logger.logUpdated(obj: Any, message: String? = null) = run { info("UPDATED: {$obj} | message: $message") }

inline fun <reified T> Logger.logUpdated(klass: Class<T>, id: Any, message: String? = null) = run { info("UPDATED: ${klass.canonicalName} with id - $id | message: $message") }

inline fun <reified T> Logger.logDeleted(klass: Class<T>, id: Any, message: String? = null) = run { info("DELETED: ${klass.canonicalName} with id - $id | message: $message") }