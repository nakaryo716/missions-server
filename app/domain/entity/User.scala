package domain.entity

import java.time.Instant

// ユーザー情報を示すエンティティ
// userIdはUUIDとして登録する
// パスワードはハッシュ化された状態で保持される
final case class User(
    id: Long,
    userId: UserId,
    userName: String,
    email: String,
    passwordHash: String,
    createdAt: Instant,
    updatedAt: Instant,
)
