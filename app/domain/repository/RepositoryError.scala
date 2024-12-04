package domain.repository

sealed trait RepositoryError

case object RowNotFound extends RepositoryError
case object RowAlreadyExists extends RepositoryError
case object InvalidInputData extends RepositoryError
