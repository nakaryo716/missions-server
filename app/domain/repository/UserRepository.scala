package domain.repository

import domain.entity.User
import scala.concurrent.Future
import domain.entity.UserId

trait UserRepository {

    // Userデータを保存する
    // @param
    // - user: User
    //
    // @return 
    // - Future.successful
    //   Left: RepositoryError
    //         致命的ではないエラー時に返される
    //   Right: UserId
    //          ユーザーId(UUID)を返す
    // - Future.failed
    //   致命的エラーのときはExceptionがthrowされる
    def create(user: User): Future[Either[RepositoryError, UserId]]

    // UserIdによってUserデータを取得する
    // @param
    // - id: UserId
    //
    // @return 
    // - Future.successful
    //   Left: RepositoryError
    //         致命的ではないエラー時に返される(ユーザーが存在しないなど)
    //   Right: User
    //          ユーザーId(UUID)を返す
    // - Future.failed
    //   致命的エラーのときはExceptionがthrowされる
    def findById(id: UserId): Future[Either[RepositoryError, User]]

    // emailによってUserデータを取得する
    // @param
    // - id: UserId
    //
    // @return 
    // - Future.successful
    //   Left: RepositoryError
    //         致命的ではないエラー時に返される(ユーザーが存在しないなど)
    //   Right: User
    // - Future.failed
    //   致命的エラーのときはExceptionがthrowされる
    def findByEmail(email: String): Future[Either[RepositoryError, User]]

    // Userデータを変更する
    // @param
    // - id: UserId
    // - user: User
    //   あらたに変更されたUserのフルデータ(userIdは変更してはならない)
    //
    // @return 
    // - Future.successful
    //   Left: RepositoryError
    //         致命的ではないエラー時に返される(ユーザーが存在しないなど)
    //   Right: Unit
    // - Future.failed
    //   致命的エラーのときはExceptionがthrowされる
    def update(id: UserId, user: User): Future[Either[RepositoryError, Unit]]

    // Userデータを削除する
    // @param  
    // - id: UserId
    //      
    // @return 
    // - Future.successful
    //   Left: RepositoryError
    //         致命的ではないエラー時に返される(ユーザーが存在しないなど)
    //   Right: Unit
    // - Future.failed
    //   致命的エラーのときはExceptionがthrowされる
    def delete(id: UserId): Future[Either[RepositoryError, Unit]]

    // Userが存在するか確認する
    // @param  
    // - id: UserId
    //      
    // @return 
    // - Future.successful
    //   Left: RepositoryError
    //         致命的ではないエラー時に返される
    //         ユーザーが存在しない場合はここではエラーを返さず、Rightでfalseにする
    //   Right: Boolean
    // - Future.failed
    //   致命的エラーのときはExceptionがthrowされる
    def isExist(id: UserId): Future[Either[RepositoryError, Boolean]]
}
