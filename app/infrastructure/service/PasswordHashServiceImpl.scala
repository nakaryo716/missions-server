package infrastructure.service

import domain.service.PasswordHashService
import domain.service.error.HashError
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.Base64
import java.util.Arrays

class PasswordHashServiceImpl extends PasswordHashService {
  private val algorithmType = "SHA3-512"
  private val saltLength = 16
  private val connector = ":"

  def hashPassword(password: String): Either[HashError,String] = {
    try {
      val salt = SaltGenerator.generateSalt(saltLength)
      val inputBytes = password.getBytes(StandardCharsets.UTF_8)
      val combined = inputBytes ++ salt
  
      val digest = MessageDigest.getInstance(algorithmType)
      val hash = digest.digest(combined)
  
      val b64Hash = b64Encode(hash)
      val b64Salt = b64Encode(salt)

      println(b64Hash)
      println(b64Salt)
      val a = b64Hash + connector + b64Salt
      Right(a)
    } catch {
      case _: Any => Left(HashError.HashingFailed)
    }
  }

  private def b64Encode(value: Array[Byte]): String = {
    Base64.getEncoder().encodeToString(value)
  }

  def verifyPassword(password: String, hashPassword: String): Either[HashError,Boolean] = {
    hashSplit(hashPassword).flatMap{ v => 
      val (encodedStoredHash, encodedSalt) = v
      try {
        val storedHash = b64Decode(encodedStoredHash)
        val salt = b64Decode(encodedSalt)
      
        val inputByte = password.getBytes()
        val combined = inputByte ++ salt
          
        val digest =  MessageDigest.getInstance(algorithmType)
        val inputHash = digest.digest(combined)
      
        Right(Arrays.equals(inputHash, storedHash))
      } catch {
        case _: Any => Left(HashError.VerificationFailed)
      }
    }
}

  private def b64Decode(value: String): Array[Byte] = {
    Base64.getDecoder().decode(value)
  }

  private def hashSplit(value: String): Either[HashError, (String, String)] = {
    val parts = value.split(connector)
    if (parts.length == 2) {
      Right(parts(0), parts(1))
    } else {
      Left(HashError.VerificationFailed)
    }
  }
}
