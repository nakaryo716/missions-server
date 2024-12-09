package domain.service

trait UUIDService {
  // UUIDを生成するメソッド
  // @param: Unit

  // @return
  // - String
  //   Stringに型変換されたUUID
  def generate(): String
}
