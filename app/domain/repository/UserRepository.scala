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
    // - Future.successful(())
    // - Future.failed(DatabaseException)
    def createUser(user: User): Future[Unit]
    // Userデータを取得する
    // @param
    // - id: UserId
    //
    // @return 
    // - Future.successful(())
    // - Future.failed(DatabaseException)
    def reade(id: UserId): Future[User]
    // Userデータを変更する
    // @param
    // - id: UserId
    // - user: User
    //   あらたに変更されたUserのフルデータ(userIdは変更してはならない)
    //
    // @return 
    // - Future.successful(())
    // - Future.failed(DatabaseException)
    def update(id: UserId, user: User): Future[Unit]
    // Userデータを削除する
    // 他のリポジトリも削除される
    // @param  
    // - id: UserId
    //      
    // @return 
    // - Future.successful(())
    // - Future.failed(DatabaseException)
    def delete(id: UserId): Future[Unit]
}
