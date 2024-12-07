package domain.repository

import domain.error.MyError

sealed trait RepositoryError extends MyError

case object RowNotFound extends RepositoryError
case object RowAlreadyExists extends RepositoryError
case object InvalidInputData extends RepositoryError
