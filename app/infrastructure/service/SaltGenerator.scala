package infrastructure.service

import java.security.SecureRandom

object SaltGenerator {
  def generateSalt(length: Int): Array[Byte] = {
    val salt = new Array[Byte](length)
    val random = new SecureRandom()
    random.nextBytes(salt)
    salt
  }
}
