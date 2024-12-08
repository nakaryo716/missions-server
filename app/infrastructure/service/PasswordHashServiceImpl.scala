package infrastructure.service

import domain.service.PasswordHashService
import domain.service.error.HashError
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.Base64
import java.util.Arrays

class PasswordHashServiceImpl extends PasswordHashService {
  // ダイジェストアルゴリズムを指定するパラメータ
  private val algorithmType = "SHA3-512"
  // ランダムに生成するSalt Stringの長さ
  private val saltLength = 16
  // 最終的にハッシュ化されたパスワードとsaltを結合するための結合子
  private val connector = ":"

  // passwordをハッシュ化するメソッド
  // @param
  //  - password: String
  //    ユーザーが渡した生データ
  // @return
  //  - Left: HashError
  //          ハッシュ化時に発生したエラー
  //          例外をthrowしない
  // 
  // - Right: String
  //          ハッシュ値とSalt Stringを':'で結合して返す
  //          HashPassword:SaltString
  def hashPassword(password: String): Either[HashError,String] = {
    try {
      // ランダムなSalt Stringを生成する
      val salt = SaltGenerator.generateSalt(saltLength)

      // ユーザーが渡したパスワードとSalt Stringを組み合わせる
      val inputBytes = password.getBytes(StandardCharsets.UTF_8)
      val combined = inputBytes ++ salt
  
      // ダイジェストアルゴリズムを指定してハッシュ化
      val digest = MessageDigest.getInstance(algorithmType)
      val hash = digest.digest(combined)
  
      // 生成されたハッシュ化とsaltをbase64エンコードでDBに保存できる形にする
      val b64Hash = b64Encode(hash)
      val b64Salt = b64Encode(salt)

      // ':'を使って HashedPassword:saltString の形にフォーマットして返す
      Right(b64Hash + connector + b64Salt)
    } catch {
      case _: Any => Left(HashError.HashingFailed)
    }
  }

  // Base64エンコードするprivateメソッド
  private def b64Encode(value: Array[Byte]): String = {
    Base64.getEncoder().encodeToString(value)
  }

  // ハッシュ化すれたパスワードが正しいかを確認するメソッド
  // @param
  //  - password: String
  //    ユーザーが渡した生データ
  //  - hashPassword: String
  //    DBなどに保存されたハッシュ化されたパスワードで
  //    'HashedPassword:SaltString' 形式になっている
  // @return
  //  - Left: HashError
  //          ハッシュ化時に発生したエラー
  //          例外をthrowしない
  // 
  // - Right: Boolean
  //          passwordとhashPasswordが一致するか
  def verifyPassword(password: String, hashPassword: String): Either[HashError,Boolean] = {
    // ':'で結合されたhashPasswordをハッシュ値とSalt Stringに分割する
    hashSplit(hashPassword).flatMap{ v => 
      try {
        val (encodedStoredHash, encodedSalt) = v

        // Base64にエンコードされた値をデコード
        val storedHash = b64Decode(encodedStoredHash)
        val salt = b64Decode(encodedSalt)
      
        // ユーザーが渡したパスワード(passWord)をSaltStringに組み合わせてハッシュ化
        val inputByte = password.getBytes()
        val combined = inputByte ++ salt

        val digest =  MessageDigest.getInstance(algorithmType)
        val inputHash = digest.digest(combined)
      
        // ユーザーが渡したパスワードと保存されたパスワードを比較する
        Right(Arrays.equals(inputHash, storedHash))
      } catch {
        case _: Any => Left(HashError.VerificationFailed)
      }
    }
}

  // Base64デコードするメソッド
  private def b64Decode(value: String): Array[Byte] = {
    Base64.getDecoder().decode(value)
  }

  // ':'で結合されたhashPasswordをハッシュ値とSalt Stringに分割するメソッド
  // @param
  //  - value: String
  //           hashPassword:saltString形式の文字列
  // @return
  //  - Left: HashError
  //          Splitした際にhashPasswordとsaltStringに分けられなかった際に発生する
  //          例外をthrowしない
  // 
  // - Right: (String, String)
  //          (hashPassword, SaltString)の形式で渡される
  private def hashSplit(value: String): Either[HashError, (String, String)] = {
    // :　で分割
    val parts = value.split(connector)
    if (parts.length == 2) {
      Right(parts(0), parts(1))
    } else {
      Left(HashError.VerificationFailed)
    }
  }
}
