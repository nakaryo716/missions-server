package infrastructure.service

import java.security.SecureRandom

// SaltStringを生成するオブジェクト
object SaltGenerator {
  def generateSalt(length: Int): Array[Byte] = {
    val salt = new Array[Byte](length)
    val random = new SecureRandom()
    random.nextBytes(salt)
    salt
  }
}
