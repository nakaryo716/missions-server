package domain.entity

// クライアントがミッションを新規作成する際にJSONとしてサーバーに渡すエンティティ
// DailyMissionInputはDailyMissionに変換される
final case class DailyMissionInput(
  title: String,
  description: Option[String],
)
