package domain.entity

// DailiyMissionInputから作られる
// DailiyMissionInputからユーザーID、isCompleted(Default false)を追加したエンティティ
// デイリーミッションをDBに追加する際に使用するエンティティ
// 追加した後は破棄されてDailiyMissionに変換される(id, createdAt, UpdatedAtが付与される)
final case class DailiyMissionBuilder(
    userId: UserId,
    title: String,
    description: Option[String],
)
