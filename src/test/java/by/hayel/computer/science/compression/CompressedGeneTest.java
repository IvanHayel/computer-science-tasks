package by.hayel.computer.science.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class CompressedGeneTest {
  /**
   * Method under test: {@link CompressedGene#CompressedGene(String)}
   */
  @Test
  void testConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new CompressedGene("Gene"));
    assertEquals("", (new CompressedGene("")).decompress());
  }

  /**
   * Method under test: {@link CompressedGene#decompress()}
   */
  @Test
  void testDecompress() {
    assertEquals("", (new CompressedGene("")).decompress());
    var gene = CompressedGene.generateRandomGene(16);
    log.info("Random gene: {}", gene);
    var compressedGene = new CompressedGene(gene);
    log.info("Bit set of gene: {}", compressedGene.getBitSet());
    var decompressedGene = compressedGene.decompress();
    log.info("Decompressed gene: {}", decompressedGene);
    assertEquals(gene, decompressedGene);
  }
}
