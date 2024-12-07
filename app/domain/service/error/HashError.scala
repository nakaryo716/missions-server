package domain.service.error

import domain.error.MyError

sealed trait HashError extends MyError {
  val message: String
}

object HashError {
  case object InvalidSalt extends HashError {
    val message: String = "Invalid salt"
  }

  case object HashingFailed extends HashError {
    val message: String = "Failed to hash"
  }

  case object VerificationFailed extends HashError {
    val message: String = "Failed to verify"
  }
}
