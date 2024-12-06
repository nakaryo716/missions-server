package domain.entity

// DailyMissionInputから作られる
// DailyMissionInputからユーザーID、isCompleted(Default false)を追加したエンティティ
// デイリーミッションをDBに追加する際に使用するエンティティ
// 追加した後は破棄されてDailyMissionに変換される(id, createdAt, UpdatedAtが付与される)
final case class DailyMissionBuilder(
    userId: UserId,
    title: String,
    description: Option[String],
)
