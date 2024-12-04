package domain.entity

// ユーザーが所持する全コインの枚数を示すエンティティ
// ```id```はDBでの```PRIMARY KEY```になっており、アプリケーション内で触れることはあまりない
// 基本は```userId```によって管理される
// userIdはUUIDを使用する
final case class Coin(
    id: Long,
    userId: String,
    totalCoins: Int,
)
