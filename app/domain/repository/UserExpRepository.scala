package domain.repository

import scala.concurrent.Future
import domain.entity.UserExp
import domain.entity.UserId

trait UserExpRepository {
  // UserExpを初期化(データベースに登録する)
  // そのため各ユーザー
  // @param
  // - userId: UserId
  //
  // @return 
  // - Future.successful
  //   Left: RepositoryError
  //         致命的ではないエラー時に返される
  //   Right: Unit
  // - Future.failed
  //   致命的エラーのときはExceptionがthrowされる
  def initExp(userId: UserId): Future[Either[RepositoryError, Unit]]

  // UserExpを取得する
  // @param
  // - userId: UserId
  //
  // @return 
  // - Future.successful
  //   Left: RepositoryError
  //         致命的ではないエラー時に返される
  //   Right: UserExp
  //          ユーザーが所持する経験値を返す
  // - Future.failed
  //   致命的エラーのときはExceptionがthrowされる
  def findByUserId(userId: UserId): Future[Either[RepositoryError, UserExp]]

  // UserExpの経験値を増加(変更)させる
  // @param
  // - user: User
  // - additionalPoints: Long
  //   現在の経験値に対して**追加する**経験値量(Delta)
  //
  // @return 
  // - Future.successful
  //   Left: RepositoryError
  //         致命的ではないエラー時に返される
  //   Right: Unit
  // - Future.failed
  //   致命的エラーのときはExceptionがthrowされる
  def addExp(userId: UserId, additionalPoints: Long): Future[Either[RepositoryError, Unit]]
}
