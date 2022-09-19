package by.hayel.computer.science.compression;

import java.util.BitSet;
import java.util.Random;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompressedGene {
  private static final char ADENINE = 'A';
  private static final char CYTOSINE = 'C';
  private static final char GUANINE = 'G';
  private static final char THYMINE = 'T';

  BitSet bitSet;
  int length;

  public static String generateRandomGene(int length) {
    var generator = new Random();
    var builder = new StringBuilder();
    for(int i = 0; i < length; i++) {
      switch (generator.nextInt(4)) {
        case 0 -> builder.append(ADENINE);
        case 1 -> builder.append(CYTOSINE);
        case 2 -> builder.append(GUANINE);
        case 3 -> builder.append(THYMINE);
        default -> throw new IllegalStateException();
      }
    }
    return builder.toString();
  }

  public CompressedGene(String gene) {
    compress(gene);
  }

  private void compress(String gene) {
    length = gene.length();
    bitSet = new BitSet(length * 2);
    final String upperGene = gene.toUpperCase();
    for (int i = 0; i < length; i++) {
      final int firstLocation = 2 * i;
      final int lastLocation = 2 * i + 1;
      switch (upperGene.charAt(i)) {
        case ADENINE -> {
          bitSet.set(firstLocation, false);
          bitSet.set(lastLocation, false);
        }
        case CYTOSINE -> {
          bitSet.set(firstLocation, false);
          bitSet.set(lastLocation, true);
        }
        case GUANINE -> {
          bitSet.set(firstLocation, true);
          bitSet.set(lastLocation, false);
        }
        case THYMINE -> {
          bitSet.set(firstLocation, true);
          bitSet.set(lastLocation, true);
        }
        default -> throw new IllegalArgumentException();
      }
    }
  }

  public String decompress() {
    if(bitSet == null) return "";
    var builder = new StringBuilder();
    for(int i = 0; i < length * 2; i += 2) {
      final int firstBit = bitSet.get(i) ? 1 : 0;
      final int secondBit = bitSet.get(i+1) ? 1 : 0;
      final int lastBits = firstBit << 1 | secondBit;
      switch (lastBits) {
        case 0b00 -> builder.append(ADENINE);
        case 0b01 -> builder.append(CYTOSINE);
        case 0b10 -> builder.append(GUANINE);
        case 0b11 -> builder.append(THYMINE);
        default -> throw new IllegalStateException();
      }
    }
    return builder.toString();
  }
}
