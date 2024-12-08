package infrastructure.service

import org.scalatestplus.play.PlaySpec
import domain.service.error.HashError
import scala.util.Try

class PasswordHashServiceImplSpec extends PlaySpec {
  "PasswordHashServiceImpl" should {
    val service = new PasswordHashServiceImpl

    "hashPassword should return a valid hash and salt" in {
      val password = "securePassword123"
      val hashResult = service.hashPassword(password)

      hashResult.isRight mustBe true

      val hashValue = hashResult.getOrElse("")
      hashValue.split(":").length mustBe 2
    }

    "verifyPassword should return true for correct password" in {
      val password = "securePassword123"
      val hashResult = service.hashPassword(password).getOrElse("")
      
      val verifyResult = service.verifyPassword(password, hashResult)
      verifyResult mustBe Right(true)
    }

    "verifyPassword should return false for incorrect password" in {
      val password = "securePassword123"
      val wrongPassword = "wrongPassword123"
      val hashResult = service.hashPassword(password).getOrElse("")

      val verifyResult = service.verifyPassword(wrongPassword, hashResult)
      verifyResult mustBe Right(false)
    }

    "verifyPassword should return VerificationFailed for malformed hash" in {
      val password = "securePassword123"
      val malformedHash = "invalid:hash:format"

      val verifyResult = service.verifyPassword(password, malformedHash)

      verifyResult mustBe Left(HashError.VerificationFailed)
    }
  }
}
