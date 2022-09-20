package by.hayel.computer.science.search.dna;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.hayel.computer.science.search.dna.Gene.Codon;
import org.junit.jupiter.api.Test;

class GeneTest {
  private static final Gene TEST_GENE =
      new Gene("ACGTGGCTCTCTAACGTACGTACGGGGTTTATATATACCCTAGGACTCCCTTT");
  private static final Codon CODON_ACG = new Codon("ACG");
  private static final Codon CODON_GAT = new Codon("GAT");
  private static final Codon CODON_TTT = new Codon("TTT");

  /**
   * Method under test: {@link Gene#linearContains(Gene.Codon)}
   */
  @Test
  void testLinearContains() {
    assertTrue(TEST_GENE.linearContains(CODON_ACG));
    assertFalse(TEST_GENE.linearContains(CODON_GAT));
    assertTrue(TEST_GENE.linearContains(CODON_TTT));
  }

  /**
   * Method under test: {@link Gene#binaryContains(Gene.Codon)}
   */
  @Test
  void testBinaryContains() {
    assertTrue(TEST_GENE.binaryContains(CODON_ACG));
    assertFalse(TEST_GENE.binaryContains(CODON_GAT));
    assertTrue(TEST_GENE.binaryContains(CODON_TTT));
  }
}

