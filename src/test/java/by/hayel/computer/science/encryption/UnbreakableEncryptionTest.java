package by.hayel.computer.science.encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UnbreakableEncryptionTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UnbreakableEncryption#encrypt(String)}
   *   <li>{@link UnbreakableEncryption#decrypt(KeyPair)}
   * </ul>
   */
  @Test
  void testEncrypt() {
    assertEquals("Original",
        UnbreakableEncryption.decrypt(UnbreakableEncryption.encrypt("Original")));
  }
}

