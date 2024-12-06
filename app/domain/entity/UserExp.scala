package domain.entity

// ユーザーが保持する経験値及びレベルを示すエンティティ
final case class UserExp(
    id: Long,
    userId: UserId,
    experiencePoints: Long,
)
