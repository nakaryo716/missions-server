package domain.entity

import java.time.Instant

// ユーザーが設定したミッションの状態を保持するエンティティ
// userIdはUUIDとしてDBに保持されている
final case class DailiyMission(
    id: Long,
    userId: String,
    title: String,
    description: Option[String],
    isCompleted: Boolean,
    createdAt: Instant,
    updatedAt: Instant,
)