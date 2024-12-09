package domain.repository

import scala.concurrent.Future
import domain.entity.DailyMission
import domain.entity.UserId
import domain.entity.DailyMissionId
import domain.entity.DailyMissionBuilder

trait DailyMissionRepository {

  // DailyMissionデータを保存する
  // @param
  // - mission: DailyMissionBuilder
  //
  // @return 
  // - Future.successful
  //   Left: RepositoryError
  //         致命的ではないエラー時に返される
  //   Right: DailyMissionId
  //          ミッションId(Long)を返す
  // - Future.failed
  //   致命的エラーのときはExceptionがthrowされる
  def create(mission: DailyMissionBuilder): Future[Either[RepositoryError, DailyMissionId]]

  // DailyMissionIdを使用して一つのDailyMissionデータを取得する
  // @param
  // - missionId: DailyMissionId
  //
  // @return 
  // - Future.successful
  //   Left: RepositoryError
  //         致命的ではないエラー時に返される
  //   Right: DailyMission
  //          ミッションを返す
  // - Future.failed
  //   致命的エラーのときはExceptionがthrowされる
  def findById(missionId: DailyMissionId): Future[Either[RepositoryError, DailyMission]]

  // ユーザーのDailyMissionデータ**すべて**を取得する
  // @param
  // - userId: UserId
  //
  // @return 
  // - Future.successful
  //   Left: RepositoryError
  //         致命的ではないエラー時に返される
  //   Right: Vector[DailyMission]
  //          ミッションのVectorを返す
  // - Future.failed
  //   致命的エラーのときはExceptionがthrowされる
  def findByUserId(userId: UserId): Future[Either[RepositoryError, Vector[DailyMission]]]

  // DailyMissionデータを変更する
  // @param
  // - mission: DailyMission
  //
  // @return 
  // - Future.successful
  //   Left: RepositoryError
  //         致命的ではないエラー時に返される
  //   Right: Unit
  // - Future.failed
  //   致命的エラーのときはExceptionがthrowされる
  def update(missionId: DailyMissionId, mission: DailyMissionBuilder): Future[Either[RepositoryError, Unit]]

  // DailyMissionのisCompleteフィールドをfalseからtrueにセットする
  // @param
  // - missionId: DailyMissionId
  //
  // @return 
  // - Future.successful
  //   Left: RepositoryError
  //         致命的ではないエラー時に返される
  //   Right: Unit
  // - Future.failed
  //   致命的エラーのときはExceptionがthrowされる
  def setCompleteTrue(missionId: DailyMissionId): Future[Either[RepositoryError, Unit]]

  // 指定されたDailyMissionデータ一つを削除する
  // @param
  // - missionId: DailyMissionId
  //
  // @return 
  // - Future.successful
  //   Left: RepositoryError
  //         致命的ではないエラー時に返される
  //   Right: Unit
  // - Future.failed
  //   致命的エラーのときはExceptionがthrowされる
  def deleteById(missionId: DailyMissionId): Future[Either[RepositoryError, Unit]]

  // ユーザーが持つ**すべて**のDailyMissionデータを削除する
  // @param
  // - UserId: UserId
  //
  // @return 
  // - Future.successful
  //   Left: RepositoryError
  //         致命的ではないエラー時に返される
  //   Right: Unit
  // - Future.failed
  //   致命的エラーのときはExceptionがthrowされる
  def deleteByUserId(userId: UserId): Future[Either[RepositoryError, Unit]]
}
