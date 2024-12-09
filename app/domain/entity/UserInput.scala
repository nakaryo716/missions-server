package domain.entity

// クライアントがサインアップする際にJSONとしてサーバーに渡すエンティティ
// UserInputはUserに変換される(user_idの付与とpasswordのHash化)
final case class UserInput(
  userName: String,
  email: String,
  password: String,
)
