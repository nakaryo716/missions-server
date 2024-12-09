package domain.service

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject.Inject

import domain.repository.UserRepository
import domain.entity.UserInput
import domain.entity.UserBuilder
import domain.entity.UserId
import domain.repository.RowNotFound
import domain.error.MyError
import domain.entity.User

class UserService @Inject() (
  userRepository: UserRepository,
  passwordHasher: PasswordHashService,
  uuidGenerator: UUIDService, 
) {
  def createUser(userInput: UserInput): Future[Either[MyError, UserId]] = {
    passwordHasher.hashPassword(userInput.password) match {
      case Left(value) => Future.successful(Left(value))
      case Right(value) => {
        val userId = UserId(uuidGenerator.generate())
        val newUser = UserBuilder(userId, userInput.userName, userInput.email, value)
        userRepository.create(newUser)
      }
    }
  }

  def findUserById(id: UserId): Future[Either[MyError, User]] = {
    userRepository.findById(id)
  }

  def isUserExist(id: UserId): Future[Either[MyError, Boolean]] = {
    userRepository.isExist(id)
  }
}
