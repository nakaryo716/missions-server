package domain.repository

import scala.concurrent.Future
import domain.entity.DailiyMission
import domain.entity.UserId
import domain.entity.DailiyMissionId
import domain.entity.DailiyMissionBuilder

trait DailiyMissionRepository {

    // DailiyMissionデータを保存する
    // @param
    // - mission: DailiyMissionBuilder
    //
    // @return 
    // - Future.successful
    //   Left: RepositoryError
    //         致命的ではないエラー時に返される
    //   Right: DailiyMissionId
    //          ミッションId(Long)を返す
    // - Future.failed
    //   致命的エラーのときはExceptionがthrowされる
    def create(mission: DailiyMissionBuilder): Future[Either[RepositoryError, DailiyMissionId]]

    // DailiyMissionIdを使用して一つのDailiyMissionデータを取得する
    // @param
    // - missionId: DailiyMissionId
    //
    // @return 
    // - Future.successful
    //   Left: RepositoryError
    //         致命的ではないエラー時に返される
    //   Right: DailiyMission
    //          ミッションを返す
    // - Future.failed
    //   致命的エラーのときはExceptionがthrowされる
    def findById(missionId: DailiyMissionId): Future[Either[RepositoryError, DailiyMission]]

    // ユーザーのDailiyMissionデータ**すべて**を取得する
    // @param
    // - userId: UserId
    //
    // @return 
    // - Future.successful
    //   Left: RepositoryError
    //         致命的ではないエラー時に返される
    //   Right: Vector[DailiyMission]
    //          ミッションのVectorを返す
    // - Future.failed
    //   致命的エラーのときはExceptionがthrowされる
    def findByUserId(userId: UserId): Future[Either[RepositoryError, Vector[DailiyMission]]]

    // DailiyMissionデータを変更する
    // @param
    // - mission: DailiyMission
    //
    // @return 
    // - Future.successful
    //   Left: RepositoryError
    //         致命的ではないエラー時に返される
    //   Right: Unit
    // - Future.failed
    //   致命的エラーのときはExceptionがthrowされる
    def update(missionId: DailiyMissionId, mission: DailiyMissionBuilder): Future[Either[RepositoryError, Unit]]

    // DailiyMissionのisCompleteフィールドをfalseからtrueにセットする
    // @param
    // - missionId: DailiyMissionId
    //
    // @return 
    // - Future.successful
    //   Left: RepositoryError
    //         致命的ではないエラー時に返される
    //   Right: Unit
    // - Future.failed
    //   致命的エラーのときはExceptionがthrowされる
    def setCompleteTrue(missionId: DailiyMissionId): Future[Either[RepositoryError, Unit]]

    // 指定されたDailiyMissionデータ一つを削除する
    // @param
    // - missionId: DailiyMissionId
    //
    // @return 
    // - Future.successful
    //   Left: RepositoryError
    //         致命的ではないエラー時に返される
    //   Right: Unit
    // - Future.failed
    //   致命的エラーのときはExceptionがthrowされる
    def deleteById(missionId: DailiyMissionId): Future[Either[RepositoryError, Unit]]

    // ユーザーが持つ**すべて**のDailiyMissionデータを削除する
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
