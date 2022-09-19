package by.hayel.computer.science.encryption;

import java.util.Random;

public class UnbreakableEncryption {
  private static final Random GENERATOR = new Random();

  private static byte[] randomKey(int length) {
    var dummy = new byte[length];
    GENERATOR.nextBytes(dummy);
    return dummy;
  }

  public static KeyPair encrypt(String original) {
    var originalBytes = original.getBytes();
    var dummyKey = randomKey(originalBytes.length);
    var encryptedKey = new byte[originalBytes.length];
    for(int i = 0; i < originalBytes.length; i++) {
      encryptedKey[i] = (byte) (originalBytes[i] ^ dummyKey[i]);
    }
    return new KeyPair(dummyKey, encryptedKey);
  }

  public static String decrypt(KeyPair keyPair) {
    var firstKey = keyPair.getFirstKey();
    var secondKey = keyPair.getSecondKey();
    var decrypted = new byte[firstKey.length];
    for(int i = 0; i < decrypted.length; i++) {
      decrypted[i] = (byte) (firstKey[i] ^ secondKey[i]);
    }
    return new String(decrypted);
  }
}
