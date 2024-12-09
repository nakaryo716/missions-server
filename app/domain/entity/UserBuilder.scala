package domain.entity

case class UserBuilder(
  userId: UserId,
  userName: String,
  email: String,
  passwordHash: String,
)
