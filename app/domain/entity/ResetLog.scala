package domain.entity

import java.time.Instant

// 各ユーザーごとに、DailyMissionがいつリセットされたのかの状態を保持するためのエンティティ
// DailyMissionは毎日リセットされるため、正常にリセットされたか(DailyMissionのisCompletedがfalseに設定されたか)記録する
final case class ResetLog(
  id: Long,
  userId: UserId,
  resetTime: Instant,
)
