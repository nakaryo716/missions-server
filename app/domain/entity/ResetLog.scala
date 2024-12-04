package domain.entity

import java.time.Instant

// 各ユーザーごとに、DailiyMissionがいつリセットされたのかの状態を保持するためのエンティティ
// DailiyMissionは毎日リセットされるため、正常にリセットされたか(DailiyMissionのisCompletedがfalseに設定されたか)記録する
final case class ResetLog(
    id: Long,
    userId: String,
    resetTime: Instant,
)
