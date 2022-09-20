package by.hayel.computer.science.search.dna;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Gene {
  @Getter
  @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
  public static class Codon implements Comparable<Codon> {
    Nucleotide first;
    Nucleotide second;
    Nucleotide third;
    Comparator<Codon> comparator =
        Comparator.comparing(Codon::getFirst)
            .thenComparing(Codon::getSecond)
            .thenComparing(Codon::getThird);

    public Codon(String codon) {
      first = Nucleotide.valueOf(codon.substring(0, 1));
      second = Nucleotide.valueOf(codon.substring(1, 2));
      third = Nucleotide.valueOf(codon.substring(2, 3));
    }

    @Override
    public int compareTo(Codon other) {
      return comparator.compare(this, other);
    }
  }

  List<Codon> codons = new ArrayList<>();

  public Gene(String gene) {
    for (int i = 0; i < gene.length() - 3; i += 3) {
      codons.add(new Codon(gene.substring(i, i + 3)));
    }
  }

  public boolean linearContains(Codon key) {
    for(Codon codon : codons) {
      if(codon.compareTo(key) == 0) {
        return true;
      }
    }
    return false;
  }

  public boolean binaryContains(Codon key) {
    var sortedCodons = new ArrayList<>(codons);
    sortedCodons.sort(Codon::compareTo);
    int low = 0;
    int high = sortedCodons.size() - 1;
    while(low <= high) {
      int middle = (low + high) >> 1;
      int comparison = sortedCodons.get(middle).compareTo(key);
      if(comparison < 0) {
        low = middle + 1;
      } else if (comparison > 0) {
        high = middle - 1;
      } else {
        return true;
      }
    }
    return false;
  }
}
