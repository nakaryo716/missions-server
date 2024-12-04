package domain.entity

// クライアントがミッションを新規作成する際にJSONとしてサーバーに渡すエンティティ
// DailiyMissionInputはDailiyMissionに変換される
final case class DailiyMissionInput(
    title: String,
    description: Option[String],
)
