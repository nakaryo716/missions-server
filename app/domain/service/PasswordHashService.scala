package domain.service

import domain.service.error.HashError

trait PasswordHashService {
    // パスワードをHash化するメソッド
    // @param
    // - password: String
    //   ユーザーが送ったHash化されていないパスワード
    //
    // @return 
    //   Left: HashError
    //         ハッシュ化に失敗した際のエラー
    //   Right: String
    //          ハッシュ化されたパスワード
    def hashPassword(password: String): Either[HashError, String]

    // ユーザーが送った平文のパスワードとHash化されたパスワードで一致するか検証するメソッド
    // @param
    // - password: String
    //   ユーザーが送ったHash化されていないパスワード
    // - hashPassword: String
    //   Hash化されたパスワード(DBから取得したパスワード)
    //
    // @return 
    //   Left: HashError
    //         検証に失敗した際のエラー
    //   Right: Boolean
    //          パスワードが正しいか識別する
    def verifyPassword(password: String, hashPassword: String): Either[HashError, Boolean]
}
